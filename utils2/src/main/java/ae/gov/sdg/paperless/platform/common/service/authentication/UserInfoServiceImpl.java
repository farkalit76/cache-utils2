package ae.gov.sdg.paperless.platform.common.service.authentication;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.MPAY;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PROFILE_TYPE_FULL;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PROFILE_TYPE_HEADER;

import java.net.URI;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.common.model.BearerAuth;
import ae.gov.sdg.paperless.platform.common.model.DubaiNowUserInfo;
import ae.gov.sdg.paperless.platform.common.model.LOGIN_SOURCE;
import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.service.generic.IRestService;
import ae.gov.sdg.paperless.platform.security.JWTService;
import ae.gov.sdg.paperless.platform.util.BusinessUtil;

/**
 * @author saddam hussain
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	public static final String HEADER_AUTHORIZATION_UNIFIED_ACG_ACCESS_TOKEN = "access_token";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String BEARER = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
	
	private IRestService<BearerAuth> restService;
    
    private JWTService jwtUtil;
    
    private PlatformConfig config;
	
	public UserInfoServiceImpl(@Qualifier("restBearerAuthService") final IRestService<BearerAuth> restService, PlatformConfig config, JWTService jwtUtil) {
    	this.restService = restService;
    	this.config = config;
    	this.jwtUtil = jwtUtil;
    }

	private UserInfo setupUserInfo(final DubaiNowUserInfo dubaiNowUserInfo) {
		if(dubaiNowUserInfo==null) {
			return null;
		}
		final UserInfo userInfo = new UserInfo();
		userInfo.setFirstnameEN(dubaiNowUserInfo.getFullName());
		userInfo.setEmail(dubaiNowUserInfo.getEmailAddress());
		userInfo.setMobile(dubaiNowUserInfo.getMobileNumber());
		userInfo.setIdn(dubaiNowUserInfo.getNationalId());
		userInfo.setUuid(dubaiNowUserInfo.getEmailAddress());
		userInfo.setSub(BusinessUtil.formatEID(dubaiNowUserInfo.getNationalId()));
		userInfo.setDob(dubaiNowUserInfo.getBirthDate());
		userInfo.setNationalityEN(dubaiNowUserInfo.getNationalityId());
		userInfo.setUserType(MPAY);
		return userInfo;
	}

	public UserInfo getUnifiedUserInfo(final String unifiedACGAcessToken, final String dubaiNowJWT) throws ParseException {
		log.info("loading unfied user info for dubai now ...");
		ResponseEntity<DubaiNowUserInfo> dubaiNowresponse = null;
		String url = config.getUnifiedUserInfoUrl();
		final RequestEntity<String> requestEntity = RequestEntity.post(
				URI.create(url))
				.accept(MediaType.ALL)
				.header(AUTHORIZATION, BEARER + dubaiNowJWT)
				.header(HEADER_AUTHORIZATION_UNIFIED_ACG_ACCESS_TOKEN, unifiedACGAcessToken)
				.header(PROFILE_TYPE_HEADER, PROFILE_TYPE_FULL)
				.header(CONTENT_TYPE, APPLICATION_JSON).body("{}");
		if(LOGIN_SOURCE.MPAY.equals(jwtUtil.getLoginSource(dubaiNowJWT))) {
			dubaiNowresponse = restService.invoke(requestEntity, DubaiNowUserInfo.class);
			return setupUserInfo(dubaiNowresponse.getBody());
		}else if(LOGIN_SOURCE.UAEPASS.equals(jwtUtil.getLoginSource(dubaiNowJWT))) {
			return restService.invoke(requestEntity, UserInfo.class).getBody();
		}
		throw new ParseException("invalid source type",0);

	}

}