package ae.gov.sdg.paperless.platform.common.model;

import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"residencyExpiryDate",
	"residencyType",
	"companyNameAR",
	"homeAddressTypeCode",
	"homeAddressAreaDescriptionAR",
	"occupationFieldCode",
	"homeAddressAreaDescriptionEN",
	"userType",
	"companyNameEN",
	"homeAddressEmirateCode",
	"passportExpiryDate",
	"gender",
	"nationalityAR",
	"firstnameEN",
	"homeAddressEmirateDescriptionEN",
	"occupationTypeAR",
	"email",
	"firstnameAR",
	"nationalityEN",
	"passportIssueDate",
	"homeAddressCityDescriptionAR",
	"uuid",
	"idCardExpiryDate",
	"amr",
	"mobile",
	"occupationTypeEN",
	"homeAddressAreaCode",
	"idCardNumber",
	"idType",
	"sub",
	"homeAddressPOBox",
	"cardHolderSignatureImage",
	"passportNumber",
	"maritalStatus",
	"homeAddressEmail",
	"idCardIssueDate",
	"fullnameAR",
	"fullnameEN",
	"domain",
	"homeAddressEmirateDescriptionAR",
	"lastnameEN",
	"occupationCode",
	"idn",
	"photo",
	"homeAddressCityCode",
	"passportCountryDescriptionEN",
	"passportCountryDescriptionAR",
	"homeAddressCityDescriptionEN",
	"homeAddressMobilePhoneNumber",
	"homeAddressStreetEN",
	"homeAddressStreetAR",
	"homeAddressBuildingNameEN",
	"homeAddressBuildingNameAR",
	"dob",
	"lastnameAR",
	"residencyNumber",
	"acr"
})
public class UserInfo {

	@JsonProperty("residencyExpiryDate")
	private String residencyExpiryDate;
	@JsonProperty("residencyType")
	private String residencyType;
	@JsonProperty("companyNameAR")
	private String companyNameAR;
	@JsonProperty("homeAddressTypeCode")
	private String homeAddressTypeCode;
	@JsonProperty("homeAddressAreaDescriptionAR")
	private String homeAddressAreaDescriptionAR;
	@JsonProperty("occupationFieldCode")
	private String occupationFieldCode;
	@JsonProperty("homeAddressAreaDescriptionEN")
	private String homeAddressAreaDescriptionEN;
	@JsonProperty("userType")
	private String userType;
	@JsonProperty("companyNameEN")
	private String companyNameEN;
	@JsonProperty("homeAddressEmirateCode")
	private String homeAddressEmirateCode;
	@JsonProperty("passportExpiryDate")
	private String passportExpiryDate;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("nationalityAR")
	private String nationalityAR;
	@JsonProperty("firstnameEN")
	private String firstnameEN;
	@JsonProperty("homeAddressEmirateDescriptionEN")
	private String homeAddressEmirateDescriptionEN;
	@JsonProperty("occupationTypeAR")
	private String occupationTypeAR;
	@JsonProperty("email")
	private String email;
	@JsonProperty("firstnameAR")
	private String firstnameAR;
	@JsonProperty("nationalityEN")
	private String nationalityEN;
	@JsonProperty("passportIssueDate")
	private String passportIssueDate;
	@JsonProperty("homeAddressCityDescriptionAR")
	private String homeAddressCityDescriptionAR;
	@JsonProperty("uuid")
	private String uuid;
	@JsonProperty("idCardExpiryDate")
	private String idCardExpiryDate;
	@JsonProperty("amr")
	private List<String> amr = null;
	@JsonProperty("mobile")
	private String mobile;
	@JsonProperty("occupationTypeEN")
	private String occupationTypeEN;
	@JsonProperty("homeAddressAreaCode")
	private String homeAddressAreaCode;
	@JsonProperty("idCardNumber")
	private String idCardNumber;
	@JsonProperty("idType")
	private String idType;
	@JsonProperty("sub")
	private String sub;
	@JsonProperty("homeAddressPOBox")
	private String homeAddressPOBox;
	@JsonProperty("cardHolderSignatureImage")
	private String cardHolderSignatureImage;
	@JsonProperty("passportNumber")
	private String passportNumber;
	@JsonProperty("maritalStatus")
	private String maritalStatus;
	@JsonProperty("homeAddressEmail")
	private String homeAddressEmail;
	@JsonProperty("idCardIssueDate")
	private String idCardIssueDate;
	@JsonProperty("fullnameAR")
	private String fullnameAR;
	@JsonProperty("fullnameEN")
	private String fullnameEN;
	@JsonProperty("domain")
	private String domain;
	@JsonProperty("homeAddressEmirateDescriptionAR")
	private String homeAddressEmirateDescriptionAR;
	@JsonProperty("lastnameEN")
	private String lastnameEN;
	@JsonProperty("occupationCode")
	private String occupationCode;
	@JsonProperty("idn")
	private String idn;
	@JsonProperty("photo")
	private String photo;
	@JsonProperty("homeAddressCityCode")
	private String homeAddressCityCode;
	@JsonProperty("passportCountryDescriptionEN")
	private String passportCountryDescriptionEN;
	@JsonProperty("passportCountryDescriptionAR")
	private String passportCountryDescriptionAR;
	@JsonProperty("homeAddressCityDescriptionEN")
	private String homeAddressCityDescriptionEN;
	@JsonProperty("homeAddressMobilePhoneNumber")
	private String homeAddressMobilePhoneNumber;

