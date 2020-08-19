package ae.gov.sdg.paperless.platform.common.model.mpay;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author swetabh raj
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"arlogo", "enName", "id", "services", "arName", "enlogo"})
public class MPayAllSubscribedEntityAccountsResponse {

    @JsonProperty("arlogo")
    private String arlogo;

    @JsonProperty("enName")
    private String enName;

    @JsonProperty("id")
    private String id;

    @JsonProperty("services")
    private List<Services> services;

    @JsonProperty("arName")
    private String arName;

    @JsonProperty("enlogo")
    private String enlogo;

    @JsonProperty("arlogo")
    public String getArlogo() {
        return arlogo;
    }

    @JsonProperty("arlogo")
    public void setArlogo(final String arlogo) {
        this.arlogo = arlogo;
    }

    @JsonProperty("enName")
    public String getEnName() {
        return enName;
    }

    @JsonProperty("enName")
    public void setEnName(final String enName) {
        this.enName = enName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }

    @JsonProperty("services")
    public List<Services> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Services> services) {
        this.services = services;
    }

    @JsonProperty("arName")
    public String getArName() {
        return arName;
    }

    @JsonProperty("arName")
    public void setArName(final String arName) {
        this.arName = arName;
    }

    @JsonProperty("enlogo")
    public String getEnlogo() {
        return enlogo;
    }

    @JsonProperty("enlogo")
    public void setEnlogo(final String enlogo) {
        this.enlogo = enlogo;
    }

}
