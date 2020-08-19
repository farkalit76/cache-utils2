package ae.gov.sdg.paperless.platform.common.model;

import java.io.Serializable;

public interface IServiceConfig extends Serializable {
	String getClientId();
	String getClientSecret();
}
