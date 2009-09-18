/*
 * Created on Feb 16, 2005 by REID
 */
package com.fivesticks.time.todo.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ReassignFromDashboardActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {
        /*
         * make sure it gets into the queue.
         */
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);

        ToDo todo1 = ToDoTestFactory.getPersisted(cust1, proj1, user1, owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        ReassignFromDashboardAction action = new ReassignFromDashboardAction();
        action.setSessionContext(sessionContext);

        action.setTarget(todo1.getId());
        action.setUsername(user2.getUsername());

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 0);

        String re = action.execute();

        assertTrue(re.equals(ActionSupport.SUCCESS));

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);

    }

}