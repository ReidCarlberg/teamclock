/*
 * Created on Jan 23, 2005 by REID
 */
package com.fivesticks.time.useradmin;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author REID
 */
public class UseradminDeleteCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicSuccessUserIsDeleted() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        UseradminDeleteCommand command = UseradminDeleteCommandFactory.factory.build();

        command.setTarget(user2);

        command.delete(sessionContext);

        try {
            User user2Test = UserBDFactory.factory.build().getByUsername(
                    user2.getUsername());
            if (user2Test != null)
                throw new Exception("not gone.");

        } catch (Exception e) {
            //do nothing.
        }

    }

    public void testBasicSuccessUserIsNotDeleted() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        SystemOwner owner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);

        SystemUserServiceDelegateFactory.factory.build().associate(owner2, user2,
                UserTypeEnum.EXTERNAL);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        UseradminDeleteCommand command = UseradminDeleteCommandFactory.factory.build();

        command.setTarget(user2);

        command.delete(sessionContext);

        User user2Test = UserBDFactory.factory.build().getByUsername(
                user2.getUsername());

        assertTrue(user2 != null);

    }

}