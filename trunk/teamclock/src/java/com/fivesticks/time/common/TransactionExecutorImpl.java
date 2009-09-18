/*
 * Created on Dec 7, 2005 by Reid
 */
package com.fivesticks.time.common;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionExecutorImpl extends AbstractTransactionManagerAware
        implements TransactionExecutor {

    public void execute(final Command command) {

        TransactionTemplate transactionTemplate = new TransactionTemplate(this
                .getTransactionManager());

        transactionTemplate
                .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            public void doInTransactionWithoutResult(TransactionStatus status) {

                /*
                 * 2005-12-07 RSC whatever we send in, it will be executed
                 * completely within a transaction;
                 */
                try {
                    command.execute();
                } catch (Exception e) {
                    throw new RuntimeException("TransactionFailed ",e);
                }

            }
        });

    }

}
