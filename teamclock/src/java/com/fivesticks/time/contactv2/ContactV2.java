/*
 * Created on Aug 30, 2006
 *
 */
package com.fivesticks.time.contactv2;

import java.util.Set;
import java.util.TreeSet;

import com.fivesticks.time.common.AbstractDateAwareTimeObject;

public class ContactV2 extends AbstractDateAwareTimeObject {

    private String nameTitle;

    private String nameFirst;

    private String nameMiddle;

    private String nameLast;

    private String nameSuffix;

    private String nameFormatted;

    private String nameNickname;

    private Integer birthdayYear;

    private Integer birthdayMonth;

    private Integer birthdayDay;

    private String organizationName;

    private String organizationTitle;

    private String organizationJob;

    private String organizationDepartment;

    private String homeAddress1;

    private String homeAddress2;

    private String homePostalBox;

    private String homeCity;

    private String homeRegion;

    private String homePostalCode;

    private String homeCountry;

    private String organizationAddress1;

    private String organizationAddress2;

    private String organizationPostalBox;

    private String organizationCity;

    private String organizationRegion;

    private String organizationPostalCode;

    private String organizationCountry;

    private String postalAddress1;

    private String postalAddress2;

    private String postalPostalBox;

    private String postalCity;

    private String postalRegion;

    private String postalPostalCode;

    private String postalCountry;

    private String homePhone;

    private String homeFax;

    private String homeEmail;

    private String homeWeb;

    private String organizationPhone;

    private String organizationFax;

    private String organizationTelex;

    private String organizationEmail;

    private String organizationWeb;

    private String miscPhoneMobile;

    private String miscPhoneCarphone;

    private String miscPhonePager;

    private String miscTimeZone;

    private String miscDescription;

    private String imAim;

    private String imIcq;

    private String imJabber;

    private String imGTalk;

    private String imYahoo;

    private String imMsn;

    private Set classes = new TreeSet();

    private Set interests;

    private Set sources;

    /**
     * @return Returns the birthdayDay.
     */
    public Integer getBirthdayDay() {
        return birthdayDay;
    }

