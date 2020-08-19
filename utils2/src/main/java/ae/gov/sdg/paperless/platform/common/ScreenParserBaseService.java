package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.LABELS_KEY;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PARAM_PROCESS_ID;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PROCESS_VAR_JSON;
import static ae.gov.sdg.paperless.platform.util.CommonUtil.sneakyThrow;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;
import ae.gov.sdg.paperless.platform.exceptions.ScreenException;
import ae.gov.sdg.paperless.platform.util.JsonUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ScreenParserBaseService {
	
	private static final Logger log = LoggerFactory.getLogger(ScreenParserBaseService.class);
	
	private PaperlessResourceLoader resourceLoader;
	
	public ScreenParserBaseService(PaperlessResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	

	protected String processTemplate(final String filename, Map<String, Object> dictionary, LangType lang,
			final String processId, final String journeyName, final StringWriter writer, final Template template)
			throws IOException {
		String json;
		try {
			if (dictionary == null) {
				dictionary = new HashMap<>();
			}
			// add any json fields converted to objects
			dictionary.putAll(this.unravelJsonElements(dictionary));
			if (lang == null) {
				lang = LangType.en;
			}
			// i18n text
			dictionary.put(LABELS_KEY, resourceLoader.getScreenLabels(filename, lang.toString(), journeyName));

			// processId
			if (StringUtils.isNotBlank(processId)) {
				dictionary.put(PARAM_PROCESS_ID, processId);
			}
			template.process(dictionary, writer);
		} catch (final TemplateException e) {
			log.error("Failed to process template for screen: {}", filename);
			throw new ScreenException(e);
		}
		json = writer.toString();
		if (log.isDebugEnabled()) {
			log.debug("Parsed json: {}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * Extract dictionary elements
	 *
	 * @param dictionary
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> unravelJsonElements(final Map<String, Object> dictionary) {

		return dictionary.entrySet().stream()
				.collect(Collector.of(() -> new HashMap<String, Object>(), (mutableMap, entry) -> {
					final String key = entry.getKey();
					if (StringUtils.startsWith(key, PROCESS_VAR_JSON)) {
						final String value = (String) entry.getValue();
						convertJsonStringToDictionary(mutableMap, key, value);
					}
				}, (map1, map2) -> {
					map1.putAll(map2);
					return map1;
				}));
	}

	/**
	 * Convert json string to dictionary and populate in map
	 * 
	 * @param mutableMap
	 * @param key
	 * @param value
	 */
	private void convertJsonStringToDictionary(HashMap<String, Object> mutableMap, final String key,
			final String value) {
		if (StringUtils.isNotBlank(value)) {
			log.debug("Unravelling json for: {}", key);
			try {
				mutableMap.put(
						// remove the json_ from the value e.g. json_carList --> carList
						StringUtils.removeStart(key, PROCESS_VAR_JSON),
						// convert the json string value to dictionary
						JsonUtil.fromJson(value));
			} catch (final IOException e) {
				sneakyThrow(e);
			}
		} else {
			log.debug("Null value found for key: {}", key);
		}
	}

}
