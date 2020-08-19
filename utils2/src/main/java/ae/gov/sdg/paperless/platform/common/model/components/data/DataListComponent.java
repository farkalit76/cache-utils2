/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * TODO likely to change from Sprint 3 to use a data-url
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataListComponent extends GenericDataListComponent<Object> {

    private static final long serialVersionUID = 7858911180239546467L;
    
    @JsonProperty("placeholder")
    private String placeholder;

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }    
    

}
