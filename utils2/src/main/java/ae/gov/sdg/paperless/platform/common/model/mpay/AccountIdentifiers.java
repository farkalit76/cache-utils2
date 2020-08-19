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
@JsonPropertyOrder({ "accountIdentifier" })
public class AccountIdentifiers {

	@JsonProperty("accountIdentifier")
	private AccountIdentifier[] accountIdentifier;

	@JsonProperty("accountIdentifier")
	public AccountIdentifier[] getAccountIdentifier() {
		return accountIdentifier;
	}

	@JsonProperty("accountIdentifier")
	public void setAccountIdentifier(final AccountIdentifier[] accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getAccountValue(final String claimName) {
		if (claimName == null || accountIdentifier == null) {
			return null;
		}
		for (AccountIdentifier accountIdentifier : accountIdentifier) {
			if (claimName.equals(accountIdentifier.getName())) {
				return accountIdentifier.getValue();
			}
		}
		return null;
	}

}