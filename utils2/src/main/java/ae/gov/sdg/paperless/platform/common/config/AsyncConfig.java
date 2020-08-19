package ae.gov.sdg.paperless.platform.common.config;

import static org.slf4j.MDC.clear;
import static org.slf4j.MDC.getCopyOfContextMap;
import static org.slf4j.MDC.setContextMap;

import java.util.Map;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;

/**
 * @author c_chandra.bommise
 *
 * Asynchronous task executor configuration for events tracing.
 *
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfig.class);
    
    /**
     * Define task executor with MDC context decorator
     *
     * @return
     */
    @Bean(name = "taskExecutor")
    @Primary
    public Executor taskExecutor(PlatformConfig config) {
        LOGGER.info("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getAsyncCorePoolSize());
        executor.setMaxPoolSize(config.getAsyncMaxPoolSize());
        executor.setQueueCapacity(config.getAsyncQueueCapacity());
        executor.setThreadNamePrefix("ChildThread-");
        // Task decorator for populating MDC context for logging while async execution
        executor.setTaskDecorator(runnable -> {
            final Map<String, String> map = getCopyOfContextMap();
            return () -> {
                try {
                    setContextMap(map);
                    runnable.run();
                } finally {
                    clear();
                }
            };
        });
        executor.initialize();
        return executor;
    }
}

