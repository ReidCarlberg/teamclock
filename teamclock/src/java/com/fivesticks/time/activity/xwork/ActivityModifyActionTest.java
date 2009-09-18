/*
 * Created on Sep 3, 2004
 *
 * 
 */
package com.fivesticks.time.activity.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Nick
 * 
 *  
 */
public class ActivityModifyActionTest extends TestCase {
    private SessionContext sessionContext;

    private SystemOwner systemOwner;

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testBasicSuccess() throws Exception {

        User user = SystemUserTestFactory.singleton.buildOwner(systemOwner);

        ActivityModifyAction action = new ActivityModifyAction();
        action.setActivityModifyContext(new ActivityModifyContext());
        sessionContext = SessionContextTestFactory
                .getContext(systemOwner, user);
        //        sessionContext.setSystemOwner(systemOwner);
        action.setSessionContext(sessionContext);

        assertTrue(action.execute().equals(ActivityModifyAction.INPUT));

    }

//    private void setContext() {
//        ActionContext context = new ActionContext(new HashMap());
//        ActionContext.setContext(context);
//
//        ActivityModifyAction activityModifyAction = new ActivityModifyAction();
//        sessionContext = new SessionContext();
//        sessionContext.setSystemOwner(systemOwner);
//        activityModifyAction.setSessionContext(sessionContext);
//    }

}