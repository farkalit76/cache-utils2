package ae.gov.sdg.paperless.platform.common.model.components;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "detail",
    "icon"
})
public class Info implements Serializable {

    private static final long serialVersionUID = -4610936298042425106L;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("detail")
    private String detail;

    @JsonProperty("icon")
    private String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }
}