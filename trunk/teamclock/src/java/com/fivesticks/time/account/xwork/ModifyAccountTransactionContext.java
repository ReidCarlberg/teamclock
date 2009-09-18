/*
 * Created on Nov 17, 2004 by Reid
 */
package com.fivesticks.time.account.xwork;

import com.fivesticks.time.account.AccountTransaction;

/**
 * @author Reid
 */
public class ModifyAccountTransactionContext {

    private AccountTransaction accountTransaction;

    /**
     * @return Returns the accountTransaction.
     */
    public AccountTransaction getAccountTransaction() {
        return accountTransaction;
    }

    /**
     * @param accountTransaction
     *            The accountTransaction to set.
     */
    public void setAccountTransaction(AccountTransaction accountTransaction) {
        this.accountTransaction = accountTransaction;
    }
}