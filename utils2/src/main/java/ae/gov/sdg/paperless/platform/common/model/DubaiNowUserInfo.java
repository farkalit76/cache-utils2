package ae.gov.sdg.paperless.platform.common.model;

public class DubaiNowUserInfo {

    private String isPushNotificationEnabled;

    private String challengeQuestionId;

    private String emailAddress;

    private String challengeAnswer;

    private String smsLanguageId;

    private String mobileNumber;

    private String nationalityId;

    private String activate;

    private String guestCustomer;

    private String fullName;

    private String cityId;

    private String birthDate;

    private String encodedDubaiNowUserToken;
    
    private String nationalId;
    
    public String getIsPushNotificationEnabled() {
        return isPushNotificationEnabled;
    }

    public void setIsPushNotificationEnabled(final String isPushNotificationEnabled) {
        this.isPushNotificationEnabled = isPushNotificationEnabled;
    }

    public String getChallengeQuestionId() {
        return challengeQuestionId;
    }

    public void setChallengeQuestionId(final String challengeQuestionId) {
        this.challengeQuestionId = challengeQuestionId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getChallengeAnswer() {
        return challengeAnswer;
    }

    public void setChallengeAnswer(final String challengeAnswer) {
        this.challengeAnswer = challengeAnswer;
    }

    public String getSmsLanguageId() {
        return smsLanguageId;
    }

    public void setSmsLanguageId(final String smsLanguageId) {
        this.smsLanguageId = smsLanguageId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(final String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(final String activate) {
        this.activate = activate;
    }

    public String getGuestCustomer() {
        return guestCustomer;
    }

    public void setGuestCustomer(final String guestCustomer) {
        this.guestCustomer = guestCustomer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(final String cityId) {
        this.cityId = cityId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "[isPushNotificationEnabled = " + isPushNotificationEnabled + ", challengeQuestionId = "
                + challengeQuestionId + ", emailAddress = " + emailAddress + ", challengeAnswer = " + challengeAnswer
                + ", smsLanguageId = " + smsLanguageId + ", mobileNumber = " + mobileNumber + ", nationalityId = "
                + nationalityId + ", activate = " + activate + ", guestCustomer = " + guestCustomer + ", fullName = "
                + fullName + ", cityId = " + cityId + ", birthDate = " + birthDate + "]";
    }

    public String getEncodedDubaiNowUserToken() {
        return encodedDubaiNowUserToken;
    }

    public void setEncodedDubaiNowUserToken(final String encodedDubaiNowUserToken) {
        this.encodedDubaiNowUserToken = encodedDubaiNowUserToken;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(final String nationalId) {
        this.nationalId = nationalId;
    }
}
