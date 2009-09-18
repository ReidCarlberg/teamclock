/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import com.fivesticks.time.timeclock.Timeclock;

/**
 * @author Reid
 */
public class TimeclockEventModifyContext {

    public Timeclock timeclockEvent;

    /**
     * @return Returns the timeclockEvent.
     */
    public Timeclock getTimeclockEvent() {
        return timeclockEvent;
    }

    /**
     * @param timeclockEvent
     *            The timeclockEvent to set.
     */
    public void setTimeclockEvent(Timeclock timeclockEvent) {
        this.timeclockEvent = timeclockEvent;
    }
}