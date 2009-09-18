/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ExternalUserModifyToDoActionTest extends AbstractTimeTestCase {



    public void testBasicSuccess() throws Exception {

        ExternalUserServiceDelegateFactory.factory.build(this.systemOwner).associate(
                user.getUsername(), this.customer.getId());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(this.systemOwner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserViewDashboardAction action = new ExternalUserViewDashboardAction();
        action.setSessionContext(sessionContext);
        action.getSessionContext().setExternalUserSessionContext(
                new ExternalUserSessionContext());

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));
        assertTrue(!action.hasErrors());

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() != null);

        ExternalUserModifyToDoAction action2 = new ExternalUserModifyToDoAction();
        action2.setSessionContext(action.getSessionContext());
        action.getSessionContext().setExternalUserSessionContext(
                action.getSessionContext().getExternalUserSessionContext());

        String re = action2.execute();

        assertTrue(re.equals(ActionSupport.INPUT));

    }

    public void testBasicSuccessAddsToDo() throws Exception {

//        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
//        User user = new SystemUserTestFactory().build(owner,
//                UserTypeEnum.EXTERNAL);
        User user2 = new SystemUserTestFactory().buildOwner(this.systemOwner);

        Customer cust1 = CustomerTestFactory.getPersisted(this.systemOwner);
        Project proj1 = ProjectTestFactory.getPersisted(this.systemOwner, cust1);

        /*
         * setup some system settings for later
         */
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner).updateSetting(
                SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO,
                user2.getUsername());

        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner).updateSetting(
                SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY,
                user2.getUsername());

        ExternalUserServiceDelegateFactory.factory.build(this.systemOwner).associate(
                user.getUsername(), cust1.getId());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(this.systemOwner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserViewDashboardAction action = new ExternalUserViewDashboardAction();
        action.setSessionContext(sessionContext);
        action.getSessionContext().setExternalUserSessionContext(
                new ExternalUserSessionContext());

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));
        assertTrue(!action.hasErrors());

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() != null);

        ExternalUserModifyToDoAction action2 = new ExternalUserModifyToDoAction();
        action2.setSessionContext(action.getSessionContext());
        action.getSessionContext().setExternalUserSessionContext(
                action.getSessionContext().getExternalUserSessionContext());

        action2.setProjectId(proj1.getId());
        action2.setDetails("Here are some todo details");
        action2.setSubmitToDo("sub");

        /*
         * make sure the before state looks right.
         */
        Collection todosBefore = ToDoServiceDelegateFactory.factory.build(this.sessionContext)
                .findIncompleteByAssignee(user2);
        assertTrue(todosBefore.size() == 0);
        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 0);

        /*
         * this is the execute
         */
        String re = action2.execute();

        assertTrue(re.equals(ActionSupport.SUCCESS));

        /*
         * test the saving...
         */
        Collection todosAfter = ToDoServiceDelegateFactory.factory.build(this.sessionContext)
                .findIncompleteByAssignee(user2);
        assertTrue(todosAfter.size() == 1);

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);
    }
}