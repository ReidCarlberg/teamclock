/*
 * Created on Aug 27, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;
import java.util.HashMap;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegate;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author shuji
 */
public class TaskModifyActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testWithoutSubmitAndTarget() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        TaskModifyAction taskModifyAction = new TaskModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        taskModifyAction.setSessionContext(sessionContext);

        //testging just excute without submit
        assertTrue(taskModifyAction.execute().equals(TaskModifyAction.INPUT));

        /*
         * rsc 8/27/2004 What else should be true about the project modify?
         * Shouldn't action context now contain a project modify context? -->
         * No.
         */

        assertTrue(!ActionContext.getContext().getSession().containsKey(
                TaskModifyContext.KEY));

    }

    public void testModifyWithoutSubmitWithTarget() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        TaskModifyAction taskModifyAction = new TaskModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        taskModifyAction.setSessionContext(sessionContext);

        /*
         * rsc 8/27/2004 the actual number here is out of our control. The
         * database may change it, so the easiest thing to do is to get the one
         * that setupdata installed.
         */
        //        //testing if set target = 1 assuming data target 1 is already there
        //        projectModifyAction.setTarget(new Long(1));
        Task baseTask = (Task) TaskServiceDelegateFactory.factory.build(systemowner)
                .getAllTaskTypes().toArray()[0];
        assertTrue(baseTask != null);
        taskModifyAction.setTarget(baseTask.getId());
        assertTrue(taskModifyAction.execute().equals(TaskModifyAction.INPUT));

        /*
         * rsc 8/27/2004 in this case, it should contain a modify context.
         */
        assertTrue(ActionContext.getContext().getSession().containsKey(
                TaskModifyContext.KEY));
        assertTrue(taskModifyAction.getNewTaskName() != null);
    }

    public void testAddNewTask() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        TaskModifyAction taskModifyAction = new TaskModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        taskModifyAction.setSessionContext(sessionContext);
        TaskServiceDelegate taskBD = TaskServiceDelegateFactory.factory.build(sessionContext);

        Collection c1 = taskBD.getAllTaskTypes();
        //suppose to create a new customer name test1 and save it
        Task ft = taskBD.getTaskType(new Long(1));
        taskModifyAction.setNewTaskName(ft + "waowao");

        taskModifyAction.setSubmitTask("");
        taskModifyAction.execute();

        Collection c2 = taskBD.getAllTaskTypes();

        assertTrue(c1.size() + 1 == c2.size());

    }

    public void testModifyATaskName() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        TaskModifyAction taskModifyAction = new TaskModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        taskModifyAction.setSessionContext(sessionContext);
        TaskServiceDelegate taskBD = TaskServiceDelegateFactory.factory.build(sessionContext);

        TaskModifyContext tMcontext = new TaskModifyContext();

        Task aTask = taskBD.getTaskType(new Long(1));

        tMcontext.setTargetTask(aTask);
        ActionContext.getContext().getSession().put(TaskModifyContext.KEY,
                tMcontext);
        String test = aTask.getNameShort();
        taskModifyAction.setNewTaskName(test + "_1");

        taskModifyAction.setSubmitTask("");
        taskModifyAction.execute();

        Task changedNameTask = taskBD.getTaskType(new Long(1));
        String test2 = changedNameTask.getNameShort();

        assertTrue(test2.equals(test + "_1"));

    }

}