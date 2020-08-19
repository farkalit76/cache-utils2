package ae.gov.sdg.paperless.platform.common.model.mpay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author swetabh raj
 *
 */
public class AutoRechargeBean {
	
	private Date startDate;
	private Date endDate;
	private String category; // type (time OR Threshold)
	private String subCategory; // daily,weekly,monthly >> autoRechargeParam
	private BigDecimal amount; // Autorecharge amount
	private BigDecimal maxAmount; //Maximum payment amount
	private BigDecimal balance; //Balance greater than
	private String triggerType; 
	private String triggerValue;
	private boolean forEver;
	
	public AutoRechargeBean() {}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(final String subCategory) {
		this.subCategory = subCategory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(final BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(final BigDecimal balance) {
		this.balance = balance;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(final String triggerType) {
		this.triggerType = triggerType;
	}

	public String getTriggerValue() {
		return triggerValue;
	}

	public void setTriggerValue(final String triggerValue) {
		this.triggerValue = triggerValue;
	}

	public boolean isForEver() {
		return forEver;
	}

	public void setForEver(final boolean forEver) {
		this.forEver = forEver;
	}
}
