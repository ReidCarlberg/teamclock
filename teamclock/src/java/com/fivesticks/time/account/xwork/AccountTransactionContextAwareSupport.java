/*
 * Created on Jun 30, 2005 by Reid
 */
package com.fivesticks.time.account.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

/**
 * @author Reid
 */
public abstract class AccountTransactionContextAwareSupport extends
        SessionContextAwareSupport implements AccountTransactionContextAware {

    private AccountTransactionContext accountTransactionContext;
    
    /**
     * @return Returns the accountTransactionContext.
     */
    public AccountTransactionContext getAccountTransactionContext() {
        return accountTransactionContext;
    }
    /**
     * @param accountTransactionContext The accountTransactionContext to set.
     */
    public void setAccountTransactionContext(
            AccountTransactionContext accountTransactionContext) {
        this.accountTransactionContext = accountTransactionContext;
    }
}
