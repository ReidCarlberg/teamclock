package com.fivesticks.time.customer;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class Customer extends AbstractTimeObject implements Serializable {

    /** persistent field */
    private String name;

    /** persistent field */
    private String street1;

    /** persistent field */
    private String street2;

    /** persistent field */
    private String city;

    /** persistent field */
    private String state;

    /** persistent field */
    private String zip;
    
    private Long status;
    
    private String country;
    
    private Boolean hidden = Boolean.FALSE;

    private Double budget;
    
    private Double budgetProgress;
    
    private String payMethod;
    
    private String payNumber;
    
    private String payMonth;
    
    private String payYear;
    
    private String payCode;
    
    private String payName;
    
    private String payStreet;
    
    private String payZip;
    
    private Long payNotifyContactId;
    
    
    /** full constructor */
    public Customer(java.lang.String name, java.lang.String street1,
            java.lang.String street2, java.lang.String City,
            java.lang.String state, java.lang.String zip) {
        this.name = name;
        this.street1 = street1;
        this.street2 = street2;
        this.city = City;
        this.state = state;
        this.zip = zip;
    }

    /** default constructor */
    public Customer() {
    }

//    public java.lang.Long getId() {
//        return this.id;
//    }
//
//    public void setId(java.lang.Long id) {
//        this.id = id;
//    }

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getStreet1() {
        return this.street1;
    }

    public void setStreet1(java.lang.String street1) {
        this.street1 = street1;
    }

    public java.lang.String getStreet2() {
        return this.street2;
    }

    public void setStreet2(java.lang.String street2) {
        this.street2 = street2;
    }

    public java.lang.String getCity() {
        return this.city;
    }

    public void setCity(java.lang.String City) {
        this.city = City;
    }

    public java.lang.String getState() {
        return this.state;
    }

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getZip() {
        return this.zip;
    }

    public void setZip(java.lang.String zip) {
        this.zip = zip;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Customer))
            return false;
        Customer castOther = (Customer) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }


    /**
     * @return Returns the type.
     */
    public Long getStatus() {
        return status;
    }
    /**
     * @param type The type to set.
     */
    public void setStatus(Long type) {
        this.status = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return Returns the hidden.
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * @param hidden The hidden to set.
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Double getBudgetProgress() {
		return budgetProgress;
	}

	public void setBudgetProgress(Double budgetProgress) {
		this.budgetProgress = budgetProgress;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public Long getPayNotifyContactId() {
		return payNotifyContactId;
	}

	public void setPayNotifyContactId(Long payNotifyContactId) {
		this.payNotifyContactId = payNotifyContactId;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public String getPayStreet() {
		return payStreet;
	}

	public void setPayStreet(String payStreet) {
		this.payStreet = payStreet;
	}

	public String getPayYear() {
		return payYear;
	}

	public void setPayYear(String payYear) {
		this.payYear = payYear;
	}

	public String getPayZip() {
		return payZip;
	}

	public void setPayZip(String payZip) {
		this.payZip = payZip;
	}
}