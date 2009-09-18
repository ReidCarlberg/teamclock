/*
 * Created on Nov 14, 2003
 *
 */
package com.fivesticks.time.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 * 
 * May need to sort. For now assume the think that the Schedule Builder will
 * sort.
 */
public class Schedule implements  Serializable {
    private List bins = new ArrayList();

    private Collection displayableBins = null;

    private SimpleDate startDate;

    private WeeklySchedule weeklySchedule;

//    private SettingsBroker settingsBroker;

    private CalendarDisplayWrapperBuilder displayWrapperBuilder;

    /*
     * We need to be able to break up a schedule in to daily grouping for a week
     * period. Days that have no calendars associated still need to be created.
     * 
     * Impl:
     * 
     * We create a hash map where the key is the bins date at midnight and the
     * value is the schedule for that day. All bins on a single day will have
     * the same midnight value. Once all bins are created we can fill in the
     * missing days with empty schedules.s
     */
    public Collection getWeeklyDisplayableDays() {
        Collection days = new ArrayList();
        /*
         * Devide the bins up to days and create separate Schedules for each.
         * 
         */
        Iterator i = bins.iterator();
//        SimpleDate currentDay = null;

//        Schedule tempSchedule = null;

        HashMap kMidnightvScheduleMap = new HashMap();

        while (i.hasNext()) {
            DailyBin currentBin = (DailyBin) i.next();

            /*
             * Build key
             */
            SimpleDate currentSimple = SimpleDate.factory
                    .buildMidnight(currentBin.getLowerBound());

            /*
             * Look up Schedule
             */
            Schedule schedule = (Schedule) kMidnightvScheduleMap.get(new Long(
                    currentSimple.getDate().getTime()));
            /*
             * Create a new Schedule if we don't already have one.
             */
            if (schedule == null) {
                schedule = new Schedule();
                schedule.setStartDate(currentSimple);
                kMidnightvScheduleMap.put(new Long(currentSimple.getDate()
                        .getTime()), schedule);
            }

            /*
             * Add bin to schdeule.
             */
            schedule.addBin(currentBin);
        }

        days = kMidnightvScheduleMap.values();

        return days;

    }

    public WeeklySchedule getWeeklySchedule() {
        if (weeklySchedule == null) {
            weeklySchedule = WeeklyScheduleBuilder.singleton.build(bins);

        }

//        weeklySchedule.setSettingsBroker(this.getSettingsBroker());
        return weeklySchedule;
    }

    /**
     * @param currentSimple
     */
    private void setStartDate(SimpleDate startDate) {
        this.startDate = startDate;
    }

//    private SimpleDate getStartDate() {
//        return this.startDate;
//    }

    public String getFormattedStartDate() {
        if (startDate == null) {
            return "";
        }
        return startDate.getMmddyyyy();

    }

    public void addBin(Bin bin) {
        bins.add(bin);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Iterator i = bins.iterator();
        while (i.hasNext()) {
            buffer.append(i.next()).append("\n");
        }

        return buffer.toString();
    }

    public List getBins() {
        return bins;
    }

    public Collection getDisplayableBins() {
        if (displayableBins == null) {
            displayableBins = new ArrayList();
            Iterator i = bins.iterator();

            while (i.hasNext()) {
                DailyBin current = (DailyBin) i.next();

                DailyBinDisplayWrapper currentDisplay = this.getDisplayWrapperBuilder().build(current);

                // DailyBinDisplayWrapper currentDisplay = new
                // DailyBinDisplayWrapper(
                // current);

//                currentDisplay.setSettingsBroker(this.getSettingsBroker());
                displayableBins.add(currentDisplay);
            }
        }

        return displayableBins;
    }



    public CalendarDisplayWrapperBuilder getDisplayWrapperBuilder() {
        return displayWrapperBuilder;
    }

    public void setDisplayWrapperBuilder(
            CalendarDisplayWrapperBuilder displayWrapperBuilder) {
        this.displayWrapperBuilder = displayWrapperBuilder;
    }
}