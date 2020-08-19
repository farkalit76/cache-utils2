package ae.gov.sdg.paperless.platform.common.service.generic;


import static ae.gov.sdg.paperless.platform.common.PlatformConstants.DICTONARYKEY_PARTIAL_ERROR;

import java.util.HashMap;
import java.util.Map;

import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.components.Request;

/**
 * @author c_chandra.bommise
 * <p>
 * Context for holding request info.
 */
public class JourneyExecutionContext {

    private Request request;

    private UserInfo userInfo;

    private String jwtToken;

    private String accessToken;

    private String platform;

    private String appVersion;

    private Map<String, Object> contextDataMap;

    public void partialFailure() {
        getContextDataMap().put(DICTONARYKEY_PARTIAL_ERROR, 1);
    }

    public Request getRequest() {
        return request;
    }

    public JourneyExecutionContext setRequest(final Request request) {
        this.request = request;
        return this;
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public JourneyExecutionContext setUserInfo(final UserInfo userInfo) {
		this.userInfo = userInfo;
		return this;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public JourneyExecutionContext setJwtToken(final String jwtToken) {
		this.jwtToken = jwtToken;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public JourneyExecutionContext setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public String getPlatform() {
		return platform;
	}

	public JourneyExecutionContext setPlatform(final String platform) {
		this.platform = platform;
		return this;
	}

	public String getAppVersion() {
		return appVersion;
	}

    public JourneyExecutionContext setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public static JourneyExecutionContext getInstance() {
        return new JourneyExecutionContext();
    }

    public Map<String, Object> getContextDataMap() {
        if (this.contextDataMap == null) this.contextDataMap = new HashMap<>();
        return this.contextDataMap;
    }
}