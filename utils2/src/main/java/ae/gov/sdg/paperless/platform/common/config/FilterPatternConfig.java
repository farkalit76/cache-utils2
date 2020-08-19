package ae.gov.sdg.paperless.platform.common.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author c_chandra.bommise
 *
 * Filtern pattern for URL's journey URL's
 */
@Component
@ConfigurationProperties(prefix = "journey.service.filters.url")
public class FilterPatternConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, List<String>> pattern = new HashMap<>();

	public Map<String, List<String>> getPattern() {
		return pattern;
	}

	public void setPattern(final Map<String, List<String>> pattern) {
		this.pattern = pattern;
	}
	

}
