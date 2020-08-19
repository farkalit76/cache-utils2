package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEY_INTRO_TEMPLATE_JSON_SUFFIX;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.LANG;
import static ae.gov.sdg.paperless.platform.util.CommonUtil.journeyOrDirectoryName;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;
import ae.gov.sdg.paperless.platform.common.processor.bean.TemplateBean;
import ae.gov.sdg.paperless.platform.common.processor.template.TemplateProvider;
import ae.gov.sdg.paperless.platform.tracing.TraceLog;
import freemarker.template.Template;

/**
 * @author c_chandra.bommise
 *
 * Fetch the screen based on journey, filename, appVersion and platform..
 *
 */
@Service
public class ScreenParserServiceV2 extends ScreenParserBaseService {

	private static final Logger log = LoggerFactory.getLogger(ScreenParserServiceV2.class);

	private TemplateProvider templateProvider;

	public ScreenParserServiceV2(PaperlessResourceLoader resourceLoader, TemplateProvider templateProvider) {
		super(resourceLoader);
		this.templateProvider = templateProvider;
	}

	public <T> String getIntroScreen(final T journeyType, final LangType lang, String appVersion,
			String platform) throws IOException {
		// return the first screen of the journey
		final String journeyName = journeyOrDirectoryName(journeyType);
        log.debug("Loading intro screen for journey: {}", journeyName);
		final Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(LANG, lang.name());
		return this.getAndPopulateScreens(journeyType.toString().toLowerCase() + JOURNEY_INTRO_TEMPLATE_JSON_SUFFIX,
				responseMap, lang, null, journeyType, appVersion, platform);
	}

	/**
	 * Populate the template screen based on journey, filename, appVersion and platform. 
	 * Template will be returned as below:
	 * 	i)   If the template exists based on version and platform it is returned else, 
	 * 	ii)  If the template exists based on version is returned else,
	 *  iii) If the template exists based on default version and platform is returned else,
	 *  iv)  If the template exists based on default platform is returned else null is returned,
	 * If matching template doesn't at all exists screen exception is thrown.
	 * 
	 * @param filename
	 * @param dictionary
	 * @param lang
	 * @param processId
	 * @param journeyType
	 * @param appVersion
	 * @param platform
	 * @return
	 * @throws IOException
	 */
	@TraceLog
	public <T> String getAndPopulateScreens(final String filename, Map<String, Object> dictionary, LangType lang,
			final String processId, final T journeyType, String appVersion, String platform)
			throws IOException {
		final StringWriter writer = new StringWriter();
		final String journeyName = journeyOrDirectoryName(journeyType);
		final Template template = this.getTemplate(filename, journeyName, appVersion, platform);
		return processTemplate(filename, dictionary, lang, processId, journeyName, writer, template);
	}

	/**
	 * Get freemarker templates for the screen either from cache or create
	 * 
	 * @param filename
	 * @param journeyType
	 * @param appVersion
	 * @param platform
	 * @return
	 */
	private Template getTemplate(final String filename, final String journeyName, String appVersion,
			String platform) {

		TemplateBean templateBean = new TemplateBean().setFileName(filename).setVersion(appVersion)
				.setPlatform(platform);
		return templateProvider.getTemplate(templateBean, journeyName);

	}
}
