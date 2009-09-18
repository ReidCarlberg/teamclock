/*
 * Created on Oct 20, 2004 by Reid
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.CalendarFilterDecorator;
import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.menu.MenuSection;

/**
 * @author Reid
 */
public class CalendarShowWeekAction extends AbstractCalendarDisplayAction {

//    private SessionContext sessionContext;

//    private CalendarListContext calendarListContext;

//    private FstxCalendarFilterParameters params = new FstxCalendarFilterParameters();

//    private String showWeek;
//
//
//
//    private String targetUser;

//    private Schedule schedule;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.CALENDAR);

        if (this.getCalendarListContext().getParams() == null) {
            this.getCalendarListContext().setParams(new CalendarFilterParameters());
        }
        
        this.decorateByTargetDate(-7, 14, DAY);

        new CalendarFilterDecorator()
        .decorateForWeeklySimpleDate(this.getCalendarListContext().getParams(), this.getSimpleTarget());  
        
        /*
         * Reid 2005-02-06 If we want to start with a different date, this is
         * where we control that.
         */
        /*
         * 2005-08-20 Removed should be handled in the SD
         */
//        filter.setOwnerKey(this.getSessionContext().getSystemOwner().getKey());

        ScheduleContext scheduleContext = new ScheduleContext();
        scheduleContext.setBaseDate(this.getSimpleTarget());
        scheduleContext.setFilter(this.getCalendarListContext().getParams());
//        scheduleContext.getFilter().setUsername(this.getParams().getUsername());
        scheduleContext.setViewType(ScheduleTypeEnum.WEEKLY);

        this.setSchedule(new ScheduleBuilderBroker().findScheduleBuilder(this.getSessionContext(),
                scheduleContext).build());



        this.getCalendarListContext().setScheduleTypeEnum(
                ScheduleTypeEnum.WEEKLY);

        return SUCCESS;
    }



//    /**
//     * @return Returns the calendarListContext.
//     */
//    public CalendarListContext getCalendarListContext() {
//        return calendarListContext;
//    }
//
//    /**
//     * @param calendarListContext
//     *            The calendarListContext to set.
//     */
//    public void setCalendarListContext(CalendarListContext calendarListContext) {
//        this.calendarListContext = calendarListContext;
//    }

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

//    /**
//     * @return Returns the params.
//     */
//    public FstxCalendarFilterParameters getParams() {
//        return params;
//    }
//
//    /**
//     * @param params
//     *            The params to set.
//     */
//    public void setParams(FstxCalendarFilterParameters params) {
//        this.params = params;
//    }

//    /**
//     * @return Returns the showDaily.
//     */
//    public String getShowWeek() {
//        return showWeek;
//    }
//
//    /**
//     * @param showDaily
//     *            The showDaily to set.
//     */
//    public void setShowWeek(String showDaily) {
//        this.showWeek = showDaily;
//    }

//    /**
//     * @return Returns the targetDate.
//     */
//    public String getTargetDate() {
//        return targetDate;
//    }
//
//    /**
//     * @param targetDate
//     *            The targetDate to set.
//     */
//    public void setTargetDate(String targetDate) {
//        this.targetDate = targetDate;
//    }


//    /**
//     * @return Returns the targetDateNext.
//     */
//    public String getTargetDateNext() {
//        return targetDateNext;
//    }
//
//    /**
//     * @param targetDateNext
//     *            The targetDateNext to set.
//     */
//    public void setTargetDateNext(String targetDateNext) {
//        this.targetDateNext = targetDateNext;
//    }
//
//    /**
//     * @return Returns the targetDatePrevious.
//     */
//    public String getTargetDatePrevious() {
//        return targetDatePrevious;
//    }

//    /**
//     * @param targetDatePrevious
//     *            The targetDatePrevious to set.
//     */
//    public void setTargetDatePrevious(String targetDatePrevious) {
//        this.targetDatePrevious = targetDatePrevious;
//    }

//    public Collection getUsers() throws Exception {
//        return SystemUserServiceDelegate.factory.build().list(
//                this.getSessionContext().getSystemOwner());
//    }

//    /**
//     * @return Returns the schedule.
//     */
//    public Schedule getSchedule() {
//        return schedule;
//    }
//
//    /**
//     * @param schedule
//     *            The schedule to set.
//     */
//    public void setSchedule(Schedule schedule) {
//        this.schedule = schedule;
//    }

//    public String getActualDate() {
//        return SimpleDate.factory.buildMidnight().getMmddyyyy();
//    }
}