/*
 * Created on Dec 7, 2005 by Reid
 */
package com.fivesticks.time.common;

public class TransactionExecutorFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "transactionExecutor";
    public static final TransactionExecutorFactory factory = new TransactionExecutorFactory();

    public TransactionExecutor build() {
        return (TransactionExecutor) this.getBean(SPRING_BEAN_NAME);
    }
}
