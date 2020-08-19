package ae.gov.sdg.paperless.platform.common.model.mpay;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author swetabh raj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSubscribedEntityServiceAccountsReqBean {

	private String entityId;
    private String favorite;
    private String serviceCode;

    public GetSubscribedEntityServiceAccountsReqBean(final String entityId, final String favorite, final String serviceCode) {
        this.entityId = entityId;
        this.favorite = favorite;
        this.serviceCode = serviceCode;
    }

    public GetSubscribedEntityServiceAccountsReqBean(String entityId) {
        this(entityId, null);
    }

    public GetSubscribedEntityServiceAccountsReqBean(String entityId, String serviceCode) {
        this(entityId, null, serviceCode);
    }

    public GetSubscribedEntityServiceAccountsReqBean() {
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(final String entityId) {
        this.entityId = entityId;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(final String favorite) {
        this.favorite = favorite;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("entityId", entityId)
            .append("favorite", favorite)
            .append("serviceCode", serviceCode).toString();
    }

}