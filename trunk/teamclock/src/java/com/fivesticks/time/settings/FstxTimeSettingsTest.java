/*
 * Created on Jun 12, 2004
 *
 */
package com.fivesticks.time.settings;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author REID
 *  
 */
public class FstxTimeSettingsTest extends TestCase {

    private SystemOwner systemOwner;

    /**
     * Constructor for FstxTimeSettingsTest.
     * 
     * @param arg0
     */
    public FstxTimeSettingsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testSettings() throws Exception {

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner);

        sd.validateSettingNotNull(SettingTypeEnum.SETTING_LOGO_URL.getName(),
                "logo");
        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK.getName(),
                "true");
        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD.getName(),
                "true");
        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE.getName(),
                "true");

        //        FstxTimeSettingsInitializer.initialize();

        //reid 2005-11-08
        SettingsInitializer init = new SettingsInitializer(systemOwner);
//        init.setSystemOwner(systemOwner);
        FstxTimeSettings settings = init.initialize();

        assertTrue(settings.getLogoURL() != null);
    }

}