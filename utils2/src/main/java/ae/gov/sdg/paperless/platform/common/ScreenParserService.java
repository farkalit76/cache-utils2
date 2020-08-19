package ae.gov.sdg.paperless.platform.common;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.COMMON;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEY_INTRO_JSON_SUFFIX;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEY_INTRO_TEMPLATE_JSON_SUFFIX;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JSON_PREFIX;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.LANG;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.SCREEN_TEMPLATE;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.TEMPLATE_PREFIX;
import static ae.gov.sdg.paperless.platform.util.CommonUtil.journeyOrDirectoryName;
import static java.util.Optional.ofNullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ae.gov.sdg.paperless.platform.common.model.components.Sequence;
import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;
import ae.gov.sdg.paperless.platform.tracing.TraceLog;
import ae.gov.sdg.paperless.platform.util.JsonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * @author c_chandra.bommise
 *
 * Populate the screen based on journey and screen.
 *
 */
@Service
public class ScreenParserService extends ScreenParserBaseService implements IScreenService {

	private final Logger log = LoggerFactory.getLogger(ScreenParserService.class);

    private Configuration freeMarkerConfig;

    private final Map<String, Template> templatesCache = new HashMap<>();
    
    private PaperlessResourceLoader resourceLoader;
    
    public ScreenParserService(PaperlessResourceLoader resourceLoader) {
		super(resourceLoader);
		this.resourceLoader = resourceLoader;
	}

    @Override
	public <T> String getIntroScreen(final T journeyType, final LangType lang) throws IOException {
        // return the first screen of the journey
    	final String journeyName = journeyOrDirectoryName(journeyType);
        log.debug("Loading intro screen for journey: {}", journeyName);
        final Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(LANG, lang.name());
        return this.getAndPopulateScreens(journeyType.toString().toLowerCase() +
            JOURNEY_INTRO_TEMPLATE_JSON_SUFFIX, responseMap, lang, null, journeyType);
    }

    @Override
	public <T> String getStaticIntroScreen(final T journeyType, final LangType lang) throws IOException {
    	final String journeyName = journeyOrDirectoryName(journeyType);
        log.debug("Loading intro screen for journey: {}", journeyName);
        return this.loadScreenFromFile(journeyName.toLowerCase() + JOURNEY_INTRO_JSON_SUFFIX, journeyName);
    }


    @Override
	@TraceLog
    public <T> String getAndPopulateScreens(final String filename, Map<String, Object> dictionary,
                                        LangType lang, final String processId, final T journeyType) throws IOException {

        String json;
        final String journeyName = journeyOrDirectoryName(journeyType);
        // to avoid processing every screen, the templated screen should be reflected on the
        // filename
        if (filename.endsWith(SCREEN_TEMPLATE)) {

            // unravel the json strings in the dictionary, these are likely passed
            // through from the Appian process as json

            // it's a template
            final StringWriter writer = new StringWriter();

            final Template template = this.getTemplate(filename, journeyName);

            return processTemplate(filename, dictionary, lang, processId, journeyName, writer, template);


        } else {

            log.warn("Should avoid using static json files and use Freemarker Templates instead, screen [{}]", filename);

            try (InputStream jsonStream = this.loadInputStreamFromFile(filename, false, journeyName);) {

                json = new BufferedReader(new InputStreamReader(jsonStream)).lines()
                    .parallel().collect(Collectors.joining("\n"));
            }

            final Sequence sequence = JsonUtil.fromJson(json, Sequence.class);
            sequence.setProcessId(processId);

            json = JsonUtil.toJson(sequence, false);
            log.debug("Parsed json: {}", json);
        }
        return json;
    }

    /**
     * Load the screen from the json file
     * @param filename
     * @return
     * @throws IOException
     */
    private String loadScreenFromFile(final String filename, final String journeyName) throws IOException {

        String json = null;

        try (InputStream jsonStream = this.loadInputStreamFromFile(filename, false, journeyName);) {

            json = new BufferedReader(new InputStreamReader(jsonStream)).lines()
                .parallel().collect(Collectors.joining("\n"));
        }

        return json;
    }

    private InputStream loadInputStreamFromFile(final String filename, final boolean template, final String journeyName) throws IOException {

        log.debug("Loading file: {}{}", journeyName, filename);
        String path = resourceLoader.getScreenPath(filename, journeyName);
        
        InputStream is = null;
        if(journeyName!=null) {
	        is = this.getClass().getClassLoader().getResourceAsStream(
	            new StringBuilder("screens/").append(path).append(filename)
	                .append((template ? TEMPLATE_PREFIX : JSON_PREFIX)).toString());
        }
        
        is = ofNullable(is).orElseGet(() -> this.getClass().getClassLoader().getResourceAsStream(
                new StringBuilder("screens/").append(COMMON).append("/").append(filename)
                .append(template ? TEMPLATE_PREFIX : JSON_PREFIX).toString()));
        
        return is;
    }

    /**
     * Get freemarker templates for the screen either from cache or create
     * @param filename
     * @param name
     * @return
     * @throws IOException
     */
    private Template getTemplate(final String filename, final String name) throws IOException {

        // get it from the cache
        Template template = templatesCache.get(filename);

        if (template == null) {
            // create it and cache it
            try (Reader templateReader = new InputStreamReader(
                loadInputStreamFromFile(filename, true, name), StandardCharsets.UTF_8)) {

                template = new Template(filename, templateReader, freeMarkerConfig);

                templatesCache.put(filename, template);
            }

        } else {
            log.debug("Serving template from cache for screen: {}", filename);
        }

        return template;
    }
}
