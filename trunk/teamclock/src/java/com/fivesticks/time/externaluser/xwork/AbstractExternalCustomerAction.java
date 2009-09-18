/*
 * Created on Aug 27, 2006
 *
 */
package com.fivesticks.time.externaluser.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateException;

public abstract class AbstractExternalCustomerAction extends SessionContextAwareSupport {

    /**
     * @throws ExternalUserServiceDelegateException
     * @throws CustomerServiceDelegateException
     *  
     */
    public void handleInitializeRelatedCustomers() throws Exception {
    
        this.getSessionContext().getExternalUserSessionContext()
                .setRelatedCustomers(
                        new ExternalUserRelatedCustomerCollectionBuilder()
                                .getRelatedCustomers(this.getSessionContext()
                                        .getSystemOwner(), this
                                        .getSessionContext().getUser()
                                        .getUser()));
    
        if (this.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers().size() == 1)
            this.getSessionContext().getExternalUserSessionContext()
                    .setActiveCustomer(
                            (Customer) this.getSessionContext()
                                    .getExternalUserSessionContext()
                                    .getRelatedCustomers().toArray()[0]);
    
    }

}
