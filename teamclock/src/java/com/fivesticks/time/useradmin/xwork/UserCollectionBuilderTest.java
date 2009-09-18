/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * @author REID
 */
public class UserCollectionBuilderTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testInternalOnly() throws Exception {

        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemSettingsServiceDelegate settingssd = SystemSettingsServiceDelegateFactory.factory
                .build(systemOwner);

        settingssd.updateSetting(SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        settingssd.updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), 3);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t2",
                "t1", "t2@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t3",
                "t1", "t3@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t4",
                "t4", "t4@fivesticks.com", UserTypeEnum.TIMECLOCK);

        Collection internalBefore = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);

        assertTrue(internalBefore.size() == 4);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t5",
                "t5", "t5@fivesticks.com", UserTypeEnum.EXTERNAL);

        Collection internalAfter = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);

        assertTrue(internalAfter.size() == 4);

    }

    public void testExternalUserListBuilder() throws Exception {

        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemSettingsServiceDelegate settingssd = SystemSettingsServiceDelegateFactory.factory
                .build(systemOwner);

        settingssd.updateSetting(SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        settingssd.updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), 3);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t1",
                "t1", "t1@fivesticks.com", UserTypeEnum.OWNERADMIN);

        UserServiceDelegateFactory.factory.build(systemOwner).createNewUser("t2",
                "t1", "t2@fivesticks.com", UserTypeEnum.EXTERNAL);

        Collection total = new UserCollectionBuilder().build(systemOwner);

        assertTrue(total.size() == 2);

        Collection ext = new UserCollectionBuilder().buildExternal(systemOwner);

        assertTrue(ext.size() == 1);

        Collection internal = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);

        assertTrue(internal.size() == 1);

    }

}