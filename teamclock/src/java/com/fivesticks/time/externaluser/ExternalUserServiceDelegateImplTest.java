/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * Written first.
 * 
 * @author Reid
 */
public class ExternalUserServiceDelegateImplTest extends TestCase {

    SystemOwner systemOwner;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = new SystemOwnerTestFactory().buildWithDefaultSettings();
    }

    public void testSetup() throws Exception {
        //just to make sure...
    }

    public void testAssociateStandard() throws Exception {

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t1",
                cust.getId());

    }

    public void testIsAssociated() throws Exception {

        User user1 = UserServiceDelegateFactory.factory.build(systemOwner)
                .createNewUser("t1", "t1", "t1@fivesticks.com",
                        UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate(
                user1.getUsername(), cust.getId());

        assertTrue(ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .isAssociated(cust, user1));

    }

    public void testNotAssociated() throws Exception {

        User user1 = UserServiceDelegateFactory.factory.build(systemOwner)
                .createNewUser("t1", "t1", "t1@fivesticks.com",
                        UserTypeEnum.EXTERNAL);

        User user2 = UserServiceDelegateFactory.factory.build(systemOwner)
                .createNewUser("t2", "t2", "t2@fivesticks.com",
                        UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate(
                user1.getUsername(), cust.getId());

        assertTrue(!ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .isAssociated(cust, user2));

    }

    public void testGetAssociatedByCustomer() throws Exception {

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t1",
                cust.getId());

        /*
         * test for the 1 we know is there.
         */
        Collection byCustomer = ExternalUserServiceDelegateFactory.factory.build(
                systemOwner).getUsers(cust.getId());
        assertTrue(byCustomer.size() == 1);

        /*
         * should be 0
         */
        Customer custNoUser = CustomerTestFactory
                .getPersisted(systemOwner);
        byCustomer = ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .getUsers(custNoUser.getId());
        assertTrue(byCustomer.size() == 0);

        /*
         * should be 2
         */
        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t2",
                "t2", "t2@fivesticks.com", UserTypeEnum.EXTERNAL);
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t2",
                cust.getId());
        byCustomer = ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .getUsers(cust.getId());
        assertTrue(byCustomer.size() == 2);

    }

    public void testGetAssociatedByUser() throws Exception {

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.EXTERNAL);
        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t2",
                "t2", "t2@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);
        Customer cust2 = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t1",
                cust.getId());
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t1",
                cust2.getId());

        /*
         * test for the 2 we know is there.
         */
        Collection byUser = ExternalUserServiceDelegateFactory.factory.build(
                systemOwner).getCustomers("t1");
        assertTrue(byUser.size() == 2);

        /*
         * should be 0
         */
        byUser = ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .getCustomers("t2");
        assertTrue(byUser.size() == 0);

    }

    public void testAssociateShouldFail_UserNotAssociated() throws Exception {
        SystemOwner otherOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        UserServiceDelegateFactory.factory.build(otherOwner).createNewUser("t1", "t1",
                "t1@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there is an error
         */
        try {
            ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate(
                    "t1", cust.getId());
            //should never get here
            assertTrue(false);
        } catch (ExternalUserServiceDelegateException e) {
            //fine.
        }

    }

    public void testAssociateShouldFail_CustomerInvalid() throws Exception {
        SystemOwner otherOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(otherOwner);

        /*
         * just making sure there is an error
         */
        try {
            ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate(
                    "t1", cust.getId());
            //should never get here
            assertTrue(false);
        } catch (ExternalUserServiceDelegateException e) {
            //fine.
        }
    }

    public void testDisassociateStandard() throws Exception {

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.EXTERNAL);

        Customer cust = CustomerTestFactory.getPersisted(systemOwner);

        /*
         * just making sure there are no errors.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).associate("t1",
                cust.getId());

        assertTrue(ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .getCustomers("t1").size() == 1);

        /*
         * now disassociate it.
         */
        ExternalUserServiceDelegateFactory.factory.build(systemOwner).disassociate(
                "t1", cust.getId());

        assertTrue(ExternalUserServiceDelegateFactory.factory.build(systemOwner)
                .getCustomers("t1").size() == 0);

    }

}