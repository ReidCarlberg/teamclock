/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.dashboard.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.common.simpledate.SimpleDate;


/**
 * @author REID
 */
public class FilterDashboardAction extends AbstractDashboardJSONAction {

    

    private String priority;

    private String unprioritized;

    private String toDoUsername;

    private Long toDoProjectFilter;

//    private String toDoTagFilter;

    private String activityUsername;

    private String calendarUsername;

    private String detail;

    private String timeclock;
    
    private String activityTargetDate;

    public String execute() throws Exception {

        if (this.getPriority() != null) {
            if (this.getPriority().equals("all")) {
                this.getDashboardContext().setPriority(null);
            } else {
                this.getDashboardContext().setPriority(this.getPriority());
            }
        }

        /*
         * 2006-06-28 reid@fivesticks.com standard users can only see their own.
         */
        if (StringUtils.hasText(this.getToDoUsername())) {
            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
                this.getDashboardContext().setToDoUsername(this.getSessionContext().getUser().getUsername());
            } else if ( this.getToDoUsername().equalsIgnoreCase("all")) {
                this.getDashboardContext().setToDoUsername(null);
            } else {
                this.getDashboardContext().setToDoUsername(this.getToDoUsername());
            }
        } 
            

//        if (this.getToDoTagFilter() != null) {
//            this.getDashboardContext()
//                    .setToDoTagFilter(this.getToDoTagFilter());
//        }
        if (this.getActivityUsername() != null)
            this.getDashboardContext().setActivityUsername(
                    this.getActivityUsername());

        if (this.getCalendarUsername() != null)
            this.getDashboardContext().setCalendarUsername(
                    this.getCalendarUsername());

        if (this.getDetail() != null)
            this.getDashboardContext().setShowingToDoDetail(
                    !this.getDashboardContext().isShowingToDoDetail());

        if (this.getTimeclock() != null)
            this.getDashboardContext().setShowingTimeClockStatus(
                    !this.getDashboardContext().isShowingTimeClockStatus());

        if (this.getUnprioritized() != null)
            this.getDashboardContext().setUnprioritized(this.getUnprioritized());

        if (this.getToDoProjectFilter() == null) {
            // do nothing

        } else if (this.getToDoProjectFilter() != null
                && this.getToDoProjectFilter().longValue() == -1) {
            this.getDashboardContext().setToDoProjectFilter(new Long(-1));
        } else {
            this.getDashboardContext().setToDoProjectFilter(
                    this.getToDoProjectFilter());
        }
        
        if (this.getActivityTargetDate() != null) {
            SimpleDate sd = SimpleDate.factory.buildMidnight(this.getActivityTargetDate());
        }

        this.setSuccessful();
        
        return SUCCESS;
    }



    /**
     * @return Returns the priority.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            The priority to set.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return Returns the username.
     */
    public String getToDoUsername() {
        return toDoUsername;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setToDoUsername(String username) {
        this.toDoUsername = username;
    }

    /**
     * @return Returns the activityUsername.
     */
    public String getActivityUsername() {
        return activityUsername;
    }

    /**
     * @param activityUsername
     *            The activityUsername to set.
     */
    public void setActivityUsername(String activityUsername) {
        this.activityUsername = activityUsername;
    }

    /**
     * @return Returns the calendarUsername.
     */
    public String getCalendarUsername() {
        return calendarUsername;
    }

    /**
     * @param calendarUsername
     *            The calendarUsername to set.
     */
    public void setCalendarUsername(String calendarUsername) {
        this.calendarUsername = calendarUsername;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return Returns the timeclock.
     */
    public String getTimeclock() {
        return timeclock;
    }

    /**
     * @param timeclock
     *            The timeclock to set.
     */
    public void setTimeclock(String timeclock) {
        this.timeclock = timeclock;
    }

    /**
     * @return Returns the unprioritized.
     */
    public String getUnprioritized() {
        return unprioritized;
    }

    /**
     * @param unprioritized
     *            The unprioritized to set.
     */
    public void setUnprioritized(String unprioritized) {
        this.unprioritized = unprioritized;
    }

    /**
     * @return Returns the toDoProjectFilter.
     */
    public Long getToDoProjectFilter() {
        return toDoProjectFilter;
    }

    /**
     * @param toDoProjectFilter
     *            The toDoProjectFilter to set.
     */
    public void setToDoProjectFilter(Long toDoProjectFilter) {
        this.toDoProjectFilter = toDoProjectFilter;
    }

//    public String getToDoTagFilter() {
//        return toDoTagFilter;
//    }
//
//    public void setToDoTagFilter(String toDoTagFilter) {
//        this.toDoTagFilter = toDoTagFilter;
//    }



    /**
     * @return Returns the activityTargetDate.
     */
    public String getActivityTargetDate() {
        return activityTargetDate;
    }



    /**
     * @param activityTargetDate The activityTargetDate to set.
     */
    public void setActivityTargetDate(String activityTargetDate) {
        this.activityTargetDate = activityTargetDate;
    }
}