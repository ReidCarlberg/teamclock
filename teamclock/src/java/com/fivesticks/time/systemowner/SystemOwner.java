/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.IdReadable;

/**
 * @author Reid
 */
public class SystemOwner implements Serializable, IdReadable {

    private Long id;

    private String key;

    private String name;

    private String address1 = "xxx";

    private String address2 = "xxx";

    private String city = "xxx";

    private String state = "xxx";

    private String postalCode = "xxx";

    private String country = "xxx";

    private String contactName;

    /*
     * 2005-12-22 RSC Added.
     */
    private String contactNameFirst;

    private String contactNameLast;

    private String contactEmail;

    private String contactPhone = "xxx";

    private boolean activated;

    private Date expirationDate;

    private boolean demo;

    private String account;

    private String billingFrequency;
    
    private boolean requiresAccountUpdate;

    private String requiresAccountUpdateReason;
    

    /**
     * @return Returns the activated.
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * @param activated
     *            The activated to set.
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * @return Returns the address1.
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1
     *            The address1 to set.
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return Returns the address2.
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2
     *            The address2 to set.
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return Returns the contactEmail.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail
     *            The contactEmail to set.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * @return Returns the contactName.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     *            The contactName to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return Returns the contactPhone.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     *            The contactPhone to set.
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return Returns the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            The country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the postalCode.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     *            The postalCode to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        boolean ret = false;

        if (obj instanceof SystemOwner) {
            SystemOwner test = (SystemOwner) obj;
            if (test.getId().longValue() == test.getId().longValue()) {
                ret = true;
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return (int) this.getId().longValue();
    }

    /**
     * @return Returns the contactNameFirst.
     */
    public String getContactNameFirst() {
        return contactNameFirst;
    }

    /**
     * @param contactNameFirst
     *            The contactNameFirst to set.
     */
    public void setContactNameFirst(String contactNameFirst) {
        this.contactNameFirst = contactNameFirst;
    }

    /**
     * @return Returns the contactNameLast.
     */
    public String getContactNameLast() {
        return contactNameLast;
    }

    /**
     * @param contactNameLast
     *            The contactNameLast to set.
     */
    public void setContactNameLast(String contactNameLast) {
        this.contactNameLast = contactNameLast;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    /**
     * @return Returns the accountType.
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param accountType
     *            The accountType to set.
     */
    public void setAccount(String accountType) {
        this.account = accountType;
    }

    public void setAccountType(AccountType accountType) {
        if (accountType != null)
            this.setAccount(accountType.getName());
    }

    public AccountType getAccountType() {
        AccountType ret = null;

        if (this.getAccount() != null) {
            ret = AccountType.getByName(this.getAccount());
        }

        return ret;
    }

    public void setBillingFrequencyType(
            BillingFrequencyType billingFrequencyType) {
        if (billingFrequencyType != null)
            this.setBillingFrequency(billingFrequencyType.getName());
    }

    public BillingFrequencyType getBillingFrequencyType() {
        BillingFrequencyType ret = null;

        if (this.getBillingFrequency() != null) {
            ret = BillingFrequencyType.getByName(this.getBillingFrequency());
        }

        return ret;
    }

    /**
     * @return Returns the billingFrequency.
     */
    public String getBillingFrequency() {
        return billingFrequency;
    }

    /**
     * @param billingFrequency
     *            The billingFrequency to set.
     */
    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public boolean isRequiresAccountUpdate() {
        return requiresAccountUpdate;
    }

    public void setRequiresAccountUpdate(boolean requiresAccountUpdate) {
        this.requiresAccountUpdate = requiresAccountUpdate;
    }

    /**
     * @return Returns the requiresAccountUpdateReason.
     */
    public String getRequiresAccountUpdateReason() {
        return requiresAccountUpdateReason;
    }

    /**
     * @param requiresAccountUpdateReason The requiresAccountUpdateReason to set.
     */
    public void setRequiresAccountUpdateReason(String requiresAccountUpdateReason) {
        this.requiresAccountUpdateReason = requiresAccountUpdateReason;
    }
}