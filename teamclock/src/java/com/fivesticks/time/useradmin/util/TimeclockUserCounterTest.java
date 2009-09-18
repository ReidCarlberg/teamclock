/*
 * Created on Dec 29, 2004 by REID
 */
package com.fivesticks.time.useradmin.util;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserCollectionBuilder;

/**
 * @author REID
 */
public class TimeclockUserCounterTest extends TestCase {

    SystemOwner owner;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        owner = new SystemOwnerTestFactory().buildWithDefaultSettings();
    }

    public void testSingleActiveUser() throws Exception {

        UserServiceDelegateFactory.factory.build(owner).createNewUser("t1", "t1",
                "t1@email.com", UserTypeEnum.TIMECLOCK);

        Collection users = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(owner);

        assertTrue(new TimeclockUserCounter().count(users) == 1);

    }

    public void testMutipleActiveUsers() throws Exception {

        UserServiceDelegateFactory.factory.build(owner).createNewUser("t1", "t1",
                "t1@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t2", "t2",
                "t2@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t3", "t3",
                "t3@email.com", UserTypeEnum.TIMECLOCK);

        Collection users = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(owner);

        assertTrue(new TimeclockUserCounter().count(users) == 3);

    }

    public void testMutipleActiveUsersInternalOnly() throws Exception {

        UserServiceDelegateFactory.factory.build(owner).createNewUser("t1", "t1",
                "t1@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t2", "t2",
                "t2@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t3", "t3",
                "t3@email.com", UserTypeEnum.TIMECLOCK);

        assertTrue(new TimeclockUserCounter().countTotalByOwner(owner) == 3);

    }

    public void testMutipleActiveAndInactiveUsersInternalOnly()
            throws Exception {

        UserServiceDelegateFactory.factory.build(owner).createNewUser("t1", "t1",
                "t1@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).makeInactive("t1");
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t2", "t2",
                "t2@email.com", UserTypeEnum.TIMECLOCK);
        UserServiceDelegateFactory.factory.build(owner).createNewUser("t3", "t3",
                "t3@email.com", UserTypeEnum.TIMECLOCK);

        assertTrue(new TimeclockUserCounter().countActiveByOwner(owner) == 2);

    }

}