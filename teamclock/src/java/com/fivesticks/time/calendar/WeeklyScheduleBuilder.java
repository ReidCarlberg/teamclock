/*
 * Created on Apr 22, 2004
 *
 */
package com.fivesticks.time.calendar;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class WeeklyScheduleBuilder {

    public static WeeklyScheduleBuilder singleton = new WeeklyScheduleBuilder();

    /**
     * 
     * 
     * Precesses the bins into timeslots. Then builds the Weekly Schedule from
     * the time slots. THis knows nothing of what days are included, or the size
     * of the bins. This should be delt with in the Schedulebuilder.
     * 
     * *Assumption: All time slots, includeing empty ones, should have been
     * created by this point. *Assumption: There should be not bins out side of
     * the intended week
     * 
     * 
     * @param bins
     * @return
     */
    public WeeklySchedule build(List bins) {

        /*
         * Iterate through bins and place into time slots.
         */

        Iterator i = bins.iterator();

        Schedule tempSchedule = null;

        HashMap kTimevTimeslotMap = new HashMap();

        while (i.hasNext()) {
            DailyBin currentBin = (DailyBin) i.next();

            /*
             * Build key
             */
            SimpleDate currentSimple = SimpleDate.factory.build(currentBin
                    .getLowerBound());

            /*
             * Look up Schedule
             */
            WeeklyScheduleTimeSlot timeSlot = (WeeklyScheduleTimeSlot) kTimevTimeslotMap
                    .get(getKey(currentSimple));
            /*
             * Create a new TimeSlot if we don't already have one.
             */
            if (timeSlot == null) {
                timeSlot = new WeeklyScheduleTimeSlot();

                kTimevTimeslotMap.put(getKey(currentSimple), timeSlot);
            }

            /*
             * Add bin to timeSlot.
             */
            timeSlot.addBin(currentBin);
//            timeSlot.setSettingsBroker(settingsBroker);
        }

        Collection timeslots = kTimevTimeslotMap.values();

        return new WeeklySchedule(timeslots);

    }

    /**
     * @param currentSimple
     * @return
     */
    private Long getKey(SimpleDate currentSimple) {
        return new Long(currentSimple.getCalendar().get(Calendar.HOUR_OF_DAY));
    }

}