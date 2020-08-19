
package ae.gov.sdg.paperless.platform.common.model.components;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ae.gov.sdg.paperless.platform.common.model.components.types.ScreenModeType;
import ae.gov.sdg.paperless.platform.common.model.components.types.ScreenType;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Screen implements Serializable {

    private static final long serialVersionUID = 680247028535193400L;
    
    @JsonProperty("processId")
    private String processId;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("mode")
    private ScreenModeType mode;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("type")
    private ScreenType type;
    
    @JsonProperty("components")
    private List<Component> components = null;
    
    @JsonProperty("menu")
    private List<Component> menu = null;
    
    @JsonProperty("backParams")
    private Object backParams;

    @JsonProperty("properties")
    private Object properties;

    @JsonProperty("actions")
    private List<Component> actions = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
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

    public void setType(final ScreenType type) {
        this.type = type;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(final List<Component> components) {
        this.components = components;
    }
    
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(final String processId) {
        this.processId = processId;
    }

    public ScreenModeType getMode() {
        return mode;
    }

    public void setMode(final ScreenModeType mode) {
        this.mode = mode;
    }

    public List<Component> getMenu() {
        return menu;
    }

    public void setMenu(final List<Component> menu) {
        this.menu = menu;
    }

    public Object getBackParams() {
        return backParams;
    }

    public void setBackParams(final Object backParams) {
        this.backParams = backParams;
    } 

    public Object getProperties() {
        return properties;
    }

    public void setProperties(final Object properties) {
        this.properties = properties;
    }

    public List<Component> getActions() {
        return actions;
    }

    public void setActions(final List<Component> actions) {
        this.actions = actions;
    }

}