package com.fivesticks.time.useradmin.settings;

import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * 2005-10-6 This is a VO to contain all the settings.
 * 
 * @author Nick
 */
public class UserSettingServiceDelegateTest extends AbstractTimeTestCase {

    public void testCustomerSettings() throws Exception,
            ObjectMetricNotFoundException {
        UserSettingServiceDelegate cssd = UserSettingServiceDelegateFactory.factory
                .build(this.systemOwner);
        UserSettingVO vo = cssd.find(this.user);
        assertNull(vo.getCalendarDefaultDashboardView());
        assertEquals(UserSettingVO.DAY, vo.getCalendarDefaultTabView());
        assertNull(vo.getCalendarNotificationEmailAddress());
        assertNull(vo.getCalendarNotificationOnOrOff());

        vo.setCalendarDefaultDashboardView((String) vo
                .getStandardCalendarDefaultDashboardViewOptions().toArray()[0]);
        vo.setCalendarDefaultTabView((String) vo
                .getStandardCalendarDefaultTabViewOptions().toArray()[0]);
        vo.setCalendarNotificationEmailAddress("fsfsd");
        vo.setCalendarNotificationOnOrOff((String) vo
                .getStandardCalendarNotificationOnOrOffOptions().toArray()[0]);

        cssd.save(this.user, vo);

        UserSettingVO voReload = cssd.find(this.user);

        assertEquals((String) vo
                .getStandardCalendarDefaultDashboardViewOptions().toArray()[0],
                voReload.getCalendarDefaultDashboardView());
        assertEquals((String) vo.getStandardCalendarDefaultTabViewOptions()
                .toArray()[0], voReload.getCalendarDefaultTabView());
        assertEquals("fsfsd", voReload.getCalendarNotificationEmailAddress());
        assertEquals((String) vo
                .getStandardCalendarNotificationOnOrOffOptions().toArray()[0],
                voReload.getCalendarNotificationOnOrOff());

    }
}
