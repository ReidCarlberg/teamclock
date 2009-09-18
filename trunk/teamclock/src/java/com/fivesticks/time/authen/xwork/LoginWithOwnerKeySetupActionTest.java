/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 */
public class LoginWithOwnerKeySetupActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {
        Map map = new HashMap();
        ActionContext actionContext = new ActionContext(map);
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        LoginWithOwnerKeySetupAction action = new LoginWithOwnerKeySetupAction();
        action.setSessionContext(new SessionContext());

        SystemOwner defaultInstalledOwner = SystemOwnerServiceDelegateFactory.factory
                .build().get(UserBDFactory.factory.build().getByUsername("admin"));

        action.setTarget(defaultInstalledOwner.getKey());

        String result = action.execute();

        assertTrue(result.equals(LoginWithOwnerKeySetupAction.SUCCESS));

        assertTrue(action.getSessionContext().getSettings().getLogoURL() != null);

    }

}