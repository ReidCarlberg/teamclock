/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ModifyActionTest extends AbstractTimeTestCase {



    public void testUserCollections() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User userInternal = new SystemUserTestFactory().build(owner,
                UserTypeEnum.OWNERADMIN);

        User userExternal = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);

        Customer customer1 = CustomerTestFactory.getPersisted(owner);

        Project project1 = ProjectTestFactory.getPersisted(owner,
                customer1);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(userInternal));

        ModifyAction action = new ModifyAction();
        action.setSessionContext(sessionContext);
        action.setTodoModifyContext(new TodoModifyContext());

        assertTrue(action.getUsers().size() == 1);

        assertTrue(action.getExternalUsers().size() == 1);

    }

    public void testModify() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User userEntered = SystemUserTestFactory.singleton.buildOwner(owner);
        User userAssigned = SystemUserTestFactory.singleton.buildOwner(owner);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);

        ToDo target = ToDoTestFactory.getPersisted(cust1, proj1, userEntered,
                owner, ToDoPriorityTypeEnum.Q1);
        target.setAssignedToUser(userAssigned.getUsername());

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, userEntered);
        TodoModifyContext todoModifyContext = new TodoModifyContext();
        todoModifyContext.setTarget(target);

        ModifyAction action = new ModifyAction();
        action.setSessionContext(sessionContext);
        action.setTodoModifyContext(todoModifyContext);

        action.setSubmitTodo("submit");

        
        String re = action.execute();

        log.info("result is " + re);
        
        assertTrue(re.equals(ActionSupport.INPUT));

        SessionContext sc = SessionContextTestFactory.getContext(owner,userEntered);
        
        ToDo targetAfter = ToDoServiceDelegateFactory.factory.build(sc).get(
                target.getId());

        assertTrue(!targetAfter.getEnteredByUser().startsWith(","));

    }

}