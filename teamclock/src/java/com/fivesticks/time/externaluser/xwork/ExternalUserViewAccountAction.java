/*
 * Created on Jan 19, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.util.BalanceDecorator;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.menu.MenuSection;

/**
 * @author REID
 */
public class ExternalUserViewAccountAction extends AbstractExternalCustomerAction {

    private static final NumberFormat format = new DecimalFormat("#,#00.00");

    private SessionContext sessionContext;

    private Collection transactions;

    private String currentBalance;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.ACTIVITY);

        /*
         * these are all of the related customers.
         */
        if (this.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers() == null) {
            handleInitializeRelatedCustomers();
        }
        
        if (this.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() == null) {
            return INPUT;
        }

        AccountTransactionServiceDelegate sd = CustomerAccountTransactionServiceDelegate.factory
                .buildTimeAccount(this.getSessionContext().getSystemOwner());
        Collection curr = sd.findUnarchived(this.getSessionContext()
                .getExternalUserSessionContext().getActiveCustomer());

        Double balance = new BalanceDecorator().getAndDecorateBalance(curr);
        
//        Double balance = sd.getCurrentBalance(this.getSessionContext()
//                .getExternalUserSessionContext().getActiveCustomer());

        this.setTransactions(curr);

        this.setCurrentBalance(format.format(balance.doubleValue()));

        return SUCCESS;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the currentBalance.
     */
    public String getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance
     *            The currentBalance to set.
     */
    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return Returns the transactions.
     */
    public Collection getTransactions() {
        return transactions;
    }

    /**
     * @param transactions
     *            The transactions to set.
     */
    public void setTransactions(Collection transactions) {
        this.transactions = transactions;
    }
}