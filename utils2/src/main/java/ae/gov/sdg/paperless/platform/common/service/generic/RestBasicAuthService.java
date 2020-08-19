package ae.gov.sdg.paperless.platform.common.service.generic;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.common.model.BasicAuth;
import ae.gov.sdg.paperless.platform.exceptions.DNRuntimeException;
import ae.gov.sdg.paperless.platform.tracing.HttpRequestHeadersAppender;

/**
 * @author c_chandra.bommise
 *
 * Rest template using basic auth invocation.
 */
@Service
public class RestBasicAuthService implements IRestService<BasicAuth> {
    private RestTemplate restTemplate = null;
    
    private List<String> appendHeaders;
    
    @Autowired
    @Qualifier("restTemplateResponseErrorHandler")
	private ResponseErrorHandler restTemplateResponseErrorHandler;
	
	@Autowired
    private PlatformConfig platformConfig;
	
	public RestBasicAuthService() {
	}
	
	public RestBasicAuthService(List<String> appendHeaders, @Qualifier("restTemplateResponseErrorHandler") ResponseErrorHandler restTemplateResponseErrorHandler, PlatformConfig platformConfig) {
		this.appendHeaders = appendHeaders;
		this.restTemplateResponseErrorHandler = restTemplateResponseErrorHandler;
		this.platformConfig = platformConfig;
	}
	
    @PostConstruct
	public void init() {
    	restTemplate = buildCommonAttributes();
	}
    
    @Override
	public RestTemplate getRestService() {
		return restTemplate;
	}
    
    @Override
	public PlatformConfig getPlatformConfig() {
		return platformConfig;
	}

	@Override
	@Autowired
	@Qualifier("restTemplateResponseErrorHandler")
	public ResponseErrorHandler getRestTemplateResponseErrorHandler() {
		return restTemplateResponseErrorHandler;
	}
    
    @Override
	public <Q, S> ResponseEntity<S> invoke(final String url, final HttpMethod method, final Q request, final Class<S> responseType,
			final BasicAuth authDetails) {
    	return invoke(getRequestEntityWithBasicAuth(request, authDetails, method, url), responseType);
	}

	@Override
	public <Q, S> ResponseEntity<S> invoke(final String url, final Q request, final Class<S> responseType, final BasicAuth authDetails) {
		return invoke(getRequestEntityWithBasicAuth(request, authDetails, HttpMethod.POST, url), responseType);
	}
	
	@Override
	public <Q, S> S invoke(final String url, final Q request, final Class<S> responseType, final HttpMethod method, final HttpHeaders headers,
			final BasicAuth authDetails) {
		addAuthentication(getRestService(), authDetails.getUserName(), authDetails.getPassword());
		return invoke(url, request, responseType, method, headers);
	}
	
	@Override
	public <Q, S> ResponseEntity<S> invoke(final RequestEntity<Q> request, final Class<S> responseType, final BasicAuth authDetails) {
		return invoke(getRequestEntityWithBasicAuth(request,authDetails), responseType);
	}

	private <T> RequestEntity<T> getRequestEntityWithBasicAuth(final RequestEntity<T> request, final BasicAuth authDetails) {
		final HttpHeaders headers = new HttpHeaders();
		headers.addAll(request.getHeaders());
		headers.setBasicAuth(getBasicAuth(authDetails.getUserName(), authDetails.getPassword()));
		return new RequestEntity<>(request.getBody(), headers, request.getMethod(), request.getUrl());
	}

	private static String getBasicAuth(final String username, final String password) {
        return HttpHeaders.encodeBasicAuth(username, password, UTF_8);
    }
	
	private <T> RequestEntity<T> getRequestEntityWithBasicAuth(final T request, final BasicAuth authDetails, final HttpMethod method, final String uri) {
        final HttpHeaders headers = authHeaders(authDetails);
        try {
			return new RequestEntity<>(request, headers, method, new URI(uri));
		} catch (URISyntaxException e) {
			throw new DNRuntimeException(e);
		}
    }

	public static HttpHeaders authHeaders(final BasicAuth authDetails) {
		final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(getBasicAuth(authDetails.getUserName(), authDetails.getPassword()));
		return headers;
	}
	
    /**
	 * Add Basic authentication credentials to restTemplate.
	 * 
	 * @param restTemplate
	 * @param username
	 * @param password
	 */
    @SuppressWarnings("deprecation")
	public static void addAuthentication(final RestTemplate restTemplate, 
	    						         final String username, 
	    						         final String password) {
        
    	if (username == null) {
            return;
        }
        
        final List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        
        final List<ClientHttpRequestInterceptor> mInterceptors = interceptors.stream().filter(interceptor-> (interceptor != null && !(interceptor instanceof BasicAuthorizationInterceptor))).collect(Collectors.toList());

		mInterceptors.add(new BasicAuthorizationInterceptor(username, password));
        restTemplate.setInterceptors(mInterceptors);
    }

    @Override
	public HttpRequestHeadersAppender headerAppenderInterecptor() {
		if(CollectionUtils.isEmpty(this.appendHeaders)) {
			this.appendHeaders = IRestService.addCommonHeaders(); 
		}
		return new HttpRequestHeadersAppender(this.appendHeaders);
	}

}
