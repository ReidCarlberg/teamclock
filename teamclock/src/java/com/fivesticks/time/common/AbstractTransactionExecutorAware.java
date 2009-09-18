/*
 * Created on Dec 7, 2005 by Reid
 */
package com.fivesticks.time.common;

public abstract class AbstractTransactionExecutorAware {

    private TransactionExecutor transactionExecutor;

    public TransactionExecutor getTransactionExecutor() {
        return transactionExecutor;
    }

    public void setTransactionExecutor(TransactionExecutor transactionExecutor) {
        this.transactionExecutor = transactionExecutor;
    }
}
