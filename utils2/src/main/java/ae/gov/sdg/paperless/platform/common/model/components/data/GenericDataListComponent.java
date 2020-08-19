/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ae.gov.sdg.paperless.platform.common.model.components.Component;
import ae.gov.sdg.paperless.platform.common.model.components.types.DataSearch;
import ae.gov.sdg.paperless.platform.common.model.components.types.DataType;

/**
 * The base for Data components. Components that rely on 
 * external data sources for its rendering
 * @author omerio
 *
 */
public class GenericDataListComponent <T> extends Component {
    
    private static final long serialVersionUID = 3152382149593175847L;
    
    @JsonProperty("dataType")
    private DataType dataType;
    
    @JsonProperty("dataUrl")
    private String dataUrl;
    
    @JsonProperty("dataPaging")
    private Boolean dataPaging;
    
    @JsonProperty("dataSearch")
    private DataSearch dataSearch;
        
    @JsonProperty("dataFilters")
    private List<Component> dataFilters;
    
    @JsonProperty("filterBy")
    private List<String> filterBy;
    
    @JsonProperty("params")
    private List<Object> params;
    
    @JsonProperty("data")
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(final DataType dataType) {
        this.dataType = dataType;
    }

    public List<Component> getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(final List<Component> dataFilters) {
        this.dataFilters = dataFilters;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(final String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public Boolean getDataPaging() {
        return dataPaging;
    }

    public void setDataPaging(final Boolean dataPaging) {
        this.dataPaging = dataPaging;
    }

    public DataSearch getDataSearch() {
        return dataSearch;
    }

    public void setDataSearch(final DataSearch dataSearch) {
        this.dataSearch = dataSearch;
    }

	public List<String> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(final List<String> filterBy) {
		this.filterBy = filterBy;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(final List<Object> params) {
		this.params = params;
	}
    
    

}
