package ae.gov.sdg.paperless.platform.common.model;

/**
 * @author c_chandra.bommise
 * 
 * Bearer Auth parameters.
 *
 */
public class BearerAuth implements IAuthType {
	private String accessToken;

	public BearerAuth() {
		super();
	}

	public BearerAuth(final String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
