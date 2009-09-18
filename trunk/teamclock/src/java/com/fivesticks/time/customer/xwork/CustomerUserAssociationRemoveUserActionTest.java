/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
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
public class CustomerUserAssociationRemoveUserActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicGoesToError() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        CustomerUserAssociationRemoveUserAction action = new CustomerUserAssociationRemoveUserAction();
        action.setCustomerModifyContext(new CustomerModifyContext());
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        action.setSessionContext(sessionContext);

        assertTrue(action.execute().equals(ActionSupport.ERROR));
    }

    public void testDisassociatesProperly() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        User intUser = SystemUserTestFactory.singleton.build(owner,
                UserTypeEnum.OWNERADMIN);

        User extUser = SystemUserTestFactory.singleton.buildExternal(owner);

        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                extUser.getUsername(), cust1.getId());

        CustomerUserAssociationRemoveUserAction action = new CustomerUserAssociationRemoveUserAction();
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        action.setSessionContext(sessionContext);

        action.setTarget(extUser.getUsername());
        //they are associated
        assertTrue(ExternalUserServiceDelegateFactory.factory.build(owner)
                .isAssociated(cust1, extUser));

        assertTrue(action.execute().equals(ActionSupport.SUCCESS));

        //they are not associated
        assertTrue(!ExternalUserServiceDelegateFactory.factory.build(owner)
                .isAssociated(cust1, extUser));

    }

}