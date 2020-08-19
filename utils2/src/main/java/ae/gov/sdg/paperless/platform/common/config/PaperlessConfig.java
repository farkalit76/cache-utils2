package ae.gov.sdg.paperless.platform.common.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author c_chandra.bommise
 *
 * Config to scan paperless platform packages.
 */
@Configuration
@ComponentScan({"ae.gov.sdg.paperless.platform"})
public class PaperlessConfig {
}
