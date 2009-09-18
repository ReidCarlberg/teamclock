/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;

/**
 * @author Reid
 */
public class CustomerUserAssociationsShowExternalAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private Long target;

    private Collection associated;

    public String execute() throws Exception {

        if (this.getTarget() != null) {
            this.getCustomerModifyContext().setTargetCustomer(
                    CustomerServiceDelegateFactory.factory.build(this.getSessionContext())
                            .getFstxCustomer(this.getTarget()));
        }

        if (this.getCustomerModifyContext().getTargetCustomer() == null) {
            return INPUT;
        }

        this.setAssociated(ExternalUserServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).getUsers(
                this.getCustomerModifyContext().getTargetCustomer().getId()));

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
     * @return Returns the associated.
     */
    public Collection getAssociated() {
        return associated;
    }

    /**
     * @param associated
     *            The associated to set.
     */
    public void setAssociated(Collection associated) {
        this.associated = associated;
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