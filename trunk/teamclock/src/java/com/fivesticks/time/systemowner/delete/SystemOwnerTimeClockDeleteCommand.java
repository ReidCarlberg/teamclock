/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.timeclock.Timeclock;
import com.fivesticks.time.timeclock.TimeclockDAO;
import com.fivesticks.time.timeclock.TimeclockDAOFactory;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;

/**
 * 
 * This will delete the SystemOwner object. All the dependents will be deleted
 * with other commands, controled by the executing service delegate.
 * 
 * @author Nick
 */
public class SystemOwnerTimeClockDeleteCommand implements FstxCommand {

    public static final String SPRING_BEAN_NAME = "transactionWrapperCommand";

    private Object target;

    private SessionContext sessionContext;

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

        SystemOwner so = (SystemOwner) this.getTarget();

        /*
         * We need to find all the activities for this systemowner.
         */
        TimeclockFilterParameters filter = new TimeclockFilterParameters();

        filter.setOwnerKey(so.getKey());

        TimeclockDAO dao = TimeclockDAOFactory.factory.build();

        Collection elements = dao.find(filter);

        for (Iterator iter = elements.iterator(); iter.hasNext();) {
            Timeclock element = (Timeclock) iter.next();
            dao.remove(element);
        }

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