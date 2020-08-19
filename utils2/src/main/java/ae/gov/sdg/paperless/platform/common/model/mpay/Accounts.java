package ae.gov.sdg.paperless.platform.common.model.mpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author swetabh raj
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"balance", "subscriptionType", "nickname", "id", "inquiryList", "favorite",
    "pushNotificationEnabled", "accountIdentifiers", "definedTopUpAmount"})
public class Accounts {

    @JsonProperty("balance")
    private String balance;

    @JsonProperty("subscriptionType")
    private String subscriptionType;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("id")
    private String id;

    @JsonProperty("inquiryList")
    private InquiryList inquiryList;

    @JsonProperty("favorite")
    private String favorite;

    @JsonProperty("pushNotificationEnabled")
    private String pushNotificationEnabled;

    @JsonProperty("accountIdentifiers")
    private AccountIdentifiers accountIdentifiers;

    @JsonProperty("definedTopUpAmount")
    private String definedTopUpAmount;

    @JsonProperty("balance")
    public String getBalance() {
        return balance;
    }

    @JsonProperty("balance")
    public void setBalance(final String balance) {
        this.balance = balance;
    }

    @JsonProperty("subscriptionType")
    public String getSubscriptionType() {
        return subscriptionType;
    }

    @JsonProperty("subscriptionType")
    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }

    @JsonProperty("inquiryList")
    public InquiryList getInquiryList() {
        return inquiryList;
    }

    @JsonProperty("inquiryList")
    public void setInquiryList(final InquiryList inquiryList) {
        this.inquiryList = inquiryList;
    }

    @JsonProperty("favorite")
    public String getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(final String favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("pushNotificationEnabled")
    public String getPushNotificationEnabled() {
        return pushNotificationEnabled;
    }

    @JsonProperty("pushNotificationEnabled")
    public void setPushNotificationEnabled(final String pushNotificationEnabled) {
        this.pushNotificationEnabled = pushNotificationEnabled;
    }

    @JsonProperty("accountIdentifiers")
    public AccountIdentifiers getAccountIdentifiers() {
        return accountIdentifiers;
    }

    @JsonProperty("accountIdentifiers")
    public void setAccountIdentifiers(final AccountIdentifiers accountIdentifiers) {
        this.accountIdentifiers = accountIdentifiers;
    }

    @JsonProperty("definedTopUpAmount")
    public String getDefinedTopUpAmount() {
        return definedTopUpAmount;
    }

    @JsonProperty("definedTopUpAmount")
    public void setDefinedTopUpAmount(final String definedTopUpAmount) {
        this.definedTopUpAmount = definedTopUpAmount;
    }

}
