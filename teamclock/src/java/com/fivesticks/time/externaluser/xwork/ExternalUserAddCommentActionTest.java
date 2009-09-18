/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.externaluser.common.ExternalUserToDoContext;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ExternalUserAddCommentActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicError() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());
        ToDo todo = ToDoTestFactory.getPersisted(cust1, proj1, user2, owner);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();
        externalUserSessionContext.setActiveCustomer(cust1);

        ExternalUserToDoContext externalUserCommentContext = new ExternalUserToDoContext();
        //externalUserCommentContext.setTarget(todo);

        ExternalUserAddCommentAction action = new ExternalUserAddCommentAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(externalUserSessionContext);
        action.setExternalUserToDoContext(externalUserCommentContext);

        /*
         * Doesn't know what todo it should be commenting on.
         */
        assertTrue(action.execute().equals(ActionSupport.ERROR));

    }

    public void testBasicInputHasTarget() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());
        ToDo todo = ToDoTestFactory.getPersisted(cust1, proj1, user2, owner);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();
        externalUserSessionContext.setActiveCustomer(cust1);

        ExternalUserToDoContext externalUserCommentContext = new ExternalUserToDoContext();
        externalUserCommentContext.setTarget(todo);

        ExternalUserAddCommentAction action = new ExternalUserAddCommentAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(externalUserSessionContext);
        action.setExternalUserToDoContext(externalUserCommentContext);

        /*
         * Doesn't know what todo it should be commenting on.
         */
        assertTrue(action.execute().equals(ActionSupport.INPUT));

    }

    public void testBasicInputGetsTarget() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());
        ToDo todo = ToDoTestFactory.getPersisted(cust1, proj1, user2, owner);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();
        externalUserSessionContext.setActiveCustomer(cust1);

        ExternalUserToDoContext externalUserCommentContext = new ExternalUserToDoContext();
        //externalUserCommentContext.setTarget(todo);

        ExternalUserAddCommentAction action = new ExternalUserAddCommentAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(externalUserSessionContext);
        action.setExternalUserToDoContext(externalUserCommentContext);
        action.setTarget(todo.getId());

        /*
         * Doesn't know what todo it should be commenting on.
         */
        assertTrue(action.getExternalUserCommentContext().getTarget() == null);

        assertTrue(action.execute().equals(ActionSupport.INPUT));

        assertTrue(action.getExternalUserCommentContext().getTarget() != null);

    }

    public void testBasicSuccess() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());
        ToDo todo = ToDoTestFactory.getPersisted(cust1, proj1, user2, owner);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();
        externalUserSessionContext.setActiveCustomer(cust1);

        ExternalUserToDoContext externalUserCommentContext = new ExternalUserToDoContext();
        externalUserCommentContext.setTarget(todo);

        ExternalUserAddCommentAction action = new ExternalUserAddCommentAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(externalUserSessionContext);
        action.setExternalUserToDoContext(externalUserCommentContext);

        action.setSubmitComment("submt");
        action.setComment("here is a comment");

        /*
         * Doesn't know what todo it should be commenting on.
         */
        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

    }

    public void testBasicSuccessSavesComments() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());
        ToDo todo = ToDoTestFactory.getPersisted(cust1, proj1, user2, owner);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();
        externalUserSessionContext.setActiveCustomer(cust1);

        ExternalUserToDoContext externalUserCommentContext = new ExternalUserToDoContext();
        externalUserCommentContext.setTarget(todo);

        ExternalUserAddCommentAction action = new ExternalUserAddCommentAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(externalUserSessionContext);
        action.setExternalUserToDoContext(externalUserCommentContext);

        action.setSubmitComment("submt");
        action.setComment("here is a comment");

        /*
         * make sure we're at 0
         */
        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.EXTERNAL_USER_EVENT).getEvents().size() == 0);
        /*
         * Doesn't know what todo it should be commenting on.
         */
        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        Collection comments = ObjectCommentServiceDelegateFactory.factory.build(owner)
                .getComments(todo);

        assertTrue(comments.size() == 1);

        /*
         * make sure the event was published.
         * 
         * rsc - 2005-05-23 orignally counting external user events.
         */

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);
    }

}