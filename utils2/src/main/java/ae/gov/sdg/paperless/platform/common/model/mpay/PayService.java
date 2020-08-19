package ae.gov.sdg.paperless.platform.common.model.mpay;

import static ae.gov.sdg.paperless.platform.util.BusinessUtil.formatAmount;
import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author swetabh raj
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayService {

    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_DOWN = "2";

    private String id;
    private String enName;
    private String arName;
    private String status;
    private List<Account> accounts;

    private String type;
    private Integer serviceOrder;

    private boolean multiVoucherPaymentEnabled;
    private boolean quickPaymentEnabled;

    private long minimumAmount;
    private long maximumAmount;
    private String serviceCode;

    private boolean partialPaymentAllowed;
    private boolean breakDownAllowed;

    private boolean pushNotificationEnabled;
    private boolean securePayment;
    private boolean favoriteEnabled;

    public PayService filterNonNullAccounts(PayService payService) {
        return filterNonNullAccounts(payService, false);
    }

    public PayService filterNonNullAccounts(PayService payService, boolean formatBalance) {
        List<Account> accounts = payService.getAccounts();
        if (isNotEmpty(accounts)) {
            if (formatBalance)
                this.accounts = accounts.stream().filter(Objects::nonNull).peek(account -> account.setBalance(parseDouble(formatAmount(account.getBalance())))).collect(toList());
            else
                this.accounts = accounts.stream().filter(Objects::nonNull).collect(toList());
        } else this.accounts = new ArrayList<>();
        return this;
    }

    public PayService() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean isDown() {
        return (getStatus() != null && getStatus().equals(STATUS_DOWN));
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the serviceOrder
     */
    public Integer getServiceOrder() {
        return serviceOrder;
    }

    /**
     * @param serviceOrder the serviceOrder to set
     */
    public void setServiceOrder(Integer serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    /**
     * @return the multiVoucherPaymentEnabled
     */
    public boolean isMultiVoucherPaymentEnabled() {
        return multiVoucherPaymentEnabled;
    }

    /**
     * @param multiVoucherPaymentEnabled the multiVoucherPaymentEnabled to set
     */
    public void setMultiVoucherPaymentEnabled(boolean multiVoucherPaymentEnabled) {
        this.multiVoucherPaymentEnabled = multiVoucherPaymentEnabled;
    }

    /**
     * @return the quickPaymentEnabled
     */
    public boolean isQuickPaymentEnabled() {
        return quickPaymentEnabled;
    }

    /**
     * @param quickPaymentEnabled the quickPaymentEnabled to set
     */
    public void setQuickPaymentEnabled(boolean quickPaymentEnabled) {
        this.quickPaymentEnabled = quickPaymentEnabled;
    }

    /**
     * @return the minimumAmount
     */
    public long getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * @param minimumAmount the minimumAmount to set
     */
    public void setMinimumAmount(long minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    /**
     * @return the maximumAmount
     */
    public long getMaximumAmount() {
        return maximumAmount;
    }

    /**
     * @param maximumAmount the maximumAmount to set
     */
    public void setMaximumAmount(long maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public boolean isPartialPaymentAllowed() {
        return partialPaymentAllowed;
    }

    public void setPartialPaymentAllowed(boolean partialPaymentAllowed) {
        this.partialPaymentAllowed = partialPaymentAllowed;
    }

    public boolean isBreakDownAllowed() {
        return breakDownAllowed;
    }

    public void setBreakDownAllowed(boolean breakDownAllowed) {
        this.breakDownAllowed = breakDownAllowed;
    }

    /**
     * @return the pushNotificationEnabled
     */
    public boolean isPushNotificationEnabled() {
        return pushNotificationEnabled;
    }

    /**
     * @param pushNotificationEnabled the pushNotificationEnabled to set
     */
    public void setPushNotificationEnabled(boolean pushNotificationEnabled) {
        this.pushNotificationEnabled = pushNotificationEnabled;
    }

    /**
     * @return the favoriteEnabled
     */
    public boolean isFavoriteEnabled() {
        return favoriteEnabled;
    }

    /**
     * @param favoriteEnabled the favoriteEnabled to set
     */
    public void setFavoriteEnabled(boolean favoriteEnabled) {
        this.favoriteEnabled = favoriteEnabled;
    }

    public boolean isSecurePayment() {
        return securePayment;
    }

    public void setSecurePayment(boolean securePayment) {
        this.securePayment = securePayment;
    }

    @Override
    public String toString() {
        return "PayService [id=" + id + ", enName=" + enName + ", arName=" + arName + ", status=" + status
            + ", accounts=" + accounts + ", type=" + type + ", serviceOrder=" + serviceOrder
            + ", multiVoucherPaymentEnabled=" + multiVoucherPaymentEnabled + ", quickPaymentEnabled="
            + quickPaymentEnabled + ", minimumAmount=" + minimumAmount + ", maximumAmount=" + maximumAmount
            + ", serviceCode=" + serviceCode + ", partialPaymentAllowed=" + partialPaymentAllowed
            + ", breakDownAllowed=" + breakDownAllowed + ", pushNotificationEnabled=" + pushNotificationEnabled
            + ", securePayment=" + securePayment + ", favoriteEnabled=" + favoriteEnabled + "]";
    }
   
}