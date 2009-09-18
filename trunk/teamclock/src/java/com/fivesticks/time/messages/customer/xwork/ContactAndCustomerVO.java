/*
 * Created on Oct 17, 2005
 *
 * 
 */
package com.fivesticks.time.messages.customer.xwork;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.customer.Customer;

public class ContactAndCustomerVO {

    private ContactV2 contactV2;

    private Customer customer;

    public ContactAndCustomerVO(ContactV2 contact, Customer customer) {
        super();
        this.contactV2 = contact;
        this.customer = customer;
    }

    public String getName() {
        return this.getContactV2().getNameLast() + ", "
                + this.getContactV2().getNameFirst() + " ("
                + this.getCustomer().getName() + ")";

    }

    public Long getId() {
        return this.getContactV2().getId();

    }

    public ContactV2 getContactV2() {
        return contactV2;
    }

    public void setContactV2(ContactV2 contact) {
        this.contactV2 = contact;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
