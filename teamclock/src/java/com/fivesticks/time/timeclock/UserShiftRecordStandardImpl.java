/*
 * Created on Nov 30, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.Iterator;

import com.fstx.stdlib.authen.users.User;

/**
 * 2006-08-30 changed visibility to public 
 * @author REID
 *  
 */
public class UserShiftRecordStandardImpl implements UserShiftRecord {

//    private static Log log = LogFactory
//            .getLog(UserShiftRecordStandardImpl.class.getName());

    private final User user;

    private final Shift shift;

    private final Collection events;

    private UserDateTimeclockStatusTypeEnum status;

    private int shiftMinutes;
    
    private int shiftBreakMinutes;

    /**
     * @return Returns the shiftBreakMinutes.
     */
    public int getShiftBreakMinutes() {
        return shiftBreakMinutes;
    }

    /**
     * @param shiftBreakMinutes The shiftBreakMinutes to set.
     */
    public void setShiftBreakMinutes(int shiftBreakMinutes) {
        this.shiftBreakMinutes = shiftBreakMinutes;
    }

    /**
     * @param user
     * @param events
     */
    public UserShiftRecordStandardImpl(User user, Shift shift, Collection events) {
        this.user = user;
        this.shift = shift;
        this.events = events;
        
        handleShiftBreakMinutes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftRecord#getUser()
     */
    public User getUser() {
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftRecord#getShift()
     */
    public Shift getShift() {
        return shift;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftRecord#getEvents()
     */
    public Collection getEvents() {
        return events;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftRecord#getStatus()
     */
    public UserDateTimeclockStatusTypeEnum getStatus() {

        if (status == null) {
            status = determineStatus(this.getEvents());
        }
//              log.debug(">>STATUS " + status);
        return status;
    }

    /**
     * <p>
     * Should handle all the normal cases.
     * 
     * @param collection
     * @return
     */
    private UserDateTimeclockStatusTypeEnum determineStatus(Collection events) {

        UserDateTimeclockStatusTypeEnum ret = UserDateTimeclockStatusTypeEnum.NO_RECORD;

        Iterator iRaw = events.iterator();

        while (iRaw.hasNext()) {
            Timeclock current = (Timeclock) iRaw.next();
            //            log.debug("current is " + current.getEvent() + ", id: "
            //                    + current.getId() + ", timestamp: "
            //                    + current.getTimestamp() + ", event: "
            //                    + current.getEventTimestamp());
            ret = determineNextStatus(ret, current.getEvent());
        }

        return ret;

    }

    private UserDateTimeclockStatusTypeEnum determineNextStatus(
            UserDateTimeclockStatusTypeEnum current, String event) {
        UserDateTimeclockStatusTypeEnum ret = null;

        //        log.info("current " + current.toString() + ", event: " + event);

        if (event.equals(TimeclockEventEnum.CLOCK_IN.getName())
                && (current == UserDateTimeclockStatusTypeEnum.NO_RECORD || current == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT)) {

            ret = UserDateTimeclockStatusTypeEnum.CLOCKED_IN;
        } else if (event.equals(TimeclockEventEnum.CLOCK_OUT.getName())
                && (current == UserDateTimeclockStatusTypeEnum.CLOCKED_IN)) {
            ret = UserDateTimeclockStatusTypeEnum.CLOCKED_OUT;
        } else if (event.equals(TimeclockEventEnum.BREAK_START.getName())
                && current == UserDateTimeclockStatusTypeEnum.CLOCKED_IN) {
            ret = UserDateTimeclockStatusTypeEnum.BREAK;
        } else if (event.equals(TimeclockEventEnum.BREAK_STOP.getName())
                && current == UserDateTimeclockStatusTypeEnum.BREAK) {
            ret = UserDateTimeclockStatusTypeEnum.CLOCKED_IN;
        } else {
            ret = UserDateTimeclockStatusTypeEnum.INCOMPLETE;
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftRecord#getShiftMinutes()
     */
    public int getShiftMinutes() {

        if (shiftMinutes == 0) {
            try {
                shiftMinutes = new ShiftMinutesResolver()
                        .resolveShiftMinutes(events);
            } catch (ShiftMinutesResolverException e) {
                //e.printStackTrace();
                //throw new RuntimeException("Shift minutes isn't resolving.",
                // e);
            }
        }

        return shiftMinutes;

    }

    public void handleShiftBreakMinutes() {
        
        int minutes = 0;
        
        try {
            minutes = new ShiftBreakMinutesResolver()
                    .resolveShiftMinutes(events);
        } catch (ShiftMinutesResolverException e) {
            //e.printStackTrace();
            //throw new RuntimeException("Shift minutes isn't resolving.",
            // e);
        }        
        
        this.setShiftBreakMinutes(minutes);
        
    }
}