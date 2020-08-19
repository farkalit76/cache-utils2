package ae.gov.sdg.paperless.platform.common.service.generic;

import java.io.IOException;

import ae.gov.sdg.paperless.platform.exceptions.UserAuthenticationFailedException;

/**
 * @author c_chandra.bommise
 * 
 * Service process interface
 *
 */
public interface IJourneyService {
	
	/**
	 * Process the service based on journey and screen.
	 * 
	 * @param context
	 * @return
	 * @throws IOException
	 * @throws UserAuthenticationFailedException
	 */
	String process(JourneyExecutionContext context) throws IOException, UserAuthenticationFailedException;
	
}
