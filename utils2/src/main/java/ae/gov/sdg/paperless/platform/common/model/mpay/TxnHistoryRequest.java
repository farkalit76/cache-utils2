package ae.gov.sdg.paperless.platform.common.model.mpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnHistoryRequest {

    @JsonProperty
    private final String from;
    @JsonProperty
    private final String to;
    @JsonProperty
    private final int maxResult;

    public TxnHistoryRequest(String from, String to, int maxResult) {
        this.from = from;
        this.to = to;
        this.maxResult = maxResult;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getMaxResult() {
        return maxResult;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
        return builder.append("from", from)
            .append("to", to)
            .append("maxResult", maxResult)
            .toString();
    }
}
