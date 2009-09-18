/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.common.util;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class TimezoneDateTimeResolver implements Serializable {
    
    private static TimezoneDateTimeResolver singleton = new TimezoneDateTimeResolver();
    
    private String timezone;
    
    
    
    

//    public static synchronized Date resolve(Date target, String timezone) {
//        SimpleDate temp = SimpleDate.factory.build(target);
//
//        return resolveStatic(temp, timezone).getDate();
//    }

//    public static synchronized SimpleDate resolveBasic(SimpleDate target,
//            String timezone) {
//
//        if (timezone == null || timezone.trim() == ""
//                || timezone.equalsIgnoreCase("us central")) {
//            // do nothing.
//        } else if (timezone.equalsIgnoreCase("us mountain")) {
//            target.advanceHour(-1);
//        } else if (timezone.equalsIgnoreCase("us eastern")) {
//            target.advanceHour(1);
//        } else if (timezone.equalsIgnoreCase("us pacific")) {
//            target.advanceHour(-2);
//        }
//
//        return target;
//    }

    public synchronized SimpleDate resolve(SimpleDate target) {
        if (this.timezone == null)
            throw new RuntimeException("timezone isn't set");
        
        return resolve(target, this.timezone);
    }

    public synchronized Date resolve(Date target) {
        if (this.timezone == null)
            throw new RuntimeException("timezone isn't set");
        
        return resolve(SimpleDate.factory.build(target), this.timezone).getDate();
    }

    public synchronized Date resolve(Date target,
            String timezone) {
        return resolve(SimpleDate.factory.build(target),timezone).getDate();
    }
    
    public static synchronized Date resolveStatic(Date target,
            String timezone) {
        return TimezoneDateTimeResolver.singleton.resolve(target,timezone);
    } 
    
    public static synchronized SimpleDate resolveStatic(SimpleDate target,
            String timezone) {
        return TimezoneDateTimeResolver.singleton.resolve(target,timezone);
    }
    
    public synchronized SimpleDate resolve(SimpleDate target,
            String timezone) {
        TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));
        
        if (timezone == null || timezone.trim() == "") {
            return target;
        }

   //     adjustToGMT0(target);

        /*
         * Handle old style. Also See TimeZoneListBuilder
         */
        if (timezone.equalsIgnoreCase("us central")) {
            timezone = "America/Chicago";
        } else if (timezone.equalsIgnoreCase("us mountain")) {
            timezone = "America/Denver";
        } else if (timezone.equalsIgnoreCase("us eastern")) {
            timezone = "America/New_York";
        } else if (timezone.equalsIgnoreCase("us pacific")) {
            timezone = "America/Los_Angeles";
        }

        TimeZone tz = getTimeZone(timezone);
        if (tz == null) {
            return target;

        }

        // if (timezone.equalsIgnoreCase(tz.getDisplayName(true,
        // TimeZone.LONG))) {
        // boolean isDaylight = tz.inDaylightTime(target.getDate());
        int offsetInMil = tz.getOffset(target.getDate().getTime());

        target.advanceMilliseconds(offsetInMil);
        // }

        return target;
    }

//    private static void adjustToGMT0(SimpleDate target) {
//        TimeZone tz = TimeZone.getDefault();
//        int offsetInMil = tz.getOffset(target.getDate().getTime());
//
//        target.advanceMilliseconds((-1) * offsetInMil);
//
//    }

    private static TimeZone getTimeZone(String timezone) {

        // String s[] = TimeZone.getAvailableIDs();
        // for (int i = 0; i < s.length; i++) {
        // String id = s[i];

        TimeZone tz = TimeZone.getTimeZone(timezone);
        // if (timezone.equalsIgnoreCase(tz.getDisplayName(true,
        // TimeZone.LONG))) {
        // return tz;
        // }
        // }

        return tz;
    }

    /**
     * @return Returns the timezone.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * @param timezone The timezone to set.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}