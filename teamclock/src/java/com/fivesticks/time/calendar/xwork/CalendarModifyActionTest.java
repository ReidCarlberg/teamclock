/*
 * Created on Sep 3, 2004
 *
 * 
 */
package com.fivesticks.time.calendar.xwork;

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
public class CalendarModifyActionTest extends TestCase {
    private SessionContext sessionContext;

    private SystemOwner systemOwner;

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testBasic() throws Exception {
        //        ActionContext context = new ActionContext(new HashMap());
        //        ActionContext.setContext(context);

        User user = SystemUserTestFactory.singleton.buildOwner(systemOwner);

        CalendarModifyAction action = new CalendarModifyAction();
        sessionContext = SessionContextTestFactory
                .getContext(systemOwner, user);
        //sessionContext.setSystemOwner(systemOwner);
        action.setSessionContext(sessionContext);
        action.setCalendarModifyContext(new CalendarModifyContext());

        assertTrue(action.execute().equals(CalendarModifyAction.INPUT));

    }

    //2005-11-12 RSC
//    private void setContext() {
//        ActionContext context = new ActionContext(new HashMap());
//        ActionContext.setContext(context);
//
//        CalendarModifyAction activityModifyAction = new CalendarModifyAction();
//        sessionContext = new SessionContext();
//        sessionContext.setSystemOwner(systemOwner);
//        activityModifyAction.setSessionContext(sessionContext);
//    }

}