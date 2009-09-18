/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.dashboard.xwork.DashboardContextAware;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Reid
 * 
 */
public class ToDoTagSingleAction extends SessionContextAwareSupport implements
        DashboardContextAware {

//    private static String SUBMIT_ADD = "Add Tag";
//
//    private static String SUBMIT_REPLACE = "Replace Tag";
//
//    private Log log = LogFactory.getLog(ToDoTagSingleAction.class);

    private DashboardContext dashboardContext;

    private ToDoServiceDelegate toDoServiceDelegate;

    private Long todosToTagId;

    private String newTag;

    private String submit;

    public String execute() throws Exception {
        if (this.getTodosToTagId() == null) {
            return ERROR;
        }

        Long id = this.getTodosToTagId();
        if (id != null) {

            ToDo todo = this.getToDoServiceDelegate().get(id);
            todo.setTag(newTag);
            this.getToDoServiceDelegate().save(todo);
        } else {
            return ERROR;

        }

        return SUCCESS;
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

    public Long getTodosToTagId() {
        return todosToTagId;
    }

    public void setTodosToTagId(Long todosToTagId) {
        this.todosToTagId = todosToTagId;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public ToDoServiceDelegate getToDoServiceDelegate() {
        if (toDoServiceDelegate == null) {
            toDoServiceDelegate = ToDoServiceDelegateFactory.factory.build(this
                    .getSystemOwner());

        }

        return toDoServiceDelegate;
    }

    public void setToDoServiceDelegate(ToDoServiceDelegate toDoServiceDelegate) {
        this.toDoServiceDelegate = toDoServiceDelegate;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

}