    /**
     * @param birthdayDay
     *            The birthdayDay to set.
     */
    public void setBirthdayDay(Integer birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    /**
     * @return Returns the birthdayMonth.
     */
    public Integer getBirthdayMonth() {
        return birthdayMonth;
    }

    /**
     * @param birthdayMonth
     *            The birthdayMonth to set.
     */
    public void setBirthdayMonth(Integer birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    /**
     * @return Returns the birthdayYear.
     */
    public Integer getBirthdayYear() {
        return birthdayYear;
    }

    /**
     * @param birthdayYear
     *            The birthdayYear to set.
     */
    public void setBirthdayYear(Integer birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    /**
     * @return Returns the homeAddress1.
     */
    public String getHomeAddress1() {
        return homeAddress1;
    }

    /**
     * @param homeAddress1
     *            The homeAddress1 to set.
     */
    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1 = homeAddress1;
    }

    /**
     * @return Returns the homeAddress2.
     */
    public String getHomeAddress2() {
        return homeAddress2;
    }

    /**
     * @param homeAddress2
     *            The homeAddress2 to set.
     */
    public void setHomeAddress2(String homeAddress2) {
        this.homeAddress2 = homeAddress2;
    }

    /**
     * @return Returns the homeCity.
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * @param homeCity
     *            The homeCity to set.
     */
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    /**
     * @return Returns the homeCountry.
     */
    public String getHomeCountry() {
        return homeCountry;
    }

    /**
     * @param homeCountry
     *            The homeCountry to set.
     */
    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    /**
     * @return Returns the homeEmail.
     */
    public String getHomeEmail() {
        return homeEmail;
    }

    /**
     * @param homeEmail
     *            The homeEmail to set.
     */
    public void setHomeEmail(String homeEmail) {
        this.homeEmail = homeEmail;
    }

    /**
     * @return Returns the homeFax.
     */
    public String getHomeFax() {
        return homeFax;
    }

    /**
     * @param homeFax
     *            The homeFax to set.
     */
    public void setHomeFax(String homeFax) {
        this.homeFax = homeFax;
    }

    /**
     * @return Returns the homePhone.
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone
     *            The homePhone to set.
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * @return Returns the homePostalBox.
     */
    public String getHomePostalBox() {
        return homePostalBox;
    }

    /**
     * @param homePostalBox
     *            The homePostalBox to set.
     */
    public void setHomePostalBox(String homePostalBox) {
        this.homePostalBox = homePostalBox;
    }

    /**
     * @return Returns the homePostalCode.
     */
    public String getHomePostalCode() {
        return homePostalCode;
    }

    /**
     * @param homePostalCode
     *            The homePostalCode to set.
     */
    public void setHomePostalCode(String homePostalCode) {
        this.homePostalCode = homePostalCode;
    }

    /**
     * @return Returns the homeRegion.
     */
    public String getHomeRegion() {
        return homeRegion;
    }

    /**
     * @param homeRegion
     *            The homeRegion to set.
     */
    public void setHomeRegion(String homeRegion) {
        this.homeRegion = homeRegion;
    }

    /**
     * @return Returns the homeWeb.
     */
    public String getHomeWeb() {
        return homeWeb;
    }

    /**
     * @param homeWeb
     *            The homeWeb to set.
     */
    public void setHomeWeb(String homeWeb) {
        this.homeWeb = homeWeb;
    }

    /**
     * @return Returns the miscPhoneCarphone.
     */
    public String getMiscPhoneCarphone() {
        return miscPhoneCarphone;
    }

    /**
     * @param miscPhoneCarphone
     *            The miscPhoneCarphone to set.
     */
    public void setMiscPhoneCarphone(String miscPhoneCarphone) {
        this.miscPhoneCarphone = miscPhoneCarphone;
    }

    /**
     * @return Returns the miscPhoneMobile.
     */
    public String getMiscPhoneMobile() {
        return miscPhoneMobile;
    }

    /**
     * @param miscPhoneMobile
     *            The miscPhoneMobile to set.
     */
    public void setMiscPhoneMobile(String miscPhoneMobile) {
        this.miscPhoneMobile = miscPhoneMobile;
    }

    /**
     * @return Returns the miscPhonePager.
     */
    public String getMiscPhonePager() {
        return miscPhonePager;
    }

    /**
     * @param miscPhonePager
     *            The miscPhonePager to set.
     */
    public void setMiscPhonePager(String miscPhonePager) {
        this.miscPhonePager = miscPhonePager;
    }

    /**
     * @return Returns the nameFirst.
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * @param nameFirst
     *            The nameFirst to set.
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    /**
     * @return Returns the nameFormatted.
     */
    public String getNameFormatted() {
        return nameFormatted;
    }

    /**
     * @param nameFormatted
     *            The nameFormatted to set.
     */
    public void setNameFormatted(String nameFormatted) {
        this.nameFormatted = nameFormatted;
    }

    /**
     * @return Returns the nameLast.
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * @param nameLast
     *            The nameLast to set.
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    /**
     * @return Returns the nameMiddle.
     */
    public String getNameMiddle() {
        return nameMiddle;
    }

    /**
     * @param nameMiddle
     *            The nameMiddle to set.
     */
    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    /**
     * @return Returns the nameSuffix.
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * @param nameSuffix
     *            The nameSuffix to set.
     */
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    /**
     * @return Returns the nameTitle.
     */
    public String getNameTitle() {
        return nameTitle;
    }

    /**
     * @param nameTitle
     *            The nameTitle to set.
     */
    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    /**
     * @return Returns the organizationAddress1.
     */
    public String getOrganizationAddress1() {
        return organizationAddress1;
    }

    /**
     * @param organizationAddress1
     *            The organizationAddress1 to set.
     */
    public void setOrganizationAddress1(String organizationAddress1) {
        this.organizationAddress1 = organizationAddress1;
    }

    /**
     * @return Returns the organizationAddress2.
     */
    public String getOrganizationAddress2() {
        return organizationAddress2;
    }

    /**
     * @param organizationAddress2
     *            The organizationAddress2 to set.
     */
    public void setOrganizationAddress2(String organizationAddress2) {
        this.organizationAddress2 = organizationAddress2;
    }

    /**
     * @return Returns the organizationCity.
     */
    public String getOrganizationCity() {
        return organizationCity;
    }

    /**
     * @param organizationCity
     *            The organizationCity to set.
     */
    public void setOrganizationCity(String organizationCity) {
        this.organizationCity = organizationCity;
    }

    /**
     * @return Returns the organizationCountry.
     */
    public String getOrganizationCountry() {
        return organizationCountry;
    }

    /**
     * @param organizationCountry
     *            The organizationCountry to set.
     */
    public void setOrganizationCountry(String organizationCountry) {
        this.organizationCountry = organizationCountry;
    }

    /**
     * @return Returns the organizationDepartment.
     */
    public String getOrganizationDepartment() {
        return organizationDepartment;
    }

    /**
     * @param organizationDepartment
     *            The organizationDepartment to set.
     */
    public void setOrganizationDepartment(String organizationDepartment) {
        this.organizationDepartment = organizationDepartment;
    }

    /**
     * @return Returns the organizationFax.
     */
    public String getOrganizationFax() {
        return organizationFax;
    }

    /**
     * @param organizationFax
     *            The organizationFax to set.
     */
    public void setOrganizationFax(String organizationFax) {
        this.organizationFax = organizationFax;
    }

    /**
     * @return Returns the organizationJob.
     */
    public String getOrganizationJob() {
        return organizationJob;
    }

    /**
     * @param organizationJob
     *            The organizationJob to set.
     */
    public void setOrganizationJob(String organizationJob) {
        this.organizationJob = organizationJob;
    }

    /**
     * @return Returns the organizationName.
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * @param organizationName
     *            The organizationName to set.
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * @return Returns the organizationPhone.
     */
    public String getOrganizationPhone() {
        return organizationPhone;
    }

    /**
     * @param organizationPhone
     *            The organizationPhone to set.
     */
    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    /**
     * @return Returns the organizationPostalBox.
     */
    public String getOrganizationPostalBox() {
        return organizationPostalBox;
    }

    /**
     * @param organizationPostalBox
     *            The organizationPostalBox to set.
     */
    public void setOrganizationPostalBox(String organizationPostalBox) {
        this.organizationPostalBox = organizationPostalBox;
    }

    /**
     * @return Returns the organizationPostalCode.
     */
    public String getOrganizationPostalCode() {
        return organizationPostalCode;
    }

    /**
     * @param organizationPostalCode
     *            The organizationPostalCode to set.
     */
    public void setOrganizationPostalCode(String organizationPostalCode) {
        this.organizationPostalCode = organizationPostalCode;
    }

    /**
     * @return Returns the organizationRegion.
     */
    public String getOrganizationRegion() {
        return organizationRegion;
    }

    /**
     * @param organizationRegion
     *            The organizationRegion to set.
     */
    public void setOrganizationRegion(String organizationRegion) {
        this.organizationRegion = organizationRegion;
    }

    /**
     * @return Returns the organizationTelex.
     */
    public String getOrganizationTelex() {
        return organizationTelex;
    }

    /**
     * @param organizationTelex
     *            The organizationTelex to set.
     */
    public void setOrganizationTelex(String organizationTelex) {
        this.organizationTelex = organizationTelex;
    }

    /**
     * @return Returns the organizationTitle.
     */
    public String getOrganizationTitle() {
        return organizationTitle;
    }

    /**
     * @param organizationTitle
     *            The organizationTitle to set.
     */
    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    /**
     * @return Returns the postalAddress1.
     */
    public String getPostalAddress1() {
        return postalAddress1;
    }

    /**
     * @param postalAddress1
     *            The postalAddress1 to set.
     */
    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }

    /**
     * @return Returns the postalAddress2.
     */
    public String getPostalAddress2() {
        return postalAddress2;
    }

    /**
     * @param postalAddress2
     *            The postalAddress2 to set.
     */
    public void setPostalAddress2(String postalAddress2) {
        this.postalAddress2 = postalAddress2;
    }

    /**
     * @return Returns the postalCity.
     */
    public String getPostalCity() {
        return postalCity;
    }

    /**
     * @param postalCity
     *            The postalCity to set.
     */
    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }

