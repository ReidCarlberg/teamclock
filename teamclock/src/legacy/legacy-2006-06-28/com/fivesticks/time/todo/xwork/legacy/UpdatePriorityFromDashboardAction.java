/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.todo.xwork.legacy;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class UpdatePriorityFromDashboardAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private Long target;

    private String priority;

    public String execute() throws Exception {

        if (this.getTarget() == null || this.getPriority() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());

        ToDo targetToDo = sd.get(this.getTarget());

        //        targetToDo.setComplete(true);

        targetToDo.setPriority(this.getPriority());

        sd.save(targetToDo);

        //        new ToDoEventPublisher().publishToDoCompleteEvent(this
        //                .getSessionContext().getSystemOwner(), this.getSessionContext()
        //                .getUser().getUser(), targetToDo);

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

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}