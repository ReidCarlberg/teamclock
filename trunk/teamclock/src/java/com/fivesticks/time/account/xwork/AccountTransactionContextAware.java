/*
 * Created on Nov 18, 2004 by Reid
 */
package com.fivesticks.time.account.xwork;

/**
 * @author Reid
 */
public interface AccountTransactionContextAware {

    public void setAccountTransactionContext(
            AccountTransactionContext accountTransactionContext);
}