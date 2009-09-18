/*
 * Created on Jan 19, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Iterator;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;

/**
 * @author REID
 */
public class ExternalUserSetActiveCustomerAction extends AbstractExternalCustomerAction {

    

    private Long target;

    public String execute() throws Exception {

        if (this.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers().size() == 1) {
            this.getSessionContext().getExternalUserSessionContext()
                    .setActiveCustomer(
                            (Customer) this.getSessionContext()
                                    .getExternalUserSessionContext()
                                    .getRelatedCustomers().toArray()[0]);
            return SUCCESS;
        }

        if (this.getTarget() == null || this.getTarget().longValue() == 0) {
            return INPUT;
        }

        Customer cust = CustomerServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).getFstxCustomer(
                this.getTarget());

        for (Iterator iter = this.getSessionContext()
                .getExternalUserSessionContext().getRelatedCustomers()
                .iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();
            if (element.getId().equals(cust.getId())) {
                this.getSessionContext().getExternalUserSessionContext()
                        .setActiveCustomer(cust);
                break;
            }
        }

        if (this.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() == null)
            return INPUT;

        return SUCCESS;
    }


    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
}