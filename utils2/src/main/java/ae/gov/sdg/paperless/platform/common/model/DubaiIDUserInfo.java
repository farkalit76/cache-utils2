package ae.gov.sdg.paperless.platform.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author swetabh raj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "http://myid.dubai.gov.ae/oidc/dob", "http://myid.dubai.gov.ae/oidc/photo",
        "http://myid.dubai.gov.ae/oidc/lastname", "http://myid.dubai.gov.ae/oidc/fullnameAR",
        "http://myid.dubai.gov.ae/oidc/firstnameAR", "http://myid.dubai.gov.ae/oidc/username",
        "http://myid.dubai.gov.ae/oidc/email", "http://myid.dubai.gov.ae/oidc/gender",
        "http://myid.dubai.gov.ae/oidc/firstname", "http://myid.dubai.gov.ae/oidc/maritalStatus",
        "http://myid.dubai.gov.ae/oidc/idcardnumber", "http://myid.dubai.gov.ae/oidc/passportNo",
        "http://myid.dubai.gov.ae/oidc/lastnameAR", "http://myid.dubai.gov.ae/oidc/emailVerified",
        "http://myid.dubai.gov.ae/oidc/idcardexpirydate", "http://myid.dubai.gov.ae/oidc/sponsorNo",
        "http://myid.dubai.gov.ae/oidc/userVerified", "http://myid.dubai.gov.ae/oidc/nationalityAR",
        "http://myid.dubai.gov.ae/oidc/sponsorType", "http://myid.dubai.gov.ae/oidc/residencyExpiryDate",
        "http://myid.dubai.gov.ae/oidc/residencyNo", "http://myid.dubai.gov.ae/oidc/uuid",
        "http://myid.dubai.gov.ae/oidc/passportIssueDate", "http://myid.dubai.gov.ae/oidc/mobile",
        "http://myid.dubai.gov.ae/oidc/idcardissuedate", "http://myid.dubai.gov.ae/oidc/passportCountry",
        "http://myid.dubai.gov.ae/oidc/nationality", "http://myid.dubai.gov.ae/oidc/idn",
        "http://myid.dubai.gov.ae/oidc/fullname", "http://myid.dubai.gov.ae/oidc/passportExpiryDate" })
public class DubaiIDUserInfo {

    @JsonProperty("http://myid.dubai.gov.ae/oidc/dob")
    private String dob;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/photo")
    private String photo;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastname")
    private String lastname;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullnameAR")
    private String fullnameAR;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstnameAR")
    private String firstnameAR;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/username")
    private String username;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/email")
    private String email;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/gender")
    private String gender;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstname")
    private String firstname;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/maritalStatus")
    private String maritalStatus;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardnumber")
    private String idcardnumber;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportNo")
    private String passportNo;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastnameAR")
    private String lastnameAR;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/emailVerified")
    private String emailVerified;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardexpirydate")
    private String idCardExpirydate;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorNo")
    private String sponsorNo;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/userVerified")
    private String userVerified;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationalityAR")
    private String nationalityAR;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorType")
    private String sponsorType;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyExpiryDate")
    private String residencyExpiryDate;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyNo")
    private String residencyNo;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/uuid")
    private String uuid;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportIssueDate")
    private String passportIssueDate;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/mobile")
    private String mobile;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardissuedate")
    private String idcardissuedate;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportCountry")
    private String passportCountry;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationality")
    private String nationality;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/idn")
    private String idn;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullname")
    private String fullname;
    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportExpiryDate")
    private String passportExpiryDate;

    @JsonProperty("http://myid.dubai.gov.ae/oidc/dob")
    public String getDob() {
        return dob;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/dob")
    public void setDob(final String dob) {
        this.dob = dob;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/photo")
    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastname")
    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullnameAR")
    public String getFullnameAR() {
        return fullnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullnameAR")
    public void setFullnameAR(final String fullnameAR) {
        this.fullnameAR = fullnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstnameAR")
    public String getFirstnameAR() {
        return firstnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstnameAR")
    public void setFirstnameAR(final String firstnameAR) {
        this.firstnameAR = firstnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/username")
    public void setUsername(final String username) {
        this.username = username;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/email")
    public void setEmail(final String email) {
        this.email = email;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/gender")
    public void setGender(final String gender) {
        this.gender = gender;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/firstname")
    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/maritalStatus")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/maritalStatus")
    public void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardnumber")
    public String getIdcardnumber() {
        return idcardnumber;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardnumber")
    public void setIdcardnumber(final String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportNo")
    public String getPassportNo() {
        return passportNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportNo")
    public void setPassportNo(final String passportNo) {
        this.passportNo = passportNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastnameAR")
    public String getLastnameAR() {
        return lastnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/lastnameAR")
    public void setLastnameAR(final String lastnameAR) {
        this.lastnameAR = lastnameAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/emailVerified")
    public String getEmailVerified() {
        return emailVerified;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/emailVerified")
    public void setEmailVerified(final String emailVerified) {
        this.emailVerified = emailVerified;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardexpirydate")
    public String getIdcardexpirydate() {
        return idCardExpirydate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardexpirydate")
    public void setIdcardexpirydate(final String idCardExpirydate) {
        this.idCardExpirydate = idCardExpirydate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorNo")
    public String getSponsorNo() {
        return sponsorNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorNo")
    public void setSponsorNo(final String sponsorNo) {
        this.sponsorNo = sponsorNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/userVerified")
    public String getUserVerified() {
        return userVerified;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/userVerified")
    public void setUserVerified(final String userVerified) {
        this.userVerified = userVerified;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationalityAR")
    public String getNationalityAR() {
        return nationalityAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationalityAR")
    public void setNationalityAR(final String nationalityAR) {
        this.nationalityAR = nationalityAR;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorType")
    public String getSponsorType() {
        return sponsorType;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/sponsorType")
    public void setSponsorType(final String sponsorType) {
        this.sponsorType = sponsorType;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyExpiryDate")
    public String getResidencyExpiryDate() {
        return residencyExpiryDate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyExpiryDate")
    public void setResidencyExpiryDate(final String residencyExpiryDate) {
        this.residencyExpiryDate = residencyExpiryDate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyNo")
    public String getResidencyNo() {
        return residencyNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/residencyNo")
    public void setResidencyNo(final String residencyNo) {
        this.residencyNo = residencyNo;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/uuid")
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportIssueDate")
    public String getPassportIssueDate() {
        return passportIssueDate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportIssueDate")
    public void setPassportIssueDate(final String passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/mobile")
    public String getMobile() {
        return mobile;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/mobile")
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardissuedate")
    public String getIdcardissuedate() {
        return idcardissuedate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idcardissuedate")
    public void setIdcardissuedate(final String idcardissuedate) {
        this.idcardissuedate = idcardissuedate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportCountry")
    public String getPassportCountry() {
        return passportCountry;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportCountry")
    public void setPassportCountry(final String passportCountry) {
        this.passportCountry = passportCountry;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/nationality")
    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idn")
    public String getIdn() {
        return idn;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/idn")
    public void setIdn(final String idn) {
        this.idn = idn;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullname")
    public String getFullname() {
        return fullname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/fullname")
    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportExpiryDate")
    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    @JsonProperty("http://myid.dubai.gov.ae/oidc/passportExpiryDate")
    public void setPassportExpiryDate(final String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

}