    /**
     * @return Returns the postalCountry.
     */
    public String getPostalCountry() {
        return postalCountry;
    }

    /**
     * @param postalCountry
     *            The postalCountry to set.
     */
    public void setPostalCountry(String postalCountry) {
        this.postalCountry = postalCountry;
    }

    /**
     * @return Returns the postalPostalBox.
     */
    public String getPostalPostalBox() {
        return postalPostalBox;
    }

    /**
     * @param postalPostalBox
     *            The postalPostalBox to set.
     */
    public void setPostalPostalBox(String postalPostalBox) {
        this.postalPostalBox = postalPostalBox;
    }

    /**
     * @return Returns the postalPostalCode.
     */
    public String getPostalPostalCode() {
        return postalPostalCode;
    }

    /**
     * @param postalPostalCode
     *            The postalPostalCode to set.
     */
    public void setPostalPostalCode(String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
    }

    /**
     * @return Returns the postalRegion.
     */
    public String getPostalRegion() {
        return postalRegion;
    }

    /**
     * @param postalRegion
     *            The postalRegion to set.
     */
    public void setPostalRegion(String postalRegion) {
        this.postalRegion = postalRegion;
    }

    /**
     * @return Returns the miscTimeZone.
     */
    public String getMiscTimeZone() {
        return miscTimeZone;
    }