	@JsonProperty("homeAddressStreetEN")
	private String homeAddressStreetEN;
	@JsonProperty("homeAddressStreetAR")
	private String homeAddressStreetAR;
	@JsonProperty("homeAddressBuildingNameEN")
	private String homeAddressBuildingNameEN;
	@JsonProperty("homeAddressBuildingNameAR")
	private String homeAddressBuildingNameAR;

	@JsonProperty("dob")
	private String dob;
	@JsonProperty("lastnameAR")
	private String lastnameAR;
	@JsonProperty("residencyNumber")
	private String residencyNumber;
	@JsonProperty("acr")
	private String acr;

	@JsonProperty("residencyExpiryDate")
	public String getResidencyExpiryDate() {
		return residencyExpiryDate;
	}

	@JsonProperty("residencyExpiryDate")
	public void setResidencyExpiryDate(final String residencyExpiryDate) {
		this.residencyExpiryDate = residencyExpiryDate;
	}

	@JsonProperty("residencyType")
	public String getResidencyType() {
		return residencyType;
	}

	@JsonProperty("residencyType")
	public void setResidencyType(final String residencyType) {
		this.residencyType = residencyType;
	}

	@JsonProperty("companyNameAR")
	public String getCompanyNameAR() {
		return companyNameAR;
	}

	@JsonProperty("companyNameAR")
	public void setCompanyNameAR(final String companyNameAR) {
		this.companyNameAR = companyNameAR;
	}

	@JsonProperty("homeAddressTypeCode")
	public String getHomeAddressTypeCode() {
		return homeAddressTypeCode;
	}

	@JsonProperty("homeAddressTypeCode")
	public void setHomeAddressTypeCode(final String homeAddressTypeCode) {
		this.homeAddressTypeCode = homeAddressTypeCode;
	}

	@JsonProperty("homeAddressAreaDescriptionAR")
	public String getHomeAddressAreaDescriptionAR() {
		return homeAddressAreaDescriptionAR;
	}

	@JsonProperty("homeAddressAreaDescriptionAR")
	public void setHomeAddressAreaDescriptionAR(final String homeAddressAreaDescriptionAR) {
		this.homeAddressAreaDescriptionAR = homeAddressAreaDescriptionAR;
	}

	@JsonProperty("occupationFieldCode")
	public String getOccupationFieldCode() {
		return occupationFieldCode;
	}

	@JsonProperty("occupationFieldCode")
	public void setOccupationFieldCode(final String occupationFieldCode) {
		this.occupationFieldCode = occupationFieldCode;
	}

	@JsonProperty("homeAddressAreaDescriptionEN")
	public String getHomeAddressAreaDescriptionEN() {
		return homeAddressAreaDescriptionEN;
	}

	@JsonProperty("homeAddressAreaDescriptionEN")
	public void setHomeAddressAreaDescriptionEN(final String homeAddressAreaDescriptionEN) {
		this.homeAddressAreaDescriptionEN = homeAddressAreaDescriptionEN;
	}

	@JsonProperty("userType")
	public String getUserType() {
		return userType;
	}

	@JsonProperty("userType")
	public void setUserType(final String userType) {
		this.userType = userType;
	}

	@JsonProperty("companyNameEN")
	public String getCompanyNameEN() {
		return companyNameEN;
	}

	@JsonProperty("companyNameEN")
	public void setCompanyNameEN(final String companyNameEN) {
		this.companyNameEN = companyNameEN;
	}

