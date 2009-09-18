/*
 * Created on Dec 6, 2005 by Reid
 */
package com.fivesticks.time.common;

import org.springframework.transaction.PlatformTransactionManager;

public class AbstractTransactionManagerAware {

    private PlatformTransactionManager transactionManager;

    /**
     * @return Returns the transactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * @param transactionManager
     *            The transactionManager to set.
     */
    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

}
