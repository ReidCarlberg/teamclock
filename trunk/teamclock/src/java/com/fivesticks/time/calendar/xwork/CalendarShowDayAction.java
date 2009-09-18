/*
 * Created on Oct 20, 2004 by Reid
 */
package com.fivesticks.time.calendar.xwork;

import java.util.Collection;

import com.fivesticks.time.calendar.CalendarFilterDecorator;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;

/**
 * @author Reid
 */
public class CalendarShowDayAction extends AbstractCalendarDisplayAction {

//    private SessionContext sessionContext;

//    private CalendarListContext calendarListContext;

//    private FstxCalendarFilterParameters params = new FstxCalendarFilterParameters();

//    private String showDaily;
//
//    private String targetDate;
//
//    private String targetDatePrevious;
//
//    private String targetDateNext;
//
//    private String targetUser;
//
//    private Schedule schedule;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.CALENDAR);
        

//        SimpleDate simpleTarget = null;
//
//        if (this.getTargetDate() == null
//                && this.getCalendarListContext().getParams().getBuildDate() == null
//                && (this.getCalendarListContext().getParams() == null || this
//                        .getCalendarListContext().getParams().getStart() == null)) {
//            simpleTarget = SimpleDate.factory.buildMidnight();
//        } else if (this.getTargetDate() != null) {
//            simpleTarget = SimpleDate.factory.buildMidnight(this
//                    .getTargetDate());
//        } else if (this.getCalendarListContext().getParams().getBuildDate() != null) {
//            simpleTarget = this.getCalendarListContext().getParams().getBuildDate();
//        } else {
//            simpleTarget = this.getCalendarListContext().getParams().getStart();
//        }
//
//        this.setTargetDate(simpleTarget.getMmddyyyy());

//        handlePreviousAndNext(simpleTarget);

        this.decorateByTargetDate(-1,2, DAY);
        
        new CalendarFilterDecorator()
                .decorateForSimpleDate(this.getCalendarListContext().getParams(),this.getSimpleTarget());
//        filter.setOwnerKey(this.getSessionContext().getSystemOwner().getKey());

        ScheduleContext scheduleContext = new ScheduleContext();
        scheduleContext.setBaseDate(this.getSimpleTarget());
        scheduleContext.setFilter(this.getCalendarListContext().getParams());
//        scheduleContext.getFilter().setUsername(this.getParams().getUsername());
        scheduleContext.setViewType(ScheduleTypeEnum.DAILY_30_MINUTES);

        this.setSchedule(new ScheduleBuilderBroker().findScheduleBuilder(this.getSessionContext(),
                scheduleContext).build());

//        this.getSchedule().setSettingsBroker(
//                this.getSessionContext().getSettings());
        
        this.getCalendarListContext().setScheduleTypeEnum(
                ScheduleTypeEnum.DAILY_30_MINUTES);
//        this.getCalendarListContext().setParams(filter);
        return SUCCESS;
    }

    /**
     * @param simpleTarget
     */
//    private void handlePreviousAndNext(SimpleDate simpleTarget) {
//        SimpleDate temp = SimpleDate.factory.buildMidnight(simpleTarget
//                .getDate());
//        temp.advanceDay(-1);
//        this.setTargetDatePrevious(temp.getMmddyyyy());
//        temp.advanceDay(2);
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
//    public String getShowDaily() {
//        return showDaily;
//    }
//
//    /**
//     * @param showDaily
//     *            The showDaily to set.
//     */
//    public void setShowDaily(String showDaily) {
//        this.showDaily = showDaily;
//    }
//
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
//
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
//
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

    public Collection getUsers() throws Exception {
        return SystemUserServiceDelegateFactory.factory.build().list(
                this.getSessionContext().getSystemOwner());
    }

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