/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account.xwork;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.account.util.BalanceDecorator;

/**
 * @author Reid
 */
public class PrintCurrentAccountTransactionsAction extends AccountTransactionContextAwareSupport
         {

    private static final NumberFormat format = new DecimalFormat("#,#00.00");

//    private SessionContext sessionContext;

//    private AccountTransactionContext accountTransactionContext;

    private Long target;

    private Collection transactions;

    private String currentBalance;

    public String execute() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner(), this.getAccountTransactionContext().getObjectTransactionType());

        Collection curr = sd.findUnarchived(this.getAccountTransactionContext()
                .getSystemOwnerKeyAware());

        //Double balance = sd.getCurrentBalance(this
          //      .getAccountTransactionContext().getSystemOwnerKeyAware());

        Double balance = new BalanceDecorator().getAndDecorateBalance(curr);
        
        this.setTransactions(curr);

        this.setCurrentBalance(format.format(balance.doubleValue()));

        return SUCCESS;
    }

//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
//    }

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

//    /**
//     * @return Returns the accountTransactionContext.
//     */
//    public AccountTransactionContext getAccountTransactionContext() {
//        return accountTransactionContext;
//    }
//
//    /**
//     * @param accountTransactionContext
//     *            The accountTransactionContext to set.
//     */
//    public void setAccountTransactionContext(
//            AccountTransactionContext accountTransactionContext) {
//        this.accountTransactionContext = accountTransactionContext;
//    }
}