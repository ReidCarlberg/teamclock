/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityFilterVO;
import com.fivesticks.time.activity.ActivityTestFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

public class ActivityListActionTest extends TestCase {
    private SessionContext sessionContext;

    private SystemOwner systemOwner;

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testFilter() throws Exception {
        //        ActionContext context = new ActionContext(new HashMap());
        //        ActionContext.setContext(context);

        User user1 = SystemUserTestFactory.singleton.buildOwner(systemOwner);

        ActivityListAction activityListAction = new ActivityListAction();
        sessionContext = SessionContextTestFactory.getContext(systemOwner,
                user1);
        //        sessionContext.setSystemOwner(systemOwner);
        activityListAction.setSessionContext(sessionContext);

        createSampleActivityBySampleBuild();

        ActivityListContext activityListContext = new ActivityListContext();

        activityListAction.setActivityListContext(activityListContext);

        activityListAction.setSearchActivities("search");

        activityListAction.execute();
        // assertTrue(activityListAction.execute().equals(activityListAction.INPUT));
        assertTrue(activityListAction.getParentCollection() != null);

        //not grouping by user, so this is just 1
        assertTrue(activityListAction.getParentCollection().size() == 1);

    }
    
    /**
     * reproducing an error from the live site.
     * 
     * @throws Exception
     */
    public void testGroupByUser() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        Activity act1 = ActivityTestFactory.singleton.build(owner, user);
        
        SessionContext sessionContext = SessionContextTestFactory.getContext(owner,user);
        
        ActivityListAction action = new ActivityListAction();
        
        action.setActivityListContext(new ActivityListContext());
        
        action.getActivityListContext().setParams(new ActivityFilterVO());
        
        action.setSessionContext(sessionContext);
        
        action.setGroupByUser(true);
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
        
        
        
    }



    private void createSampleActivityBySampleBuild() throws Exception {
        ActivityTestFactory fasf = new ActivityTestFactory();
        ActivityTestFactory fasf2 = new ActivityTestFactory();

        User u =SystemUserTestFactory.singleton.buildOwner(systemOwner);

        User u2 = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        
        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, u,
                UserTypeEnum.OWNERADMIN);
        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, u2,
                UserTypeEnum.OWNERADMIN);

        fasf.build(systemOwner, u);
        fasf2.build(systemOwner, u2);
    }
}