package ae.gov.sdg.paperless.platform.common.service.generic;

import static com.google.common.io.Files.getNameWithoutExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import ae.gov.sdg.paperless.platform.exceptions.DNRuntimeException;
import ae.gov.sdg.paperless.platform.util.JsonUtil;

/**
 * @author c_chandra.bommise
 * <p>
 * Load the route configuration on server start up from routes path and based on route file name passed at runtime if not loaded already.
 */
@Configuration
public class RouteConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(RouteConfiguration.class);

    private static final String ROUTES_PATH = "classpath:routes/*";
    private static final String EXTENSION_JSON = ".json";
    private Map<String, Map<String, Object>> routes = new ConcurrentHashMap<>();

    /**
     * Populate the routes on server startup.
     */
    @PostConstruct
    public void init() {
        final ClassLoader cl = this.getClass().getClassLoader();
        final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        try {
            final Resource[] resources = resolver.getResources(ROUTES_PATH);

            Arrays.stream(resources)
                    .filter(r -> Objects.requireNonNull(r.getFilename()).endsWith(EXTENSION_JSON))
                    .forEach(this::loadResource);
        } catch (final IOException e) {
            throw new DNRuntimeException(e);
        }
    }

    private void loadResource(final Resource resource) {
        try {
            loadRoute(resource);
        } catch (final IOException e) {
            throw new DNRuntimeException(e);
        }
    }

    /**
     * Load route based on resource path.
     *
     * @param resource route file resource
     * @throws IOException
     */
    private void loadRoute(final Resource resource) throws IOException {
        if (routes.get(resource.getFilename()) == null) {
            synchronized (RouteConfiguration.class) {
            	LOG.info("loading routes for fileName: {}", resource.getFilename());
                final InputStream routeJson = resource.getInputStream();
                final Map<String, Object> classConfig = JsonUtil.fromJson(routeJson);
                routes.put(getNameWithoutExtension(Objects.requireNonNull(resource.getFilename())), classConfig);
            }
        }
    }

    /**
     * Load the route based on route file name.
     *
     * @param fileName name of route file
     * @throws IOException throws when no file found or any IO opertion fails
     */
    public void loadRoute(final String fileName) throws IOException {
        if (routes.get(fileName) == null) {
        	LOG.info("loading routes for fileName: {}", fileName);
            final ClassLoader cl = this.getClass().getClassLoader();
            final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            final Resource resource = resolver.getResource(ROUTES_PATH + fileName + EXTENSION_JSON);
            loadRoute(resource);
        }
    }

    public Map<String, Map<String, Object>> getRoutes() {
        return routes;
    }

}
