/*
 * Created on Jun 4, 2004
 *
 */
package com.fivesticks.time.common;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Reid
 *  
 */
public interface TransactionManagerAware {

    public void setTransactionManager(
            PlatformTransactionManager transactionManager);
}