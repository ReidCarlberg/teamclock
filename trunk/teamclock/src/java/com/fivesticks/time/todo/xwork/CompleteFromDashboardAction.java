/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.dashboard.util.DashboardRecentActionType;
import com.fivesticks.time.dashboard.util.DashboardRecentToDoAction;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.dashboard.xwork.DashboardContextAware;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

/**
 * @author Reid
 * 
 */
public class CompleteFromDashboardAction extends AbstractJSONAction implements
        DashboardContextAware {

    private Long target;

    //    

    private DashboardContext dashboardContext;

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        ToDo targetToDo = sd.get(this.getTarget());

        targetToDo.setComplete(true);

        sd.save(targetToDo);

        /*
         * not publishing for everyone yet
         */
        if (this.getSessionContext().isFeatureComplete()) {
            new ToDoEventPublisher().publishToDoCompleteEvent(this
                    .getSessionContext(), this
                    .getSessionContext().getUser().getUser(), targetToDo);
        }

        /*
         * 2006-06-25 reid@fivesticks.com
         * probably need to have this added to an even publisher at somepoint...
         */
        this.getSessionContext().addToRecentActions(
                new DashboardRecentToDoAction(
                        DashboardRecentActionType.TODO_COMPLETED, targetToDo,
                        this.getSystemOwner()));
        
        // for JSON so there's something to eval.
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

    /**
     * @return Returns the dashboardContext.
     */
    public DashboardContext getDashboardContext() {
        return dashboardContext;
    }

    /**
     * @param dashboardContext
     *            The dashboardContext to set.
     */
    public void setDashboardContext(DashboardContext dashboardContext) {
        this.dashboardContext = dashboardContext;
    }
}