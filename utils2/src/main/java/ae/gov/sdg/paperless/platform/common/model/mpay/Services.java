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
@JsonPropertyOrder({"quickPaymentEnabled", "serviceCode", "breakDownAllowed", "maximumAmount", "serviceOrder", "type",
    "arName", "down", "pushNotificationEnabled", "partialPaymentAllowed", "securePayment", "enName",
    "minimumAmount", "id", "accounts", "favoriteEnabled", "status", "multiVoucherPaymentEnabled"})
public class Services {

    @JsonProperty("quickPaymentEnabled")
    private String quickPaymentEnabled;

    @JsonProperty("serviceCode")
    private String serviceCode;

    @JsonProperty("breakDownAllowed")
    private String breakDownAllowed;

    @JsonProperty("maximumAmount")
    private String maximumAmount;

    @JsonProperty("serviceOrder")
    private String serviceOrder;

    @JsonProperty("type")
    private String type;

    @JsonProperty("arName")
    private String arName;

    @JsonProperty("down")
    private String down;

    @JsonProperty("pushNotificationEnabled")
    private String pushNotificationEnabled;

    @JsonProperty("partialPaymentAllowed")
    private String partialPaymentAllowed;

    @JsonProperty("securePayment")
    private String securePayment;

    @JsonProperty("enName")
    private String enName;

    @JsonProperty("minimumAmount")
    private String minimumAmount;

    @JsonProperty("id")
    private String id;

    @JsonProperty("accounts")
    private List<Accounts> accounts;

    @JsonProperty("favoriteEnabled")
    private String favoriteEnabled;

    @JsonProperty("status")
    private String status;

    @JsonProperty("multiVoucherPaymentEnabled")
    private String multiVoucherPaymentEnabled;

    @JsonProperty("quickPaymentEnabled")
    public String getQuickPaymentEnabled() {
        return quickPaymentEnabled;
    }

    @JsonProperty("quickPaymentEnabled")
    public void setQuickPaymentEnabled(final String quickPaymentEnabled) {
        this.quickPaymentEnabled = quickPaymentEnabled;
    }

    @JsonProperty("serviceCode")
    public String getServiceCode() {
        return serviceCode;
    }

    @JsonProperty("serviceCode")
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @JsonProperty("breakDownAllowed")
    public String getBreakDownAllowed() {
        return breakDownAllowed;
    }

    @JsonProperty("breakDownAllowed")
    public void setBreakDownAllowed(final String breakDownAllowed) {
        this.breakDownAllowed = breakDownAllowed;
    }

    @JsonProperty("maximumAmount")
    public String getMaximumAmount() {
        return maximumAmount;
    }

    @JsonProperty("maximumAmount")
    public void setMaximumAmount(final String maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    @JsonProperty("serviceOrder")
    public String getServiceOrder() {
        return serviceOrder;
    }

    @JsonProperty("serviceOrder")
    public void setServiceOrder(final String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    @JsonProperty("arName")
    public String getArName() {
        return arName;
    }

    @JsonProperty("arName")
    public void setArName(final String arName) {
        this.arName = arName;
    }

    @JsonProperty("down")
    public String getDown() {
        return down;
    }

    @JsonProperty("down")
    public void setDown(final String down) {
        this.down = down;
    }

    @JsonProperty("pushNotificationEnabled")
    public String getPushNotificationEnabled() {
        return pushNotificationEnabled;
    }

    @JsonProperty("pushNotificationEnabled")
    public void setPushNotificationEnabled(final String pushNotificationEnabled) {
        this.pushNotificationEnabled = pushNotificationEnabled;
    }

    @JsonProperty("partialPaymentAllowed")
    public String getPartialPaymentAllowed() {
        return partialPaymentAllowed;
    }

    @JsonProperty("partialPaymentAllowed")
    public void setPartialPaymentAllowed(final String partialPaymentAllowed) {
        this.partialPaymentAllowed = partialPaymentAllowed;
    }

    @JsonProperty("securePayment")
    public String getSecurePayment() {
        return securePayment;
    }

    @JsonProperty("securePayment")
    public void setSecurePayment(final String securePayment) {
        this.securePayment = securePayment;
    }

    @JsonProperty("enName")
    public String getEnName() {
        return enName;
    }

    @JsonProperty("enName")
    public void setEnName(final String enName) {
        this.enName = enName;
    }

    @JsonProperty("minimumAmount")
    public String getMinimumAmount() {
        return minimumAmount;
    }

    @JsonProperty("minimumAmount")
    public void setMinimumAmount(final String minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }

    @JsonProperty("accounts")
    public List<Accounts> getAccounts() {
        return accounts;
    }

    @JsonProperty("accounts")
    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    @JsonProperty("favoriteEnabled")
    public String getFavoriteEnabled() {
        return favoriteEnabled;
    }

    @JsonProperty("favoriteEnabled")
    public void setFavoriteEnabled(final String favoriteEnabled) {
        this.favoriteEnabled = favoriteEnabled;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    @JsonProperty("multiVoucherPaymentEnabled")
    public String getMultiVoucherPaymentEnabled() {
        return multiVoucherPaymentEnabled;
    }

    @JsonProperty("multiVoucherPaymentEnabled")
    public void setMultiVoucherPaymentEnabled(final String multiVoucherPaymentEnabled) {
        this.multiVoucherPaymentEnabled = multiVoucherPaymentEnabled;
    }

}
