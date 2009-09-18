/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.dashboard.util.DashboardRecentActionType;
import com.fivesticks.time.dashboard.util.DashboardRecentToDoAction;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author REID
 */
public class SetPriorityFromDashboardAction extends AbstractDashboardJSONAction {

    

    private Long target;

    private String priority;

    public String execute() throws Exception {

        if (this.getTarget() == null || this.getPriority() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        
        targetToDo.setPriority(this.getPriority());

        sd.save(targetToDo);

        this.getSessionContext().addToRecentActions(
                new DashboardRecentToDoAction(
                        DashboardRecentActionType.TODO_PRIORITY, targetToDo,
                        this.getSystemOwner()));
        
        return SUCCESS;

    }

    /**
     * @return Returns the id.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setTarget(Long id) {
        this.target = id;
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


}