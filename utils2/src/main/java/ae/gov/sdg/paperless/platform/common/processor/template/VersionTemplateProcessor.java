package ae.gov.sdg.paperless.platform.common.processor.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import ae.gov.sdg.paperless.platform.common.processor.bean.TemplateBean;

/**
 * @author c_chandra.bommise
 * 
 * Fetch the template or json files based on only app version
 *
 */
@Component
public class VersionTemplateProcessor extends AbstractTemplateProcessor {
	
	private final Map<TemplateBean, Boolean> templateExistsCache = new ConcurrentHashMap<>();
	
	public TemplateBean getTemplateBean(TemplateBean actualBean) {
		TemplateBean beanByClass = new TemplateBean();
		beanByClass.setVersion(actualBean.getVersion());
		beanByClass.setFileName(actualBean.getFileName());
		return beanByClass;
	}
	
	protected Map<TemplateBean, Boolean> getTemplateExistsCache() {
		return templateExistsCache;
	}
}
