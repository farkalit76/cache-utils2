
package ae.gov.sdg.paperless.platform.common.model.components;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ae.gov.sdg.paperless.platform.common.model.components.data.DataListComponent;
import ae.gov.sdg.paperless.platform.common.model.components.data.DataObjectComponent;
import ae.gov.sdg.paperless.platform.common.model.components.form.FormComponent;
import ae.gov.sdg.paperless.platform.common.model.components.map.MapComponent;
import ae.gov.sdg.paperless.platform.common.model.components.types.ComponentType;
import ae.gov.sdg.paperless.platform.common.model.components.types.StateType;


/***
 * Base class for all components
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
// use the component type attribute as the marker for which class to initialize this gives us the flexibility of
// mapping different types to different subclasses of component
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value=Component.class, name="video"),
    @JsonSubTypes.Type(value=Component.class, name="html_text"),
    @JsonSubTypes.Type(value=Component.class, name="fork"),
    
    @JsonSubTypes.Type(value=Component.class, name="journey_intro"),
    @JsonSubTypes.Type(value=Component.class, name="journey_phases"),
    @JsonSubTypes.Type(value=Component.class, name="journey_phase"),
    @JsonSubTypes.Type(value=Component.class, name="confirm"),
    @JsonSubTypes.Type(value=Component.class, name="icon_button"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="link_button"),
    @JsonSubTypes.Type(value=Component.class, name="add_button"),
    @JsonSubTypes.Type(value=Component.class, name="action_buttons"),
    @JsonSubTypes.Type(value=Component.class, name="journey_steps"),
    @JsonSubTypes.Type(value=Component.class, name="journey_step"),
    @JsonSubTypes.Type(value=Component.class, name="location_picker"),
    @JsonSubTypes.Type(value=Component.class, name="notify"),
    @JsonSubTypes.Type(value=Component.class, name="image_detail_cell"),
    @JsonSubTypes.Type(value=Component.class, name="image_info_cell"),
    @JsonSubTypes.Type(value=Component.class, name="image_text"),
    @JsonSubTypes.Type(value=Component.class, name="terms_and_conditions"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="label_value"),
    @JsonSubTypes.Type(value=Component.class, name="center_label"),
    @JsonSubTypes.Type(value=Component.class, name="summary_label_value"),
    @JsonSubTypes.Type(value=Component.class, name="paragraph"),
    @JsonSubTypes.Type(value=Component.class, name="section_header"),
    @JsonSubTypes.Type(value=Component.class, name="action_paragraph"),
    @JsonSubTypes.Type(value=Component.class, name="floating_button"),
    @JsonSubTypes.Type(value=FormComponent.class, name="multiple_choice"),
   
    
    @JsonSubTypes.Type(value=DataListComponent.class, name="product_list"),
    @JsonSubTypes.Type(value=Component.class, name="descriptive_cell"),
    @JsonSubTypes.Type(value=Component.class, name="product_package"),
    @JsonSubTypes.Type(value=Component.class, name="form_listing"),
    @JsonSubTypes.Type(value=Component.class, name="pdf_document"),
    @JsonSubTypes.Type(value=Component.class, name="collapsible_header"),
    @JsonSubTypes.Type(value=Component.class, name="icon_details"),
    @JsonSubTypes.Type(value=Component.class, name="action_detail"),
    @JsonSubTypes.Type(value=Component.class, name="upload_image"),
    @JsonSubTypes.Type(value=Component.class, name="sticky_help"),
    @JsonSubTypes.Type(value=Component.class, name="powered_by"),
    @JsonSubTypes.Type(value=Component.class, name="extra_info"),
    @JsonSubTypes.Type(value=Component.class, name="whats_next"),
    @JsonSubTypes.Type(value=Component.class, name="bottom_sheet"),
    @JsonSubTypes.Type(value=Component.class, name="form_info"),
    @JsonSubTypes.Type(value=Component.class, name="single_line_label"),
    
    // form related fields
    @JsonSubTypes.Type(value=FormComponent.class, name="border_button"),
    @JsonSubTypes.Type(value=FormComponent.class, name="button"),
    @JsonSubTypes.Type(value=FormComponent.class, name="link_action_button"),
    @JsonSubTypes.Type(value=FormComponent.class, name="form_input"),
    @JsonSubTypes.Type(value=FormComponent.class, name="input_field"),
    @JsonSubTypes.Type(value=FormComponent.class, name="drop_down"),
    @JsonSubTypes.Type(value=FormComponent.class, name="toggle"),
    @JsonSubTypes.Type(value=FormComponent.class, name="date_picker"),
    @JsonSubTypes.Type(value=FormComponent.class, name="radio_group"),
    @JsonSubTypes.Type(value=FormComponent.class, name="submit"),
    @JsonSubTypes.Type(value=FormComponent.class, name="form_text"),
    @JsonSubTypes.Type(value=FormComponent.class, name="options_picker"),
    @JsonSubTypes.Type(value=FormComponent.class, name="options_detail_picker"),
    @JsonSubTypes.Type(value=FormComponent.class, name="range_picker"),
    @JsonSubTypes.Type(value=FormComponent.class, name="number_selector"),
    @JsonSubTypes.Type(value=FormComponent.class, name="options_selecter"),
    @JsonSubTypes.Type(value=FormComponent.class, name="color_selecter"),
    @JsonSubTypes.Type(value=FormComponent.class, name="location_selector"),
    @JsonSubTypes.Type(value=FormComponent.class, name="checkbox"),
    @JsonSubTypes.Type(value=FormComponent.class, name="visual_option_selector"),
    @JsonSubTypes.Type(value=FormComponent.class, name="options_group"),
    @JsonSubTypes.Type(value=FormComponent.class, name="carousel"),
    
    // data specific components
    @JsonSubTypes.Type(value=DataListComponent.class, name="data_listing"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="grid_listing"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="listing"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="selectable_group"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="gallery"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="gallery_small"),
    @JsonSubTypes.Type(value=DataListComponent.class, name="payment"),
    
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="vehicle_summary"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="vehicle_plate_info"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="document_signing"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="document"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="sticky_call_to_action"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="product_list"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="icon_text"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="detail_view"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="details_view"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="selectable"),
    @JsonSubTypes.Type(value=DataObjectComponent.class, name="text_top_bottom"),
    @JsonSubTypes.Type(value=MapComponent.class, name="map")
})
public class Component implements Serializable {

    private static final long serialVersionUID = 1814826345954674438L;

    @JsonTypeId
    @JsonProperty("type")
    private ComponentType type;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("value")
    private Object value;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("detail")
    private String detail;
    
    @JsonProperty("titleOb")
    private Object titleOb;
    
    @JsonProperty("detailOb")
    private Object detailOb;

    @JsonProperty("detail2Ob")
    private Object detail2Ob;

    @JsonProperty("subDetail")
    private String subDetail;
    
    @JsonProperty("subDetailOb")
    private Object subDetailOb;
    
    @JsonProperty("state")
    private StateType state;
    
    @JsonProperty("url")
    private String url;
    
    // if the component has action = true it will submit to the server
    @JsonProperty("action")
    private Boolean action;
    
    @JsonProperty("actionText")
    private String actionText;
    
    @JsonProperty("actionTextOb")
    private Object actionTextOb;
    
    @JsonProperty("icon")
    private String icon;
    
    @JsonProperty("collapsible")
    private Boolean collapsible;
    
    @JsonProperty("sticky")
    private Boolean sticky; 
    
    @JsonProperty("info")
    private List<Info> info = null;
    
    @JsonProperty("tooltip")
    private Info tooltip;
    
    @JsonProperty("components")
    private List<Component> components = null;
    
    // actions are also components, but they to the server
    @JsonProperty("actions")
    private List<Component> actions = null;
    
    @JsonProperty("dependencies")
    private List<Object> dependencies;
      
    @JsonProperty("documents")
    private List<Object> documents = null;

    @JsonProperty("isHorizantal")
    private Boolean isHorizantal; 
    
    @JsonProperty("shadow")
    private Boolean shadow;

    @JsonProperty("properties")
    private Object properties;
    
    @JsonProperty("alternateTitle")
    private String alternateTitle;

    @JsonProperty("componentBehavior")
    private String componentBehavior;
    
    @JsonProperty("showDivider")
    private String showDivider;

    @JsonProperty("actionType")
    private String actionType;

    /*@JsonCreator
    public Component(@JsonProperty("type") ComponentType type) {
        super();
        this.type = type;
    }*/

    public ComponentType getType() {
        return type;
    }

    public void setType(final ComponentType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }

    public Object getTitleOb() {
		return titleOb;
	}

	public void setTitleOb(final Object titleOb) {
		this.titleOb = titleOb;
	}

	public Object getDetailOb() {
		return detailOb;
	}

	public void setDetailOb(final Object detailOb) {
		this.detailOb = detailOb;
	}

	public Boolean getAction() {
        return action;
    }

    public void setAction(final Boolean action) {
        this.action = action;
    }

    public StateType getState() {
        return state;
    }

    public void setState(final StateType state) {
        this.state = state;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(final List<Info> info) {
        this.info = info;
    }

    public String getActionText() {
        return actionText;
    }

    public void setActionText(final String actionText) {
        this.actionText = actionText;
    }
    
    public Object getActionTextOb() {
		return actionTextOb;
	}

	public void setActionTextOb(final Object actionTextOb) {
		this.actionTextOb = actionTextOb;
	}

	public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(final List<Component> components) {
        this.components = components;
    }
    
    public List<Component> getActions() {
        return actions;
    }

    public void setActions(final List<Component> actions) {
        this.actions = actions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setCollapsible(final Boolean collapsible) {
        this.collapsible = collapsible;
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(final List<Object> documents) {
        this.documents = documents;
    }

    public Boolean getCollapsible() {
        return collapsible;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(final Boolean sticky) {
        this.sticky = sticky;
    }

    public List<Object> getDependencies() {
        return dependencies;
    }

    public void setDependencies(final List<Object> dependencies) {
        this.dependencies = dependencies;
    }

    public Info getTooltip() {
        return tooltip;
    }

    public void setTooltip(final Info tooltip) {
        this.tooltip = tooltip;
    }

    public Boolean getIsHorizantal() {
        return isHorizantal;
    }

    public void setIsHorizantal(final Boolean isHorizantal) {
        this.isHorizantal = isHorizantal;
    }

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(final Boolean shadow) {
        this.shadow = shadow;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(final Object properties) {
        this.properties = properties;
    }

    public String getSubDetail() {
        return subDetail;
    }

    public void setSubDetail(final String subDetail) {
        this.subDetail = subDetail;
    }
    
    public Object getSubDetailOb() {
		return subDetailOb;
	}

	public void setSubDetailOb(final Object subDetailOb) {
		this.subDetailOb = subDetailOb;
	}

	public String getAlternateTitle() {
        return alternateTitle;
    }

    public void setAlternateTitle(final String alternateTitle) {
        this.alternateTitle = alternateTitle;
    }

    public String getComponentBehavior() {
        return componentBehavior;
    }

    public void setComponentBehavior(final String componentBehavior) {
        this.componentBehavior = componentBehavior;
    }

	public String getShowDivider() {
		return showDivider;
	}

	public void setShowDivider(final String showDivider) {
		this.showDivider = showDivider;
	}

    public Object getDetail2Ob() {
        return detail2Ob;
    }

    public void setDetail2Ob(final Object detail2Ob) {
        this.detail2Ob = detail2Ob;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(final String actionType) {
        this.actionType = actionType;
    }


}