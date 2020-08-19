/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components.map;

import com.fasterxml.jackson.annotation.JsonInclude;

import ae.gov.sdg.paperless.platform.common.model.components.data.GenericDataListComponent;


/**
 * TODO likely to change from Sprint 3 to use a data-url
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapComponent extends GenericDataListComponent<Location> {

    private static final long serialVersionUID = 5301624209058677007L;
    
}
