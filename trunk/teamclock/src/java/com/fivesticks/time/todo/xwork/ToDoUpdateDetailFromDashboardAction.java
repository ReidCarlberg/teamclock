/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.dashboard.util.DashboardRecentActionType;
import com.fivesticks.time.dashboard.util.DashboardRecentToDoAction;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ToDoUpdateDetailFromDashboardAction extends AbstractJSONAction {

    private Long target;

    private String priority;

//    private String tag;

    private String assignToUser;

    private Double estimatedTotalTime;

    private Double estimatedRemainingTime;
    
    private String status;
    
    private String detail;
    
    private boolean reopen;

    public String execute() throws Exception {

        if (this.getTarget() == null) {
            return SUCCESS;
        }
        
        ToDoServiceDelegate toDoServiceDelegate = ToDoServiceDelegateFactory.factory
                .build(this.getSessionContext());
        
        ToDo todo = toDoServiceDelegate.get(this.getTarget());

//        todo.setTag(this.getTag());
        
        /*
         * 2006-06-15 standard cant change their assignments. 
         */
        if (this.getSessionContext().getUserTypeEnum() != UserTypeEnum.STANDARD) {
            todo.setAssignedToUser(this.getAssignToUser());
        }
        
        todo.setPriority(this.getPriority());
        
        todo.setEstimatedTotalHours(this.getEstimatedTotalTime());
        
        todo.setEstimatedRemainingHours(this.getEstimatedRemainingTime());
        
        todo.setStatus(this.getStatus());
       
        todo.setDetail(this.getDetail());
        
        if (this.isReopen() && todo.isComplete()) {
            todo.setComplete(false);
        }
        
        toDoServiceDelegate.save(todo);
        
        this.getSessionContext().addToRecentActions(
                new DashboardRecentToDoAction(
                        DashboardRecentActionType.TODO_MODIFIED, todo,
                        this.getSystemOwner()));
        
        return SUCCESS;
    }

    /**
     * @return Returns the assignToUser.
     */
    public String getAssignToUser() {
        return assignToUser;
    }

    /**
     * @param assignToUser
     *            The assignToUser to set.
     */
    public void setAssignToUser(String assignToUser) {
        this.assignToUser = assignToUser;
    }

    /**
     * @return Returns the estimatedRemainingTime.
     */
    public Double getEstimatedRemainingTime() {
        return estimatedRemainingTime;
    }

    /**
     * @param estimatedRemainingTime
     *            The estimatedRemainingTime to set.
     */
    public void setEstimatedRemainingTime(Double estimatedRemainingTime) {
        this.estimatedRemainingTime = estimatedRemainingTime;
    }

    /**
     * @return Returns the estimatedTotalTime.
     */
    public Double getEstimatedTotalTime() {
        return estimatedTotalTime;
    }

    /**
     * @param estimatedTotalTime
     *            The estimatedTotalTime to set.
     */
    public void setEstimatedTotalTime(Double estimatedTotalTime) {
        this.estimatedTotalTime = estimatedTotalTime;
    }

    /**
     * @return Returns the priortiy.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priortiy
     *            The priortiy to set.
     */
    public void setPriority(String priortiy) {
        this.priority = priortiy;
    }

//    /**
//     * @return Returns the tag.
//     */
//    public String getTag() {
//        return tag;
//    }
//
//    /**
//     * @param tag
//     *            The tag to set.
//     */
//    public void setTag(String tag) {
//        this.tag = tag;
//    }

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

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return Returns the reopen.
     */
    public boolean isReopen() {
        return reopen;
    }

    /**
     * @param reopen The reopen to set.
     */
    public void setReopen(boolean reopen) {
        this.reopen = reopen;
    }
}
