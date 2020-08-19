/**
 * 
 */
package ae.gov.sdg.paperless.platform.common.model.components.map;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {
        "title": "Barakat Optical",
        "address": "Dubai Mall Al Doha St. - Dubai",
        "info": "1.5 km away",
        "name": "center",
        "value": "center1",
        "latitude": 25.2357189,
        "longitude": 55.2424443
    }
 * @author omerio
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location implements Serializable {

    private static final long serialVersionUID = -3928654795296357009L;

    @JsonProperty("title")
    private String title;
    @JsonProperty("address")
    private String address;
    @JsonProperty("info")
    private String info;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private Object value;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(final String address) {
        this.address = address;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(final String info) {
        this.info = info;
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
    
}