	@JsonProperty("homeAddressEmirateCode")
	public String getHomeAddressEmirateCode() {
		return homeAddressEmirateCode;
	}

	@JsonProperty("homeAddressEmirateCode")
	public void setHomeAddressEmirateCode(final String homeAddressEmirateCode) {
		this.homeAddressEmirateCode = homeAddressEmirateCode;
	}

	@JsonProperty("passportExpiryDate")
	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	@JsonProperty("passportExpiryDate")
	public void setPassportExpiryDate(final String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(final String gender) {
		this.gender = gender;
	}

	@JsonProperty("nationalityAR")
	public String getNationalityAR() {
		return nationalityAR;
	}

	@JsonProperty("nationalityAR")
	public void setNationalityAR(final String nationalityAR) {
		this.nationalityAR = nationalityAR;
	}

	@JsonProperty("firstnameEN")
	public String getFirstnameEN() {
		return firstnameEN;
	}

	@JsonProperty("firstnameEN")
	public void setFirstnameEN(final String firstnameEN) {
		this.firstnameEN = firstnameEN;
	}

	@JsonProperty("homeAddressEmirateDescriptionEN")
	public String getHomeAddressEmirateDescriptionEN() {
		return homeAddressEmirateDescriptionEN;
	}

	@JsonProperty("homeAddressEmirateDescriptionEN")
	public void setHomeAddressEmirateDescriptionEN(final String homeAddressEmirateDescriptionEN) {
		this.homeAddressEmirateDescriptionEN = homeAddressEmirateDescriptionEN;
	}

	@JsonProperty("occupationTypeAR")
	public String getOccupationTypeAR() {
		return occupationTypeAR;
	}

	@JsonProperty("occupationTypeAR")
	public void setOccupationTypeAR(final String occupationTypeAR) {
		this.occupationTypeAR = occupationTypeAR;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(final String email) {
		this.email = email;
	}

	@JsonProperty("firstnameAR")
	public String getFirstnameAR() {
		return firstnameAR;
	}

	@JsonProperty("firstnameAR")
	public void setFirstnameAR(final String firstnameAR) {
		this.firstnameAR = firstnameAR;
	}

	@JsonProperty("nationalityEN")
	public String getNationalityEN() {
		return nationalityEN;
	}

	@JsonProperty("nationalityEN")
	public void setNationalityEN(final String nationalityEN) {
		this.nationalityEN = nationalityEN;
	}

	@JsonProperty("passportIssueDate")
	public String getPassportIssueDate() {
		return passportIssueDate;
	}

	@JsonProperty("passportIssueDate")
	public void setPassportIssueDate(final String passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}

	@JsonProperty("homeAddressCityDescriptionAR")
	public String getHomeAddressCityDescriptionAR() {
		return homeAddressCityDescriptionAR;
	}

	@JsonProperty("homeAddressCityDescriptionAR")
	public void setHomeAddressCityDescriptionAR(final String homeAddressCityDescriptionAR) {
		this.homeAddressCityDescriptionAR = homeAddressCityDescriptionAR;
	}

	@JsonProperty("uuid")
	public String getUuid() {
		return uuid;
	}

	@JsonProperty("uuid")
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	@JsonProperty("idCardExpiryDate")
	public String getIdCardExpiryDate() {
		return idCardExpiryDate;
	}

	@JsonProperty("idCardExpiryDate")
	public void setIdCardExpiryDate(final String idCardExpiryDate) {
		this.idCardExpiryDate = idCardExpiryDate;
	}

	@JsonProperty("amr")
	public List<String> getAmr() {
		return amr;
	}

	@JsonProperty("amr")
	public void setAmr(final List<String> amr) {
		this.amr = amr;
	}

	@JsonProperty("mobile")
	public String getMobile() {
		return mobile;
	}

	@JsonProperty("mobile")
	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	@JsonProperty("occupationTypeEN")
	public String getOccupationTypeEN() {
		return occupationTypeEN;
	}

	@JsonProperty("occupationTypeEN")
	public void setOccupationTypeEN(final String occupationTypeEN) {
		this.occupationTypeEN = occupationTypeEN;
	}

	@JsonProperty("homeAddressAreaCode")
	public String getHomeAddressAreaCode() {
		return homeAddressAreaCode;
	}

	@JsonProperty("homeAddressAreaCode")
	public void setHomeAddressAreaCode(final String homeAddressAreaCode) {
		this.homeAddressAreaCode = homeAddressAreaCode;
	}

	@JsonProperty("idCardNumber")
	public String getIdCardNumber() {
		return idCardNumber;
	}

	@JsonProperty("idCardNumber")
	public void setIdCardNumber(final String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	@JsonProperty("idType")
	public String getIdType() {
		return idType;
	}

	@JsonProperty("idType")
	public void setIdType(final String idType) {
		this.idType = idType;
	}

	@JsonProperty("sub")
	public String getSub() {
		return sub;
	}

	@JsonProperty("sub")
	public void setSub(final String sub) {
		this.sub = sub;
	}

	@JsonProperty("homeAddressPOBox")
	public String getHomeAddressPOBox() {
		return homeAddressPOBox;
	}

	@JsonProperty("homeAddressPOBox")
	public void setHomeAddressPOBox(final String homeAddressPOBox) {
		this.homeAddressPOBox = homeAddressPOBox;
	}

	@JsonProperty("cardHolderSignatureImage")
	public String getCardHolderSignatureImage() {
		return cardHolderSignatureImage;
	}

	@JsonProperty("cardHolderSignatureImage")
	public void setCardHolderSignatureImage(final String cardHolderSignatureImage) {
		this.cardHolderSignatureImage = cardHolderSignatureImage;
	}

	@JsonProperty("passportNumber")
	public String getPassportNumber() {
		return passportNumber;
	}

	@JsonProperty("passportNumber")
	public void setPassportNumber(final String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@JsonProperty("maritalStatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	@JsonProperty("maritalStatus")
	public void setMaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@JsonProperty("homeAddressEmail")
	public String getHomeAddressEmail() {
		return homeAddressEmail;
	}

	@JsonProperty("homeAddressEmail")
	public void setHomeAddressEmail(final String homeAddressEmail) {
		this.homeAddressEmail = homeAddressEmail;
	}

	@JsonProperty("idCardIssueDate")
	public String getIdCardIssueDate() {
		return idCardIssueDate;
	}

	@JsonProperty("idCardIssueDate")
	public void setIdCardIssueDate(final String idCardIssueDate) {
		this.idCardIssueDate = idCardIssueDate;
	}

	@JsonProperty("fullnameAR")
	public String getFullnameAR() {
		return fullnameAR;
	}

	@JsonProperty("fullnameAR")
	public void setFullnameAR(final String fullnameAR) {
		this.fullnameAR = fullnameAR;
	}

	@JsonProperty("fullnameEN")
	public String getFullnameEN() {
		return fullnameEN;
	}

	@JsonProperty("fullnameEN")
	public void setFullnameEN(final String fullnameEN) {
		this.fullnameEN = fullnameEN;
	}

	@JsonProperty("domain")
	public String getDomain() {
		return domain;
	}

	@JsonProperty("domain")
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	@JsonProperty("homeAddressEmirateDescriptionAR")
	public String getHomeAddressEmirateDescriptionAR() {
		return homeAddressEmirateDescriptionAR;
	}

	@JsonProperty("homeAddressEmirateDescriptionAR")
	public void setHomeAddressEmirateDescriptionAR(final String homeAddressEmirateDescriptionAR) {
		this.homeAddressEmirateDescriptionAR = homeAddressEmirateDescriptionAR;
	}

	@JsonProperty("lastnameEN")
	public String getLastnameEN() {
		return lastnameEN;
	}

	@JsonProperty("lastnameEN")
	public void setLastnameEN(final String lastnameEN) {
		this.lastnameEN = lastnameEN;
	}

	@JsonProperty("occupationCode")
	public String getOccupationCode() {
		return occupationCode;
	}

	@JsonProperty("occupationCode")
	public void setOccupationCode(final String occupationCode) {
		this.occupationCode = occupationCode;
	}

	@JsonProperty("idn")
	public String getIdn() {
		return idn;
	}

	@JsonProperty("idn")
	public void setIdn(final String idn) {
		this.idn = idn;
	}

	@JsonProperty("photo")
	public String getPhoto() {
		return photo;
	}

	@JsonProperty("photo")
	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@JsonProperty("homeAddressCityCode")
	public String getHomeAddressCityCode() {
		return homeAddressCityCode;
	}

	@JsonProperty("homeAddressCityCode")
	public void setHomeAddressCityCode(final String homeAddressCityCode) {
		this.homeAddressCityCode = homeAddressCityCode;
	}

	@JsonProperty("passportCountryDescriptionEN")
	public String getPassportCountryDescriptionEN() {
		return passportCountryDescriptionEN;
	}

	@JsonProperty("passportCountryDescriptionEN")
	public void setPassportCountryDescriptionEN(final String passportCountryDescriptionEN) {
		this.passportCountryDescriptionEN = passportCountryDescriptionEN;
	}

	@JsonProperty("passportCountryDescriptionAR")
	public String getPassportCountryDescriptionAR() {
		return passportCountryDescriptionAR;
	}

	@JsonProperty("passportCountryDescriptionAR")
	public void setPassportCountryDescriptionAR(final String passportCountryDescriptionAR) {
		this.passportCountryDescriptionAR = passportCountryDescriptionAR;
	}

	@JsonProperty("homeAddressCityDescriptionEN")
	public String getHomeAddressCityDescriptionEN() {
		return homeAddressCityDescriptionEN;
	}

	@JsonProperty("homeAddressCityDescriptionEN")
	public void setHomeAddressCityDescriptionEN(final String homeAddressCityDescriptionEN) {
		this.homeAddressCityDescriptionEN = homeAddressCityDescriptionEN;
	}

	@JsonProperty("homeAddressMobilePhoneNumber")
	public String getHomeAddressMobilePhoneNumber() {
		return homeAddressMobilePhoneNumber;
	}

	@JsonProperty("homeAddressMobilePhoneNumber")
	public void setHomeAddressMobilePhoneNumber(final String homeAddressMobilePhoneNumber) {
		this.homeAddressMobilePhoneNumber = homeAddressMobilePhoneNumber;
	}

	@JsonProperty("homeAddressStreetEN")
	public String getHomeAddressStreetEN() {
		return homeAddressStreetEN;
	}

	@JsonProperty("homeAddressStreetEN")
	public void setHomeAddressStreetEN(final String homeAddressStreetEN) {
		this.homeAddressStreetEN = homeAddressStreetEN;
	}

	@JsonProperty("homeAddressStreetAR")
	public String getHomeAddressStreetAR() {
		return homeAddressStreetAR;
	}

	@JsonProperty("homeAddressStreetAR")
	public void setHomeAddressStreetAR(final String homeAddressStreetAR) {
		this.homeAddressStreetAR = homeAddressStreetAR;
	}

	@JsonProperty("homeAddressBuildingNameEN")
	public String getHomeAddressBuildingNameEN() {
		return homeAddressBuildingNameEN;
	}

	@JsonProperty("homeAddressBuildingNameEN")
	public void setHomeAddressBuildingNameEN(final String homeAddressBuildingNameEN) {
		this.homeAddressBuildingNameEN = homeAddressBuildingNameEN;
	}

	@JsonProperty("homeAddressBuildingNameAR")
	public String getHomeAddressBuildingNameAR() {
		return homeAddressBuildingNameAR;
	}

	@JsonProperty("homeAddressBuildingNameAR")
	public void setHomeAddressBuildingNameAR(final String homeAddressBuildingNameAR) {
		this.homeAddressBuildingNameAR = homeAddressBuildingNameAR;
	}


	@JsonProperty("dob")
	public String getDob() {
		return dob;
	}

	@JsonProperty("dob")
	public void setDob(final String dob) {
		this.dob = dob;
	}

	@JsonProperty("lastnameAR")
	public String getLastnameAR() {
		return lastnameAR;
	}

	@JsonProperty("lastnameAR")
	public void setLastnameAR(final String lastnameAR) {
		this.lastnameAR = lastnameAR;
	}

	@JsonProperty("residencyNumber")
	public String getResidencyNumber() {
		return residencyNumber;
	}

	@JsonProperty("residencyNumber")
	public void setResidencyNumber(final String residencyNumber) {
		this.residencyNumber = residencyNumber;
	}

	@JsonProperty("acr")
	public String getAcr() {
		return acr;
	}

	@JsonProperty("acr")
	public void setAcr(final String acr) {
		this.acr = acr;
	}

	@JsonIgnore
	public boolean isAccountVerfied() {
		return ( userType != null && ("SOP2".equals(userType) || "SOP3".equals(userType)) );
	}

	@JsonIgnore
	public boolean isValidUserInfo() {
		return ( !StringUtils.isEmpty(this.email) && !StringUtils.isEmpty(this.mobile) && !StringUtils.isEmpty(this.uuid) );
	}

}
