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

public class SettingsBrokerUserSettingsTest extends AbstractTimeTestCase {

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
        userSettingVO.setCalendarDefaultDashboardView("Week");
        userSettingVO.setCalendarDefaultTabView("Week");
        userSettingVO
                .setCalendarNotificationEmailAddress("email@fivesticks.com");
        userSettingVO.setCalendarNotificationOnOrOff("On");
        userSettingVO.setShowTimeClockStatus("Yes");

        ussd.save(this.user, userSettingVO);

        MasterSettingsBroker.singleton.resetBroker();
        
        UserSystemSettingsWrapper settings = MasterSettingsBroker.singleton.getUserSettings(this.systemOwner, this.user);

        /*
         * Now the broker should see the user setting to override the system
         * timezone.
         */
        assertEquals("America/Los_Angeles", settings.getSystemTimeZone());

        assertEquals("Week", settings.getCalendarDefaultDashboardView());
        assertEquals("Week", settings.getCalendarDefaultTabView());
        assertEquals("email@fivesticks.com", settings
                .getCalendarNotificationEmailAddress());
        assertEquals("On", settings.getCalendarNotificationOnOrOff());
        assertEquals("Yes", settings.getShowTimeClockStatus());

        assertTrue(settings.isDefaultDashboardViewWeekly());
        assertFalse(settings.isDefaultDashboardViewDaily());
        assertFalse(settings.isDefaultTabViewDaily());
        assertFalse(settings.isDefaultTabViewMonthly());
        assertTrue(settings.isDefaultTabViewWeekly());
        assertTrue(settings.isDefaultToShowingTimeClockStatus());
    }

}
