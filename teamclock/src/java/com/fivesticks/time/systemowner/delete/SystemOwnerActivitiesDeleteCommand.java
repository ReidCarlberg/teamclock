/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityDAO;
import com.fivesticks.time.activity.ActivityDAOFactory;
import com.fivesticks.time.activity.ActivityFilterVO;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * 
 * This will delete the SystemOwner object. All the dependents will be deleted
 * with other commands, controled by the executing service delegate.
 * 
 * @author Nick
 */
public class SystemOwnerActivitiesDeleteCommand implements FstxCommand {

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
        ActivityFilterVO filter = new ActivityFilterVO();
        filter.setOwnerKey(so.getKey());

        ActivityDAO dao = ActivityDAOFactory.factory.build();

        List activities = dao.find(filter);
        for (Iterator iter = activities.iterator(); iter.hasNext();) {
            Activity element = (Activity) iter.next();
            dao.delete(element);
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