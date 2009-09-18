/*
 * Created on Jan 27, 2005 by REID
 */
package com.fivesticks.time.todo.xwork.legacy;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.FstxProject;
import com.fivesticks.time.customer.FstxProjectTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fivesticks.time.todo.xwork.TodoModifyContext;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ToDoAddCommentActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testCommentAddsSingleEvent() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        FstxProject proj1 = FstxProjectTestFactory.getPersisted(owner, cust1);
        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);
        User extUser = SystemUserTestFactory.singleton.buildExternal(owner);

        ToDo todo1 = ToDoTestFactory.getPersisted(cust1, proj1, user1, owner);
        todo1.setExternalUser(extUser.getUsername());
        ToDoServiceDelegateFactory.factory.build(owner).save(todo1);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);
        TodoModifyContext modifyContext = new TodoModifyContext();
        modifyContext.setTarget(todo1);

        ToDoAddCommentAction action = new ToDoAddCommentAction();
        action.setSessionContext(sessionContext);
        action.setTodoModifyContext(modifyContext);

        action.setComment("here is the comment");
        action.setSubmitComment("submitComment");

        String re = action.execute();

        assertTrue(re.equals(ActionSupport.SUCCESS));
        assertTrue(!action.hasErrors());

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);
    }

}