    /**
     * @param miscTimeZone
     *            The miscTimeZone to set.
     */
    public void setMiscTimeZone(String miscTimeZone) {
        this.miscTimeZone = miscTimeZone;
    }

    /**
     * @return Returns the organizationEmail.
     */
    public String getOrganizationEmail() {
        return organizationEmail;
    }

    /**
     * @param organizationEmail
     *            The organizationEmail to set.
     */
    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    /**
     * @return Returns the organizationWeb.
     */
    public String getOrganizationWeb() {
        return organizationWeb;
    }

    /**
     * @param organizationWeb
     *            The organizationWeb to set.
     */
    public void setOrganizationWeb(String organizationWeb) {
        this.organizationWeb = organizationWeb;
    }

    /**
     * @return Returns the imAim.
     */
    public String getImAim() {
        return imAim;
    }

    /**
     * @param imAim
     *            The imAim to set.
     */
    public void setImAim(String imAim) {
        this.imAim = imAim;
    }

    /**
     * @return Returns the imGTalk.
     */
    public String getImGTalk() {
        return imGTalk;
    }

    /**
     * @param imGTalk
     *            The imGTalk to set.
     */
    public void setImGTalk(String imGTalk) {
        this.imGTalk = imGTalk;
    }

    /**
     * @return Returns the imIcq.
     */
    public String getImIcq() {
        return imIcq;
    }

    /**
     * @param imIcq
     *            The imIcq to set.
     */
    public void setImIcq(String imIcq) {
        this.imIcq = imIcq;
    }

    /**
     * @return Returns the imJabber.
     */
    public String getImJabber() {
        return imJabber;
    }

    /**
     * @param imJabber
     *            The imJabber to set.
     */
    public void setImJabber(String imJabber) {
        this.imJabber = imJabber;
    }

    /**
     * @return Returns the imMessenger.
     */
    public String getImMsn() {
        return imMsn;
    }

    /**
     * @param imMessenger
     *            The imMessenger to set.
     */
    public void setImMsn(String imMessenger) {
        this.imMsn = imMessenger;
    }

    /**
     * @return Returns the imYahoo.
     */
    public String getImYahoo() {
        return imYahoo;
    }

    /**
     * @param imYahoo
     *            The imYahoo to set.
     */
    public void setImYahoo(String imYahoo) {
        this.imYahoo = imYahoo;
    }

    /**
     * @return Returns the nameNickname.
     */
    public String getNameNickname() {
        return nameNickname;
    }

    /**
     * @param nameNickname
     *            The nameNickname to set.
     */
    public void setNameNickname(String nameNickname) {
        this.nameNickname = nameNickname;
    }

    /**
     * @return Returns the miscDescription.
     */
    public String getMiscDescription() {
        return miscDescription;
    }

    /**
     * @param miscDescription
     *            The miscDescription to set.
     */
    public void setMiscDescription(String miscDescription) {
        this.miscDescription = miscDescription;
    }

    public boolean isOrganizationAddressPresent() {

        boolean ret = this.getOrganizationAddress1() != null
                || this.getOrganizationAddress2() != null
                || this.getOrganizationCity() != null
                || this.getOrganizationCountry() != null
                || this.getOrganizationRegion() != null
                || this.getOrganizationPostalCode() != null;

        return ret;
    }

    public boolean isHomeAddressPresent() {

        boolean ret = this.getHomeAddress1() != null
                || this.getHomeAddress2() != null || this.getHomeCity() != null
                || this.getHomeCountry() != null
                || this.getHomeRegion() != null
                || this.getHomePostalCode() != null;

        return ret;
    }

    public boolean isPostalAddressPresent() {

        boolean ret = this.getPostalAddress1() != null
                || this.getPostalAddress2() != null
                || this.getPostalCity() != null
                || this.getPostalCountry() != null
                || this.getPostalRegion() != null
                || this.getPostalPostalCode() != null;

        return ret;
    }

    /**
     * @return Returns the classes.
     */
    public Set getClasses() {
        return classes;
    }

    /**
     * @param classes The classes to set.
     */
    public void setClasses(Set classes) {
        this.classes = classes;
    }

    /**
     * @return Returns the interests.
     */
    public Set getInterests() {
        return interests;
    }

    /**
     * @param interests The interests to set.
     */
    public void setInterests(Set interests) {
        this.interests = interests;
    }

    /**
     * @return Returns the sources.
     */
    public Set getSources() {
        return sources;
    }

    /**
     * @param sources The sources to set.
     */
    public void setSources(Set sources) {
        this.sources = sources;
    }

}
