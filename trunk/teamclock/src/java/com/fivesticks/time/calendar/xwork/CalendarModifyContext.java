/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.TcCalendar;

/**
 * @author shuji
 */
public class CalendarModifyContext {

    private TcCalendar targetCalendar;

    /**
     * @return Returns the targetCalendar.
     */
    public TcCalendar getTargetCalendar() {
        return targetCalendar;
    }

    /**
     * @param targetCalendar
     *            The targetCalendar to set.
     */
    public void setTargetCalendar(TcCalendar targetCalendar) {
        this.targetCalendar = targetCalendar;
    }
}