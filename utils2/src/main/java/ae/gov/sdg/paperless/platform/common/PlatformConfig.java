package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.CERT_DEV_PATH;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.CERT_PROD_PATH;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.CERT_QA_PATH;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.DEV_PROFILE;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.LOCAL_PROFILE;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PROD_PROFILE;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.QA_PROFILE;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import ae.gov.sdg.paperless.platform.common.config.FilterPatternConfig;
import ae.gov.sdg.paperless.platform.common.config.ServiceAuthModes;

@Configuration
@Primary
public class PlatformConfig implements Serializable {

	private static final long serialVersionUID = -318269362580939761L;
	
	@Value("${microservice.name:MS}")
    private String msName;
	
	@Value("${spring.profiles.active:Unknown}")
    private String activeProfile;
	
	@Value("${journey.service.logLevelVerbose}")
    private boolean logLevelVerbose;
	
	@Value("${journey.service.logApiCalls}")
	private boolean logApiCalls;
	
	@Value("${ids.oauth2.token.api}")
    private String oauthTokenApiUrl;
	
	@Value("${dubai.now.cache.ctxt.url:http://localhost.dubai.ae/paperless-common-services/}")
    private String dubaiNowCacheContextUrl;
	
    @Value("${dubai.now.cache.username}")
    private String dubaiNowCacheUserName;

    @Value("${dubai.now.cache.password}")
    private String dubaiNowCachePassword;
	
	@Value("${unified.userinfo.url}")
    private String unifiedUserInfoUrl;
	
	@Value("${uae.pass.userinfo.service.url}")
    private String uaePassUserInfoUrl;

    @Value("${dubai.now.userinfo.service.url}")
    private String dubaiNowUserInfoUrl;
    
    @Value("${journey.service.auth.username}")
    private String dubaiNowServiceUsername;

    @Value("${journey.service.auth.password}")
    private String dubaiNowServicePassword;
    
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;
    
    @Autowired
    private ServiceAuthModes authModes;
    
    @Autowired
    private FilterPatternConfig filterConfig;
    
    @Value("${dubai.now.mpay.subscription.context.url}")
    private String dubaiNowMpaySubscriptionContextUrl;

    @Value("${dubai.now.user.data.url}")
    private String dubaiNowUserDataUrl;

    @Value("${dubai.now.mpay.userdata.url}")
    private String dubaiNowMpayUserDataUrl;

    @Value("${journey.service.swagger.regex}")
    private String swaggerRegex;

    @Value("${application.version}")
    private String applicationVersion;
    
    @Value("${async.core.pool.size}")
    private Integer asyncCorePoolSize;

    @Value("${async.max.pool.size}")
    private Integer asyncMaxPoolSize;

    @Value("${async.queue.capacity}")
    private Integer asyncQueueCapacity;

	public String getMsName() {
		return msName;
	}

	public void setMsName(final String msName) {
		this.msName = msName;
	}

	/**
     * Utility to check if currently active profile is local
     * @return
     */
    public boolean isLocalProfile() {
        return LOCAL_PROFILE.equalsIgnoreCase(activeProfile);
    }

    /**
     * Utility to check if currently active profile is dev
     * @return
     */
    public boolean isDevProfile() {
        return DEV_PROFILE.equalsIgnoreCase(activeProfile);
    }
    
    public String getActiveProfile() {
		return activeProfile;
	}

	public String  getJWTCertificatePath() {
        if(PROD_PROFILE.equalsIgnoreCase(activeProfile)) {
            return CERT_PROD_PATH;
        }else if(QA_PROFILE.equalsIgnoreCase(activeProfile)) {
            return CERT_QA_PATH;
        }else {
            return CERT_DEV_PATH;
        }
    }

	public boolean isLogLevelVerbose() {
		return logLevelVerbose;
	}

	public void setLogLevelVerbose(final boolean logLevelVerbose) {
		this.logLevelVerbose = logLevelVerbose;
	}

	public boolean isLogApiCalls() {
		return logApiCalls;
	}

	public void setLogApiCalls(final boolean logApiCalls) {
		this.logApiCalls = logApiCalls;
	}

	public String getOauthTokenApiUrl() {
		return oauthTokenApiUrl;
	}

	public void setOauthTokenApiUrl(final String oauthTokenApiUrl) {
		this.oauthTokenApiUrl = oauthTokenApiUrl;
	}

