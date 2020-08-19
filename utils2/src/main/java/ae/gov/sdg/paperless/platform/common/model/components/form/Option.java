package ae.gov.sdg.paperless.platform.common.model.components.form;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *   "value": "AD",
     "label": "Andorra",
     "icon": "icon_flag_ad"
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option implements Serializable {

    private static final long serialVersionUID = -50210017926793544L;

    @JsonProperty("label")
    private String label;
    
    @JsonProperty("value")
    private String value;
    
    @JsonProperty("icon")
    private String icon;
    
    @JsonProperty("params")
    private Object params;
    
    @JsonProperty("valueOfParent")
    private String valueOfParent;
    
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(final Object params) {
        this.params = params;
    }

	public String getValueOfParent() {
		return valueOfParent;
	}

	public void setValueOfParent(final String valueOfParent) {
		this.valueOfParent = valueOfParent;
	}
    
    

}