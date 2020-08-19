package ae.gov.sdg.paperless.platform.common.model.mpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author swetabh raj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"serviceCode"})
public class DubaiNowSubscriptionRequest {

    @JsonProperty("serviceCode")
    private String serviceCode;

    public DubaiNowSubscriptionRequest(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public DubaiNowSubscriptionRequest() {
    }

    @JsonProperty("serviceCode")
    public String getServiceCode() {
        return serviceCode;
    }

    @JsonProperty("serviceCode")
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }

}