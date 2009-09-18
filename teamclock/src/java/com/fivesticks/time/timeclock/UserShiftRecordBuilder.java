/*
 * Created on Nov 30, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 * 
 */
public class UserShiftRecordBuilder {

    private SessionContext sessionContext;

    private SystemOwner systemOwner;

    public UserShiftRecordBuilder() {
        super();
    }

    public UserShiftRecordBuilder(SystemOwner systemOwner) {
        super();
        this.systemOwner = systemOwner;
    }

    private static Log log = LogFactory
            .getLog(UserShiftCollectionBuilder.class);

//    public UserShiftRecord buildByStartDate(User user, Date date,
//            String timezone) throws UserShiftRecordBuilderException {
//
//        if (this.systemOwner == null)
//            throw new UserShiftRecordBuilderException("SystemOwner is null;");
//
//        SimpleDate resolved = TimezoneDateTimeResolver.resolveStatic(
//                SimpleDate.factory.build(date), timezone);
//
//        log.info("timezone " + timezone);
//        log.info("original date is " + date);
//
//        log.info("resolved date is " + resolved.getDate());
//
//        UserShiftRecord ret = null;
//
//        ret = handleBuildByStartDate(user, resolved.getDate(), timezone);
//
//        return ret;
//    }
    
//    public UserShiftRecord buildByResolvedStartDate(User user, Date date) throws UserShiftRecordBuilderException {
//
//        if (this.systemOwner == null)
//            throw new UserShiftRecordBuilderException("SystemOwner is null;");
//
//        UserShiftRecord ret = null;
//
//        ret = handleBuildByStartDate(user, resolved.getDate());
//
//        return ret;
//    }

    public UserShiftRecord buildByResolvedStartDate(User user, Date date) throws UserShiftRecordBuilderException {

        if (this.systemOwner == null)
            throw new UserShiftRecordBuilderException("SystemOwner is null;");

        UserShiftRecord ret = null;

        ret = handleBuildByStartDate(user, date);

        return ret;
    }

    /**
     * @param user
     * @param date
     * @return
     */
    private UserShiftRecord handleBuildByStartDate(User user, Date date) throws UserShiftRecordBuilderException {

        // log.debug("building a shift record for user: " + user + ", date: "
        // + date.toString());

        Shift shift = handleShift(date);

        TimeclockFilterParameters filter = new TimeclockFilterParameters();

        filter.setUsername(user.getUsername());

        // TODO needs to be resolved.
        filter.setEventTimestampRangeStart(shift.getStart());
        filter.setEventTimestampRangeEnd(shift.getEnd());

        Collection events = null;
        try {
            events = TiimeclockBDFactory.factory.build(this.systemOwner)
                    .searchByFilter(filter);
        } catch (TimeclockBDException e) {
            e.printStackTrace();
            throw new UserShiftRecordBuilderException(e);
        }

        UserShiftRecord ret = new UserShiftRecordStandardImpl(user, shift,
                events);

        return ret;
    }

    /**
     * <p>
     * This can be refactored to a shift builder that takes a username, looks up
     * the shift they're working, and then
     * 
     * @param date
     * @return
     */
    private Shift handleShift(Date date) {

        SimpleDate start = SimpleDate.factory.buildMidnight(date);
        SimpleDate stop = SimpleDate.factory.buildMidnight(date);

        stop.advanceDay();
        stop.advanceMilliseconds(-1);

//        log.info("handleShift " + start.getDate() + ", " + stop.getDate());

        Shift ret = new Shift(start.getDate(), stop.getDate());

        return ret;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;

        this.systemOwner = sessionContext.getSystemOwner();
    }
}