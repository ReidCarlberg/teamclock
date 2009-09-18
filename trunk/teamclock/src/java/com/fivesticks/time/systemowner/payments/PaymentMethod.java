/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

public class PaymentMethod extends AbstractTimeObject implements Serializable {

//    private String methodName;
    
    private String nameOnCard;
//    private String nameLast;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    
    private String phone;
    
//    private String type;
    private String number;
    private String expiresMonth;
    private String expiresYear;
    private String cvv;
    
    private boolean current;

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return Returns the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return Returns the current.
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * @param current The current to set.
     */
    public void setCurrent(boolean current) {
        this.current = current;
    }

    /**
     * @return Returns the cvv.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv The cvv to set.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return Returns the expiresMonth.
     */
    public String getExpiresMonth() {
        return expiresMonth;
    }

    /**
     * @param expiresMonth The expiresMonth to set.
     */
    public void setExpiresMonth(String expiresMonth) {
        this.expiresMonth = expiresMonth;
    }

    /**
     * @return Returns the expiresYear.
     */
    public String getExpiresYear() {
        return expiresYear;
    }

    /**
     * @param expiresYear The expiresYear to set.
     */
    public void setExpiresYear(String expiresYear) {
        this.expiresYear = expiresYear;
    }



    /**
     * @return Returns the nameFirst.
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * @param nameFirst The nameFirst to set.
     */
    public void setNameOnCard(String nameFirst) {
        this.nameOnCard = nameFirst;
    }


    /**
     * @return Returns the number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number The number to set.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Returns the street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * @return Returns the zip.
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip The zip to set.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    public String getObscureNumber() {
        
        
        String ret = "****" + this.getNumber().substring(this.getNumber().length()-4);
        
        return ret;
        
    }
    
}
