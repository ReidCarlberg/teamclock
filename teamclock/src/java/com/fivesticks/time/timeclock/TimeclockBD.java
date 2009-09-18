package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.Date;

import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * 2006-08-19 added sourceip
 * 
 * @author Reid
 * 
 */
public interface TimeclockBD {

    public abstract void punchIn(User user, String sourceip)
            throws TimeclockBDException;

    public abstract void punchOut(User user, String sourceip)
            throws TimeclockBDException;

    public abstract void breakStart(User user, String sourceip)
            throws TimeclockBDException;

    public abstract void breakStop(User user, String sourceip)
            throws TimeclockBDException;

    public void handleEvent(User user, TimeclockEventEnum event,
            String sourceip) throws TimeclockBDException;

    public abstract Collection searchByFilter(TimeclockFilterParameters filter)
            throws TimeclockBDException;

    public abstract Timeclock update(User user, Timeclock target)
            throws TimeclockBDException;

    public abstract Timeclock updateResolved(User user, Timeclock target)
            throws TimeclockBDException;

    public abstract Timeclock getById(Long id)
            throws TimeclockBDException;

    /**
     * @param target
     */
    public abstract void delete(Timeclock target)
            throws TimeclockBDException;
    
    //2006-08-29 reid added 
    public abstract UserDateTimeclockStatusTypeEnum getCurrentState(User user, Date targetDate) throws TimeclockBDException;
    public abstract UserDateTimeclockStatusTypeEnum getCurrentState(User user, SimpleDate targetDate) throws TimeclockBDException;
}