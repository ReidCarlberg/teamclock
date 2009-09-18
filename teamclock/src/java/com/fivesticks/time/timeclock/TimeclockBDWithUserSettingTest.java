package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.util.TimezoneDateTimeResolver;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * We want to be sure the bd will use the user settings to determine the
 * timezone.
 * 
 * Plan
 * 
 * Set up the system with chicago's time zone.
 * 
 * Give the user LA.
 * 
 * Punch in the user.
 * 
 * Retrieve the punch. It will be the only one in the system.
 * 
 * If we compare the punch time with the current time adjusted to LA they should
 * be with in milliseconds.
 * 
 * We get the # on min between the times, and expect it to be 0.
 * 
 * 
 * @author NIck
 * 
 */
public class TimeclockBDWithUserSettingTest extends AbstractTimeTestCase {

    private static Log log = LogFactory.getLog(TimeclockBDWithUserSettingTest.class);
    
    public void testPunchIn() throws Exception {

        TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));
        /*
         * Set Up the TimeZones.
         */
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.systemOwner);
        UserSettingServiceDelegate ussd = UserSettingServiceDelegateFactory.factory
                .build(this.systemOwner);

        /*
         * Set a system timezone.
         */
        sd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE, "America/Chicago");

        /*
         * 2005-11-08 This was the original
         */
        // /*
        // * The broker should get the new one.
        // */
        // SettingsBroker broker = new SettingsBroker();
        // broker.setOwner(this.systemOwner);
        // broker.setUser(this.user);
        //
        // assertEquals("America/Chicago", broker.getSystemTimeZone());
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
         * Now the broker should see the user setting to override the system
         * timezone. RSC 2005-11-08 Removed
         */
        // assertEquals("America/Los_Angeles", broker.getSystemTimeZone());
        TiimeclockBDFactory.factory.build(this.systemOwner, "America/Chicago",
                "").punchIn(this.user,"testip");

        Collection punches = TiimeclockBDFactory.factory.build(this.systemOwner,
                "America/Chicago", "").searchByFilter(
                new TimeclockFilterParameters());
        assertNotNull(punches);

        assertEquals(1, punches.size());

        /*
         * Let's see when the punch occuered. It should be in LA time.
         */
        Timeclock timeclock = (Timeclock) punches.toArray()[0];
        SimpleDate punchSimpleDate = SimpleDate.factory.build(timeclock
                .getEventTimestamp());

        log.info("punchSimpleDate" + punchSimpleDate.getDate());
        
        /*
         * What time is it NOW in la.
         */
        Date currentDate = TimezoneDateTimeResolver.resolveStatic(
                SimpleDate.factory.build(new Date()), "America/Chicago")
                .getDate();
        SimpleDate currentSimpleDate = SimpleDate.factory.build(currentDate);
        log.info("currentSimpleDate" + currentSimpleDate.getDate());
        
        /*
         * So we should be milliseconds between the dates..
         */

        int minutesBetween = currentSimpleDate
                .getMinutesBetween(punchSimpleDate);

        assertEquals(0, minutesBetween);

    }

}