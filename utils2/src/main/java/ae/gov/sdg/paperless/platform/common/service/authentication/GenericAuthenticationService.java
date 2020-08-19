package ae.gov.sdg.paperless.platform.common.service.authentication;

import ae.gov.sdg.paperless.platform.common.model.GenericAuthToken;

/**
 * GenericAuthenticationService service is responsible of obtaining access tokens from IDS
 * 
 * @author swetabh raj
 *
 */
public interface GenericAuthenticationService {
	
	/**
	 * Gets an GenericAuthToken access token from GSB using provided credentials
	 * @param tokenName Cached token name
	 * @param username Username
	 * @param password Password
	 * @return An instance of <code>GenericAuthToken</code>
	 */
	public GenericAuthToken getOAuthAccessToken(String tokenName, String username, String password);
}