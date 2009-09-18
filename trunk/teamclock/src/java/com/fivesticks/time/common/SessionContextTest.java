/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fivesticks.time.common;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class SessionContextTest extends TestCase {

    private Log log = LogFactory.getLog(SessionContextTest.class);
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSettings() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemSettingsServiceDelegateFactory.factory.build(owner).updateSetting(
                SettingTypeEnum.SETTING_LOGO_URL, "Here's a logo A.");

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        assertTrue(sessionContext.getSettings().getLogoURL() != null);
    }

    public void testSettingsMultipleOwners() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemSettingsServiceDelegateFactory.factory.build(owner).updateSetting(
                SettingTypeEnum.SETTING_LOGO_URL, "logo1");
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(owner);

        SystemOwner owner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemSettingsServiceDelegateFactory.factory.build(owner2).updateSetting(
                SettingTypeEnum.SETTING_LOGO_URL, "logo2");
        SessionContext sessionContext2 = new SessionContext();
        sessionContext2.setSystemOwner(owner2);

        //System.out.println(sessionContext.getSettings().getLogoURL() + "\n");

        log.info("SessionContext logo - " + sessionContext.getSettings().getLogoURL());
        
        assertTrue(sessionContext.getSettings().getLogoURL().equals("logo1"));

        log.info("SessionContext2 logo - " + sessionContext2.getSettings().getLogoURL());
        
        assertTrue(sessionContext2.getSettings().getLogoURL().equals("logo2"));

    }
}