package ae.gov.sdg.paperless.platform.common.model.components.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ae.gov.sdg.paperless.platform.common.model.components.data.DataListComponent;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormComponent extends DataListComponent {

    private static final long serialVersionUID = 1714465574511589581L;

    @JsonProperty("placeholder")
    private String placeholder;

    @JsonProperty("required")
    private Boolean required;
    
    @JsonProperty("readOnly")
    private Boolean readOnly;
    
    @JsonProperty("validRegEx")
    private String validRegEx;
    
    @JsonProperty("validMsg")
    private String validMsg;
    
    @JsonProperty("format")
    private String format;
    
    @JsonProperty("minValue")
    private Integer minValue;
    
    @JsonProperty("maxValue")
    private Integer maxValue;
    
    @JsonProperty("increment")
    private Integer increment;
    
    @JsonProperty("fromName")
    private String fromName;
    
    @JsonProperty("keyboardType")
    private String keyboardType;
    
    @JsonProperty("toName")
    private String toName;
    
    @JsonProperty("multiple")
    private Boolean multiple;
    
    @JsonProperty("options")
    private List<Option> options = null;
    
    @JsonProperty("swapValueWithTitle")
    private Boolean swapValueWithTitle;
    
    /*@JsonCreator
    public FormComponent(@JsonProperty("type") ComponentType type) {
        super(type);
    }*/
    
    @Override
	public String getPlaceholder() {
        return placeholder;
    }

    @Override
	public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(final Boolean required) {
        this.required = required;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(final Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getValidRegEx() {
        return validRegEx;
    }

    public void setValidRegEx(final String validRegEx) {
        this.validRegEx = validRegEx;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(final String validMsg) {
        this.validMsg = validMsg;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(final List<Option> options) {
        this.options = options;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(final Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(final Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(final Integer increment) {
        this.increment = increment;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(final String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(final String toName) {
        this.toName = toName;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(final Boolean multiple) {
        this.multiple = multiple;
    }

    public String getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardType(final String keyboardType) {
        this.keyboardType = keyboardType;
    }

	public Boolean getSwapValueWithTitle() {
		return swapValueWithTitle;
	}

	public void setSwapValueWithTitle(final Boolean swapValueWithTitle) {
		this.swapValueWithTitle = swapValueWithTitle;
	}
    
    

}