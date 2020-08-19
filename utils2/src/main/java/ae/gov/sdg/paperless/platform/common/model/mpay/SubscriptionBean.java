package ae.gov.sdg.paperless.platform.common.model.mpay;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionBean {

	private String accountNickName;
    private String accountDesc;
    private Set<ServiceClaimBean> customerServiceClaims;
    private AutoRechargeBean autoRechargeInfo;

    //added by saddam
    private boolean favorite = true;
    private boolean pushNotificationEnabled = true;
    private String serviceCode;
    private String oldNickName;

    public SubscriptionBean(String serviceCode, String accountNickName) {
        this.serviceCode = serviceCode;
        this.accountNickName = accountNickName;
    }

    public SubscriptionBean() {
    }

    public String getAccountNickName() {
        return accountNickName;
    }

	public void setAccountNickName(final String accountNickName) {
        this.accountNickName = accountNickName;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

	public void setAccountDesc(final String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public Set<ServiceClaimBean> getCustomerServiceClaims() {
		if (customerServiceClaims == null) {
			customerServiceClaims = new HashSet<>();
		}
		return customerServiceClaims;
	}

	public void setCustomerServiceClaims(
			final Set<ServiceClaimBean> customerServiceClaims) {
		this.customerServiceClaims = customerServiceClaims;
	}

	public AutoRechargeBean getAutoRechargeInfo() {
		return autoRechargeInfo;
	}

	public void setAutoRechargeInfo(final AutoRechargeBean autoRechargeInfo) {
		this.autoRechargeInfo = autoRechargeInfo;
	}
	
	public String getClaimValue(final String claimName) {
		if (claimName == null || customerServiceClaims == null) {
			return null;
		}
		
		for (final ServiceClaimBean claim: customerServiceClaims) {
			if (claimName.equals(claim.getName())) {
				return claim.getValue();
			}
		}

		return null;
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



	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(final String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getOldNickName() {
		return oldNickName;
	}

	public void setOldNickName(final String oldNickName) {
		this.oldNickName = oldNickName;
	}

    @Override
    public String toString() {
        return "SubscriptionBean [accountNickName=" + accountNickName + ", accountDesc=" + accountDesc
                + ", customerServiceClaims=" + customerServiceClaims + ", autoRechargeInfo=" + autoRechargeInfo
                + ", favorite=" + favorite + ", pushNotificationEnabled=" + pushNotificationEnabled + ", serviceCode="
                + serviceCode + ", oldNickName=" + oldNickName + "]";
    }


}