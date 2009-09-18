/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

/**
 * @author Reid
 *  
 */
public class AcknowledgeFromDashboardAction extends AbstractJSONAction {

    private Long target;

    

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        targetToDo.setStatus(null);

        sd.save(targetToDo);

        /*
         * not publishing for everyone yet
         */
        if (this.getSessionContext().isFeatureComplete()) {
            new ToDoEventPublisher().publishToDoCompleteEvent(this
                    .getSessionContext(), this
                    .getSessionContext().getUser().getUser(), targetToDo);
        }

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