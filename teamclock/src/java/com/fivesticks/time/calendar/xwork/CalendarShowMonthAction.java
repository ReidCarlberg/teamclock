/*
 * Created on Oct 20, 2004 by Reid
 */
package com.fivesticks.time.calendar.xwork;

import java.text.SimpleDateFormat;

import com.fivesticks.time.calendar.CalendarFilterDecorator;
import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.menu.MenuSection;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class CalendarShowMonthAction extends AbstractCalendarDisplayAction {

    private static SimpleDateFormat monthNameFormat = new SimpleDateFormat(
            "MMMMM");



    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.CALENDAR);
        
        if (this.getCalendarListContext().getParams() == null) {
            this.getCalendarListContext().setParams(new CalendarFilterParameters());
        }

        this.decorateByTargetDate(-1,2,MONTH);
        
        new CalendarFilterDecorator()
                .decorateForMonthlySimpleDate(this.getCalendarListContext().getParams(), this.getSimpleTarget());
//        filter.setOwnerKey(this.getSessionContext().getSystemOwner().getKey());

        ScheduleContext scheduleContext = new ScheduleContext();
        scheduleContext.setBaseDate(this.getSimpleTarget());
        scheduleContext.setFilter(this.getCalendarListContext().getParams());
//        scheduleContext.getFilter().setUsername(this.getCalendarListContext().getParams().getUsername());
        scheduleContext.setViewType(ScheduleTypeEnum.MONTHLY);

        this.setSchedule(new ScheduleBuilderBroker().findScheduleBuilder(this.getSessionContext(),
                scheduleContext).build());

//        this.getSchedule().setSettingsBroker(
//                this.getSessionContext().getSettings());

//        this.getCalendarListContext().setParams(filter);
        this.getCalendarListContext().setScheduleTypeEnum(
                ScheduleTypeEnum.MONTHLY);
        return SUCCESS;
    }

//    /**
//     * @param simpleTarget
//     */
//    private void handlePreviousAndNext(SimpleDate simpleTarget) {
//        SimpleDate temp = SimpleDate.factory.buildMidnight(simpleTarget
//                .getDate());
//        temp.advanceMonth(-1);
//        this.setTargetDatePrevious(temp.getMmddyyyy());
//        temp.advanceMonth(2);
//        this.setTargetDateNext(temp.getMmddyyyy());
//    }

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
//     * @return Returns the targetUser.
//     */
//    public String getTargetUser() {
//        return targetUser;
//    }
//
//    /**
//     * @param targetUser
//     *            The targetUser to set.
//     */
//    public void setTargetUser(String targetUser) {
//        this.targetUser = targetUser;
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
//
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

    public String getMonthName() {

        return monthNameFormat.format(SimpleDate.factory.buildMidnight(
                this.getTargetDate()).getDate());

    }

    public String getMonthNamePrevious() {
        return monthNameFormat.format(SimpleDate.factory.buildMidnight(
                this.getTargetDatePrevious()).getDate());
    }

    public String getMonthNameNext() {
        return monthNameFormat.format(SimpleDate.factory.buildMidnight(
                this.getTargetDateNext()).getDate());

    }

//    public String getActualDate() {
//        return SimpleDate.factory.buildMidnight().getMmddyyyy();
//    }
}