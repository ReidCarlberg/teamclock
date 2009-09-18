/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.util.CustomerSettingVO;

/**
 * @author shuji
 */
public class CustomerModifyContext implements Serializable {

    // public static final String KEY = "context.modify.customer";

    private Customer targetCustomer;

    private CustomerSettingVO customerSettingVO;

    private CustomerDisplayWrapper displayCustomer;

    private Collection contacts;

    private Collection projects;

    private Double timeAccountBalance;

    private ContactV2 targetContact;

    /**
     * @return
     */
    public Customer getTargetCustomer() {
        return targetCustomer;
    }

    /**
     * @param customer
     */
    public void setTargetCustomer(Customer customer) {
        targetCustomer = customer;
    }

    public ContactV2 getTargetContact() {
        return targetContact;
    }

    public void setTargetContact(ContactV2 targetContact) {
        this.targetContact = targetContact;
    }

    public Collection getContacts() {
        return contacts;
    }

    public void setContacts(Collection contacts) {
        this.contacts = contacts;
    }

    public String getFirstContactPhone() {
        String ret = null;

        if (this.getContacts() != null && this.getContacts().size() > 0) {
            ContactV2 contact = (ContactV2) this.getContacts().toArray()[0];

            ret = contact.getOrganizationPhone();

        }

        return ret;
    }

    /**
     * @return Returns the displayCustomer.
     */
    public CustomerDisplayWrapper getDisplayCustomer() {
        return displayCustomer;
    }

    /**
     * @param displayCustomer
     *            The displayCustomer to set.
     */
    public void setDisplayCustomer(CustomerDisplayWrapper displayCustomer) {
        this.displayCustomer = displayCustomer;
    }

    /**
     * @return Returns the projects.
     */
    public Collection getProjects() {
        return projects;
    }

    /**
     * @param projects
     *            The projects to set.
     */
    public void setProjects(Collection projects) {
        this.projects = projects;
    }

    /**
     * @return Returns the timeAccountBalance.
     */
    public Double getTimeAccountBalance() {
        return timeAccountBalance;
    }

    /**
     * @param timeAccountBalance
     *            The timeAccountBalance to set.
     */
    public void setTimeAccountBalance(Double timeAccountBalance) {
        this.timeAccountBalance = timeAccountBalance;
    }

    public CustomerSettingVO getCustomerSettingVO() {
        return customerSettingVO;
    }

    public void setCustomerSettingVO(CustomerSettingVO customerSettingVO) {
        this.customerSettingVO = customerSettingVO;
    }
}