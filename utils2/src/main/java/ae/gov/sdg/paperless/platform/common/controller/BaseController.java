package ae.gov.sdg.paperless.platform.common.controller;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_DN_APP_VERSION;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.X_DN_PLATFORM;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;

import ae.gov.sdg.paperless.platform.common.PlatformConstants;
import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.components.Request;
import ae.gov.sdg.paperless.platform.common.service.generic.JourneyExecution;
import ae.gov.sdg.paperless.platform.common.service.generic.JourneyExecutionContext;
import ae.gov.sdg.paperless.platform.exceptions.BusinessValidationException;
import ae.gov.sdg.paperless.platform.exceptions.CustomException;
import ae.gov.sdg.paperless.platform.exceptions.ExternalApiException;
import ae.gov.sdg.paperless.platform.exceptions.GenericException;
import ae.gov.sdg.paperless.platform.exceptions.GenericExceptionContext;
import ae.gov.sdg.paperless.platform.exceptions.InvalidRequestException;
import ae.gov.sdg.paperless.platform.exceptions.ServiceInstantiationException;
import ae.gov.sdg.paperless.platform.exceptions.Severity;
import ae.gov.sdg.paperless.platform.exceptions.UserAuthenticationFailedException;

/**
 * The Class BaseController.
 *
 * @author c_chandra.bommise
 * 
 * Base controller with common functionalities.
 */
public abstract class BaseController {
	
	private JourneyExecution journeyExecution;
	
	private HttpServletRequest httpRequest;
	
	/**
	 * Gets the journey execution.
	 *
	 * @return the journey execution
	 */
	public JourneyExecution getJourneyExecution() {
		return journeyExecution;
	}

	/**
	 * Sets the journey execution.
	 *
	 * @param journeyExecution the new journey execution
	 */
	@Autowired
	public void setJourneyExecution(final JourneyExecution journeyExecution) {
		this.journeyExecution = journeyExecution;
	}
	
	/**
	 * Gets the http request.
	 *
	 * @return the http request
	 */
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * Sets the http request.
	 *
	 * @param httpRequest the new http request
	 */
	@Autowired
	public void setHttpRequest(final HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * Http headers.
	 *
	 * @return the http headers
	 */
	protected HttpHeaders httpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(APPLICATION_JSON_UTF8);
		return httpHeaders;
	}
	
	/**
	 * Generic exception.
	 *
	 * @param request the request
	 * @param ex the ex
	 */
	protected void genericException(final Request request, final Exception ex) {
		final Map<String, Object> dictionary = new HashMap<>();
		dictionary.put(PlatformConstants.PREVIOUS_SCREEN, request.getScreen());
		final GenericExceptionContext context = new GenericExceptionContext(request, PlatformConstants.GENERIC_ERROR_DISMISS_TEMPLATE_SCREEN,
				dictionary, request.getLang(), "", null, Severity.MEDIUM);
		throw new GenericException(ex.getMessage(), ex, context);
	}
	
	/**
	 * Populate journey context.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @param accessToken the access token
	 * @param jwtToken the jwt token
	 * @return the journey execution context
	 */
	protected JourneyExecutionContext populateJourneyContext(final Request request, final UserInfo userInfo, final String accessToken, final String jwtToken) {
		final JourneyExecutionContext context = JourneyExecutionContext.getInstance();
		final String appVersion = httpRequest.getHeader(X_DN_APP_VERSION);
		final String platform = httpRequest.getHeader(X_DN_PLATFORM);
		context.setRequest(request).setUserInfo(userInfo).setAccessToken(accessToken).setJwtToken(jwtToken)
				.setPlatform(platform).setAppVersion(appVersion);
		return context;
	}
	
	/**
	 * Populate journey context.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @return the journey execution context
	 */
	protected JourneyExecutionContext populateJourneyContext(final Request request, final UserInfo userInfo) {
		return populateJourneyContext(request, userInfo, null, null);
	}
	
	/**
	 * Custom screen.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UserAuthenticationFailedException the user authentication failed exception
	 */
	protected String customScreen(final Request request, final UserInfo userInfo) throws IOException, UserAuthenticationFailedException {
		final JourneyExecutionContext context = populateJourneyContext(request, userInfo);
		return journeyExecution.execute(context);
	}
	
	/**
	 * Screen.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @return the string
	 * @throws UserAuthenticationFailedException the user authentication failed exception
	 */
	protected String screen(final Request request, final UserInfo userInfo) throws UserAuthenticationFailedException {
		try {
			return customScreen(request, userInfo);
		} catch ( ExternalApiException | CustomException | GenericException | InvalidRequestException| ServiceInstantiationException| BusinessValidationException | UserAuthenticationFailedException | RestClientException ex) {
			throw ex;
		} catch (final Exception ex) {
			genericException(request, ex);
		}
		return null;
	}
	
	/**
	 * Custom screen.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @param accessToken the access token
	 * @param jwtToken the jwt token
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UserAuthenticationFailedException the user authentication failed exception
	 */
	protected String customScreen(final Request request, final UserInfo userInfo, final String accessToken, final String jwtToken) throws IOException, UserAuthenticationFailedException {
		final JourneyExecutionContext context = populateJourneyContext(request, userInfo, accessToken, jwtToken);
		return journeyExecution.execute(context);
	}
	
	/**
	 * Screen.
	 *
	 * @param request the request
	 * @param userInfo the user info
	 * @param accessToken the access token
	 * @param jwtToken the jwt token
	 * @return the string
	 * @throws UserAuthenticationFailedException the user authentication failed exception
	 */
	protected String screen(final Request request, final UserInfo userInfo, final String accessToken, final String jwtToken) throws UserAuthenticationFailedException {
		try {
			return customScreen(request, userInfo, accessToken, jwtToken);
		} catch (ExternalApiException | CustomException | GenericException | InvalidRequestException| ServiceInstantiationException| BusinessValidationException | UserAuthenticationFailedException | RestClientException ex) {
			throw ex;
		} catch (final Exception ex) {
			genericException(request, ex);
		}
		return null;
	}

}
