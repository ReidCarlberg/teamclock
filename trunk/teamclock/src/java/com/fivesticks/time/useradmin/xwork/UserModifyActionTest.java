/*
 * Created on Sep 15, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.HashMap;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyGenerator;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeDeterminator;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 */
public class UserModifyActionTest extends TestCase {

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {

        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicActionInput() throws Exception {

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());
        assertTrue(action.execute().equals(UserModifyAction.INPUT));
    }

    public void testBasicActionInputWithTarget() throws Exception {
        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        String ADMIN = "admin";

        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername(ADMIN));

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);

        action.setTarget(ADMIN);

        assertTrue(action.execute().equals(UserModifyAction.INPUT));

        assertTrue(action.getUserEmailString() != null);

        assertTrue(action.getUserEmailString().equals("admin@fivesticks.com"));

        assertTrue(action.getUserModifyContext().getTarget() != null);

    }

    public void testCancel() throws Exception {
        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        String ADMIN = "admin";

        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername(ADMIN));

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);
        action.setTarget(ADMIN);
        action.setCancelUser("cancel");

        assertTrue(action.execute().equals(UserModifyAction.SUCCESS));
    }

    public void testSubmitWithEmailChange() throws Exception {
        String ADMIN = "admin";

        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername(ADMIN));

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);

        action.setTarget(ADMIN);

        action.execute();

        //        assertTrue(action.getUserAndType() != null);
        //        
        //        assertTrue(action.getUserAndType().getUser().getUsername().equals("admin"));

        /*
         *  
         */
        UserModifyContext temp = action.getUserModifyContext();
        action = new UserModifyAction();
        action.setUserModifyContext(temp);

        action.setSessionContext(sessionContext);

        action.setSubmitUser("submit");

        action.setUserEmailString("admin-new@fivesticks.com");

        action.setUserTypeString(UserTypeEnum.OWNERADMIN.getName());

        assertTrue(action.execute().equals(UserModifyAction.SUCCESS));

        User newUser = UserBDFactory.factory.build().getUserByEmail(
                "admin-new@fivesticks.com");

        assertTrue(newUser != null);

        assertTrue(newUser.getUsername().equals("admin"));

    }

    public void testSubmitWithUserTypeChange() throws Exception {

        String ADMIN = "admin";

        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername(ADMIN));

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);

        action.setTarget(ADMIN);

        action.execute();

        //        assertTrue(action.getUserAndType() != null);
        //        
        //        assertTrue(action.getUserAndType().getUser().getUsername().equals("admin"));

        /*
         *  
         */
        UserModifyContext temp = action.getUserModifyContext();
        action = new UserModifyAction();
        action.setUserModifyContext(temp);

        action.setSessionContext(sessionContext);

        action.setSubmitUser("submit");

        action.setUserEmailString("admin@fivesticks.com");

        action.setUserTypeString(UserTypeEnum.TIMECLOCK.getName());

        assertTrue(action.execute().equals(UserModifyAction.SUCCESS));

        User newUser = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        assertTrue(newUser != null);

        assertTrue(newUser.getUsername().equals("admin"));

        assertTrue(new UserTypeDeterminator().getUserType(systemOwner, newUser)
                .getName().equals(UserTypeEnum.TIMECLOCK.getName()));

    }

    public void testAttemptToModifyUserOfAnotherOwner() throws Exception {

        /*
         * setting up some other test criteria
         */
        SystemOwner tempSystemOwner = new SystemOwner();
        tempSystemOwner.setActivated(true);
        tempSystemOwner.setName("tempName");
        tempSystemOwner.setKey(SystemOwnerKeyGenerator.singleton
                .getGeneratedKey());
        tempSystemOwner.setAddress1("add1");
        tempSystemOwner.setCity("city");
        tempSystemOwner.setState("tat");
        tempSystemOwner.setPostalCode("post");
        tempSystemOwner.setContactEmail("rsc21");
        tempSystemOwner.setContactName("name");
        tempSystemOwner.setContactPhone("phone");
        tempSystemOwner.setCountry("country");
        SystemOwnerServiceDelegateFactory.factory.build().save(tempSystemOwner);

        User tempUser = UserBDFactory.factory.build().addUser("temp1", "temp1",
                "temp1@fivesticks.com");

        SystemUserServiceDelegateFactory.factory.build().associate(tempSystemOwner,
                tempUser, null);

        /*
         * ok now let's try to modify it.
         */
        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));

        SessionContext sessionContext = new SessionContext();

        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);

        action.setTarget(tempUser.getUsername());

        String result = action.execute();

        assertTrue(!result.equals(UserModifyAction.SUCCESS));

    }

    public void testAttempToModifyEmailToAnotherThatAlreadyExistsForAnotherUser()
            throws Exception {

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        /*
         * setting up some other test criteria
         */
        SystemOwner tempSystemOwner = new SystemOwner();
        tempSystemOwner.setActivated(true);
        tempSystemOwner.setName("tempName");
        tempSystemOwner.setKey(SystemOwnerKeyGenerator.singleton
                .getGeneratedKey());
        tempSystemOwner.setAddress1("add1");
        tempSystemOwner.setCity("city");
        tempSystemOwner.setState("tat");
        tempSystemOwner.setPostalCode("post");
        tempSystemOwner.setContactEmail("rsc21");
        tempSystemOwner.setContactName("name");
        tempSystemOwner.setContactPhone("phone");
        tempSystemOwner.setCountry("country");
        SystemOwnerServiceDelegateFactory.factory.build().save(tempSystemOwner);

        User tempUser = UserBDFactory.factory.build().addUser("temp1", "temp1",
                "temp1@fivesticks.com");

        SystemUserServiceDelegateFactory.factory.build().associate(tempSystemOwner,
                tempUser, null);

        /*
         * ok now let's try to modify it.
         */
        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));

        SessionContext sessionContext = new SessionContext();

        sessionContext.setSystemOwner(systemOwner);

        action.setSessionContext(sessionContext);

        action.setTarget("admin");

        String result = action.execute();

        assertTrue(result.equals(UserModifyAction.INPUT));

        /*
         * 2nd part of the request
         */
        UserModifyContext temp = action.getUserModifyContext();
        action = new UserModifyAction();
        action.setUserModifyContext(temp);

        action.setSessionContext(sessionContext);

        action.setSubmitUser("submit");

        action.setUserEmailString("temp1@fivesticks.com");

        action.setUserTypeString(UserTypeEnum.TIMECLOCK.getName());

        assertTrue(action.execute().equals(UserModifyAction.INPUT));

    }

    public void testUpdateUserTypeFromTimeclockToOtherFails() throws Exception {
        /*
         * when updating from a timeclock to something else, the action should
         * fail if the update would result in too many non timeclock active
         * users.
         */
        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setSession(new HashMap());
        ActionContext.setContext(actionContext);

        /*
         * setting up some other test criteria
         */
        SystemOwner tempSystemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        SystemSettingsServiceDelegate settingssd = SystemSettingsServiceDelegateFactory.factory
                .build(tempSystemOwner);

        settingssd.updateSetting(SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        settingssd.updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), 3);

        UserServiceDelegateFactory.factory.build(tempSystemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(tempSystemOwner).createNewUser("t2",
                "t1", "t2@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(tempSystemOwner).createNewUser("t3",
                "t1", "t3@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(tempSystemOwner).createNewUser("t4",
                "t4", "t4@fivesticks.com", UserTypeEnum.TIMECLOCK);

        /*
         * ok now let's try to modify it.
         */
        UserModifyAction action = new UserModifyAction();
        action.setUserModifyContext(new UserModifyContext());
        SystemOwner systemOwner = tempSystemOwner;
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);
        assertTrue(sessionContext.getSettings().getMaxActiveUsers() == 3);

        action.setSessionContext(sessionContext);
        action.setTarget("t4");
        String result = action.execute();
        assertTrue(result.equals(UserModifyAction.INPUT));

        /*
         * 2nd part of the request
         */
        UserModifyContext temp = action.getUserModifyContext();
        action = new UserModifyAction();
        action.setUserModifyContext(temp);
        action.setSessionContext(sessionContext);

        action.setSubmitUser("submit");

        action.setUserEmailString("t4@fivesticks.com");

        action.setUserTypeString(UserTypeEnum.PRIVILEGED.getName());

        assertTrue(action.execute().equals(UserModifyAction.INPUT));

        assertTrue(action.hasErrors());

    }
}