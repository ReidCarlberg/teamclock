/*
 * Created on Nov 14, 2003
 *
 */
package com.fivesticks.time.calendar;

import java.util.Calendar;
import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class ScheduleBuilderWeeklyImpl extends AbstractScheduleBuilderImpl {

    private int binSizeInMinutes;
    
    private long dayStarts;
    
    private long dayEnds;
    
    private final Date resolvedNow;
    
    private int minutesInSchedule;

    public ScheduleBuilderWeeklyImpl(SystemOwner systemOwner, CalendarFilterParameters filter,
            int minutes, long dayStarts, long dayEnds, Date resolvedNow, int minutesInSchedule) {
        super(filter);

        this.binSizeInMinutes = minutes;
        this.setSystemOwner(systemOwner);
        this.dayStarts = dayStarts;
        this.dayEnds = dayEnds;
        this.resolvedNow = resolvedNow;
        this.minutesInSchedule = minutesInSchedule;
    }

    protected void handleBuildBins(Schedule schedule, Date startDate,
            Date stopDate) {
        int minutes = 0;

        //binSizeInMinutes = 60;
        int hoursTemp = 0;
        int minTemp = 0;

        while (minutes < minutesInSchedule) {

            hoursTemp = (minutes / 60);
            minTemp = minutes % 60;

            //Get lower bound
            SimpleDate simpleDateLowerBound = SimpleDate.factory
                    .build(startDate);

            /*
             * We don't have to advance the day as it is a side effect of
             * setting Hours to a number greater that 24.
             * 
             * Thus setHours(50) will set it to 2 day in the future at 2 am.
             */
            //	simpleDateLowerBound.advanceDay(minutes/MINUTES_PER_DAY );
            simpleDateLowerBound.setHours(hoursTemp);
            simpleDateLowerBound.setMinutes(minTemp);
            simpleDateLowerBound.setSeconds(0);

            String label = simpleDateLowerBound.getMmddyyyy() + " " + hoursTemp
                    + ":" + minTemp;

            //Get upperbound
            minutes += binSizeInMinutes;
            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            //		
            SimpleDate simpleDateUpperBound = SimpleDate.factory
                    .build(startDate);
            //	simpleDateUpperBound.advanceDay(minutes/MINUTES_PER_DAY );
            simpleDateUpperBound.setHours(hoursTemp);
            simpleDateUpperBound.setMinutes(minTemp - 1);
            simpleDateUpperBound.setSeconds(0);

            //            DailyBin bin = new DailyBin(simpleDateLowerBound.getDate(),
            //                    simpleDateUpperBound.getDate(), label);

            DailyBin bin = new DailyBin();

            //2006-06-28
            bin.setResolvedNow(this.resolvedNow);
            
            bin.setLowerBound(simpleDateLowerBound.getDate());
            bin.setUpperBound(simpleDateUpperBound.getDate());
            bin.setLabel(label);

            if (simpleDateLowerBound.getCalendar().get(Calendar.HOUR_OF_DAY) >= this.dayStarts
                    && simpleDateUpperBound.getCalendar().get(Calendar.HOUR_OF_DAY) < this.dayEnds) {
                schedule.addBin(bin);    
            }
            

        }

    }

////    private static int MINUTES_PER_DAY = 60 * 24;
//
//    private static int MINUTES_PER_WEEK = 60 * 24 * 7;

    protected CalendarDisplayWrapperBuilder getDisplayWrapperBuilder() {
        
        return new WeeklyCalendarDisplayWrapperBuilder();
    }
}