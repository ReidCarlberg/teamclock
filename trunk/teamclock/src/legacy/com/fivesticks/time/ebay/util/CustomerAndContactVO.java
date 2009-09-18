/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.io.Serializable;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.contact.Contact;

/**
 * @author REID
 */
public class CustomerAndContactVO implements Serializable {
    
    private FstxCustomer customer;
    private Contact contact;
    
    
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public FstxCustomer getCustomer() {
        return customer;
    }
    public void setCustomer(FstxCustomer customer) {
        this.customer = customer;
    }
}
