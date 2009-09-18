/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.ActivityDeleteCommand;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.common.util.Rounder;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegate;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ActivityModifyAction extends SessionContextAwareSupport implements ActivityModifyContextAware {

    
    private ActivityModifyContext activityModifyContext;

    private String submitActivity;
    
    private String submitAndOverrideChargeable;

    private String cancelActivity;

    private String deleteActivity;

    private Long target;

    private Activity targetActivity = new Activity();
    

    private String StartString;

    private String StopString;


    private int startHour;

    private int startMin;

    private int stopHour;

    private int stopMin;


    private Rounder rounder;


    public String execute() throws Exception {

        if (this.getCancelActivity() != null) {
            return this.getSuccess();
        }

        ActivityBD activityBD = ActivityBDFactory.factory.build(this
                .getSessionContext());

        if (this.getDeleteActivity() != null) {
            ActivityDeleteCommand dc = new ActivityDeleteCommand(this
                    .getActivityModifyContext().getTargetActivity());
            DeleteContext deleteContext = DeleteContextFactory.factory.build(dc, this
                    .getSessionContext().getSuccessOverride());
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        }

        if (this.getSubmitActivity() == null && this.getSubmitAndOverrideChargeable() == null) {
            this.getTargetActivity().setDate(new Date());
            this.setStartString(getCurrentRoundedTime());
        }

        //workin on action context sep 20 mon shuji

        if (this.getSubmitActivity() == null  && this.getSubmitAndOverrideChargeable() == null) {
            if (this.getTarget() != null) {
                Activity activityTarget = activityBD.get(this.getTarget());
                this.setTargetActivity(activityTarget);
                if (activityTarget != null) {

                    SimpleDateFormat fo = new SimpleDateFormat("hh:mm a");

                    this.setStartString(fo.format(this.getTargetActivity().getStart()));
                    if (this.getTargetActivity().getStop() != null)
                        this.setStopString(fo.format(this.getTargetActivity().getStop()));

//                    this.setComments(activityTarget.getComments());

                    this.getActivityModifyContext().setTargetActivity(
                            activityTarget);
                }
            } else {
                this.getTargetActivity().setUsername(this.getSessionContext().getUser()
                        .getUsername());
                this.getActivityModifyContext().setTargetActivity(null);
            }
            return INPUT;
        }

        /*
         * check for field validation
         */

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        decorateTargetFromContext(activityBD, this.getTargetActivity());
        

        
        if (this.getActivityModifyContext().getTargetActivity() != null) {
            this.getTargetActivity().setId(this.getActivityModifyContext().getTargetActivity().getId());
        }
        
        activityBD.save(this.getTargetActivity());

        return this.getSuccess();
    }


    public void validate() {
        ValidationHelper help = new ValidationHelper();

        if (this.getTargetActivity().getDate() == null) {
            this.addFieldError("targetActivity.date", "Date is required");
        }

        try {
            if (this.getSessionContext().getUserTypeEnum() != UserTypeEnum.STANDARD
                    && help.isStringEmpty(this.getTargetActivity().getUsername())) {
                this.addFieldError("targetActivity.username", "User is required.");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        if (help.isStringEmpty(this.getTargetActivity().getComments())) {
            this.addFieldError("targetActivity.comments", "Comments are required.");
        }

        if (!help.isNonZero(this.getTargetActivity().getProjectId())) {
            this.addFieldError("targetActivity.projectId", "Project is required.");
        }

        if (!help.isNonZero(this.getTargetActivity().getTaskId())) {
            this.addFieldError("targetActivity.taskId", "Task is required.");
        }

        if (help.isStringEmpty(this.getStartString())) {
            this.addFieldError("", "Start time is required.");
        }
        /*
         * have to make sure that stop is not earlier than start.
         * 
         * 2006-06-28 reid@fivesticks.com if it is, we'll just reverse them.
         */
//        if (!this.hasErrors() && !help.isStringEmpty(this.getStopString())) {
//            TimeResolver res = new TimeResolver();
//            Date dateStart = res.resolve(this.getTargetActivity().getDate(), this.getStartString());
//            Date dateStop = res.resolve(this.getTargetActivity().getDate(), this.getStopString());
//
//            if (dateStop.getTime() < dateStart.getTime()) {
//                this.addFieldError("stopString",
//                        "Stop cannot be earlier than start.");
//            }
//        }

    }

    private void decorateTargetFromContext(ActivityBD fabd, Activity targetActivity)
            throws Exception {

        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(this
                .getSessionContext());
        Project project;
        try {
            project = projectBD.getFstxProject(this.getTargetActivity().getProjectId());
        } catch (Exception e) {
            throw new RuntimeException("project could not be found");
        }


        
        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            targetActivity.setUsername(this.getSessionContext().getUser().getUsername());
        }

        targetActivity.setProject(project.getShortName());

        TaskServiceDelegate taskBD = TaskServiceDelegateFactory.factory.build(this.getSessionContext());
        Task task = null;
        try {
            task = taskBD.getTaskType(this.getTargetActivity().getTaskId());
        } catch (Exception e) {
            new RuntimeException(e);
        }

        targetActivity.setTaskId(targetActivity.getTaskId());
        if (task != null) {
            targetActivity.setTask(task.getNameShort());
        }


        TimeResolver resolver = new TimeResolver(targetActivity.getDate(), this
                .getStartString());

        TimeResolver resolver2 = new TimeResolver(targetActivity.getDate(), this
                .getStopString());

        Date start = this.getRounder().round(resolver.resolve());

        Date stop = null;

        if (this.getStopString().trim().length() > 0) {
            stop = this.getRounder().round(resolver2.resolve());
        }
        
        if (stop != null && stop.compareTo(start) < 0) {
            Date temp = stop;
            stop = start;
            start = temp;
        }

        targetActivity.setStart(start);
        targetActivity.setStop(stop);

        targetActivity.setElapsed(ActivityUtils.getElapsed(start, stop));
        
        if (this.getSubmitAndOverrideChargeable() != null) {
            //do nothing its in the jsp
        } else {
            targetActivity.setChargeableElapsed(ActivityUtils.getElapsed(start, stop));
        }


    }



    private String getCurrentRoundedTime() {
        //        SimpleDate sDate =
        // SimpleDate.factory.build(TimezoneDateTimeResolver.resolve(new
        // Date(),this.getSessionContext().getSettings().getSystemTimeZone()));

        Date rounded = this.getRounder()
                .round(this.getSessionContext().resolveTime(new Date()));
        SimpleDate sDate = SimpleDate.factory.build(rounded);

        int minute = sDate.getCalendar().get(Calendar.MINUTE);
        int segmentSize = 10;

        int segments = minute / segmentSize;
        int remainder = minute % segmentSize;

        int roundedMin = segments * segmentSize;
        if (remainder >= segmentSize / 2) {
            roundedMin += segmentSize;
        }

        String roundedMinString;

        /*
         * With out this 5:60 will display rather than 6:00.
         */
        int hourAdjustment = 0;
        if (roundedMin == 60) {
            hourAdjustment = 1;
            roundedMin = 0;
        }

        /*
         * Make sure 5:00 is not displayed 5:0
         */
        if (roundedMin < 10) {
            roundedMinString = "0" + roundedMin;
        } else {
            roundedMinString = "" + roundedMin;
        }

        String time = (sDate.getCalendar().get(Calendar.HOUR_OF_DAY) + hourAdjustment)
                + ":" + roundedMinString;

        return time;
    }

    private Rounder getRounder() {

        if (this.rounder == null) {
            rounder = DateTimeRounderFactory.factory.build(this.getSessionContext()
                    .getSettings().getActivityRounderType());
        }

        return rounder;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }





    public Collection getProjects() throws Exception {
        return ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                .getAllActive();
    }


    /**
     * @param rounder
     *            The rounder to set.
     */
    public void setRounder(Rounder rounder) {
        this.rounder = rounder;
    }

    public String getSubmitActivity() {
        return submitActivity;
    }

    public void setSubmitActivity(String submit) {
        this.submitActivity = submit;
    }

    public Collection getTasks() throws Exception {
        return TaskServiceDelegateFactory.factory.build(this.getSessionContext())
                .getAllTaskTypes();
    }

    public Collection getUsers() throws Exception {
        return UserListBroker.singleton.getUserList(this.getSessionContext()
                .getSystemOwner());
    }

    /**
     * @return Returns the startHour.
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * @param startHour
     *            The startHour to set.
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * @return Returns the startMin.
     */
    public int getStartMin() {
        return startMin;
    }

    /**
     * @param startMin
     *            The startMin to set.
     */
    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    /**
     * @return Returns the stopHour.
     */
    public int getStopHour() {
        return stopHour;
    }

    /**
     * @param stopHour
     *            The stopHour to set.
     */
    public void setStopHour(int stopHour) {
        this.stopHour = stopHour;
    }

    /**
     * @return Returns the stopMin.
     */
    public int getStopMin() {
        return stopMin;
    }

    /**
     * @param stopMin
     *            The stopMin to set.
     */
    public void setStopMin(int stopMin) {
        this.stopMin = stopMin;
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
//
//    /**
//     * @return Returns the findProject.
//     */
//    public FstxProject getFindProject() {
//        return findProject;
//    }
//
//    /**
//     * @param findProject
//     *            The findProject to set.
//     */
//    public void setFindProject(FstxProject findProject) {
//        this.findProject = findProject;
//    }

//    /**
//     * @return Returns the start.
//     */
//    public Date getStart() {
//        return start;
//    }
//
//    /**
//     * @param start
//     *            The start to set.
//     */
//    public void setStart(Date start) {
//        this.start = start;
//    }

    /**
     * @return Returns the startString.
     */
    public String getStartString() {
        return StartString;
    }

    /**
     * @param startString
     *            The startString to set.
     */
    public void setStartString(String startString) {
        StartString = startString;
    }

//    /**
//     * @return Returns the stop.
//     */
//    public Date getStop() {
//        return stop;
//    }
//
//    /**
//     * @param stop
//     *            The stop to set.
//     */
//    public void setStop(Date stop) {
//        this.stop = stop;
//    }

    /**
     * @return Returns the stopString.
     */
    public String getStopString() {
        return StopString;
    }

    /**
     * @param stopString
     *            The stopString to set.
     */
    public void setStopString(String stopString) {
        StopString = stopString;
    }

    /**
     * @return Returns the submitCancel.
     */
    public String getCancelActivity() {
        return cancelActivity;
    }

    /**
     * @param submitCancel
     *            The submitCancel to set.
     */
    public void setCancelActivity(String submitCancel) {
        this.cancelActivity = submitCancel;
    }

    /**
     * @return Returns the deleteActivity.
     */
    public String getDeleteActivity() {
        return deleteActivity;
    }

    /**
     * @param deleteActivity
     *            The deleteActivity to set.
     */
    public void setDeleteActivity(String deleteActivity) {
        this.deleteActivity = deleteActivity;
    }

    /**
     * @return Returns the activityModifyContext.
     */
    public ActivityModifyContext getActivityModifyContext() {
        return activityModifyContext;
    }

    /**
     * @param activityModifyContext
     *            The activityModifyContext to set.
     */
    public void setActivityModifyContext(
            ActivityModifyContext activityModifyContext) {
        this.activityModifyContext = activityModifyContext;
    }
    /**
     * @return Returns the targetActivity.
     */
    public Activity getTargetActivity() {
        return targetActivity;
    }
    /**
     * @param targetActivity The targetActivity to set.
     */
    public void setTargetActivity(Activity targetActivity) {
        this.targetActivity = targetActivity;
    }


    /**
     * @return Returns the submitAndComputeActivity.
     */
    public String getSubmitAndOverrideChargeable() {
        return submitAndOverrideChargeable;
    }


    /**
     * @param submitAndComputeActivity The submitAndComputeActivity to set.
     */
    public void setSubmitAndOverrideChargeable(String submitAndComputeActivity) {
        this.submitAndOverrideChargeable = submitAndComputeActivity;
    }
}