	public String getDubaiNowCacheContextUrl() {
		return dubaiNowCacheContextUrl;
	}

	public void setDubaiNowCacheContextUrl(final String dubaiNowCacheContextUrl) {
		this.dubaiNowCacheContextUrl = dubaiNowCacheContextUrl;
	}

	public String getDubaiNowCacheUserName() {
		return dubaiNowCacheUserName;
	}

	public void setDubaiNowCacheUserName(final String dubaiNowCacheUserName) {
		this.dubaiNowCacheUserName = dubaiNowCacheUserName;
	}

	public String getDubaiNowCachePassword() {
		return dubaiNowCachePassword;
	}

	public void setDubaiNowCachePassword(final String dubaiNowCachePassword) {
		this.dubaiNowCachePassword = dubaiNowCachePassword;
	}

	public String getUnifiedUserInfoUrl() {
		return unifiedUserInfoUrl;
	}

	public void setUnifiedUserInfoUrl(final String unifiedUserInfoUrl) {
		this.unifiedUserInfoUrl = unifiedUserInfoUrl;
	}

	public String getUaePassUserInfoUrl() {
		return uaePassUserInfoUrl;
	}

	public void setUaePassUserInfoUrl(final String uaePassUserInfoUrl) {
		this.uaePassUserInfoUrl = uaePassUserInfoUrl;
	}

	public String getDubaiNowUserInfoUrl() {
		return dubaiNowUserInfoUrl;
	}

	public void setDubaiNowUserInfoUrl(final String dubaiNowUserInfoUrl) {
		this.dubaiNowUserInfoUrl = dubaiNowUserInfoUrl;
	}

	public String getDubaiNowServiceUsername() {
		return dubaiNowServiceUsername;
	}

	public void setDubaiNowServiceUsername(final String dubaiNowServiceUsername) {
		this.dubaiNowServiceUsername = dubaiNowServiceUsername;
	}

	public String getDubaiNowServicePassword() {
		return dubaiNowServicePassword;
	}

	public void setDubaiNowServicePassword(final String dubaiNowServicePassword) {
		this.dubaiNowServicePassword = dubaiNowServicePassword;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(final String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public ServiceAuthModes getAuthModes() {
		return authModes;
	}

	public void setAuthModes(final ServiceAuthModes authModes) {
		this.authModes = authModes;
	}

	public FilterPatternConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(final FilterPatternConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public String getDubaiNowMpaySubscriptionContextUrl() {
		return dubaiNowMpaySubscriptionContextUrl;
	}

	public void setDubaiNowMpaySubscriptionContextUrl(final String dubaiNowMpaySubscriptionContextUrl) {
		this.dubaiNowMpaySubscriptionContextUrl = dubaiNowMpaySubscriptionContextUrl;
	}

	public String getSwaggerRegex() {
		return swaggerRegex;
	}

	public void setSwaggerRegex(final String swaggerRegex) {
		this.swaggerRegex = swaggerRegex;
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public String getDubaiNowUserDataUrl() {
		return dubaiNowUserDataUrl;
	}

	public void setDubaiNowUserDataUrl(String dubaiNowUserDataUrl) {
		this.dubaiNowUserDataUrl = dubaiNowUserDataUrl;
	}

	public Integer getAsyncCorePoolSize() {
		return asyncCorePoolSize;
	}

	public void setAsyncCorePoolSize(Integer asyncCorePoolSize) {
		this.asyncCorePoolSize = asyncCorePoolSize;
	}

	public Integer getAsyncMaxPoolSize() {
		return asyncMaxPoolSize;
	}

	public void setAsyncMaxPoolSize(Integer asyncMaxPoolSize) {
		this.asyncMaxPoolSize = asyncMaxPoolSize;
	}

	public Integer getAsyncQueueCapacity() {
		return asyncQueueCapacity;
	}

	public void setAsyncQueueCapacity(Integer asyncQueueCapacity) {
		this.asyncQueueCapacity = asyncQueueCapacity;
	}

	public String getDubaiNowMpayUserDataUrl() {
		return dubaiNowMpayUserDataUrl;
	}

	public void setDubaiNowMpayUserDataUrl(String dubaiNowMpayUserDataUrl) {
		this.dubaiNowMpayUserDataUrl = dubaiNowMpayUserDataUrl;
	}
}