/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.TransactionManagerAware;

/**
 * @author Reid
 */
public class TransactionWrapperCommand implements TransactionManagerAware,
        FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private PlatformTransactionManager transactionManager;

    private FstxCommand rootCommand;

    private Object target;

    private SessionContext sessionContext;

    /**
     *  
     */
    public void execute() throws Exception {

        if (this.getRootCommand() == null) {
            throw new RuntimeException(
                    "You did not give me a command to transact!.");
        }

        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    try {
                        //                        getRootCommand().setSessionContext(getSessionContext());
                        getRootCommand().setTarget(getTarget());
                        getRootCommand().execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Transaction failed."
                                + e.getMessage(), e);
                    }
                }

            });

        } catch (RuntimeException e) {
            throw new Exception(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.TransactionManagerAware#setTransactionManager(org.springframework.transaction.PlatformTransactionManager)
     */
    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * @return Returns the transactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public FstxCommand getRootCommand() {
        return rootCommand;
    }

    public void setRootCommand(FstxCommand rootCommand) {
        this.rootCommand = rootCommand;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}