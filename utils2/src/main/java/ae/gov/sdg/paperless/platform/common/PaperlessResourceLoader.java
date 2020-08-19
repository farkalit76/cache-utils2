package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.COMMON;
import static ae.gov.sdg.paperless.platform.util.FileUtil.fileList;
import static ae.gov.sdg.paperless.platform.util.JsonUtil.fromJson;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author c_chandra.bommise
 * <p>
 * Load i18 files.
 */
@Service
public class PaperlessResourceLoader {
    private static Logger log = LoggerFactory.getLogger(PaperlessResourceLoader.class);
    private static Map<String, Map<String, Object>> journeyScreenResources;
    private static Map<String, Map<String, String>> screenPath;

    private static final String SCREEN_PATH_PRFIX = "classpath:screens/screenspath/*";
    private static final String I18N_FILE_PREFIX = "classpath:i18n/*";

    
    /**
	 * Get the path for the screen.
	 *
	 * @param <T> the generic type
	 * @param screenName  the screen name
	 * @param journeyName the journey name
	 * @return the screen path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String getScreenPath(final String screenName, final String journeyName) throws IOException {
		if (screenPath == null) {
			loadScreenPath();
		}
		String path = null;
    	if(screenPath!=null) {
    		if (journeyName!=null && isScreenPathJourneyExists(journeyName.toLowerCase())) {
    			path = screenPath.get(journeyName.toLowerCase()).get(screenName);
    		}
	
	        if (path == null && isScreenPathJourneyExists(COMMON)) {
	            path = screenPath.get(COMMON).get(screenName);
	        }
    	}
		return path != null ? path : "";
	}
	
	/**
	 * Gets the screen labels.
	 *
	 * @param <T> the generic type
	 * @param screenName  as String
	 * @param lang        as String
	 * @param journeyname as String
	 * @return a map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Map<String, String> getScreenLabels(final String screenName, final String lang, final String journeyname)
			throws IOException {
		if (journeyScreenResources == null) {
			loadScreenLabels();
		}
		final Map<String, String> labels = null;
		return processScreenLabels(screenName, lang, labels, journeyname);
	}
	
	/**
	 * Load screen path.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void loadScreenPath() throws IOException {
		synchronized (PaperlessResourceLoader.class) {
			screenPath = new HashMap<>();
			fileList(SCREEN_PATH_PRFIX, PaperlessResourceLoader.class).parallel().forEach(file -> {
				try {
					log.info("Loading resource for file: {}", file);
					final InputStream json = file.getInputStream();
					final Map<String, String> resource = fromJson(json, new TypeReference<Map<String, String>>() {
					});
					screenPath.put(requireNonNull(file.getFilename()).split("-")[0], resource);
				} catch (IOException | NullPointerException e) {
					log.info("Exception occured while fetching screen path for file: {}", file);
				}
			});
		}
	}
	
	/**
	 * Load screen labels.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void loadScreenLabels() throws IOException {
        synchronized (PaperlessResourceLoader.class) {
            journeyScreenResources = new HashMap<>();
            fileList(I18N_FILE_PREFIX, PaperlessResourceLoader.class).parallel().forEach(file -> {
                try {
                	log.info("Loading resource for file: {}",file);
                    final InputStream json = file.getInputStream();
                    final Map<String, Object> resource = fromJson(json);
                    journeyScreenResources.put(requireNonNull(file.getFilename()).split("-")[1].split("\\.")[0], resource);
                } catch (IOException | NullPointerException e) {
                	log.info("Exception occured while loading i18 resource for file: {}",file);
                }
            });
        }
    }

	/**
	 * Process screen labels.
	 *
	 * @param screenName the screen name
	 * @param lang       the lang
	 * @param labels     the labels
	 * @param journeyName 
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> processScreenLabels(final String screenName, final String lang, Map<String, String> labels, String journeyName) {
		Object object = null;
        if (journeyName != null && isJourneyNonNull(journeyName)) {
        	object = journeyScreenResources.get(journeyName.toLowerCase()).get(screenName);
        }
        if (object == null) {
            object = journeyScreenResources.get(COMMON).get(screenName);
        }

		if (object != null) {
			final Object langs = ((Map<String, Object>) object).get(lang);

			if (langs != null) {
				labels = (Map<String, String>) langs;
			}
		}

		return defaultIfNull(labels, Collections.emptyMap());
	}
	
	/**
	 * @param journeyName
	 * @return
	 */
	private boolean isJourneyNonNull(final String journeyName) {
		return journeyScreenResources.get(journeyName.toLowerCase())!=null;
	}

	/**
	 * Check if journey is configured in screen paths
	 * 
	 * @param journeyName
	 * @return
	 */
	private boolean isScreenPathJourneyExists(final String journeyName) {
		return screenPath.containsKey(journeyName);
	}

}
