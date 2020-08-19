package ae.gov.sdg.paperless.platform.common.config;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * A condition for loading configuration if the profile is not set to stub
 * @author omerio
 *
 */
public class LoadIfNotStubProfileCondition implements ConfigurationCondition   {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        for (final String profile : activeProfiles) {
            if(profile.equalsIgnoreCase("stub")){
                return false;
            }
        }
        return true;
    }

}