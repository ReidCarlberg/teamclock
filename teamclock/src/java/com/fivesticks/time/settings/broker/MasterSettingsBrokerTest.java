/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * @author Reid
 */
public class MasterSettingsBrokerTest extends AbstractTimeTestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
//        JunitSettings.initializeTestSystem();
    }

    public void testBasicDefault() throws Exception {
        assertTrue(MasterSettingsBroker.singleton.getSettings().getSystemName() != null);
    }

    public void testWithACoupleOfOwners() throws Exception {

        String name = "NAMETEST";

        SystemOwner owner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        SystemSettingsServiceDelegateFactory.factory.build(owner1).updateSetting(
                SettingTypeEnum.SETTING_SYSTEM_NAME, name);

        SystemSettingsServiceDelegateFactory.factory.build(owner1).updateSetting(
                SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS, true);

        SystemOwner owner1a = SystemOwnerServiceDelegateFactory.factory.build().get(
                owner1.getId());

        assertTrue(owner1 != owner1a);

        assertTrue(MasterSettingsBroker.singleton.getRights(owner1)
                .isCanHaveExternalUsers());

        SystemSettingsServiceDelegateFactory.factory.build(owner1).updateSetting(
                SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS, false);

        MasterSettingsBroker.singleton.removeSettings(owner1a);

        assertTrue(!MasterSettingsBroker.singleton.getRights(owner1)
                .isCanHaveExternalUsers());

    }
    
    public void testNullUser() throws Exception {
        
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner).updateSetting(SettingTypeEnum.SETTING_TIMEZONE,"zero");
        
        FstxTimeSettings r = MasterSettingsBroker.singleton.getSettings(systemOwner, null);
        
        assertEquals("zero", r.getSystemTimeZone());
        
    }
    
    public void testNullUserWithMultipleOwners() throws Exception {
        
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner).updateSetting(SettingTypeEnum.SETTING_TIMEZONE,"zero");
        
        FstxTimeSettings r = MasterSettingsBroker.singleton.getSettings(systemOwner, null);
        
        assertEquals("zero", r.getSystemTimeZone());

        SystemOwner systemOwner2 = SystemOwnerTestFactory.getOwner();
        
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner2).updateSetting(SettingTypeEnum.SETTING_TIMEZONE,"one");

        FstxTimeSettings r2 = MasterSettingsBroker.singleton.getSettings(systemOwner2, null);
        
        assertEquals("one", r2.getSystemTimeZone());
    }
    public void testUserOverridesGeneral() throws Exception {
        SystemOwner systemOwner2 = SystemOwnerTestFactory.getOwner();
        
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner2).updateSetting(SettingTypeEnum.SETTING_TIMEZONE,"one");

        
        
        
    }

}