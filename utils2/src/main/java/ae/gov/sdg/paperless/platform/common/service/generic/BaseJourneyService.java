package ae.gov.sdg.paperless.platform.common.service.generic;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.BEARER;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.DUBAI_NOW_ACCESS_TOKEN;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.GENERIC_ERROR_TEMPLATE_SCREEN;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.LANG;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import ae.gov.sdg.paperless.platform.cache.service.json.RedisJsonCacheService;
import ae.gov.sdg.paperless.platform.cache.service.json.RedisSimpleJsonCacheService;
import ae.gov.sdg.paperless.platform.cache.service.plaintext.RedisPlainTextCacheService;
import ae.gov.sdg.paperless.platform.common.ScreenParserService;
import ae.gov.sdg.paperless.platform.common.ScreenParserServiceV2;
import ae.gov.sdg.paperless.platform.common.model.BearerAuth;
import ae.gov.sdg.paperless.platform.common.model.GenericAuthToken;
import ae.gov.sdg.paperless.platform.common.model.IJourneyType;
import ae.gov.sdg.paperless.platform.common.model.IServiceConfig;
import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.components.Request;
import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;
import ae.gov.sdg.paperless.platform.common.service.authentication.GenericAuthenticationService;

/**
 * @author c_chandra.bommise
 * 
 * Base service which has common functionalities that can be used by implemented
 * services.
 */
public abstract class BaseJourneyService implements IJourneyService {

	private ScreenParserService screenService;
	
	private ScreenParserServiceV2 screenServiceV2;

	private DubaiNowScreenUtil dubaiNowScreenUtil;

	private IServiceConfig config;

	private IRestService<BearerAuth> restService;

	private GenericAuthenticationService genAuthService;
	
	private RedisJsonCacheService jsonCacheService;
	
	private RedisSimpleJsonCacheService jsonSimpleCacheService;
	
	private RedisPlainTextCacheService textCacheService;

	public ScreenParserService getScreenService() {
		return screenService;
	}

	@Autowired
	public void setScreenService(final ScreenParserService screenService) {
		this.screenService = screenService;
	}
	
	public ScreenParserServiceV2 getScreenServiceV2() {
		return screenServiceV2;
	}

	@Autowired
	public void setScreenServiceV2(final ScreenParserServiceV2 screenServiceV2) {
		this.screenServiceV2 = screenServiceV2;
	}

	public DubaiNowScreenUtil getDubaiNowScreenUtil() {
		return dubaiNowScreenUtil;
	}

	@Autowired
	public void setDubaiNowScreenUtil(final DubaiNowScreenUtil dubaiNowScreenUtil) {
		this.dubaiNowScreenUtil = dubaiNowScreenUtil;
	}

	public IServiceConfig getConfig() {
		return config;
	}

	@Autowired
	@Qualifier("serviceConfig")
	public void setConfig(final IServiceConfig config) {
		this.config = config;
	}

	public IRestService<BearerAuth> getRestService() {
		return restService;
	}

	public RestTemplate bearerRestTemplate() {
		return restService.getRestService();
	}

	@Autowired
	@Qualifier("restBearerAuthService")
	public void setRestService(final IRestService<BearerAuth> restService) {
		this.restService = restService;
	}

	public GenericAuthenticationService getGenAuthService() {
		return genAuthService;
	}

	@Autowired
	public void setGenAuthService(final GenericAuthenticationService genAuthService) {
		this.genAuthService = genAuthService;
	}
	
	public RedisPlainTextCacheService getTextCacheService() {
		return textCacheService;
	}
	@Autowired
	public void setTextCacheService(RedisPlainTextCacheService textCacheService) {
		this.textCacheService = textCacheService;
	}

	/**
	 * @deprecated
	 * This method will be deprecated. Replaced by {@link #getJsonSimpleCacheService()}
	 */
	@Deprecated
	public RedisJsonCacheService getJsonCacheService() {
		return jsonCacheService;
	}

	/**
	 * @deprecated
	 * This method will be deprecated. Replaced by {@link #setJsonSimpleCacheService(RedisSimpleJsonCacheService jsonSimpleCacheService)}
	 */
	@Autowired
	@Deprecated
	public void setJsonCacheService(RedisJsonCacheService jsonCacheService) {
		this.jsonCacheService = jsonCacheService;
	}

	public RedisSimpleJsonCacheService getJsonSimpleCacheService() {
		return jsonSimpleCacheService;
	}

	@Autowired
	public void setJsonSimpleCacheService(RedisSimpleJsonCacheService jsonSimpleCacheService) {
		this.jsonSimpleCacheService = jsonSimpleCacheService;
	}

	protected String fetchClientAccessToken() {
		final GenericAuthToken dubaiNowAccessInfo = getGenAuthService().getOAuthAccessToken(DUBAI_NOW_ACCESS_TOKEN,
				getConfig().getClientId(), getConfig().getClientSecret());
		return BEARER + dubaiNowAccessInfo.getIdToken();
	}

	protected HttpHeaders httpHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	protected String genericErrorScreen(final LangType lang, final IJourneyType journeyName) throws IOException {
		String errorScreen = null;
		errorScreen = this.getScreenService().getAndPopulateScreens(GENERIC_ERROR_TEMPLATE_SCREEN,
				new HashMap<>(), (lang != null ? lang : LangType.en), "", journeyName);
		return errorScreen;
	}

	protected String populateScreenValidateUser(final JourneyExecutionContext context, final String screenName) throws IOException {
		final UserInfo userInfo = context.getUserInfo();
		Validate.notNull(userInfo, "required user info not present");
		final Map<String, Object> responseMap = new HashMap<>();
		final Request request = context.getRequest();
		responseMap.put(LANG, request.getLang().name());
		return getScreenService().getAndPopulateScreens(screenName, responseMap, request.getLang(), "", request.getJourney());
	}

}