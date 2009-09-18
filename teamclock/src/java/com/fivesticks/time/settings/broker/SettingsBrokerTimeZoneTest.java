/*
 * Created on Oct 20, 2005
 *
 * 
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;

public class SettingsBrokerTimeZoneTest extends AbstractTimeTestCase {

    public void testSettingsTimeZone() throws Exception {
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner);
        UserSettingServiceDelegate ussd = UserSettingServiceDelegateFactory.factory
                .build(this.systemOwner);

        /*
         * Set a system timezone.
         */
        sd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE, "America/Chicago");

        /*
         * The broker should get the new one.
         */
//        SettingsBroker broker = new SettingsBroker();
//        broker.setOwner(this.systemOwner);
//        broker.setUser(this.user);

        FstxTimeSettings broker = MasterSettingsBroker.singleton.getSettings(this.systemOwner, this.user);

        assertEquals("America/Chicago", broker.getSystemTimeZone());

        /*
         * Lets set a user pref.
         */
        UserSettingVO userSettingVO = ussd.find(this.user);
        assertEquals(null, userSettingVO.getUserTimeZone());

        userSettingVO.setUserTimeZone("America/Los_Angeles");
        ussd.save(this.user, userSettingVO);

        assertEquals("America/Los_Angeles", ussd.find(this.user)
                .getUserTimeZone());

        /*
         * For now we'll clear out the master settings broker manually. 
         */
        MasterSettingsBroker.singleton.resetBroker();
        
        /*
         * Now the broker should see the user setting to override the 
         * system timezone.
         */
         assertEquals("America/Los_Angeles", MasterSettingsBroker.singleton.getSettings(this.systemOwner, this.user).getSystemTimeZone());  
    }

    
    
        public void testSettingsTimeZoneInvalidZone() throws Exception {
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner);
        UserSettingServiceDelegate ussd = UserSettingServiceDelegateFactory.factory
                .build(this.systemOwner);

        /*
         * Set a system timezone.
         */
        sd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE, "America/Chicago");

        /*
         * The broker should get the new one.
         */
//        SettingsBroker broker = new SettingsBroker();
//        broker.setOwner(this.systemOwner);
//        broker.setUser(this.user);

        FstxTimeSettings broker = MasterSettingsBroker.singleton.getSettings(this.systemOwner, this.user);

        
        assertEquals("America/Chicago", broker.getSystemTimeZone());

        /*
         * Lets set a user pref. and give it an invalid time zone
         */
        UserSettingVO userSettingVO = ussd.find(this.user);
        assertEquals(null, userSettingVO.getUserTimeZone());

        userSettingVO.setUserTimeZone("Whoo/Hoo");
        ussd.save(this.user, userSettingVO);

        assertEquals("Whoo/Hoo", ussd.find(this.user)
                .getUserTimeZone());

        /*
         * Since we have garabage in the user tz we revert back to the 
         * Systems.
         */
         assertEquals("America/Chicago", broker.getSystemTimeZone());

        
         
         
         
    }
    
}
