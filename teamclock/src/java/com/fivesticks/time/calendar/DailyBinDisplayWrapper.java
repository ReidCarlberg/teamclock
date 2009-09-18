package com.fivesticks.time.calendar;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class DailyBinDisplayWrapper implements 
        Serializable {

    private final DailyBin bin;

    private static final DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    
    protected static final DateFormat monthDayFormat = new SimpleDateFormat("MM/d");

    public DailyBinDisplayWrapper(final DailyBin bin) {
        this.bin = bin;
    }

    /**
     * @return
     */
    public DailyBin getBin() {
        return bin;
    }

    public String getBinRange() {
        StringBuffer ret = new StringBuffer();

        DateFormat df = DateFormat.getTimeInstance();

        ret.append(df.format(bin.getLowerBound()));

        ret.append(" - ");

        ret.append(df.format(bin.getUpperBound()));

        return ret.toString();
    }

    public String getBinLower() {
        return dateFormat.format(bin.getLowerBound());
    }

    public String getBinUpper() {
        return dateFormat.format(bin.getUpperBound());
    }

    public String getDayOfMonth() {
        String dayOfMonth = ""
                + SimpleDate.factory.build(bin.getLowerBound()).getCalendar()
                        .get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }

    /*
     * 2006-06-28 reid needs to be using resolved date.
     * pushed it down into the bin.
     */
    public boolean isToday() {

        return bin.isToday();
    }

    public String getFormattedDate() {
        return SimpleDate.factory.build(bin.getLowerBound()).getMmddyyyy();
    }

    public boolean isSaturday() {
        return SimpleDate.factory.build(bin.getLowerBound()).isSaturday();
    }

    public boolean isRegularDay(FstxTimeSettings settings) {

//        boolean ret = false;
//        /*
//         * what is a regular day?
//         */
//
//        SimpleDate binLower = SimpleDate.factory.build(bin.getLowerBound());
//
//        SimpleDate binUpper = SimpleDate.factory.build(bin.getUpperBound());
//        if (binLower.getCalendar().get(Calendar.HOUR_OF_DAY) >= settings.getCalendarNormalDayStart()
//                && binUpper.getCalendar().get(Calendar.HOUR_OF_DAY) < settings.getCalendarNormalDayEnds()) {
//            ret = true;
//        }
        //RSC 2005-11-17
        return true;
    }

    public Collection getDisplayCalendars() {

        return bin.getCalendars();
    }



}