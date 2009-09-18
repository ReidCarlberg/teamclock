/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.dashboard.xwork;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoFilterBuilder;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;

/**
 * @author REID
 */
public class DashboardContext implements Serializable {

    private ToDoCriteriaParameters todoFilter = new ToDoCriteriaParameters();

    private String activityUsername;

    private String calendarUsername;

    private String calendarDisplayType;

    private boolean showingToDoDetail;

    private Boolean showingTimeClockStatus;
    
    private Date activityTargetDate;
    
    public DashboardContext() {
        // this.setPriority(ToDoPriorityTypeEnum.Q1.getName());

        todoFilter = new ToDoFilterBuilder().buildIncompleteByAssignee(null,
                ToDoPriorityTypeEnum.Q1.getName(), "false", new Boolean(true));

    }

    /**
     * @return Returns the priority.
     */
    public String getPriority() {
        return this.getTodoFilter().getPriority();
    }

    /**
     * @param priority
     *            The priority to set.
     */
    public void setPriority(String priority) {
        this.getTodoFilter().setPriority(priority);
    }

    /**
     * @return Returns the username.
     */
    public String getToDoUsername() {
        return this.getTodoFilter().getAssignedToUser();
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setToDoUsername(String username) {
        this.getTodoFilter().setAssignedToUser(username);
    }

    /**
     * @return Returns the showingDetail.
     */
    public boolean isShowingToDoDetail() {
        return showingToDoDetail;
    }

    /**
     * @param showingDetail
     *            The showingDetail to set.
     */
    public void setShowingToDoDetail(boolean showingDetail) {
        this.showingToDoDetail = showingDetail;
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

    /*
     * This may be a bit overdone, but I need a way to tell if the value has
     * been explicitly set or not so when we set the UserSetting we know if we
     * are just defaulting to off.
     */
    public boolean isShowingTimeClockStatusExplicitlySet() {
        return showingTimeClockStatus == null ? false : true;
    }

    /**
     * @return Returns the showingTimeClockStatus.
     */
    public boolean isShowingTimeClockStatus() {
        /*
         * If not set explicitly assume false;
         */
        return showingTimeClockStatus != null ? showingTimeClockStatus
                .booleanValue() : false;
    }

    /**
     * @param showingTimeClockStatus
     *            The showingTimeClockStatus to set.
     */
    public void setShowingTimeClockStatus(boolean showingTimeClockStatus) {
        this.showingTimeClockStatus = new Boolean(showingTimeClockStatus);
    }

    /**
     * @return Returns the unprioritied.
     */
    public String getUnprioritized() {
        return this.getTodoFilter().getUnprioritized().toString();
    }

    /**
     * @param unprioritied
     *            The unprioritied to set.
     */
    public void setUnprioritized(String unprioritied) {
        this.getTodoFilter().setUnprioritized(new Boolean(unprioritied));
    }

    /**
     * @return Returns the toDoProjectFilter.
     */
    public Long getToDoProjectFilter() {
        return this.getTodoFilter().getProjectId();
    }

    /**
     * @param toDoProjectFilter
     *            The toDoProjectFilter to set.
     */
    public void setToDoProjectFilter(Long toDoProjectFilter) {
        this.getTodoFilter().setProjectId(toDoProjectFilter);
    }

//    public String getToDoTagFilter() {
//        return this.getTodoFilter().getTag();
//    }
//
//    public void setToDoTagFilter(String toDoTagFilter) {
//        this.getTodoFilter().setTag(toDoTagFilter);
//    }

    public ToDoCriteriaParameters getTodoFilter() {
        todoFilter.setTodoComplete("false");
        return todoFilter;
    }

    public String getCalendarDisplayType() {
        return calendarDisplayType;
    }

    public void setCalendarDisplayType(String calendarDisplayType) {
        this.calendarDisplayType = calendarDisplayType;
    }

    /**
     * @return Returns the activityTargetDate.
     */
    public Date getActivityTargetDate() {
        return activityTargetDate;
    }

    /**
     * @param activityTargetDate The activityTargetDate to set.
     */
    public void setActivityTargetDate(Date activityTargetDate) {
        this.activityTargetDate = activityTargetDate;
    }


}