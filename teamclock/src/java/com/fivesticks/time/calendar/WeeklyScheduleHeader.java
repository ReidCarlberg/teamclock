/*
 * Created on Apr 22, 2004
 *
 */
package com.fivesticks.time.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class WeeklyScheduleHeader {

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");

//    private SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

    private SimpleDate date;

    /**
     * @param date
     */
    public WeeklyScheduleHeader(Date date) {
        this.date = SimpleDate.factory.build(date);
    }

    public String getFormattedStartDate() {
        return sdf.format(date.getDate());
    }

    public String getTargetFormattedStartDate() {
        return date.getMmddyyyy();
    }

    public String getDayOfWeek() {

        SimpleDateFormat format = new SimpleDateFormat("EEE");

        return format.format(date.getDate());
        //		int dayofweek = date.getDayOfWeek();
        //
        //		return days[dayofweek];

    }

}