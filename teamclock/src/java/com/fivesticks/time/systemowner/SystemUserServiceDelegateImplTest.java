/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class SystemUserServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSetup() throws Exception {

    }

    public void testUserSingleOwner() throws Exception {

        SystemOwner one = new SystemOwnerTestFactory().buildWithDefaultSettings();

        UserServiceDelegateFactory.factory.build(one).createNewUser("test1", "test1",
                "test1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        Collection oneCollection = SystemUserServiceDelegateFactory.factory.build()
                .list("test1");

        assertTrue(oneCollection.size() == 1);

    }

    public void testUserTwoOwners() throws Exception {

        SystemOwner one = new SystemOwnerTestFactory().buildWithDefaultSettings();

        SystemOwner two = new SystemOwnerTestFactory().buildWithDefaultSettings();

        UserServiceDelegateFactory.factory.build(one).createNewUser("test1", "test1",
                "test1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        SystemUserServiceDelegateFactory.factory.build().associate(two,
                UserBDFactory.factory.build().getByUsername("test1"),
                UserTypeEnum.OWNERADMIN);

        Collection twoCollection = SystemUserServiceDelegateFactory.factory.build()
                .list("test1");

        assertTrue(twoCollection.size() == 2);

    }

    public void testSystemUserType() throws Exception {

        SystemOwner one = new SystemOwnerTestFactory().buildWithDefaultSettings();

        UserServiceDelegateFactory.factory.build(one).createNewUser("test1", "test1",
                "test1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        SystemUser test = SystemUserServiceDelegateFactory.factory.build()
                .getBySystemAndUser(one,
                        UserBDFactory.factory.build().getByUsername("test1"));

        assertTrue(test != null);

        assertTrue(test.getUserType() != null);

        assertTrue(test.getUserType().equals(UserTypeEnum.OWNERADMIN.getName()));

    }
}