/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ae.gov.sdg.paperless.platform.common.model.components.Component;
import ae.gov.sdg.paperless.platform.common.model.components.types.DataType;


/**
 * Generic Data object component
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataObjectComponent extends Component {

    private static final long serialVersionUID = 7858933380239546467L;
    
    @JsonProperty("dataType")
    private DataType dataType;
    
    @JsonProperty("data")
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(final DataType dataType) {
        this.dataType = dataType;
    }

}