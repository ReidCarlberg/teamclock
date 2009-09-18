/*
 * Created on Apr 24, 2006
 *
 */
package com.fivesticks.time.activity.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.ws.xwork.AbstractRestAction;

public class RestActivityAction extends
        AbstractRestAction {

    private ActivityRestActionType activityAction;



    private String projectKey;

    private String startTime;

    private String stopTime;

    private String comments;


    protected void handleActionTypeValidate() throws Exception{

        if (!ParamValidator.isSearchable(this.getProjectKey())
                || !ParamValidator.isSearchable(this.getComments())
                || !ParamValidator.isSearchable(this.getStartTime())
                || !ParamValidator.isSearchable(this.getStopTime())) {
            throw new RuntimeException("activity parameters fails");
        }

        this.activityAction = ActivityRestActionType
                .getByName(this.getAction());

        if (this.activityAction == null) {
            throw new RuntimeException();
        }

    }



    protected void handleAction() throws Exception {

        Project project = ProjectServiceDelegateFactory.factory
                .build(
                        this.getAuthenticationBasedServiceSupport()
                                .getSessionContext())
                .getFstxProjectByShortName(this.getProjectKey());

        DateFormat sdf = SimpleDateFormat.getDateTimeInstance();

        Date startDate = sdf.parse(this.getStartTime());

        Date stopDate = sdf.parse(this.getStopTime());

        Task task = TaskServiceDelegateFactory.factory
                .build(
                        this.getAuthenticationBasedServiceSupport()
                                .getSessionContext()).getTaskType(
                        new Long(this.getAuthenticationBasedServiceSupport()
                                .getSessionContext().getSettings()
                                .getActivityDefaultTask()));
        
        ActivityBD activityBD = ActivityBDFactory.factory.build(this.getAuthenticationBasedServiceSupport().getSessionContext());
        
        activityBD.post(this.getAuthenticationBasedServiceSupport().getUser(), project, startDate, stopDate, task, this.getComments());

    }


    /**
     * @return Returns the activityAction.
     */
    public ActivityRestActionType getActivityAction() {
        return activityAction;
    }

    /**
     * @param activityAction
     *            The activityAction to set.
     */
    public void setActivityAction(ActivityRestActionType activityAction) {
        this.activityAction = activityAction;
    }

    /**
     * @return Returns the notes.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param notes
     *            The notes to set.
     */
    public void setComments(String notes) {
        this.comments = notes;
    }

    /**
     * @return Returns the projectKey.
     */
    public String getProjectKey() {
        return projectKey;
    }

    /**
     * @param projectKey
     *            The projectKey to set.
     */
    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    /**
     * @return Returns the startTime.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            The startTime to set.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return Returns the stopTime.
     */
    public String getStopTime() {
        return stopTime;
    }

    /**
     * @param stopTime
     *            The stopTime to set.
     */
    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

}
