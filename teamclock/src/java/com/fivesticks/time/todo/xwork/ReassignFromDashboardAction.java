/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 *  
 */
public class ReassignFromDashboardAction extends ActionSupport implements
        SessionContextAware {

    private Long target;

    private String username;

    private SessionContext sessionContext;

    public String execute() throws Exception {

        if (this.getTarget() == null || this.getUsername() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        targetToDo.setAssignedToUser(this.getUsername());

        sd.save(targetToDo);

        new ToDoEventPublisher().publishToDoReassignedEvent(this
                .getSessionContext(), this.getSessionContext()
                .getUser().getUser(), targetToDo);

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

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}