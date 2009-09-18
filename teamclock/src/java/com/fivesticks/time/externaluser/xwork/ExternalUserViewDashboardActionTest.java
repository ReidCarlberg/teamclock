/*
 * Created on Jan 17, 2005 by Reid
 */
package com.fivesticks.time.externaluser.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ExternalUserViewDashboardActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserViewDashboardAction action = new ExternalUserViewDashboardAction();
        action.setSessionContext(sessionContext);

        action.getSessionContext().setExternalUserSessionContext(
                new ExternalUserSessionContext());

        assertTrue(action.execute().equals(ActionSupport.INPUT));
        assertTrue(!action.hasErrors());

    }

    public void testExternalCustomers_Single() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer fstxCustomer = CustomerTestFactory.getPersisted(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), fstxCustomer.getId());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();

        ExternalUserViewDashboardAction action = new ExternalUserViewDashboardAction();
        action.setSessionContext(sessionContext);
        action.getSessionContext().setExternalUserSessionContext(
                externalUserSessionContext);

        action.execute();

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers().size() == 1);

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() != null);
    }

    public void testExternalCustomers_Multiple() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user = new SystemUserTestFactory().build(owner,
                UserTypeEnum.EXTERNAL);
        Customer fstxCustomer = CustomerTestFactory.getPersisted(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), fstxCustomer.getId());

        Customer fstxCustomer2 = CustomerTestFactory
                .getPersisted(owner);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), fstxCustomer2.getId());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);
        sessionContext.setUser(AuthenticatedUserFactory.factory.build(user));

        ExternalUserSessionContext externalUserSessionContext = new ExternalUserSessionContext();

        ExternalUserViewDashboardAction action = new ExternalUserViewDashboardAction();
        action.setSessionContext(sessionContext);
        action.getSessionContext().setExternalUserSessionContext(
                externalUserSessionContext);

        action.execute();

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers().size() == 2);

        assertTrue(action.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() == null);
    }

}