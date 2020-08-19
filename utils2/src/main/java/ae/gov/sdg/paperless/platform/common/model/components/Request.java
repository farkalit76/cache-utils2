package ae.gov.sdg.paperless.platform.common.model.components;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import ae.gov.sdg.paperless.platform.common.model.IJourneyType;
import ae.gov.sdg.paperless.platform.common.model.components.types.LangType;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request implements Serializable {

    private static final long serialVersionUID = 1717942839072883019L;
    
    @ApiModelProperty(
            value = "The process id if progressing an existing process",
            example = "123456",
            required = false)
    @JsonProperty("processId")
    private String processId;

    @ApiModelProperty(
            value = "The current journey",
            example = "DPTrafficFines",
            notes = "The current journey identifier",
            allowableValues = "DPTrafficFines,YallaCompare",
            required = true)
    @NotNull
    @JsonProperty("journey")
    private IJourneyType journey;
    
    @ApiModelProperty(
            value = "The current screen handled by the user",
            example = "StartJourneyScreen",
            required = false)
    @JsonProperty("sequence")
    private String screen;
    
    @ApiModelProperty(
            value = "The current component handled by the user",
            example = "DrivingLicenseChoice",
            required = false)
    @JsonProperty("component")
    private String component;
    
    @ApiModelProperty(
            value = "The language",
            example = "en",
            notes = "The customer chosen language",
            allowableValues = "ar,en",
            required = false)
    @NotNull
    @JsonProperty("lang")
    private LangType lang;
    
    @ApiModelProperty(
            value = "Dictionary of posted parameters",
            notes = "If the user clicks on a component with action=true then this will contain the "
                    + "name/value of the component. If this is from a form submit then it will include"
                    + "all the form parameter names and values", 
            required = false)
    @JsonProperty("params")
    private Map<String, Object> params;
    
    public IJourneyType getJourney() {
        return journey;
    }

    public void setJourney(final IJourneyType journey) {
        this.journey = journey;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(final String screen) {
        this.screen = screen;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(final String component) {
        this.component = component;
    }

    public LangType getLang() {
        return lang;
    }

    public void setLang(final LangType lang) {
        this.lang = lang;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(final Map<String, Object> params) {
        this.params = params;
    }
    
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(final String processId) {
        this.processId = processId;
    }
    
    public String toJsonString() throws JsonProcessingException {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
    /**
     * Check if this is an initial request to start the journey
     * @return
     */
    @JsonIgnore
    public boolean isInitial() {
        return this.getJourney() != null && 
                this.lang != null && 
                this.component == null && 
        		// This #initiateMicroService check has been added because we need to open a micro-service from another
                // and we only require journey and lang parameters to load initial screen but mobile is also sending some extra params.
                // Due to this, this extra param check has been added. Once mobile team removes the extra params coming in this scenario, 
                //we can remove the below or condition.
               (this.params == null || (!this.params.isEmpty() && this.params.containsKey("#initiateMicroService"))) &&
                this.processId == null && 
                this.screen == null;
    }

    @Override
    public String toString() {
    	return new ToStringBuilder(this).append("processId()", getProcessId()).append("journey", getJourney())
				.append("screen()", getScreen()).append("component()", getComponent())
				.append("lang()", getLang()).append("params()", getParams()).build();
    }


}