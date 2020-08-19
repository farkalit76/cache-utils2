package ae.gov.sdg.paperless.platform.common.service.generic;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEYTYPE_LISTING;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import ae.gov.sdg.paperless.platform.common.model.IJourneyType;
import ae.gov.sdg.paperless.platform.common.model.components.Request;
import ae.gov.sdg.paperless.platform.exceptions.ServiceInstantiationException;
import ae.gov.sdg.paperless.platform.exceptions.UserAuthenticationFailedException;

/**
 * @author c_chandra.bommise
 * <p>
 * First invocation point for navigating to the service based on journey and screen.
 */
@Service
public class JourneyExecution implements IJourneyExecution {

    private static final Logger log = LoggerFactory.getLogger(JourneyExecution.class);

    private static final String ROUTE_CONFIG = "route-";
    private static final String INITIAL_SCREEN = "initialScreen";

    @Autowired
    private DubaiNowScreenUtil dubaiNowScreenUtil;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RouteConfiguration routeConfig;

    @Override
	public String execute(final JourneyExecutionContext context) throws IOException, UserAuthenticationFailedException {
        return routeFlow(context).process(context);
    }

    /**
     * Fetch the service to be invoked based on journey and screen name.
     *
     * @param context
     * @return
     * @throws ServiceInstantiationException
     */
    private IJourneyService routeFlow(final JourneyExecutionContext context) {
        final Request request = context.getRequest();
        final IJourneyType journey = request.getJourney();
        final String name = journey.name();
		log.info("Routing initiated for journey: {}", name);
		String routeFileName = getRouteFileName(request, journey);
        try {
            if (routeConfig.getRoutes().get(routeFileName) == null) {
                routeConfig.loadRoute(routeFileName);
            }
            final Map<String, Object> classConfig = routeConfig.getRoutes().get(routeFileName);
            String className = null;
            if(request.getParams()!=null && request.getParams().containsKey("refresh") && Boolean.valueOf(""+request.getParams().get("refresh"))) {
            	log.info("Refresh for screen: {}", request.getScreen());
            	className = (String) classConfig.get(request.getScreen());
            } else if (StringUtils.isNotEmpty(request.getScreen())) {
                log.info("Fetching the class name associated for screen: {}", request.getScreen());
                String fileName = dubaiNowScreenUtil.getScreenFilename(request);
                if(StringUtils.isEmpty(fileName) && classConfig.containsKey("generic-service-screen")) {
                	fileName = "generic-service-screen";
                }
                log.info("Fetching the classname associated for file: {}", fileName);
                className = (String) classConfig.get(fileName);
            } else {
                log.info("Fetching the class name for initial screen");
                className = (String) classConfig.get(INITIAL_SCREEN);
            }
            log.info("Service class name being loaded: {}", className);
            return loadEntity(className);
        } catch (ClassNotFoundException | IOException  e) {
        	throw new ServiceInstantiationException("Error occurred during service instantiation for journey: " + journey + "screen: " + request.getScreen(), e);
        }
    }

    /**
     * Load the entity.
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private IJourneyService loadEntity(final String className) throws ClassNotFoundException {
        final IJourneyService service = (IJourneyService) context.getBean(Class.forName(className));
        log.info("Service class name fetched from context: {}", service);
        return service;
    }
    
    /**
	 * @param request
	 * @param journey
	 * @return routeFileName
	 */
	private String getRouteFileName(final Request request, final IJourneyType journey) {
		log.info("getRouteFileName method initated for journey: {}", journey);
		String routeFileName = "";
        if(JOURNEYTYPE_LISTING.equals(journey.name())) {
        	log.info("routeFileName loading for entity: {}", request.getParams().get("entity"));
        	routeFileName = new StringBuilder(ROUTE_CONFIG).append(((String)request.getParams().get("entity")).toLowerCase()).toString();

        }else {
        	routeFileName = new StringBuilder(ROUTE_CONFIG).append(journey.name().toLowerCase()).toString();
        }
		return routeFileName;
	}

}
