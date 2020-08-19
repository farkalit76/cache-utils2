package ae.gov.sdg.paperless.platform.common.service.authentication;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.common.model.BasicAuth;
import ae.gov.sdg.paperless.platform.common.model.GenericAuthToken;
import ae.gov.sdg.paperless.platform.common.service.generic.IRestService;
import ae.gov.sdg.paperless.platform.common.service.generic.RestBasicAuthService;
import ae.gov.sdg.paperless.platform.security.GenericAuthTokenCache;

/**
 * @author swetabh raj
 */
@Service
public class GenericAuthenticationServiceImpl implements GenericAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(GenericAuthenticationServiceImpl.class);

	private final IRestService<BasicAuth> restService;
    
    public GenericAuthenticationServiceImpl(@Qualifier("restBasicAuthService") final IRestService<BasicAuth> restService) {
    	this.restService = restService;
    }

    @Autowired
    private PlatformConfig config;

    private final GenericAuthTokenCache tokensCache = GenericAuthTokenCache.getInstance();

    @Override
    public GenericAuthToken getOAuthAccessToken(final String tokenName, final String username, final String password) {

        log.info("getGenericOauthAccessToken...");
        GenericAuthToken token = tokensCache.getToken(tokenName);
        if (token != null) {
            return token;
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("scope", "openid");


        try {
        	final RequestEntity<Void> requestEntity = RequestEntity
                    .post(URI.create(config.getOauthTokenApiUrl().concat("?grant_type=client_credentials&scope=openid")))
                    .accept(MediaType.ALL).headers(RestBasicAuthService.authHeaders(new BasicAuth(username, password)))
                    .headers(headers).build();
            token = restService.invoke(requestEntity, GenericAuthToken.class).getBody();
            tokensCache.addToken(tokenName, token);

            return token;
        } catch (final Exception e) {
            log.error("getOauthAccessToken() Error --> ", e);
            return null;
        }
    }
}