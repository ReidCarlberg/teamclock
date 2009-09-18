/*
 * Created on Jun 22, 2004
 *
 */
package com.fivesticks.time.common.util;

import java.util.Calendar;
import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class GenericRounder {

    public SimpleDate round(SimpleDate toRound, int interval) {
        
        return SimpleDate.factory.build(this.round(toRound.getDate(),interval));
    }
    
    public Date round(Date toRound, int interval) {

        SimpleDate sd = SimpleDate.factory.build(toRound);

        int minutes = sd.getCalendar().get(Calendar.MINUTE);

        int whole = minutes / interval;

        int remainder = minutes % interval;

        int cutoff = 0;

        if (interval % 2 == 0) {
            cutoff = interval / 2 - 1;
        } else {
            cutoff = interval / 2;
        }

        if (remainder <= cutoff) {
            remainder = 0;
        } else {
            remainder = interval;
        }

        int newTotal = whole * interval + remainder;

        if (newTotal < 60) {
            sd.getCalendar().set(Calendar.MINUTE, whole * interval + remainder);
        } else {
            sd.advanceHour(1);
            sd.getCalendar().set(Calendar.MINUTE, 0);
        }

        sd.getCalendar().set(Calendar.SECOND, 0);

        return sd.getDate();

    }

    public int roundUp(int toRound, String rounderType, boolean forceMinimum) {

        int mod = 1;

        if (rounderType.equalsIgnoreCase("raw"))
            mod = 1;
        else if (rounderType.equalsIgnoreCase("quarter"))
            mod = 15;
        else if (rounderType.equalsIgnoreCase("sixths"))
            mod = 10;
        else if (rounderType.equalsIgnoreCase("tenths"))
            mod = 6;
        else
            mod = 1;

        int remainder = toRound % mod;

        int main = (toRound - remainder) / mod;

        if (remainder > mod / 2) {
            main++;
        }

        if (main == 0 && forceMinimum) {
            main = mod;
        } else {
            main = main * mod;
        }

        return main;
    }
}