/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork.legacy;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.todo.schedule.ServiceDelegateException;
import com.fivesticks.time.todo.schedule.ToDoScheduleDisplayBuilder;
import com.fivesticks.time.todo.schedule.ToDoScheduleQueryParameters;
import com.fivesticks.time.todo.schedule.ToDoScheduleServiceDelegate;
import com.fivesticks.time.todo.schedule.ToDoScheduleServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 *  
 */

public class ScheduleListAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private ToDoScheduleServiceDelegate toDoScheduleServiceDelegate;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.TODO);

        return SUCCESS;
    }

    public Collection getScheduledTodos() {
        ToDoScheduleQueryParameters params = new ToDoScheduleQueryParameters();
        Collection col = null;
        try {
            col = this.getToDoScheduleServiceDelegate().search(params);
        } catch (ServiceDelegateException e) {
            e.printStackTrace();
            return null;

        }

        ToDoScheduleDisplayBuilder builder = new ToDoScheduleDisplayBuilder(
                this.getSessionContext().getSystemOwner());
        return builder.build(col);
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public ToDoScheduleServiceDelegate getToDoScheduleServiceDelegate() {
        toDoScheduleServiceDelegate = ToDoScheduleServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());
        return toDoScheduleServiceDelegate;
    }

    public void setToDoScheduleServiceDelegate(
            ToDoScheduleServiceDelegate toDoScheduleServiceDelegate) {

        this.toDoScheduleServiceDelegate = toDoScheduleServiceDelegate;
    }
}