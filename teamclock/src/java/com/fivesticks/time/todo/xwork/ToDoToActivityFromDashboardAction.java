/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class ToDoToActivityFromDashboardAction extends AbstractJSONAction {

    private Long target;

    

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        if (targetToDo == null)
            return INPUT;

        //        SessionContext context = (SessionContext) ActionContext.getContext()
        //                .getSession().get(SessionContext.KEY);

        Activity fstxActivity = new Activity();

        fstxActivity.setToDoId(targetToDo.getId());
        
        fstxActivity.setDate(SimpleDate.factory.buildMidnight(this.getSessionContext().getResolvedNow()).getDate());

        if (targetToDo.getProjectId() != null
                && targetToDo.getProjectId().longValue() > 0) {
            fstxActivity.setProjectId(targetToDo.getProjectId());
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

        fstxActivity.setComments(targetToDo.getDetail());

        fstxActivity.setOwnerKey(this.getSessionContext().getSystemOwner()
                .getKey());

        ActivityBDFactory.factory.build(this.getSessionContext()).start(
                fstxActivity);

        //2006-06-14 this way there is something to eval();
        this.setSuccessful();
        
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