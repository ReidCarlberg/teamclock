/*
 * Created on Dec 16, 2003
 *
 */
package com.fivesticks.time.calendar;

import java.util.Calendar;
import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 * 
 */
public class CalendarFilterDecorator {

    public void decorateForToday(CalendarFilterParameters filter) {
        decorateForSimpleDate(filter, SimpleDate.factory.buildMidnight());
    }

    public void decorateForDate(CalendarFilterParameters filter, Date date) {
        decorateForSimpleDate(filter, SimpleDate.factory.buildMidnight(date));
    }

    public void decorateForSimpleDate(CalendarFilterParameters filter,
            SimpleDate date) {
        // FstxCalendarFilterParameters filter = new
        // FstxCalendarFilterParameters();
        filter.setStart(date);
        /*
         * Only uses the startdate
         */

        filter.setStop(date);
        // return filter;
    }

    /**
     * @param targetDate
     * @return
     */
    public void decorateForWeeklySimpleDate(
            CalendarFilterParameters filter, SimpleDate date) {

        SimpleDate weekStart = SimpleDate.factory.build(date.getDate());
        while (!weekStart.isSunday()) {
            weekStart.advanceDay(-1);
        }
        SimpleDate weekStop = SimpleDate.factory.build(date.getDate());
        while (!weekStop.isSaturday()) {
            weekStop.advanceDay(1);
        }

        // FstxCalendarFilterParameters filter = new
        // FstxCalendarFilterParameters();
        filter.setStart(weekStart);

        filter.setStop(weekStop);

        filter.setBuildDate(date);

        // return filter;

    }

    public void decorateForWeeklyTodayPlusTwoWeeks(
            CalendarFilterParameters filter, SimpleDate date) {

        SimpleDate weekStart = SimpleDate.factory.build(date.getDate());

        SimpleDate weekStop = SimpleDate.factory.build(date.getDate());

        weekStop.advanceDay(13);

        // FstxCalendarFilterParameters filter = new
        // FstxCalendarFilterParameters();
        filter.setStart(weekStart);

        filter.setStop(weekStop);

        filter.setBuildDate(date);

        // return filter;

    }

    /**
     * @param parameters
     * @param targetDate
     * @return
     */
    public void decorateForMonthlySimpleDate(
            CalendarFilterParameters filter, SimpleDate date) {
        SimpleDate weekStart = SimpleDate.factory.build(date.getDate());

        /*
         * We want it to start at the first sunday before the first of the
         * month. Rather than blank space we can have useful data.
         * 
         */
        weekStart.getCalendar().set(Calendar.DAY_OF_MONTH, 1);

        while (!weekStart.isSunday()) {
            weekStart.advanceDay(-1);
        }

        SimpleDate weekStop = SimpleDate.factory.build(date.getDate());

        weekStop.getCalendar().set(Calendar.DAY_OF_MONTH,
                weekStop.getCalendar().getMaximum(Calendar.DAY_OF_MONTH));

        while (!weekStop.isSaturday()) {
            weekStop.advanceDay(1);
        }

        weekStop.advanceDay();
        weekStop.advanceMilliseconds(-1);

        // FstxCalendarFilterParameters filter = new
        // FstxCalendarFilterParameters();
        filter.setBuildDate(date);
        filter.setStart(weekStart);
        filter.setStop(weekStop);

        // return filter;
    }

    // public void decorateFilterForPrivate(FstxCalendarFilterParameters params,
    // User currentUser, UserTypeEnum currentUserType) {
    // /*
    // * standard users really dont have private records.
    // */
    // if (currentUserType == UserTypeEnum.USERTYPE_STANDARD) {
    // params.setUsername(currentUser.getUsername());
    // } else if (currentUserType == UserTypeEnum.USERTYPE_PRIVILEGED ||
    // currentUserType == UserTypeEnum.USERTYPE_OWNERADMIN) {
    // params.setUsernameForPrivateRecords(currentUser.getUsername());
    // }
    //        
    // }
}