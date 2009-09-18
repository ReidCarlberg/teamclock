/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class CustomerUserAssociationsShowExternalActionTest extends TestCase {

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
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        CustomerUserAssociationsShowExternalAction action = new CustomerUserAssociationsShowExternalAction();
        action.setCustomerModifyContext(new CustomerModifyContext());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        action.setSessionContext(sessionContext);

        assertTrue(action.execute().equals(ActionSupport.INPUT));
    }

    public void testShowsAssociatedUsers() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj1 = ProjectTestFactory.getPersisted(owner, cust1);

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User extUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.EXTERNAL);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                extUser.getUsername(), cust1.getId());

        CustomerUserAssociationsShowExternalAction action = new CustomerUserAssociationsShowExternalAction();
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.setTarget(cust1.getId());

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        action.setSessionContext(sessionContext);

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        assertTrue(action.getAssociated() != null);

        assertTrue(action.getAssociated().size() == 1);

        Customer cust2 = CustomerTestFactory.getPersisted(owner);
        action.setTarget(cust2.getId());

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        assertTrue(action.getCustomerModifyContext().getTargetCustomer()
                .getId().equals(cust2.getId()));

        assertTrue(action.getAssociated() != null);

        assertTrue(action.getAssociated().size() == 0);

    }

}