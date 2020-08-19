package ae.gov.sdg.paperless.platform.common.model.mpay;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author swetabh raj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("rawtypes")
public class Account {

    private String id;
    private String nickname;
    private double balance;
	private HashMap inquiryList;
    private Long definedTopUpAmount;
    private String accountType;
    private String subscriptionType;

    private boolean favorite;
    private boolean pushNotificationEnabled;

    private AccountIdentifiers accountIdentifiers;

    @JsonIgnore
    private String display = "block";
    @JsonIgnore
    private String accountNumber;

    @JsonIgnore
    private double originalBalance;

    /**
     * need to implement as required
     */
    public Account() {
    	//need to implement as required
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    @JsonIgnore
    public String getBalanceFormatted() {
        return String.format("%1$,.2f", balance);
    }

    /**
     * @return long
     */
    public Long getDefinedTopUpAmount() {
        return definedTopUpAmount;
    }

    /**
     * @param definedTopUpAmount long
     */
    public void setDefinedTopUpAmount(final Long definedTopUpAmount) {
        this.definedTopUpAmount = definedTopUpAmount;
    }

    /**
     * @return the accountIdentifiers
     */
    public AccountIdentifiers getAccountIdentifiers() {
        return accountIdentifiers;
    }

    /**
     * @param accountIdentifiers the accountIdentifiers to set
     */
    public void setAccountIdentifiers(final AccountIdentifiers accountIdentifiers) {
        this.accountIdentifiers = accountIdentifiers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Account other = (Account) obj;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        return true;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(final boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isPushNotificationEnabled() {
        return pushNotificationEnabled;
    }

    public void setPushNotificationEnabled(final boolean pushNotificationEnabled) {
        this.pushNotificationEnabled = pushNotificationEnabled;
    }

    @JsonIgnore
    public String getDisplay() {
        return display;
    }

    @JsonIgnore
    public void setDisplay(final String display) {
        this.display = display;
    }

    @JsonIgnore
    public String getAccountNumber() {

        if (this.accountIdentifiers != null && this.accountIdentifiers.getAccountIdentifier() != null) {
            for (final AccountIdentifier accountIdentifier : this.accountIdentifiers.getAccountIdentifier()) {
                this.accountNumber = accountIdentifier.getValue();
                break;
            }
        }

        return accountNumber;
    }

    @JsonIgnore
    public String getPlateNumber() {

        String returnValue = "";
        if (this.accountIdentifiers != null && this.accountIdentifiers.getAccountIdentifier() != null) {
            for (final AccountIdentifier accountIdentifier : this.accountIdentifiers.getAccountIdentifier()) {
                if ("plateNumber".equalsIgnoreCase(accountIdentifier.getName())) {
                    returnValue = accountIdentifier.getValue();
                    break;
                }
            }
        }
        return returnValue;
    }

    @JsonIgnore
    public String getPlateCode() {

        String returnValue = "";
        if (this.accountIdentifiers != null && this.accountIdentifiers.getAccountIdentifier() != null) {
            for (final AccountIdentifier accountIdentifier : this.accountIdentifiers.getAccountIdentifier()) {
                if ("plateCode".equalsIgnoreCase(accountIdentifier.getName())) {
                    returnValue = accountIdentifier.getValue();
                    break;
                }
            }
        }
        return returnValue;
    }

    @JsonIgnore
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public HashMap getInquiryList() {
        return inquiryList;
    }

    public void setInquiryList(final HashMap inquiryList) {
        this.inquiryList = inquiryList;
    }

    @JsonIgnore
    public double getOriginalBalance() {
        return originalBalance;
    }

    @JsonIgnore
    public void setOriginalBalance(final double originalBalance) {
        this.originalBalance = originalBalance;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

}