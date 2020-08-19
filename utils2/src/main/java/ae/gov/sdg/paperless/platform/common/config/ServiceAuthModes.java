package ae.gov.sdg.paperless.platform.common.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dubai.now")
public class ServiceAuthModes implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, List<String>> service = new HashMap<>();

    public Map<String, List<String>> getService() {
        return service;
    }

    public void setService(final Map<String, List<String>> service) {
        this.service = service;
    }

}