/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;

import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;

/**
 * @author Reid
 */
public class MockTransactionSucceedCommand implements FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private Collection chainCommands;

    private Object target;

    private SessionContext sessionContext;

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     *  
     */
    public void execute() throws Exception {

        LogFactory.getLog(this.getClass()).info("I passed!");

    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Collection getChainCommands() {
        return chainCommands;
    }

    public void setChainCommands(Collection chainCommands) {
        this.chainCommands = chainCommands;
    }
}