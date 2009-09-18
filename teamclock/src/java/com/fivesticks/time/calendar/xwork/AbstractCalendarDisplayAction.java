/*
 * Created on Aug 20, 2005
 *
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.Schedule;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public abstract class AbstractCalendarDisplayAction extends
        SessionContextAwareSupport implements CalendarListContextAware {

    protected static final String DAY = "day";

    protected static final String MONTH = "month";

    private CalendarListContext calendarListContext;

    private String targetDate;

    private String targetDatePrevious;

    private String targetDateNext;

    private SimpleDate simpleTarget;

    private Schedule schedule;

    /**
     * @return Returns the targetDate.
     */
    public String getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate
     *            The targetDate to set.
     */
    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * @return Returns the targetDateNext.
     */
    public String getTargetDateNext() {
        return targetDateNext;
    }

    /**
     * @param targetDateNext
     *            The targetDateNext to set.
     */
    public void setTargetDateNext(String targetDateNext) {
        this.targetDateNext = targetDateNext;
    }

    /**
     * @return Returns the targetDatePrevious.
     */
    public String getTargetDatePrevious() {
        return targetDatePrevious;
    }

    /**
     * @param targetDatePrevious
     *            The targetDatePrevious to set.
     */
    public void setTargetDatePrevious(String targetDatePrevious) {
        this.targetDatePrevious = targetDatePrevious;
    }

    public void setCalendarListContext(CalendarListContext calendarListContext) {
        this.calendarListContext = calendarListContext;
    }

    /**
     * @return Returns the calendarListContext.
     */
    public CalendarListContext getCalendarListContext() {
        return calendarListContext;
    }

    protected void decorateByTargetDate(int previous, int next,
            String dayOrMonth) {

        if (this.getCalendarListContext().getParams() == null) {
            this.getCalendarListContext().setParams(
                    new CalendarFilterParameters());
        }

        SimpleDate simpleTarget = null;

        if (this.getTargetDate() == null
                && this.getCalendarListContext().getParams().getBuildDate() == null
                && (this.getCalendarListContext().getParams() == null || this
                        .getCalendarListContext().getParams().getBuildDate() == null)) {
            // RSC 2005-11-17 Needs to be resolved.
            simpleTarget = SimpleDate.factory.buildMidnight(this
                    .getSessionContext().getResolvedNow());
        } else if (this.getTargetDate() != null) {
            simpleTarget = SimpleDate.factory.buildMidnight(this
                    .getTargetDate());
        } else if (this.getCalendarListContext().getParams().getBuildDate() != null) {
            simpleTarget = this.getCalendarListContext().getParams()
                    .getBuildDate();
        } else {
            simpleTarget = this.getCalendarListContext().getParams()
                    .getBuildDate();
        }

        this.setTargetDate(simpleTarget.getMmddyyyy());

        handlePreviousAndNext(simpleTarget, previous, next, dayOrMonth);

        this.setSimpleTarget(simpleTarget);

    }

    /**
     * @param simpleTarget
     */
    private void handlePreviousAndNext(SimpleDate simpleTarget,
            int previousOffset, int nextOffset, String dayOrMonth) {
        SimpleDate temp = SimpleDate.factory.buildMidnight(simpleTarget
                .getDate());

        if (dayOrMonth.equals(DAY)) {
            temp.advanceDay(previousOffset);
            this.setTargetDatePrevious(temp.getMmddyyyy());
            temp.advanceDay(nextOffset);
            this.setTargetDateNext(temp.getMmddyyyy());
        } else {
            temp.advanceMonth(previousOffset);
            this.setTargetDatePrevious(temp.getMmddyyyy());
            temp.advanceMonth(nextOffset);
            this.setTargetDateNext(temp.getMmddyyyy());
        }
    }

    /**
     * @return Returns the simpleTarget.
     */
    public SimpleDate getSimpleTarget() {
        return simpleTarget;
    }

    /**
     * @param simpleTarget
     *            The simpleTarget to set.
     */
    public void setSimpleTarget(SimpleDate simpleTarget) {
        this.simpleTarget = simpleTarget;
    }

    /**
     * @return Returns the schedule.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule
     *            The schedule to set.
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /*
     * 2006-06-28 adjusted to get resolved.
     */
    public String getActualDate() {
        return SimpleDate.factory.buildMidnight(this.getSessionContext().getResolvedNow()).getMmddyyyy();
    }
}
