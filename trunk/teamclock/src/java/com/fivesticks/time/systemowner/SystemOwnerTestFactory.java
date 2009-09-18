/*
 * Created on Oct 25, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.util.RounderTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;

/**
 * 2004-12-29 added singleton and counter.
 * 
 * @author Reid
 */
public class SystemOwnerTestFactory {

    private static int count = 0;

    public static final SystemOwnerTestFactory singleton = new SystemOwnerTestFactory();

    public static SystemOwner getOwner() throws Exception {
        return SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
    }

    public static SystemOwner getUnpersisted() throws Exception {

        return SystemOwnerTestFactory.singleton.buildUnpersisted();
    }

    public SystemOwner build() throws Exception {

//        count++;

        SystemOwner ret = buildUnpersisted();

        ret.setKey(SystemOwnerKeyGenerator.singleton.getGeneratedKey());
        ret.setActivated(true);
        ret = SystemOwnerServiceDelegateFactory.factory.build().save(ret);
        
        return ret;
    }

    public SystemOwner buildUnpersisted() throws Exception {

        count++;

        SystemOwner ret = new SystemOwner();
        ret.setAddress1("add1" + count);
        ret.setAddress2("add2" + count);
        ret.setCity("city" + count);
        ret.setState("state" + count);
        ret.setContactEmail("contemail" + count);
        ret.setContactName("cont name" + count);
        ret.setContactPhone("contphone" + count);
        ret.setCountry("country" + count);
        ret.setName("name" + count);
        ret.setPostalCode("post" + count);

        return ret;
    }

    public SystemOwner buildWithDefaultSettings() throws Exception {

        SystemOwner ret = this.build();

        
        
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(ret);

        sd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE, "America/Chicago");
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE,
                RounderTypeEnum.ROUNDER_TENTHS.getName());
        sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_ROUNDER_TYPE,
                RounderTypeEnum.ROUNDER_TENTHS.getName());
        sd.updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_NAME, "MailFromName");
        sd.updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_ADDRESS, "reidtest1@fivesticks.com");

        return ret;
    }

    public SystemOwner buildMock() {
        return new MockSystemOwner();
    }
}