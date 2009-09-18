/*
 * Created on Jan 12, 2005 by Reid
 */
package com.fivesticks.time.todo.xwork.legacy;

import com.fivesticks.time.dashboard.xwork.AbstractDashboardContextAware;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Reid
 */
public class DashboardSequenceSoonerAction extends AbstractDashboardContextAware {

//    private DashboardContext dashboardContext;
//
//    private SessionContext sessionContext;

    private Long target;

    public String execute() throws Exception {

        /*
         * need to know user and priority
         */

        if (this.getDashboardContext().getToDoUsername() == null
                || this.getDashboardContext().getPriority() == null
                || this.getTarget() == null) {
            return INPUT;
        }

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());

        ToDo sooner = sd.get(this.getTarget());

        sd.resequenceIncompleteSooner(sooner);

        return SUCCESS;
    }

//    /**
//     * @return Returns the dashboardContext.
//     */
//    public DashboardContext getDashboardContext() {
//        return dashboardContext;
//    }
//
//    /**
//     * @param dashboardContext
//     *            The dashboardContext to set.
//     */
//    public void setDashboardContext(DashboardContext dashboardContext) {
//        this.dashboardContext = dashboardContext;
//    }
//
//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
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
}