/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.authen.events;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.fivesticks.time.authen.xwork.GeneralLoginAction;
import com.fivesticks.time.authen.xwork.LogoutAction;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.EventHandlerCommand;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.LoginHistory;
import com.fstx.stdlib.authen.LoginHistoryBD;
import com.fstx.stdlib.authen.LoginHistoryBDFactory;
import com.fstx.stdlib.authen.LoginHistoryFilterParameters;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author REID
 */
public class AuthenticationEventHandlerTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSpring() throws Exception {
        AuthenticationEventHandler handler = AuthenticationEventHandlerFactory.factory
                .build();

        assertTrue(handler != null);

    }

    public void testLogin() throws Exception {
        /*
         * make sure we have nothing in login history
         */
        Collection before = LoginHistoryBDFactory.factory.build().search(
                new LoginHistoryFilterParameters());
        assertTrue(before.size() == 0);

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
         * lets look at the events actually tested more thoroughly in the
         * authenwebwork/EventPublisherTest
         */
        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());

        /*
         * handle it.
         */
        new EventHandlerCommand().execute();

        assertTrue(!EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());

        /*
         * check to be sure it made it into the login history.
         */
        Collection c = LoginHistoryBDFactory.factory.build().search(
                new LoginHistoryFilterParameters());

        assertTrue(c.size() == 1);

        LoginHistory history = (LoginHistory) c.toArray()[0];
        assertTrue(history.getType().equals(LoginHistoryBD.LOGIN));
        assertTrue(history.getUsername().equals("admin"));
        assertTrue(history.getOwnerKey().equals(systemOwner.getKey()));

        /*
         * OK now let's look for the user.
         */
        ObjectMetric om = ObjectMetricServiceDelegateFactory.factory.build(
                sessionContext.getSystemOwner()).getMetric(
                sessionContext.getUser().getUser(),
                ObjectMetricTypeEnum.USER_LOGIN_COUNT);

        assertTrue(om != null);
        assertTrue(om.getMetricValue().equals("1"));
    }

    public void testLogout() throws Exception {
        /*
         * make sure we have nothing in login history
         */
        Collection before = LoginHistoryBDFactory.factory.build().search(
                new LoginHistoryFilterParameters());
        assertTrue(before.size() == 0);

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

        LogoutAction logoutAction = new LogoutAction();
        logoutAction.setSessionContext(sessionContext);
        logoutAction.execute();

        /*
         * lets look at the events actually tested more thoroughly in the
         * authenwebwork/EventPublisherTest
         */
        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());

        /*
         * handle it.
         */
        new EventHandlerCommand().execute();

        assertTrue(!EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT).hasEvents());

        /*
         * check to be sure it made it into the login history.
         */
        Collection c = LoginHistoryBDFactory.factory.build().search(
                new LoginHistoryFilterParameters());

        assertTrue(c.size() == 2);

        LoginHistory history = (LoginHistory) c.toArray()[1];
        assertTrue(history.getType().equals(LoginHistoryBD.LOGOUT));
        assertTrue(history.getUsername().equals("admin"));
        assertTrue(history.getOwnerKey().equals(systemOwner.getKey()));
    }
}