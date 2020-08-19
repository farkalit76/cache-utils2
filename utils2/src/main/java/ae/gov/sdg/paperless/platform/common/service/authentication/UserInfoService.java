package ae.gov.sdg.paperless.platform.common.service.authentication;

import java.text.ParseException;

import ae.gov.sdg.paperless.platform.common.model.UserInfo;

/**
 * @author saddam hussain
 *
 */
public interface UserInfoService {

	UserInfo getUnifiedUserInfo(String unifiedACGAcessToken, String dubaiNowJWT) throws ParseException;
}