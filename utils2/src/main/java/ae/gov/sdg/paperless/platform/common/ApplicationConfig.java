package ae.gov.sdg.paperless.platform.common;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ae.gov.sdg.paperless.platform.common.config.LoadIfNotStubProfileCondition;
import ae.gov.sdg.paperless.platform.common.filters.ApplicationFilter;
import ae.gov.sdg.paperless.platform.security.AuthenticationInterceptor;

/**
 * 
 * @author saddam hussain
 *
 */
@Configuration
@Conditional(LoadIfNotStubProfileCondition.class)
public class ApplicationConfig implements WebMvcConfigurer {
	
    @Bean
	AuthenticationInterceptor getAuthenticationInterceptor() {
		return new AuthenticationInterceptor();
	}
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
	    registry.addInterceptor(getAuthenticationInterceptor());
	}
	
	@Bean
    public FilterRegistrationBean<ApplicationFilter> applicationFilter(PlatformConfig platformConfig){
        final FilterRegistrationBean<ApplicationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApplicationFilter());
        if(MapUtils.isNotEmpty(platformConfig.getFilterConfig().getPattern())) {
	        final List<String> applicationPatterns = platformConfig.getFilterConfig().getPattern().get("application");
	        if(CollectionUtils.isNotEmpty(applicationPatterns)) {
	        	registrationBean.addUrlPatterns(applicationPatterns.stream().toArray(String[]::new));
	        }
        }
        return registrationBean;    
    }
	
	@Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        mapper.disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        return mapper;
    }

}
