/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.useradmin.events;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class UseradminEventHandlerTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicResetsList() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        Collection origList = UserListBroker.singleton.getUserList(owner);
        assertTrue(origList.size() == 1);

        /*
         * here's the new information
         */
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);

        Collection testList1 = UserListBroker.singleton.getUserList(owner);
        assertTrue(testList1.size() == 1);

        UseradminEventImpl event = new UseradminEventImpl();
        event.setSystemOwner(owner);
        event.setUser(user1);
        event.setNewUser(user2);
        event.setUseradminEventType(UseradminEventType.CREATED);
        event.setUserTypeEnum(UserTypeEnum.EXTERNAL);

        new UseradminEventHandler().handleUseradminEvent(event);

        /*
         * we just want to make sure that the handler tells the userlistbroker
         * to reset itself.
         */
        Collection testList2 = UserListBroker.singleton.getUserList(owner);
        assertTrue(testList2.size() == 2);
    }

}