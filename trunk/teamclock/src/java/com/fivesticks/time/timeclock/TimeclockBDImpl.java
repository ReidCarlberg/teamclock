package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.util.Rounder;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 * 
 */
public class TimeclockBDImpl extends AbstractServiceDelegate implements
        TimeclockBD {

    private TimeclockDAO fstxTimeclockDAO;

    // private SessionContext sessionContext;

    private SimpleDate targetDate;

    private static Log log = LogFactory.getLog(TimeclockBDImpl.class);

    /**
     * 
     */
    public TimeclockBDImpl() {
        super();
        // here
        targetDate = SimpleDate.factory.build();
    }

    // public FstxTimeclockBDImpl(Date date) {
    // super();
    // this.targetDate = SimpleDate.factory.build(date);
    // }
    //
    // public FstxTimeclockBDImpl(SimpleDate targetDate) {
    // super();
    // this.targetDate = targetDate;
    // }

    public void punchIn(User user, String sourceip)
            throws TimeclockBDException {

        handleEvent(user, TimeclockEventEnum.CLOCK_IN, sourceip);

    }

    public void punchOut(User user, String sourceip)
            throws TimeclockBDException {

        handleEvent(user, TimeclockEventEnum.CLOCK_OUT, sourceip);

    }

    public void breakStart(User user, String sourceip)
            throws TimeclockBDException {

        handleEvent(user, TimeclockEventEnum.BREAK_START, sourceip);

    }

    public void breakStop(User user, String sourceip)
            throws TimeclockBDException {

        handleEvent(user, TimeclockEventEnum.BREAK_STOP, sourceip);

    }

    public void handleEvent(User user, TimeclockEventEnum event,
            String sourceip) throws TimeclockBDException {

        SimpleDate timezoneDate = SimpleDate.factory
                .build(targetDate.getDate());

        log.info("timezone date before " + timezoneDate.getDate());

        timezoneDate = this.getResolverUtil().resolve(timezoneDate);

        log.info("timezone date after " + timezoneDate.getDate());

        validateEvent(user, event, timezoneDate);
        // validateEvent(user, event, timezoneDate);

        log.info("timezone date after validation " + timezoneDate.getDate());

        Timeclock record = new Timeclock();

        record.setUsername(user.getUsername());
        record.setTimestamp(timezoneDate.getDate());
        record.setEventTimestamp(timezoneDate.getDate());
        record.setEvent(event.getName());

        record.setSourceip(sourceip);

        updateResolved(user, record);

    }

    /**
     * @param user
     * @param event
     */
    private void validateEvent(User user, TimeclockEventEnum event,
            SimpleDate validateDateAlreadyResolved)
            throws TimeclockBDException {

        log.info("validateEvent future event is " + event.getFriendlyName());

        log.info("target date in validate is "
                + validateDateAlreadyResolved.getDate());

        UserDateTimeclockStatusTypeEnum currentStatus = this.getCurrentState(
                user, validateDateAlreadyResolved);

        log.info("validateEvent current even is "
                + currentStatus.getFriendlyName());

        /**
         * possibility one: already clocked in.
         */
        if (event == TimeclockEventEnum.CLOCK_IN
                && (currentStatus == UserDateTimeclockStatusTypeEnum.BREAK
                        || currentStatus == UserDateTimeclockStatusTypeEnum.CLOCKED_IN || currentStatus == UserDateTimeclockStatusTypeEnum.INCOMPLETE)) {
            throw new TimeclockBDException("can't clock in.");
        }

        /**
         * possibility two: cant' start on break under some circumstances.
         */
        UserDateTimeclockStatusTypeEnum enumCurrent = currentStatus;
        if ((event == TimeclockEventEnum.BREAK_START || event == TimeclockEventEnum.BREAK_STOP)
                && (currentStatus == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT
                        || currentStatus == UserDateTimeclockStatusTypeEnum.INCOMPLETE || currentStatus == UserDateTimeclockStatusTypeEnum.NO_RECORD)) {
            throw new TimeclockBDException(
                    "clock in not valid--can't start a break");
        }

        if ((event == TimeclockEventEnum.BREAK_START)
                && (currentStatus == UserDateTimeclockStatusTypeEnum.BREAK)) {
            throw new TimeclockBDException(
                    "can't start break--you're already on a break.");
        }

        if ((event == TimeclockEventEnum.BREAK_STOP)
                && (currentStatus != UserDateTimeclockStatusTypeEnum.BREAK)) {
            throw new TimeclockBDException(
                    "can't stop break--you're not on a break.");
        }

        /**
         * possibility three: attempting to clock out
         */
        if (event == TimeclockEventEnum.CLOCK_OUT
                && (currentStatus == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT
                        || currentStatus == UserDateTimeclockStatusTypeEnum.NO_RECORD
                        || currentStatus == UserDateTimeclockStatusTypeEnum.BREAK || currentStatus == UserDateTimeclockStatusTypeEnum.INCOMPLETE)) {
            throw new TimeclockBDException(
                    "clock out not valid--not clocked in properly");
        }
    }

    public Collection searchByFilter(TimeclockFilterParameters filter)
            throws TimeclockBDException {
        Collection ret;
        try {
            ret = this.getFstxTimeclockDAO().find(filter);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TimeclockBDException(e);
        }

        return ret;
    }

    public Timeclock updateResolved(User user, Timeclock targetResolved)
            throws TimeclockBDException {

        if (user == null || targetResolved == null) {
            throw new TimeclockBDException("user or timeclock is null");
        }

        Rounder rounder = this.getRounderUtil();

        targetResolved.setEventTimestamp(rounder.round(targetResolved
                .getEventTimestamp()));

        targetResolved.setOwnerKey(this.getSystemOwner().getKey());

        log.info("punch eventTimestamp is "
                + targetResolved.getEventTimestamp());

        try {
            return this.getFstxTimeclockDAO().save(targetResolved);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TimeclockBDException(e);
        }
    }

    public Timeclock update(User user, Timeclock target)
            throws TimeclockBDException {
        if (user == null || target == null) {
            throw new TimeclockBDException("user or timeclock is null");
        }

        target.setEventTimestamp(this.getResolverUtil().resolve(
                target.getEventTimestamp()));

        this.updateResolved(user, target);

        return target;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.FstxTimeclockBD#getById(java.lang.Long)
     */
    public Timeclock getById(Long id) throws TimeclockBDException {
        try {
            return this.getFstxTimeclockDAO().get(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TimeclockBDException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.FstxTimeclockBD#delete(com.fstx.time.timeclock.FstxTimeclock)
     */
    public void delete(Timeclock target) throws TimeclockBDException {

        try {
            this.getFstxTimeclockDAO().remove(target);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TimeclockBDException(e);
        }
    }

    /**
     * @return
     */
    public TimeclockDAO getFstxTimeclockDAO() {
        return fstxTimeclockDAO;
    }

    /**
     * @param timeclockDAO
     */
    public void setFstxTimeclockDAO(TimeclockDAO timeclockDAO) {
        fstxTimeclockDAO = timeclockDAO;
    }

    public UserDateTimeclockStatusTypeEnum getCurrentState(User user,
            Date resolvedDate) throws TimeclockBDException {
        return this.getCurrentState(user, SimpleDate.factory
                .build(resolvedDate));
    }

    public UserDateTimeclockStatusTypeEnum getCurrentState(User user,
            SimpleDate resolvedDate) throws TimeclockBDException {

        UserShiftRecord current;

        // 2006-08-29 reid -- don't know when this comment is from.
        // we should round that target date...
        // otherwise the user shift record builder fails when we round
        // to the qarter hour.
        // Rounder rounder = Rounder.factory.build(timeclockRounderType);

        try {
            current = new UserShiftRecordBuilder(this.getSystemOwner())
                    .buildByResolvedStartDate(user, this.getRounderUtil()
                            .round(resolvedDate).getDate());

        } catch (UserShiftRecordBuilderException e) {
            e.printStackTrace();
            throw new TimeclockBDException(e);
        }

        return current.getStatus();
    }

}