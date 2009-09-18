/*
 * Created on Dec 16, 2003
 *
 */
package com.fivesticks.time.calendar;

/**
 * @author REID
 *  
 */
public interface ScheduleBuilder {

    public static final int MINUTES_PER_WEEK = 60 * 24 * 7;
    
    public static final int MINUTES_PER_TWOWEEKS = MINUTES_PER_WEEK * 2;
    
    public static final int MINUTES_PER_DAY = 60 * 24;
    
    public abstract Schedule build() throws ScheduleBuilderException;
}