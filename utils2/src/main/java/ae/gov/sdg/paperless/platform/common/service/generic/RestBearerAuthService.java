package ae.gov.sdg.paperless.platform.common.service.generic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.common.model.BearerAuth;
import ae.gov.sdg.paperless.platform.exceptions.DNRuntimeException;
import ae.gov.sdg.paperless.platform.tracing.HttpRequestHeadersAppender;

/**
 * @author c_chandra.bommise
 *
 * Rest template using bearer auth invocation.
 */
@Service
public class RestBearerAuthService implements IRestService<BearerAuth> {
    private RestTemplate restTemplate = null;
    
    private List<String> appendHeaders;
    
    @Autowired
    @Qualifier("restTemplateResponseErrorHandler")
	private ResponseErrorHandler restTemplateResponseErrorHandler;
	
	@Autowired
    private PlatformConfig platformConfig;
	
	public RestBearerAuthService() {
		
	}
	
	public RestBearerAuthService(List<String> appendHeaders, @Qualifier("restTemplateResponseErrorHandler") ResponseErrorHandler restTemplateResponseErrorHandler, PlatformConfig platformConfig) {
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
    @Autowired
    @Qualifier("restTemplateResponseErrorHandler")
    public ResponseErrorHandler getRestTemplateResponseErrorHandler() {
		return restTemplateResponseErrorHandler;
	}

    @Override
	public PlatformConfig getPlatformConfig() {
		return platformConfig;
	}

	@Override
	public <Q, S> ResponseEntity<S> invoke(final String url, final HttpMethod method, final Q request, final Class<S> responseType,
			final BearerAuth authDetails) {
    	return invoke(getRequestEntityWithBearerToken(request, authDetails, method, url), responseType);
	}

	@Override
	public <Q, S> ResponseEntity<S> invoke(final String url, final Q request, final Class<S> responseType, final BearerAuth authDetails)
			 {
		return invoke(getRequestEntityWithBearerToken(request, authDetails, HttpMethod.POST, url), responseType);
	}
	
	@Override
	public <Q, S> S invoke(final String url, final Q request, final Class<S> responseType, final HttpMethod method, final HttpHeaders headers,
			final BearerAuth authDetails) {
		return invoke(url, request, responseType, method, headers);
	}

	@Override
	public <Q, S> ResponseEntity<S> invoke(final RequestEntity<Q> request, final Class<S> responseType, final BearerAuth authDetails) {
		return invoke(getRequestEntityWithBearerToken(request,authDetails), responseType);
	}
	
	private <T> RequestEntity<T> getRequestEntityWithBearerToken(final RequestEntity<T> request, final BearerAuth authDetails) {
		final HttpHeaders headers = new HttpHeaders();
		headers.addAll(request.getHeaders());
		headers.setBearerAuth(authDetails.getAccessToken());
		return new RequestEntity<>(request.getBody(), headers, request.getMethod(), request.getUrl());
	}

	private <T> RequestEntity<T> getRequestEntityWithBearerToken(final T request, final BearerAuth authDetails, final HttpMethod method, final String url) {
		final HttpHeaders headers = authHeaders(authDetails);
        try {
        	return new RequestEntity<>(request, headers, method, new URI(url));
        } catch (URISyntaxException e) {
			throw new DNRuntimeException(e);
		}
    }
	
	public static HttpHeaders authHeaders(final BearerAuth authDetails) {
		final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authDetails.getAccessToken());
		return headers;
	}
	
	@Override
	public HttpRequestHeadersAppender headerAppenderInterecptor() {
		if(CollectionUtils.isEmpty(this.appendHeaders)) {
			this.appendHeaders = IRestService.addCommonHeaders(); 
		}
		return new HttpRequestHeadersAppender(this.appendHeaders);
	}

}
