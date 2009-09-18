/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.account.xwork.AccountTransactionContextAwareSupport;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;

/**
 * @author Reid
 */
public class ViewCustomerAccountTransactionsAction extends
        AccountTransactionContextAwareSupport implements
        CustomerModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    public String execute() throws Exception {

        this.getAccountTransactionContext().setSystemOwnerKeyAware(
                this.getCustomerModifyContext().getTargetCustomer());

        if (this.getAccountTransactionContext().getSystemOwnerKeyAware() == null)
            return INPUT;

        this.getAccountTransactionContext().setObjectTransactionType(
                CustomerAccountTransactionType.EBAY_ACCOUNT);

        this.getAccountTransactionContext().setLabel(
                this.getCustomerModifyContext().getTargetCustomer().getName());

        this.getAccountTransactionContext().setBackAction(
                "customerdetail.action");

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
}