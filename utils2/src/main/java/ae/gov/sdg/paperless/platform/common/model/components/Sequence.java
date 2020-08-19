/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import ae.gov.sdg.paperless.platform.common.model.components.types.ScreenType;
import ae.gov.sdg.paperless.platform.util.JsonUtil;

/**
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sequence implements Serializable {

    private static final long serialVersionUID = 3977137711511773696L;

    @JsonProperty("processId")
    private String processId;
    
    @JsonProperty("taskId")
    private String taskId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("backToExit")
    private Boolean backToExit;
    
    @JsonProperty("backState")
    private String backState;
    
    @JsonProperty("type")
    private final ScreenType type = ScreenType.sequence;
    
    @JsonProperty("screens")
    private List<Screen> screens = null;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(final String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
		return taskId;
	}

	public void setTaskId(final String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ScreenType getType() {
        return type;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(final List<Screen> screens) {
        this.screens = screens;
    }    

    public Boolean getBackToExit() {
        return backToExit;
    }

    public void setBackToExit(final Boolean backToExit) {
        this.backToExit = backToExit;
    }
    
    public String getBackState() {
		return backState;
	}

	public void setBackState(final String backState) {
		this.backState = backState;
	}

	public String toJsonString() throws JsonProcessingException {
        return JsonUtil.toJson(this, true);
    }
}
