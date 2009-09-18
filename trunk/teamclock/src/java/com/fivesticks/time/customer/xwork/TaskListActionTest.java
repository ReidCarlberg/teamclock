/*
 * Created on Aug 27, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author shuji
 */
public class TaskListActionTest extends TestCase {
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testList() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        TaskListAction taskListAction = new TaskListAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        taskListAction.setSessionContext(sessionContext);

        assertTrue(taskListAction.execute().equals(TaskListAction.SUCCESS));
        assertTrue(taskListAction.getTasks() != null);

        /*
         * rsc 8/27/2004 We should have more than 0
         */

        assertTrue(taskListAction.getTasks().size() > 0);

        /*
         * rsc 8/27/2004 make sure they belong to the right owner
         */
        for (Iterator i = taskListAction.getTasks().iterator(); i.hasNext();) {
            Task current = (Task) i.next();
            assertTrue(current.getOwnerKey().equals(systemowner.getKey()));
        }
    }
}