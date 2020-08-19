package ae.gov.sdg.paperless.platform.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * @author omerio
 *
 */
@Configuration
public class FreeMarkerConfig {
    
    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfig() {
        final FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        
        bean.setTemplateLoaderPaths("classpath:/screens");        

        return bean;
    }

}
