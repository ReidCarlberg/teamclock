/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.ActivityFilterVO;
import com.fivesticks.time.activity.ActivityWrapperArrayListBuilder;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fivesticks.time.useradmin.xwork.UserListKeyValue;

public class ActivityListPrintAction extends SessionContextAwareSupport implements
         ActivityListContextAware {

//    private SessionContext sessionContext;

    //    private Collection users;

//    private Collection projects;

//    private Collection tasks;
//
//    private Date start;
//
//    private Date stop;
//
//    private String startTimeString;
//
//    private String stopTimeString;
//
//    private String viewNextDay;
//
//    private String viewPreviousDay;
//
//    private String addButton;
//
//    private String searchActivities;
//
//    private String searchReset;
//
//    private String viewBillingFormat;

    private boolean groupByUser;

    private Collection parentCollection;

    private ActivityFilterVO params = new ActivityFilterVO();

    private ActivityListContext activityListContext;

    public String execute() throws Exception {

        parentCollection = new ArrayList();

        ActivityBD activityBD = ActivityBDFactory.factory.build(this
                .getSessionContext());

        this.setParams(this.getActivityListContext().getParams());

        if (this.getActivityListContext().isGroupByUser()) {
            //            Iterator itr = this.getUsers().iterator();
            //            while (itr.hasNext()) {
            for (Iterator iter = this.getUsers().iterator(); iter.hasNext();) {
                UserListKeyValue element = (UserListKeyValue) iter.next();
                //                SystemUser us = (SystemUser) itr.next();
                params.setUsername(element.getKey());
                ActivityChildCollection childCollection = new ActivityChildCollection();
                if (activityBD.searchByFilter(this.getParams()).size() != 0) {
                    Collection raw = activityBD
                            .searchByFilter(this.getParams());
                    Collection display = new ActivityWrapperArrayListBuilder()
                            .buildFxtxTimeWrapperArrayList(raw, this
                                    .getSessionContext());
                    childCollection.setChild(display);
                    childCollection.setTotal(calcTotal(raw));
                    parentCollection.add(childCollection);
                }
            }
            params.setUsername(null);

        } else {
            ActivityChildCollection childCollection = new ActivityChildCollection();
            Collection raw = activityBD.searchByFilter(this
                    .getActivityListContext().getParams());
            Collection display = new ActivityWrapperArrayListBuilder()
                    .buildFxtxTimeWrapperArrayList(raw, this
                            .getSessionContext());
            childCollection.setChild(display);

            childCollection.setTotal(calcTotal(raw));
            parentCollection.add(childCollection);

        }

        return SUCCESS;
    }

    private Double calcTotal(Collection c) {
        Iterator i = c.iterator();
        Activity fa = null;
        double tot = 0;
        while (i.hasNext()) {
            fa = (Activity) i.next();
            if (fa.getElapsed() != null) {
                tot += fa.getElapsed().doubleValue();
            }
        }

        return new Double(tot);
    }

    /*
     * this invoked when previous and next button pressed
     */

    //    private void previous() {
    //        SimpleDate start = SimpleDate.factory
    //                .build(this.getParams().getStart());
    //        SimpleDate stop = SimpleDate.factory.build(this.getParams().getStop());
    //
    //        int numberOfDays = start.getDaysBetween(stop) + 1;
    //
    //        start.advanceDay(-1 * numberOfDays);
    //        stop.advanceDay(-1 * numberOfDays);
    //
    //        this.getParams().setStart(start.getDate());
    //        this.getParams().setStop(stop.getDate());
    //
    //    }
    //
    //    private void next() {
    //        SimpleDate start = SimpleDate.factory
    //                .build(this.getParams().getStart());
    //        SimpleDate stop = SimpleDate.factory.build(this.getParams().getStop());
    //
    //        int numberOfDays = start.getDaysBetween(stop) + 1;
    //
    //        start.advanceDay(numberOfDays);
    //        stop.advanceDay(numberOfDays);
    //
    //        this.getParams().setStart(start.getDate());
    //        this.getParams().setStop(stop.getDate());
    //    }
    /**
     * @return Returns the testIt.
     */

    //    /**
    //     * @return Returns the activityList.
    //     */
    //    public Collection getActivities() {
    //        return activities;
    //    }
    //
    //    /**
    //     * @param activityList
    //     * The activityList to set.
    //     */
    //    public void setActivities(Collection activityList) {
    //        this.activities = activityList;
    //    }
    /**
     * @return Returns the params.
     */
    public ActivityFilterVO getParams() {
        return params;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(ActivityFilterVO params) {
        this.params = params;
    }

    /**
     * @return Returns the groupByUser.
     */
    public boolean isGroupByUser() {
        return groupByUser;
    }

    //    /**
    //     * @param groupByUser
    //     * The groupByUser to set.
    //     */
    //    public void setGroupByUser(boolean groupByUser) {
    //        this.groupByUser = groupByUser;
    //    }
    //
    //    public String getSearchActivities() {
    //        return searchActivities;
    //    }
    //
    //    public void setSearchActivities(String submit) {
    //        this.searchActivities = submit;
    //    }

    //    /**
    //     * @return Returns the projects.
    //     */
    //    public Collection getProjects() throws Exception {
    //        //get all projects
    //        FstxProjectBD projectBD = FstxProjectBD.factory.build(this
    //                .getSessionContext());
    //        //should be getAll()?? shuji sep. 12
    //        return projectBD.getAllActive();
    //    }
    //
    //    /**
    //     * @param projects
    //     * The projects to set.
    //     */
    //    public void setProjects(Collection projects) {
    //        this.projects = projects;
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
    //     * @return Returns the tasks.
    //     */
    //    public Collection getTasks() throws Exception {
    //        FstxTaskBD taskBD = FstxTaskBD.factory.build(this.getSessionContext());
    //        return taskBD.getAllTaskTypes();
    //    }
    //
    //    /**
    //     * @param tasks
    //     * The tasks to set.
    //     */
    //    public void setTasks(Collection tasks) {
    //        this.tasks = tasks;
    //    }

    //    /**
    //     * @return Returns the users.
    //     */
    public Collection getUsers() throws Exception {
        // get all users
        //        return SystemUserServiceDelegate.factory.build().list(
        //                this.getSessionContext().getSystemOwner());
        return UserListBroker.singleton.getUserList(this.getSessionContext()
                .getSystemOwner());

    }

    //
    //    public Date getStart() {
    //        return start;
    //    }
    //
    //    public void setStart(Date start) {
    //        this.start = start;
    //    }
    //
    //    public Date getStop() {
    //        return stop;
    //    }
    //
    //    public void setStop(Date stop) {
    //        this.stop = stop;
    //    }
    //
    //    public String getViewNextDay() {
    //        return viewNextDay;
    //    }
    //
    //    public void setViewNextDay(String nextButton) {
    //        this.viewNextDay = nextButton;
    //    }
    //
    //    public String getViewPreviousDay() {
    //        return viewPreviousDay;
    //    }
    //
    //    public void setViewPreviousDay(String previousButton) {
    //        this.viewPreviousDay = previousButton;
    //    }
    //
    //    public String getAddButton() {
    //        return addButton;
    //    }
    //
    //    public void setAddButton(String addButton) {
    //        this.addButton = addButton;
    //    }
    //
    //    public String getViewBillingFormat() {
    //        return viewBillingFormat;
    //    }
    //
    //    public void setViewBillingFormat(String billing) {
    //        this.viewBillingFormat = billing;
    //    }
    //
    //    /**
    //     * @return Returns the startTimeString.
    //     */
    //    public String getStartTimeString() {
    //        return startTimeString;
    //    }
    //
    //    /**
    //     * @param startTimeString
    //     * The startTimeString to set.
    //     */
    //    public void setStartTimeString(String startTimeString) {
    //        this.startTimeString = startTimeString;
    //    }
    //
    //    /**
    //     * @return Returns the stopTimeString.
    //     */
    //    public String getStopTimeString() {
    //        return stopTimeString;
    //    }
    //
    //    /**
    //     * @param stopTimeString
    //     * The stopTimeString to set.
    //     */
    //    public void setStopTimeString(String stopTimeString) {
    //        this.stopTimeString = stopTimeString;
    //    }

    /**
     * @return Returns the parentCollection.
     */
    public Collection getParentCollection() {
        return parentCollection;
    }

    /**
     * @param parentCollection
     *            The parentCollection to set.
     */
    public void setParentCollection(Collection parentCollection) {
        this.parentCollection = parentCollection;
    }

    /**
     * @return Returns the activityListContext.
     */
    public ActivityListContext getActivityListContext() {
        return activityListContext;
    }

    /**
     * @param activityListContext
     *            The activityListContext to set.
     */
    public void setActivityListContext(ActivityListContext activityListContext) {
        this.activityListContext = activityListContext;
    }

    //    /**
    //     * @return Returns the searchReset.
    //     */
    //    public String getSearchReset() {
    //        return searchReset;
    //    }
    //
    //    /**
    //     * @param searchReset
    //     * The searchReset to set.
    //     */
    //    public void setSearchReset(String searchReset) {
    //        this.searchReset = searchReset;
    //    }
}