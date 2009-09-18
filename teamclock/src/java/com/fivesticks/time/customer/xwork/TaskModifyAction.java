/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegate;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 *  
 */
public class TaskModifyAction extends SessionContextAwareSupport  {

    private String submitTask;

    private String cancelTask;

    private Long target;

    private String newTaskName;

    public String execute() throws Exception {

        if (this.getCancelTask() != null)
            return SUCCESS;

        TaskServiceDelegate taskBD = TaskServiceDelegateFactory.factory.build(this.getSessionContext());

        if (this.getSubmitTask() == null) {
            ActionContext.getContext().getSession().remove(
                    TaskModifyContext.KEY);
            if (this.getTarget() != null) {
                Task taskTarget = taskBD.getTaskType(this.getTarget());
                if (taskTarget != null) {
                    this.setNewTaskName(taskTarget.getNameShort());
                    TaskModifyContext context = new TaskModifyContext();
                    context.setTargetTask(taskTarget);
                    ActionContext.getContext().getSession().put(
                            TaskModifyContext.KEY, context);
                }
            }
            return INPUT;
        }

        /*
         * validate
         */

        /*
         * write
         */

        if (ActionContext.getContext().getSession().get(TaskModifyContext.KEY) != null) {
            TaskModifyContext context = (TaskModifyContext) ActionContext
                    .getContext().getSession().get(TaskModifyContext.KEY);

            context.getTargetTask().setNameShort(this.getNewTaskName());
            context.getTargetTask().setNameLong(this.getNewTaskName());

            taskBD.save(context.getTargetTask());
        } else {
            taskBD.addTaskType(this.getNewTaskName());
        }

        return SUCCESS;

    }

    /**
     * @return
     */
    public String getSubmitTask() {
        return submitTask;
    }

    /**
     * @return
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param string
     */
    public void setSubmitTask(String string) {
        submitTask = string;
    }

    /**
     * @param long1
     */
    public void setTarget(Long long1) {
        target = long1;
    }

    /**
     * @return
     */
    public String getNewTaskName() {
        return newTaskName;
    }

    /**
     * @param string
     */
    public void setNewTaskName(String string) {
        newTaskName = string;
    }


    /**
     * @return Returns the cancelTask.
     */
    public String getCancelTask() {
        return cancelTask;
    }

    /**
     * @param cancelTask
     *            The cancelTask to set.
     */
    public void setCancelTask(String cancelTask) {
        this.cancelTask = cancelTask;
    }
}