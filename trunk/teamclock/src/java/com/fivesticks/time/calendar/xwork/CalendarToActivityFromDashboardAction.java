/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.calendar.CalendarBD;
import com.fivesticks.time.calendar.CalendarBDFactory;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class CalendarToActivityFromDashboardAction extends AbstractDashboardJSONAction {

    private Long target;

    

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        CalendarBD sd = CalendarBDFactory.factory.build(this
                .getSessionContext().getSystemOwner());

        TcCalendar targetCalendar = sd.get(this.getTarget());

        if (targetCalendar == null)
            return INPUT;

        // SessionContext context = (SessionContext) ActionContext.getContext()
        // .getSession().get(SessionContext.KEY);

        Activity fstxActivity = new Activity();

        fstxActivity.setDate(SimpleDate.factory.buildMidnight().getDate());

        if (targetCalendar.getProjectId() != null
                && targetCalendar.getProjectId().longValue() > 0) {
            fstxActivity.setProjectId(targetCalendar.getProjectId());
        } else {
            fstxActivity.setProjectId(new Long(this.getSessionContext()
                    .getSettings().getActivityDefaultProject()));
        }

        Project project = ProjectServiceDelegateFactory.factory.build(
                this.getSessionContext()).getFstxProject(
                fstxActivity.getProjectId());

        fstxActivity.setProject(project.getShortName());

        Task task = TaskServiceDelegateFactory.factory.build(this.getSessionContext())
                .getTaskType(
                        new Long(this.getSessionContext().getSettings()
                                .getActivityDefaultTask()));
        fstxActivity.setTaskId(task.getId());
        fstxActivity.setTask(task.getNameShort());

        fstxActivity.setUsername(this.getSessionContext().getUser()
                .getUsername());

        fstxActivity.setComments(targetCalendar.getDescription());

        fstxActivity.setOwnerKey(this.getSessionContext().getSystemOwner()
                .getKey());

        ActivityBDFactory.factory.build(this.getSessionContext())
                .start(
                        fstxActivity);

        return SUCCESS;
    }

    /**
     * @return
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param long1
     */
    public void setTarget(Long long1) {
        target = long1;
    }

}