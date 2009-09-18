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
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 *  
 */
public class AcceptFromDashboardAction extends ActionSupport implements
        SessionContextAware {

    private Long target;

    private SessionContext sessionContext;

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        targetToDo.setAssignedToUser(this.getSessionContext().getUser()
                .getUsername());

        sd.save(targetToDo);

        /*
         * We will worry about this later
         */
        //            new ToDoEventPublisher().publishToDoAcceptedEvent(this
        //                    .getSessionContext().getSystemOwner(), this
        //                    .getSessionContext().getUser().getUser(), targetToDo);
        //      
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
}