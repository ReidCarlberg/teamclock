/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import java.util.Collection;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * Works with multiple owners to be sure all is good.
 * 
 * @author Reid
 */
public class GeneralLoginActionMultipleOwnerTest extends TestCase {

    Log log = LogFactory.getLog(GeneralLoginActionMultipleOwnerTest.class);
    
    int ownerCount = 2;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem(ownerCount);
    }

    public void testSetup() throws Exception {
        //let's make sure this works.
        /*
         * Yes, btw, this did fail the first time.
         */
    }

    /*
     * this includes some hard coded info from the datasetup. namely that the
     * system name will end with a 1 for the second default system we created.
     */
    public void testLoginWithOwnerKey_BasicSetup() throws Exception {

        if (ownerCount < 2)
            throw new RuntimeException(
                    "can't use this with less than 2 owners.");

        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();
        assertTrue(owners.size() == ownerCount);

        SystemOwner owner1 = (SystemOwner) owners.toArray()[0];
        SystemOwner owner2 = (SystemOwner) owners.toArray()[1];

        SessionContext session1 = new SessionContext();
        SessionContext session2 = new SessionContext();

        GeneralLoginAction login1 = new GeneralLoginAction();
        login1.setSessionContext(session1);
        login1.setTarget(owner1.getKey());
        login1.execute();
        log.info(session1.getSettings().getSystemName());
        assertTrue(session1.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com"));

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.setTarget(owner2.getKey());
        login2.execute();
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com2"));

    }

    /*
     * this includes some hard coded info from the datasetup. namely that the
     * system name will end with a 1 for the second default system we created.
     */
    public void testLoginWithOwnerKey_TimeclockLogin() throws Exception {

        if (ownerCount < 2)
            throw new RuntimeException(
                    "can't use this with less than 2 owners.");

        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();
        assertTrue(owners.size() == ownerCount);

        SystemOwner owner1 = (SystemOwner) owners.toArray()[0];
        SystemOwner owner2 = (SystemOwner) owners.toArray()[1];

        SessionContext session1 = new SessionContext();
        SessionContext session2 = new SessionContext();

        GeneralLoginAction login1 = new GeneralLoginAction();
        login1.setSessionContext(session1);
        login1.setTarget(owner1.getKey());
        login1.execute();
        assertTrue(session1.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com"));
        login1.setUsername("timeclock");
        login1.setPassword("timeclock");
        login1.setLoginButton("submit");
        String r1 = login1.execute();
        assertTrue(r1
                .equalsIgnoreCase(GlobalForwardStatics.GLOBAL_TIMECLOCK_DASHBOARD));

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.setTarget(owner2.getKey());
        login2.execute();
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com2"));
        login2.setUsername("timeclock2");
        login2.setPassword("timeclock2");
        login2.setLoginButton("submit");
        assertTrue(login2.execute().equalsIgnoreCase(
                GlobalForwardStatics.GLOBAL_TIMECLOCK_DASHBOARD));

    }

    /*
     * this includes some hard coded info from the datasetup. namely that the
     * system name will end with a 1 for the second default system we created.
     */
    public void testLoginWithOwnerKey_StandardLogin() throws Exception {

        if (ownerCount < 2)
            throw new RuntimeException(
                    "can't use this with less than 2 owners.");

        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();
        assertTrue(owners.size() == ownerCount);

        SystemOwner owner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user1 = SystemUserTestFactory.singleton.buildOwner(owner1);
        String user1pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(user1,user1pw);
        
        SystemSettingsServiceDelegateFactory.factory.build(owner1).updateSetting(
                SettingTypeEnum.SETTING_SYSTEM_NAME, "XXNAME1");

        SystemOwner owner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User user2 = SystemUserTestFactory.singleton.buildOwner(owner2);
        String user2pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(user2, user2pw);
        
        SystemSettingsServiceDelegateFactory.factory.build(owner2).updateSetting(
                SettingTypeEnum.SETTING_SYSTEM_NAME, "XXNAME2");

        SessionContext session1 = SessionContextTestFactory.getContext(owner1,
                user1);
        SessionContext session2 = SessionContextTestFactory.getContext(owner2,
                user2);

        GeneralLoginAction login1 = new GeneralLoginAction();
        login1.setSessionContext(session1);
        login1.setTarget(owner1.getKey());
        login1.execute();

        
        assertTrue(session1.getSettings().getSystemName().equalsIgnoreCase(
                "XXNAME1"));

        login1.setUsername(user1.getUsername());
        login1.setPassword(user1pw);
        login1.setLoginButton("submit");
        String r1 = login1.execute();
        assertTrue(r1.equalsIgnoreCase(ActionSupport.SUCCESS));

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.setTarget(owner2.getKey());
        login2.execute();
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "XXNAME2"));
        login2.setUsername(user2.getUsername());
        login2.setPassword(user2pw);
        login2.setLoginButton("submit");
        assertTrue(login2.execute().equalsIgnoreCase(ActionSupport.SUCCESS));

    }

    /*
     * this includes some hard coded info from the datasetup. namely that the
     * system name will end with a 1 for the second default system we created.
     */
    public void testLoginWithOwnerKey_ExternalLogin() throws Exception {

        if (ownerCount < 2)
            throw new RuntimeException(
                    "can't use this with less than 2 owners.");

        /*
         * rsc 2005-05-23 using this by id to ensure they come back in the correct order.
         * 
         */
        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAllById();
        assertTrue(owners.size() == ownerCount);

        SystemOwner owner1 = (SystemOwner) owners.toArray()[0];
        SystemOwner owner2 = (SystemOwner) owners.toArray()[1];

        SessionContext session1 = new SessionContext();
        SessionContext session2 = new SessionContext();

        GeneralLoginAction login1 = new GeneralLoginAction();
        login1.setSessionContext(session1);
        login1.setTarget(owner1.getKey());
        login1.execute();
        
        log.info("Session 1 system name: " + session1.getSettings().getSystemName());
        
        assertTrue(session1.getSettings().getSystemName().startsWith(
                "TeamClock.com"));
        login1.setUsername("external");
        login1.setPassword("external");
        login1.setLoginButton("external");
        String r1 = login1.execute();
        assertTrue(r1
                .equalsIgnoreCase(GlobalForwardStatics.GLOBAL_EXTERNAL_DASHBOARD));

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.setTarget(owner2.getKey());
        login2.execute();
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com2"));
        login2.setUsername("external2");
        login2.setPassword("external2");
        login2.setLoginButton("submit");
        assertTrue(login2.execute().equalsIgnoreCase(
                GlobalForwardStatics.GLOBAL_EXTERNAL_DASHBOARD));

    }

    public void testLoginWithoutKey() throws Exception {

        SessionContext session2 = new SessionContext();

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.execute();
        login2.setUsername("standard2");
        login2.setPassword("standard2");
        login2.setLoginButton("submit");
        assertTrue(login2.execute().equalsIgnoreCase(ActionSupport.SUCCESS));
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com2"));

    }

    public void testLoginWithoutKey_otherowner() throws Exception {

        SessionContext session2 = new SessionContext();

        GeneralLoginAction login2 = new GeneralLoginAction();
        login2.setSessionContext(session2);
        login2.execute();
        login2.setUsername("standard");
        login2.setPassword("standard");
        login2.setLoginButton("submit");
        assertTrue(login2.execute().equalsIgnoreCase(ActionSupport.SUCCESS));
        assertTrue(session2.getSettings().getSystemName().equalsIgnoreCase(
                "teamclock.com"));

    }

}