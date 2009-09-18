/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.common.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
public class TimeclockEventTimestampRounderTenthsHourImplTest extends TestCase {

    /**
     * Constructor for TimeclockEventTimestampRounderTenthsHourImplTest.
     * 
     * @param arg0
     */
    public TimeclockEventTimestampRounderTenthsHourImplTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testZero() throws Exception {

        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MINUTE, 0);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 0);

    }

    public void testTwo() throws Exception {

        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MINUTE, 2);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 0);

    }

    public void testEight() throws Exception {
        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MINUTE, 3);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 6);

    }

    public void testThirtySeven() throws Exception {
        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MINUTE, 32);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 30);

    }

    public void testThirtyEight() throws Exception {
        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MINUTE, 33);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 36);

    }

    public void testEndOfDay() throws Exception {
        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.DAY_OF_MONTH, 5);
        sd.getCalendar().set(Calendar.MINUTE, 58);
        sd.getCalendar().set(Calendar.HOUR_OF_DAY, 23);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 0);
        assertTrue(retCheck.getCalendar().get(Calendar.HOUR) == 0);

        assertTrue(retCheck.getCalendar().get(Calendar.DAY_OF_MONTH) == sd
                .getCalendar().get(Calendar.DAY_OF_MONTH) + 1);

    }

    public void testEndOfDayAndMonth() throws Exception {
        SimpleDate sd = SimpleDate.factory.build();

        sd.getCalendar().set(Calendar.MONTH, 2);
        sd.getCalendar().set(Calendar.DAY_OF_MONTH, 31);
        sd.getCalendar().set(Calendar.MINUTE, 58);
        sd.getCalendar().set(Calendar.HOUR_OF_DAY, 23);

        Date ret = new RounderTenthsImpl().round(sd.getDate());

        SimpleDate retCheck = SimpleDate.factory.build(ret);

        assertTrue(retCheck.getCalendar().get(Calendar.MINUTE) == 0);
        assertTrue(retCheck.getCalendar().get(Calendar.HOUR) == 0);

        assertTrue(retCheck.getCalendar().get(Calendar.DAY_OF_MONTH) == 1);
        assertTrue(retCheck.getCalendar().get(Calendar.MONTH) == sd
                .getCalendar().get(Calendar.MONTH) + 1);

    }

}