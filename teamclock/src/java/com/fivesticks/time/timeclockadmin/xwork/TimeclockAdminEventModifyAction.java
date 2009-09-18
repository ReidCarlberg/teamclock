/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.Calendar;
import java.util.Collection;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.Timeclock;
import com.fivesticks.time.timeclock.TimeclockDeleteCommand;
import com.fivesticks.time.timeclock.TimeclockEventEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class TimeclockAdminEventModifyAction extends SessionContextAwareSupport implements
        TimeclockAdminContextAware,
        TimeclockEventModifyContextAware {

//    private SessionContext sessionContext;

    private TimeclockAdminContext timeclockAdminContext;

    private TimeclockEventModifyContext timeclockEventModifyContext;

    private Long target;

    private String submitTimeclock;

    private String cancelTimeclock;

    private String deleteTimeclock;

    private Timeclock targetTimeclockEvent;

    private String newEventDate;

    private int newEventHours;

    private int newEventMinutes;

    private String newEventMeridian;

    private String actionName;
    
    private String deleteDestination;
    
    public String execute() throws Exception {

        if (this.getCancelTimeclock() != null) {
            return SUCCESS;
        }

        if (this.getDeleteTimeclock() != null) {
            TimeclockDeleteCommand timeclockDeleteCommand = new TimeclockDeleteCommand(
                    this.getTimeclockEventModifyContext().getTimeclockEvent());
            timeclockDeleteCommand.setXWorkSuccess(this.getDeleteDestination());
            DeleteContext deleteContext = DeleteContextFactory.factory
                    .build(timeclockDeleteCommand);
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;

        }

        if (this.getTarget() == null && this.getSubmitTimeclock() == null) {
            this.getTimeclockEventModifyContext().setTimeclockEvent(null);
        }

        if (this.getTarget() != null) {
            Timeclock target = TiimeclockBDFactory.factory.build(
                    this.getSessionContext()).getById(this.getTarget());

//            if (!target.getUsername().equals(
//                    this.getTimeclockAdminContext().getUser().getUsername())) {
//                throw new RuntimeException("egads wrong name.");
//            }

            this.getTimeclockEventModifyContext().setTimeclockEvent(target);

            this.setTargetTimeclockEvent(target);

            SimpleDate eventTimestamp = SimpleDate.factory.build(target
                    .getEventTimestamp());

            this.setNewEventDate(eventTimestamp.getMmddyyyy());

            if (eventTimestamp.getCalendar().get(Calendar.HOUR_OF_DAY) == 0) {
                this.setNewEventHours(12);
                this.setNewEventMeridian("AM");
            } else if (eventTimestamp.getCalendar().get(Calendar.HOUR_OF_DAY) < 12) {
                this.setNewEventHours(eventTimestamp.getCalendar().get(
                        Calendar.HOUR));
                this.setNewEventMeridian("AM");
            } else if (eventTimestamp.getCalendar().get(Calendar.HOUR_OF_DAY) == 12) {
                this.setNewEventHours(12);
                this.setNewEventMeridian("PM");
            } else {
                this.setNewEventHours(eventTimestamp.getCalendar().get(
                        Calendar.HOUR_OF_DAY) - 12);
                this.setNewEventMeridian("PM");
            }

            this.setNewEventMinutes(eventTimestamp.getCalendar().get(
                    Calendar.MINUTE));

            return INPUT;
        } else if (this.getSubmitTimeclock() == null) {
            this.getTimeclockEventModifyContext().setTimeclockEvent(null);
            this.setNewEventDate(SimpleDate.factory.build(
                    this.getTimeclockAdminContext().getTargetDate())
                    .getMmddyyyy());
            return INPUT;
        }

        //validate.

        if (this.getTargetTimeclockEvent().getEvent() == null)
            this.addFieldError("event", "You must enter an event.");

        if (this.getNewEventDate() == null)
            this.addFieldError("newEventDate",
                    "You must enter a new event date.");

        if (this.getNewEventHours() < 1 || this.getNewEventHours() > 12)
            this.addFieldError("newEventHours",
                    "Hours must be between 1 and 12.");

        if (this.getNewEventMinutes() < 0 || this.getNewEventMinutes() > 59)
            this.addFieldError("newEventMinutes",
                    "Minutes must be between 0 and 59.");

        if (this.getTargetTimeclockEvent().getComment() == null
                || this.getTargetTimeclockEvent().getComment().length() == 0) {
            this.addFieldError("targetTimeclockEvent.comment",
                    "Comments are required when editing a time clock entry.");
        }

        if (this.hasErrors())
            return INPUT;

        //ok here we're doing the actual update.

        SimpleDate newEventTimestamp = SimpleDate.factory.build(this
                .getNewEventDate());

        if (this.getNewEventHours() == 12
                && this.getNewEventMeridian().equalsIgnoreCase("am")) {
            newEventTimestamp.setHours(0);
        } else if (this.getNewEventHours() < 12
                && this.getNewEventMeridian().equalsIgnoreCase("am")) {
            newEventTimestamp.setHours(this.getNewEventHours());
        } else if (this.getNewEventHours() == 12) {
            newEventTimestamp.setHours(12);
        } else {
            newEventTimestamp.setHours(12 + this.getNewEventHours());
        }

        newEventTimestamp.setMinutes(this.getNewEventMinutes());

        Timeclock newTimeclock = new Timeclock();

        if (this.getTimeclockEventModifyContext().getTimeclockEvent() != null) {
            newTimeclock = this.getTimeclockEventModifyContext()
                    .getTimeclockEvent();

        } else {
            newTimeclock.setUsername(this.getTimeclockAdminContext().getUser()
                    .getUsername());

        }

        newTimeclock.setEvent(this.getTargetTimeclockEvent().getEvent());
        newTimeclock.setComment(this.getTargetTimeclockEvent().getComment());
        newTimeclock.setEventTimestamp(newEventTimestamp.getDate());
        /*
         * 2005-11-14 RSC
         */
        newTimeclock.setTimestamp(this.getSessionContext().getResolvedNow());

        User user = this.getSessionContext().getUser().getUser();
        
        if (this.getTimeclockAdminContext() != null && this.getTimeclockAdminContext().getUser() != null) {
            user = this.getTimeclockAdminContext().getUser();
        }
        
        TiimeclockBDFactory.factory.build(this.getSessionContext()).updateResolved(
                user, newTimeclock);

        return SUCCESS;
    }

    /**
     * @return Returns the cancelTimeclock.
     */
    public String getCancelTimeclock() {
        return cancelTimeclock;
    }

    /**
     * @param cancelTimeclock
     *            The cancelTimeclock to set.
     */
    public void setCancelTimeclock(String cancelTimeclock) {
        this.cancelTimeclock = cancelTimeclock;
    }

    /**
     * @return Returns the deleteTimeclock.
     */
    public String getDeleteTimeclock() {
        return deleteTimeclock;
    }

    /**
     * @param deleteTimeclock
             The deleteTimeclock to set.
     */
    public void setDeleteTimeclock(String deleteTimeclock) {
        this.deleteTimeclock = deleteTimeclock;
    }

//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
//    }

    /**
     * @return Returns the submitTimeclock.
     */
    public String getSubmitTimeclock() {
        return submitTimeclock;
    }

    /**
     * @param submitTimeclock
     *            The submitTimeclock to set.
     */
    public void setSubmitTimeclock(String submitTimeclock) {
        this.submitTimeclock = submitTimeclock;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the timeclockAdminContext.
     */
    public TimeclockAdminContext getTimeclockAdminContext() {
        return timeclockAdminContext;
    }

    /**
     * @param timeclockAdminContext
     *            The timeclockAdminContext to set.
     */
    public void setTimeclockAdminContext(
            TimeclockAdminContext timeclockAdminContext) {
        this.timeclockAdminContext = timeclockAdminContext;
    }

    /**
     * @return Returns the timeclockEventModifyContext.
     */
    public TimeclockEventModifyContext getTimeclockEventModifyContext() {
        return timeclockEventModifyContext;
    }

    /**
     * @param timeclockEventModifyContext
     *            The timeclockEventModifyContext to set.
     */
    public void setTimeclockEventModifyContext(
            TimeclockEventModifyContext timeclockEventModifyContext) {
        this.timeclockEventModifyContext = timeclockEventModifyContext;
    }

    /**
     * @return Returns the targetTimeclockEvent.
     */
    public Timeclock getTargetTimeclockEvent() {
        return targetTimeclockEvent;
    }

    /**
     * @param targetTimeclockEvent
     *            The targetTimeclockEvent to set.
     */
    public void setTargetTimeclockEvent(Timeclock targetTimeclockEvent) {
        this.targetTimeclockEvent = targetTimeclockEvent;
    }

    /**
     * @return Returns the newEventDate.
     */
    public String getNewEventDate() {
        return newEventDate;
    }

    /**
     * @param newEventDate
     *            The newEventDate to set.
     */
    public void setNewEventDate(String newEventDate) {
        this.newEventDate = newEventDate;
    }

    /**
     * @return Returns the newEventHours.
     */
    public int getNewEventHours() {
        return newEventHours;
    }

    /**
     * @param newEventHours
     *            The newEventHours to set.
     */
    public void setNewEventHours(int newEventHours) {
        this.newEventHours = newEventHours;
    }

    /**
     * @return Returns the newEventMeridian.
     */
    public String getNewEventMeridian() {
        return newEventMeridian;
    }

    /**
     * @param newEventMeridian
     *            The newEventMeridian to set.
     */
    public void setNewEventMeridian(String newEventMeridian) {
        this.newEventMeridian = newEventMeridian;
    }

    /**
     * @return Returns the newEventMinutes.
     */
    public int getNewEventMinutes() {
        return newEventMinutes;
    }

    /**
     * @param newEventMinutes
     *            The newEventMinutes to set.
     */
    public void setNewEventMinutes(int newEventMinutes) {
        this.newEventMinutes = newEventMinutes;
    }

    /**
     * @return Returns the events.
     */
    public Collection getEvents() {
        return TimeclockEventEnum.getCollection();
    }

    /**
     * @return Returns the actionName.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName The actionName to set.
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * @return Returns the deleteDestination.
     */
    public String getDeleteDestination() {
        return deleteDestination;
    }

    /**
     * @param deleteDestination The deleteDestination to set.
     */
    public void setDeleteDestination(String deleteDestination) {
        this.deleteDestination = deleteDestination;
    }
}