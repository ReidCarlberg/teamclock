/*
 * Created on Jan 19, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ExternalUserSetActiveCustomerActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Customer cust2 = CustomerTestFactory.getPersisted(owner);
        User user1 = SystemUserTestFactory.singleton.buildExternal(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust1.getId());
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust2.getId());

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        ExternalUserSetActiveCustomerAction action = new ExternalUserSetActiveCustomerAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(new ExternalUserSessionContext());
        sessionContext.getExternalUserSessionContext().setRelatedCustomers(
                new ExternalUserRelatedCustomerCollectionBuilder()
                        .getRelatedCustomers(owner, user1));

        String r = action.execute();

        assertTrue(r.equals(ActionSupport.INPUT));
    }

    public void testSingleCustomerGoesToSuccess() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        //FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
        User user1 = SystemUserTestFactory.singleton.buildExternal(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust1.getId());
        //        ExternalUserServiceDelegate.factory.build(owner).associate(
        //                user1.getUsername(), cust2.getId());

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        ExternalUserSetActiveCustomerAction action = new ExternalUserSetActiveCustomerAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(new ExternalUserSessionContext());
        sessionContext.getExternalUserSessionContext().setRelatedCustomers(
                new ExternalUserRelatedCustomerCollectionBuilder()
                        .getRelatedCustomers(owner, user1));

        String r = action.execute();

        assertTrue(r.equals(ActionSupport.SUCCESS));

        assertTrue(sessionContext.getExternalUserSessionContext()
                .getActiveCustomer().getId().equals(cust1.getId()));

    }

    public void testSelectCustomerGoesToSuccess() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Customer cust2 = CustomerTestFactory.getPersisted(owner);
        User user1 = SystemUserTestFactory.singleton.buildExternal(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust1.getId());
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust2.getId());

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        ExternalUserSetActiveCustomerAction action = new ExternalUserSetActiveCustomerAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(new ExternalUserSessionContext());
        sessionContext.getExternalUserSessionContext().setRelatedCustomers(
                new ExternalUserRelatedCustomerCollectionBuilder()
                        .getRelatedCustomers(owner, user1));

        action.setTarget(cust2.getId());

        String r = action.execute();

        assertTrue(r.equals(ActionSupport.SUCCESS));

        assertTrue(sessionContext.getExternalUserSessionContext()
                .getActiveCustomer() != null);

        assertTrue(sessionContext.getExternalUserSessionContext()
                .getActiveCustomer().getId().equals(cust2.getId()));

    }

    public void testSelectUnrelatedCustomerGoesToInput() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Customer cust2 = CustomerTestFactory.getPersisted(owner);
        Customer cust3 = CustomerTestFactory.getPersisted(owner);
        User user1 = SystemUserTestFactory.singleton.buildExternal(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust1.getId());
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user1.getUsername(), cust2.getId());

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        ExternalUserSetActiveCustomerAction action = new ExternalUserSetActiveCustomerAction();
        action.setSessionContext(sessionContext);
        sessionContext
                .setExternalUserSessionContext(new ExternalUserSessionContext());
        sessionContext.getExternalUserSessionContext().setRelatedCustomers(
                new ExternalUserRelatedCustomerCollectionBuilder()
                        .getRelatedCustomers(owner, user1));

        action.setTarget(cust3.getId());

        String r = action.execute();

        assertTrue(r.equals(ActionSupport.INPUT));

        assertTrue(sessionContext.getExternalUserSessionContext()
                .getActiveCustomer() == null);

    }

}