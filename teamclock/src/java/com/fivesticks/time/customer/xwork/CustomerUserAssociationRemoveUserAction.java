/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegate;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.events.ExternalUserEventPublisher;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class CustomerUserAssociationRemoveUserAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware {


    private CustomerModifyContext customerModifyContext;

    private String target;

    public String execute() throws Exception {
        if (this.getCustomerModifyContext().getTargetCustomer() == null)
            return ERROR;

        if (this.getTarget() == null)
            return SUCCESS; //since we're just not deleting anything.

        User user = null;

        user = UserBDFactory.factory.build().getByUsername(this.getTarget());

        /*
         * get the external user and delete it.
         */

        ExternalUserServiceDelegate eusd = ExternalUserServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());

        eusd.disassociate(this.getTarget(), this.getCustomerModifyContext()
                .getTargetCustomer().getId());

        new ExternalUserEventPublisher().publishUserDisassociatedEvent(this
                .getSessionContext(), user, this
                .getCustomerModifyContext().getTargetCustomer());

        return SUCCESS;
    }

    /**
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }


    /**
     * @return Returns the username.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setTarget(String username) {
        this.target = username;
    }
}