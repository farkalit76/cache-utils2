package ae.gov.sdg.paperless.platform.common.service.generic;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_SESSION_ID;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_TRACE_ID;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.exceptions.DNRuntimeException;
import ae.gov.sdg.paperless.platform.tracing.HttpRequestHeadersAppender;
import ae.gov.sdg.paperless.platform.tracing.LoggingInterceptor;

/**
 * @author c_chandra.bommise
 *
 * Interface for rest template invocations.
 * @param <T>
 */
public interface IRestService<T> {
	
	<Q, S> ResponseEntity<S> invoke(final String url, final HttpMethod method, final Q request, Class<S> responseType, T authDetails);

    <Q, S> ResponseEntity<S> invoke(String url, Q request, Class<S> responseType, T authDetails);
    
    <Q, S> S invoke(String url, Q params, Class<S> responseType, HttpMethod post, HttpHeaders headers, T authDetails);
   
    <Q, S> ResponseEntity<S> invoke(RequestEntity<Q> request, Class<S> responseType, T authDetails);
    
    default <Q, S> ResponseEntity<S> invoke(final String url, final Q request, final Class<S> responseType, final HttpMethod method) {
		return invoke(getRequestEntity(request, url, method), responseType);
	}
    
    default <Q, S> ResponseEntity<S> invoke(final RequestEntity<Q> requestEntity, final Class<S> responseType) {
		return getRestService().exchange(requestEntity, responseType);
	}
    
    default <Q, S> ResponseEntity<S> invoke(final RequestEntity<Q> requestEntity, final ParameterizedTypeReference<S> responseType) {
		return getRestService().exchange(requestEntity, responseType);
	}
    
    default <Q, S> S invoke(final String url, final Q request, final Class<S> responseType, final HttpMethod method, final HttpHeaders headers)
			{
		return getRestService().exchange(url, method, getHttpEntity(request, headers), responseType).getBody();
	}

	default <Q, S> S invoke(final String url, final HttpEntity<Q> requestEntity, final Class<S> responseType, final HttpMethod method) {
		return getRestService().exchange(url, method, requestEntity, responseType).getBody();
	}

	default <Q, S> S invoke(final String url, final HttpEntity<Q> requestEntity, final Class<S> responseType, final HttpMethod method, final Map<String, ?> params) {
		return getRestService().exchange(url, method, requestEntity, responseType, params).getBody();
	}

	/**
     * Helper method to set common attributes between different REST templates producers.
     *
     * @return REST template with default configurations like (timeout, interceptors, converters ...etc)
     */
    default RestTemplate buildCommonAttributes() {

        final SimpleClientHttpRequestFactory clientConfig = new SimpleClientHttpRequestFactory();
        clientConfig.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(60));
        clientConfig.setReadTimeout((int) TimeUnit.SECONDS.toMillis(60));

        final ObjectMapper nonNullMapper = new ObjectMapper().setSerializationInclusion(
                JsonInclude.Include.NON_NULL);

        final RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(clientConfig));
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(nonNullMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        restTemplate.setErrorHandler(getRestTemplateResponseErrorHandler());
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
       
        restTemplate.getInterceptors().add(headerAppenderInterecptor());
        if(getPlatformConfig().isLogApiCalls()) {
        	LoggingInterceptor interceptor = LoggingInterceptor.getInstance();
        	interceptor.setContext(getPlatformConfig().getMsName()).setLogRequest(true).setLogResponse(true);
        	restTemplate.getInterceptors().add(interceptor);
        }
        return restTemplate;
    }
	
    /**
     * Add common request headers
     * 
     * @return
     */
	 static List<String> addCommonHeaders() {
		final List<String> headers = new ArrayList<>();
    	headers.add(X_SESSION_ID);
    	headers.add(X_TRACE_ID);
		return headers;
	}

	static <T> HttpEntity<T> getHttpEntity(final T request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return getHttpEntity(request, headers);
    }

    static <T> RequestEntity<T> getRequestEntity(final T request, final String uri, final HttpMethod method) {
        try {
			return getRequestEntity(request, new URI(uri), method);
		} catch (URISyntaxException e) {
			throw new DNRuntimeException(e);
		}
    }

    static <T> RequestEntity<T> getRequestEntity(final T request, final URI uri, final HttpMethod method) {
        return new RequestEntity<>(request, method, uri);
    }

    static <T> RequestEntity<T> getRequestEntity(final T request, final URI uri, final HttpMethod method, final HttpHeaders headers) {
        return new RequestEntity<>(request, headers, method, uri);
    }

    static <T> HttpEntity<T> getHttpEntity(final T request, final HttpHeaders headers) {
        return new HttpEntity<>(request, new HttpHeaders(headers));
    }
    
    RestTemplate getRestService();

	PlatformConfig getPlatformConfig();

	ResponseErrorHandler getRestTemplateResponseErrorHandler();
	
	HttpRequestHeadersAppender headerAppenderInterecptor();

}