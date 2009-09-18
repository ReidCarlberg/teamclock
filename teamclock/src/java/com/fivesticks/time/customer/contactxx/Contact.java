/*
 * Created on Mar 1, 2005
 *
 * 
 */
package com.fivesticks.time.customer.contactxx;

import java.io.Serializable;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Nick
 * 
 *  
 */
public class Contact implements SystemOwnerKeyAware, Serializable {

    private Long id;

    private String ownerKey;

    private Long custId;
    
    private String nameFirst;
    
    private String nameLast;

    private String primaryPhone;

    private String alternatePhone;

    private String primaryEmail;

    private String alternateEmail1;

    private String alternateEmail2;

    private String alternateEmail3;

    private String alternateEmail4;

    public String getAlternateEmail1() {
        return alternateEmail1;
    }

    public void setAlternateEmail1(String alternateEmail1) {
        this.alternateEmail1 = alternateEmail1;
    }

    public String getAlternateEmail2() {
        return alternateEmail2;
    }

    public void setAlternateEmail2(String alternateEmail2) {
        this.alternateEmail2 = alternateEmail2;
    }

    public String getAlternateEmail3() {
        return alternateEmail3;
    }

    public void setAlternateEmail3(String alternateEmail3) {
        this.alternateEmail3 = alternateEmail3;
    }

    public String getAlternateEmail4() {
        return alternateEmail4;
    }

    public void setAlternateEmail4(String alternateEmail4) {
        this.alternateEmail4 = alternateEmail4;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String name) {
        this.nameFirst = name;
    }

    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().intValue();
        } else {
            return -1;
        }
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
    /**
     * @return Returns the customerId.
     */
    public Long getCustId() {
        return custId;
    }
    /**
     * @param customerId The customerId to set.
     */
    public void setCustId(Long customerId) {
        this.custId = customerId;
    }
    public String getNameLast() {
        return nameLast;
    }
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }
}