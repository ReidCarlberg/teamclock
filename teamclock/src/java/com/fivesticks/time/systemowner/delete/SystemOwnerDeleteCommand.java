/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerDAO;

/**
 * 
 * This will delete the SystemOwner object. All the dependents will be deleted
 * with other commands, controled by the executing service delegate.
 * 
 * @author Nick
 */
public class SystemOwnerDeleteCommand implements FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private Object target;

    private SystemOwnerDAO dao;

//    private SessionContext sessionContext;

    /**
     *  
     */
    public void execute() throws Exception {

        if (target == null) {
            throw new Exception("SystemOwnerDeleteCommand: I need a target.");
        }

        if (!(target instanceof SystemOwner)) {
            throw new Exception(
                    "SystemOwnerDeleteCommand:  I don't know how to handle this target - "
                            + target);
        }

        /*
         * 2006-08-26 reid@fivesticks.com - added so I don't accidentally delete my
         * own account!
         */
        SystemOwner notFiveSticksTest = (SystemOwner) this.getTarget();
        
        if (notFiveSticksTest.getKey().equals("CXZASTPKGU")) {
            throw new Exception("Cannot delete the main Five Sticks account.");
        }
        
        if (this.getDao() == null) {
            throw new Exception("SystemOwnerDeleteCommand:  I need a dao!");
        }

        this.getDao().delete((SystemOwner) this.getTarget());

    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public SystemOwnerDAO getDao() {
        return dao;
    }

    public void setDao(SystemOwnerDAO dao) {
        this.dao = dao;
    }

    //    public SessionContext getSessionContext() {
    //        return sessionContext;
    //    }
    //
    //    public void setSessionContext(SessionContext sessionContext) {
    //        this.sessionContext = sessionContext;
    //    }
}