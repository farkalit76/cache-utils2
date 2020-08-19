package ae.gov.sdg.paperless.platform.common.model;

/**
 * @author c_chandra.bommise
 * 
 * Basic auth parameters.
 *
 */
public class BasicAuth implements IAuthType {
	private String userName;
	
	private String password;
	
	public BasicAuth() {
		super();
	}
	public BasicAuth(final String userName, final String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	
	
}
