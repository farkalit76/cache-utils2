package ae.gov.sdg.paperless.platform.common.service.generic;

import java.io.IOException;

import ae.gov.sdg.paperless.platform.exceptions.UserAuthenticationFailedException;

/**
 * @author c_chandra.bommise
 * 
 * Journey execution service interface
 *
 */
public interface IJourneyExecution {
	
	/**
	 * Service execution based on journey.
	 * 
	 * @param context
	 * @return
	 * @throws IOException
	 * @throws UserAuthenticationFailedException
	 */
	String execute(JourneyExecutionContext context) throws IOException, UserAuthenticationFailedException;

}
