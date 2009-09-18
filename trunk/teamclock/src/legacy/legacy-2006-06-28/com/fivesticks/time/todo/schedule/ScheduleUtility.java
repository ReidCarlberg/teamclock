/*
 * Created on Oct 7, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 * 
 *  
 */
public class ScheduleUtility {

    public ScheduleUtility() {
        super();

    }

    public static Date increment(Date initiationDate, String frequency,
            int multiplier) {
        ScheduleFrequencyEnum freq = ScheduleFrequencyEnum.getByName(frequency);
        return increment(initiationDate, freq, multiplier);

    }

    public static Date increment(Date initiationDate,
            ScheduleFrequencyEnum frequency, int multiplier) {

        SimpleDate simpleDate = SimpleDate.factory.build(initiationDate);

        if (frequency == ScheduleFrequencyEnum.MONTHLY) {
            simpleDate.advanceMonth(multiplier);

        }
        if (frequency == ScheduleFrequencyEnum.WEEKLY) {
            simpleDate.advanceWeekOfYear(multiplier);
        }

        return simpleDate.getDate();

    }

}