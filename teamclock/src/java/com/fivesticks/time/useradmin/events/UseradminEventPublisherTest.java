/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.useradmin.events;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class UseradminEventPublisherTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testCreatePublishesEvent() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user1);

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.USER_EVENT).getEvents().size() == 0);

        UserServiceDelegateFactory.factory.build(sessionContext).createNewUser(
                "samples1", "pw123", "email@test.com", UserTypeEnum.EXTERNAL);

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.USER_EVENT).getEvents().size() == 1);

    }

}