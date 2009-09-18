/*
 * Created on November 30, 2004
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.SessionContext;

/**
 * This is a generic chain command--chain because we hand in a bunch of commands
 * and cruise through them. order? don't know yet.
 * 
 * @author Nick
 */
public class SessionContextChainCommand implements FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private Collection chainCommands;

    private Object target;

    private SessionContext sessionContext;

    /**
     *  
     */
    public void execute() throws Exception {

        if (this.getChainCommands() == null) {
            throw new RuntimeException(
                    "You did not give me any commands chain!.");
        }
        if (this.getChainCommands().size() == 0) {
            throw new RuntimeException(
                    "You did not give me any commands chain!.");
        }

        /*
         * If one
         */

        for (Iterator iter = this.getChainCommands().iterator(); iter.hasNext();) {
            FstxCommand element = (FstxCommand) iter.next();
            element.setTarget(this.getTarget());
            //            element.setSessionContext(this.getSessionContext());
            /*
             * If one command failed the whole will fail.
             */
            element.execute();

        }

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

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}