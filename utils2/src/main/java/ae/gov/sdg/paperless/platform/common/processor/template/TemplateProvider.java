package ae.gov.sdg.paperless.platform.common.processor.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ae.gov.sdg.paperless.platform.common.processor.bean.TemplateBean;
import freemarker.template.Template;

@Component
public class TemplateProvider {

	private static final Logger LOG = LoggerFactory.getLogger(TemplateProvider.class);

	private AbstractTemplateProcessor processor;

	private VersionPlatformTemplateProcessor versionPlatformProcessor;

	private VersionTemplateProcessor versionProcessor;

	private DefaultPlatformTemplateProcessor defaultPlatformProcessor;

	private DefaultTemplateProcessor defaultProcessor;

	/**
	 * Inject the processors and chain them.
	 * 
	 * @param versionPlatformProcessor
	 * @param versionProcessor
	 * @param defaultPlatformProcessor
	 * @param defaultProcessor
	 */
	public TemplateProvider(VersionPlatformTemplateProcessor versionPlatformProcessor,
			VersionTemplateProcessor versionProcessor, DefaultPlatformTemplateProcessor defaultPlatformProcessor,
			DefaultTemplateProcessor defaultProcessor) {
		// initialize the chain
		this.versionPlatformProcessor = versionPlatformProcessor;
		this.versionProcessor = versionProcessor;
		this.defaultPlatformProcessor = defaultPlatformProcessor;
		this.defaultProcessor = defaultProcessor;

		this.processor = this.versionPlatformProcessor;
		// set the chain of responsibility
		this.processor.setNextProcessor(this.versionProcessor);
		this.versionProcessor.setNextProcessor(this.defaultPlatformProcessor);
		this.defaultPlatformProcessor.setNextProcessor(this.defaultProcessor);
	}

	/**
	 * Get freemarker templates for the screen either from cache or create based on version or default
	 * 
	 * Template will be returned as below:
	 * 	i)   If the template exists based on version and platform it is returned else, 
	 * 	ii)  If the template exists based on version is returned else,
	 *  iii) If the template exists based on default version and platform is returned else,
	 *  iv)  If the template exists based on default platform is returned else null is returned
	 * 
	 * @param templateBean
	 * @param journeyName
	 * @return
	 */
	public Template getTemplate(TemplateBean templateBean, String journeyName) {
		LOG.info("Start of fetch template for fileName: {} appVersion: {} platform: {}", templateBean.getFileName(),
				templateBean.getVersion(), templateBean.getPlatform());
		Template template = processor.getTemplate(templateBean, journeyName);
		LOG.info("End of fetch template for fileName: {} appVersion: {} platform: {}", templateBean.getFileName(),
				templateBean.getVersion(), templateBean.getPlatform());
		return template;
	}
}
