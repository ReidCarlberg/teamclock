/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class CustomerUserAssociationAddUserActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testGoesToError() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());

        assertTrue(action.execute().equals(ActionSupport.ERROR));

    }

    public void testGoesToInput() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);

        assertTrue(action.execute().equals(ActionSupport.INPUT));

    }

    public void testGoesToSuccessAndUserIsAssociated() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);

        String extUser1 = "newuser@fivesticks.com";
        action.setEmail(extUser1);
        action.setUsername("newuser1");

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        User user = UserBDFactory.factory.build().getUserByEmail(extUser1);

        assertTrue(user != null);

    }

    public void testGoesToSuccessWithNotify() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        assertTrue(!EventChannelBroker.singleton.getChannel(
                GeneralEventType.EXTERNAL_USER_EVENT).hasEvents());

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        String extUser1 = "newuser@fivesticks.com";
        action.setEmail(extUser1);
        action.setUsername("newuser1");

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        User user = UserBDFactory.factory.build().getUserByEmail(extUser1);

        assertTrue(user != null);

        assertTrue(EventChannelBroker.singleton.getChannel(
                GeneralEventType.EXTERNAL_USER_EVENT).hasEvents());
        // assertTrue(new SendQueuedMessagesCommandProxy().getUnsentMessages()
        // .size() == 1);

    }

    public void testGoesToInputWithBadEmailAddress() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        /*
         * invalid email so probably not great.
         */
        String extUser1 = "aaa";
        action.setEmail(extUser1);
        action.setUsername("123");

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
        assertTrue(action.hasFieldErrors());
        assertTrue(action.getFieldErrors().containsKey("email"));
    }

    public void testGoesToInputUserIsAlreadyInternal() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User intUser2 = SystemUserTestFactory.singleton.buildOwner(owner);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        /*
         * invalid email so probably not great.
         */
        String extUser1 = intUser2.getEmail();
        action.setEmail(extUser1);
        action.setUsername(intUser2.getUsername());

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
        assertTrue(!action.hasFieldErrors());
        assertTrue(action.hasActionErrors());
    }

    public void testGoesToInputEmailExistsWithDifferentUsername()
            throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User intUser2 = SystemUserTestFactory.singleton.buildOwner(owner);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        User extUser1 = SystemUserTestFactory.singleton.buildExternal(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        /*
         * invalid email so probably not great.
         */
        String extUserEmail = extUser1.getEmail();
        action.setEmail(extUserEmail);
        action.setUsername("aaaaa1234");

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
        assertTrue(action.hasFieldErrors());

        assertTrue(action.getUsername().equals(extUser1.getUsername()));
    }

    public void testGoesToSuccessAfterRecommendingUsername() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User intUser2 = SystemUserTestFactory.singleton.buildOwner(owner);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        User extUser1 = SystemUserTestFactory.singleton.buildExternal(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        /*
         * invalid email so probably not great.
         */
        String extUserEmail = extUser1.getEmail();
        action.setEmail(extUserEmail);
        action.setUsername("aaaaa1234");

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
        assertTrue(action.hasFieldErrors());

        assertTrue(action.getUsername().equals(extUser1.getUsername()));

        /*
         * try again...
         */
        CustomerUserAssociationAddUserAction action2 = new CustomerUserAssociationAddUserAction();
        action2.setSessionContext(action.getSessionContext());
        action2.setCustomerModifyContext(action.getCustomerModifyContext());
        action2.setEmail(action.getEmail());
        action2.setUsername(action.getUsername());

        assertTrue(action2.execute().equals(ActionSupport.SUCCESS));

    }

    public void testGoesToInputUserExists() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton
                .buildWithDefaultSettings();

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User intUser2 = SystemUserTestFactory.singleton.buildOwner(owner);

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        User extUser1 = SystemUserTestFactory.singleton.buildExternal(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, intUser);

        CustomerUserAssociationAddUserAction action = new CustomerUserAssociationAddUserAction();
        action.setSessionContext(sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        // action.setNotify(true);

        /*
         * invalid email so probably not great.
         */
        String extUserEmail = "newuser123@fivesticks.com";
        action.setEmail(extUserEmail);
        action.setUsername(intUser.getUsername());

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
        assertTrue(action.hasFieldErrors());

        // assertTrue(action.getUsername().equals(extUser1.getUsername()));
    }

    public void testBasicWithRequiresPasswordReset() throws Exception {
        throw new RuntimeException(
                "here's the deal--when we add an external user we need to have them reset their password.");
    }
}