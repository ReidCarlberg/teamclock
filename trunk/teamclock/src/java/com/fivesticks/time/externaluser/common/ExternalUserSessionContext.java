/*
 * Created on Jan 17, 2005 by Reid
 */
package com.fivesticks.time.externaluser.common;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.customer.Customer;

/**
 * @author Reid
 */
public class ExternalUserSessionContext implements Serializable {

    //an external user can be related to more than one customer.
    private Collection relatedCustomers;

    //if they are related to more than one, which one are we using?
    private Customer activeCustomer;

    /**
     * @return Returns the activeCustomer.
     */
    public Customer getActiveCustomer() {
        return activeCustomer;
    }

    /**
     * @param activeCustomer
     *            The activeCustomer to set.
     */
    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    /**
     * @return Returns the relatedCustomers.
     */
    public Collection getRelatedCustomers() {
        return relatedCustomers;
    }

    /**
     * @param relatedCustomers
     *            The relatedCustomers to set.
     */
    public void setRelatedCustomers(Collection relatedCustomers) {
        this.relatedCustomers = relatedCustomers;
    }
}