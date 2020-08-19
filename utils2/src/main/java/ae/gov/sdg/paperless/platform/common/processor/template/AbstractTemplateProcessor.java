package ae.gov.sdg.paperless.platform.common.processor.template;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.COMMON;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JSON_PREFIX;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.TEMPLATE_PREFIX;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ae.gov.sdg.paperless.platform.common.PaperlessResourceLoader;
import ae.gov.sdg.paperless.platform.common.processor.bean.TemplateBean;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Holds the common logic for template processor
 * 
 * @author c_chandra.bommise
 *
 */
public abstract class AbstractTemplateProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTemplateProcessor.class);

	private final Map<TemplateBean, Template> templatesCache = new ConcurrentHashMap<>();

	private Configuration freeMarkerConfig;

	private AbstractTemplateProcessor processor;

	private PaperlessResourceLoader resourceLoader;

	public void setNextProcessor(AbstractTemplateProcessor processor) {
		this.processor = processor;
	}

	public AbstractTemplateProcessor getNextProcessor() {
		return this.processor;
	}

	public Configuration getFreeMarkerConfig() {
		return freeMarkerConfig;
	}

	@Autowired
	public void setResourceLoader(PaperlessResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public PaperlessResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * Get freemarker templates for the screen based on version and platform
	 * provided by implementation class
	 * 
	 * @param filename
	 * @param journeyName
	 * @param templateResponse
	 * @param appVersion
	 * @param platform
	 * @return
	 * @return
	 */
	public Template getTemplate(TemplateBean actualBean, String journeyName) {
		Template template = null;
		TemplateBean beanByClass = getTemplateBean(actualBean);
		Boolean templateKeyExists = isTemplateExistsCache(beanByClass);
		LOG.info("Start of get template by version:{} and platform:{}", beanByClass.getVersion(),
				beanByClass.getPlatform());
		if (isNull(templateKeyExists)){
			StringBuilder tempFileName = new StringBuilder(actualBean.getFileName());
			if (StringUtils.isNotEmpty(beanByClass.getPlatform())) {
				tempFileName.append("_").append(beanByClass.getPlatform());
			}
			// create it and cache it
			try {
				Reader templateReader = new InputStreamReader(loadInputStreamFromFile(actualBean.getFileName(), true,
						journeyName, beanByClass.getVersion(), beanByClass.getPlatform()), StandardCharsets.UTF_8);
				template = new Template(tempFileName.toString(), templateReader, getFreeMarkerConfig());
			} catch (Exception e) {
				LOG.info("Could not load template file for {} {} {} {}", actualBean.getFileName(), journeyName,
						actualBean.getVersion(), actualBean.getPlatform());
			}
		} else if (Boolean.TRUE.equals(templateKeyExists)) {
			template = templatesCache.get(beanByClass);
		} 
		if (template != null && isNull(templateKeyExists)) {
			addTemplateExistsCache(beanByClass, true);
			templatesCache.put(beanByClass, template);
		} else if (template == null && getNextProcessor() != null) {
			addTemplateExistsCache(beanByClass, false);
			return getNextProcessor().getTemplate(actualBean, journeyName);
		}
		LOG.info("End of get template by version:{} and platform:{}", beanByClass.getVersion(),
				beanByClass.getPlatform());
		return template;
	}

	/**
	 * Load the screen based on version and platform.
	 * 
	 * @param filename
	 * @param isTemplate
	 * @param journeyName
	 * @param appVersion
	 * @param platform
	 * @return
	 * @throws IOException
	 */
	public InputStream loadInputStreamFromFile(final String filename, final boolean isTemplate,
			final String journeyName, String appVersion, String platform) throws IOException {
		StringBuilder tempFileName = new StringBuilder(filename);
		if (StringUtils.isNotEmpty(platform)) {
			tempFileName.append("_").append(platform);
		}
		StringBuilder path = new StringBuilder(getResourceLoader().getScreenPath(filename, journeyName));
		if (StringUtils.isNotEmpty(appVersion)) {
			path.insert(0, appVersion + "/");
		}
		InputStream is = null;
		if (journeyName != null) {
			is = this.getClass().getClassLoader().getResourceAsStream(new StringBuilder("screens/").append(path)
					.append(tempFileName).append((isTemplate ? TEMPLATE_PREFIX : JSON_PREFIX)).toString());
		}

		is = ofNullable(is).orElseGet(() -> {
			String commonPath = new StringBuilder(appVersion).append("/").append(COMMON).toString();
			return this.getClass().getClassLoader().getResourceAsStream(new StringBuilder("screens/").append(commonPath)
					.append("/").append(tempFileName).append(isTemplate ? TEMPLATE_PREFIX : JSON_PREFIX).toString());
		});

		return is;
	}

	/**
	 * Populate template exists map with template exists value
	 * 
	 * @param actualBean
	 * @param exists
	 */
	protected void addTemplateExistsCache(TemplateBean actualBean, Boolean exists) {
		getTemplateExistsCache().put(actualBean, exists);
	}

	/**
	 * Fetch template exists value
	 * 
	 * @return the boolean
	 */
	protected Boolean isTemplateExistsCache(TemplateBean actualBean) {
		return getTemplateExistsCache().get(actualBean);
	}

	protected abstract Map<TemplateBean, Boolean> getTemplateExistsCache();

	public abstract TemplateBean getTemplateBean(TemplateBean actualBean);

}
