package ae.gov.sdg.paperless.platform.common;

import java.io.IOException;
import java.util.Map;

import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;

/**
 * 
 * @author omerio
 *
 */
public interface IScreenService {

    <T> String getAndPopulateScreens(String filename, Map<String, Object> dictionary, 
            LangType lang, String processId, T journeyType) throws IOException;

    <T> String getIntroScreen(T journeyType, LangType lang) throws IOException;

    /**
     * The static screen in a json file
     * @param journeyType
     * @param lang
     * @return
     * @throws IOException
     */
    <T> String getStaticIntroScreen(T journeyType, LangType lang) throws IOException;

}
