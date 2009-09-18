/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.authen.events;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.fivesticks.time.authen.xwork.GeneralLoginAction;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author REID
 */
public class EventPublisherTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testPublishLogin() throws Exception {
        /*
         * make sure there's nothing in here.
         */
        assertTrue(!EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());
        /*
         * copied from GeneralLoginActionTest
         */
        Map map = new HashMap();
        ActionContext actionContext = new ActionContext(map);

        ActionContext.setContext(actionContext);

        SessionContext sessionContext = new SessionContext();
        GeneralLoginAction gla = new GeneralLoginAction();
        gla.setSessionContext(sessionContext);
        gla.setLoginButton("submit");
        gla.setUsername("admin");
        gla.setPassword("admin");

        assertEquals(gla.execute(), GeneralLoginAction.SUCCESS);
        sessionContext = gla.getSessionContext();
        assertTrue(sessionContext.getSystemOwner() != null);
        User user = UserBDFactory.factory.build().getByUsername("admin");

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(user);

        assertTrue(sessionContext.getSystemOwner().getKey().equals(
                systemOwner.getKey()));

        assertTrue(sessionContext.getUser() != null);

        assertTrue(sessionContext.getUser().getUser().getUsername().equals(
                user.getUsername()));
        /*
         * lets look at the events
         */
        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());

        Collection events = EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).getEvents();

        assertTrue(events.size() == 1);

        AuthenticationEvent event = (AuthenticationEvent) events.toArray()[0];

        assertTrue(event.getDescription().equals(
                AuthenticationEventPublisher.LOGIN));
        assertTrue(event.getUser().getUsername().equals("admin"));
        assertTrue(event.getSystemOwner().getKey().equals(
                sessionContext.getSystemOwner().getKey()));
    }

}