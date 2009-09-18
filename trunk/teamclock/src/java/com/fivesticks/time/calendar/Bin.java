package com.fivesticks.time.calendar;

/**
 * @author Nick
 *  
 */
public interface Bin {

    boolean fit(TcCalendar calendar);

    void addCalendar(TcCalendar calendar);

}