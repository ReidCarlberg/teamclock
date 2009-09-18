package com.fivesticks.time.calendar;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
class ScheduleBuilderMinuteImpl implements ScheduleBuilder {

    private CalendarFilterParameters filter;
    
    private SystemOwner systemOwner;

//    private long calendarNormalDayStarts;
//    
//    private long calendarNormalDayEnds;
    
    public ScheduleBuilderMinuteImpl(SystemOwner systemOwner, CalendarFilterParameters filter) {
        super();
        this.filter = filter;
        this.setSystemOwner(systemOwner);
//        this.calendarNormalDayStarts = start;
//        this.calendarNormalDayEnds = ends;
    }

    public Schedule build() throws ScheduleBuilderException {

        Schedule schedule = new Schedule();

        handleBuildDay(schedule, 1, filter.getStart().getDate());

        List calendars = CalendarDAOFactory.factory.build().find(filter);

        mergeCalendars(schedule, calendars);

        return schedule;
    }

    private void mergeCalendars(Schedule schedule, List calendars) {

        Iterator i = calendars.iterator();
        TcCalendar calTemp;
        while (i.hasNext()) {

            calTemp = (TcCalendar) i.next();
            Iterator subI = schedule.getBins().iterator();
            Bin binTemp;
            while (subI.hasNext()) {
                binTemp = (Bin) subI.next();
                if (binTemp.fit(calTemp)) {
                    binTemp.addCalendar(calTemp);
                }
            }
        }

    }

    private void handleBuildDay(Schedule schedule, int binSizeInMinutes,
            Date date) {
        int minutes = 0;

        int hoursTemp = 0;
        int minTemp = 0;
        while (minutes < MINUTES_PER_DAY) {

            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            String label = hoursTemp + ":" + minTemp;
            //Get lower bound
            SimpleDate simpleDateLowerBound = SimpleDate.factory.build(date);
            simpleDateLowerBound.setHours(hoursTemp);
            simpleDateLowerBound.setMinutes(minTemp);
            simpleDateLowerBound.setSeconds(0);

            //Get upperbound
            minutes += binSizeInMinutes;
            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            SimpleDate simpleDateUpperBound = SimpleDate.factory.build(date);
            simpleDateUpperBound.setHours(hoursTemp);
            simpleDateUpperBound.setMinutes(minTemp - 1);
            simpleDateUpperBound.setSeconds(0);

            //            DailyBin bin = new DailyBin(simpleDateLowerBound.getDate(),
            //                    simpleDateUpperBound.getDate(), label);

            DailyBin bin = new DailyBin();

            bin.setLowerBound(simpleDateLowerBound.getDate());
            bin.setUpperBound(simpleDateUpperBound.getDate());
            bin.setLabel(label);

            schedule.addBin(bin);

        }

    }

//    private static int MINUTES_PER_DAY = 60 * 24;

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}