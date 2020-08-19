package ae.gov.sdg.paperless.platform.common.service.generic;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEYTYPE_LISTING;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ae.gov.sdg.paperless.platform.common.model.IJourneyType;
import ae.gov.sdg.paperless.platform.common.model.components.Request;
import ae.gov.sdg.paperless.platform.util.JsonUtil;

/**
 * @author swetabh raj
 */
@Component
public class DubaiNowScreenUtil {

	private static Logger log = LoggerFactory.getLogger(DubaiNowScreenUtil.class);

	private Map<String, Map<String, Object>> sequences = new ConcurrentHashMap<>();

	public DubaiNowScreenUtil() {
		super();
	}

	/**
	 * Load a particular screen based on a sequence
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String getScreenFilename(final Request request) throws IOException {
		String filename = null;
		final IJourneyType journey = request.getJourney();
		Validate.notNull(journey);
		String journeyStr ="";
		if (JOURNEYTYPE_LISTING.equals(journey.name())) {
			journeyStr = (String) request.getParams().get("entity");
		}else {
        	journeyStr = journey.toString();
        }

		final Map<String, Object> params = request.getParams();
		final String screenName = request.getScreen();
		if (StringUtils.isNotBlank(screenName)) {
			if (sequences.get(journeyStr) == null) {
				loadSequence(journeyStr);
			}
			final Map<String, Object> sequence = sequences.get(journeyStr);
			final Map<String, Object> screenConfig = (Map<String, Object>) sequence.get(screenName);
			if (screenConfig != null) {
				filename = getFilename(filename, params, screenName, screenConfig);
			}
		}
		log.info("Screen name for fetching the next screen: {}", filename);
		return filename;
	}

	/**
	 * @param filename
	 * @param params
	 * @param back
	 * @param screenName
	 * @param screenConfig
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getFilename(String filename, final Map<String, Object> params, final String screenName,
			final Map<String, Object> screenConfig) {
		boolean back = false;

		if (params != null) {
			back = params.containsKey("back");
		}
		if (back) {
			log.info("Fetching the previous screen for request screen: {}", screenName);
			filename = (String) screenConfig.get("previous");
		} else if (screenConfig.get("next") != null) {
			log.info("Fetching the next screen for request screen: {}", screenName);
			filename = (String) screenConfig.get("next");
		} else if (screenConfig.get("fork") != null) {
			log.info("Fetching the fork screen for request screen: {}", screenName);
			final Map<String, Object> fork = (Map<String, Object>) screenConfig.get("fork");
			final String field = (String) fork.get("key");
			final Map<String, String> mapping = (Map<String, String>) fork.get("values");
			// get the value from params
			if (params != null) {
				final String value = (String) params.get(field);
				if (value != null) {
					filename = mapping.get(value);
				}
			}
		}
		return filename;
	}

	/**
	 * 
	 * @param journey
	 * @throws IOException
	 */
	private void loadSequence(final String journey) throws IOException {
		log.info("Loading sequence for journey: {}", journey);
		if (sequences.get(journey) == null) {
			synchronized (DubaiNowScreenUtil.class) {
				final InputStream json = DubaiNowScreenUtil.class.getClassLoader()
						.getResourceAsStream(new StringBuilder("screens/screen-sequence-")
								.append(journey.trim().toLowerCase()).append(".json").toString());
				final Map<String, Object> sequence = JsonUtil.fromJson(json);
				sequences.put(journey, sequence);
			}
		}
	}
}