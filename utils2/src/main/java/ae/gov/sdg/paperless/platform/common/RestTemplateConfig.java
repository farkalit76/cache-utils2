
package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_DN_APP_VERSION;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_DN_PLATFORM;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;

import ae.gov.sdg.paperless.platform.common.service.generic.IRestService;
import ae.gov.sdg.paperless.platform.common.service.generic.RestBasicAuthService;

/**
 * RestTemplateConfig.java
 * Purpose: Generate REST client templates to be injected into consumer components.
 *
 * @author Ibrahim Abdel Aziz
 * @version 1.0
 */
@Configuration
public class RestTemplateConfig {
	
    @Bean
    @Qualifier("commonServicesRestTemplate")
    public RestBasicAuthService commonRestTemplate(@Qualifier("restTemplateResponseErrorHandler") ResponseErrorHandler restTemplateResponseErrorHandler, PlatformConfig platformConfig) {
    	final List<String> headers = IRestService.addCommonHeaders();
    	headers.add(X_DN_APP_VERSION);
    	headers.add(X_DN_PLATFORM);
    	return new RestBasicAuthService(headers, restTemplateResponseErrorHandler, platformConfig);
    }

}
