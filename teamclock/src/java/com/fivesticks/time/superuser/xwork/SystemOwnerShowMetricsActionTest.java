/*
 * Created on Jan 5, 2005 by REID
 */
package com.fivesticks.time.superuser.xwork;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author REID
 */
public class SystemOwnerShowMetricsActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    /*
     * Class under test for String execute()
     */
    public void testExecute() throws Exception {
        Map map = new HashMap();
        ActionContext actionContext = new ActionContext(map);
        ActionContext.setContext(actionContext);

        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        ObjectMetricServiceDelegateFactory.factory.build(systemOwner).setValue(
                systemOwner, ObjectMetricTypeEnum.__TEST, "xyz");

        User user = UserFactory.singleton
                .getPersistedWithSystemOwner(systemOwner);
        AuthenticatedUser auser = AuthenticatedUserFactory.factory.build(user);
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);
        sessionContext.setUser(auser);
        SystemOwnerShowMetricsAction action = new SystemOwnerShowMetricsAction();
        action.setSessionContext(sessionContext);

        action.setTarget(systemOwner.getId());

        action.execute();

        assertTrue(!action.hasErrors());
        assertTrue(action.getMetrics() != null);
        assertTrue(action.getMetrics().size() == 1);

    }

}