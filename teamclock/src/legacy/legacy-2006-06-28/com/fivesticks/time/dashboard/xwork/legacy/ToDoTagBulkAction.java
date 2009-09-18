/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.StringUtils;

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
public class ToDoTagBulkAction extends SessionContextAwareSupport implements
        DashboardContextAware {

    private static String SUBMIT_ADD = "Add Tag";

    private static String SUBMIT_REPLACE = "Replace Tag";

//    private Log log = LogFactory.getLog(ToDoTagBulkAction.class);

    private DashboardContext dashboardContext;

    private ToDoServiceDelegate toDoServiceDelegate;

    private Collection todosToTag;

    private String newTag;

    private String submit;

    public String execute() throws Exception {
        if (this.getTodosToTag() == null) {
            return ERROR;
        }

        if (StringUtils.hasText(this.getSubmit())) {

            for (Iterator iter = this.getTodosToTag().iterator(); iter
                    .hasNext();) {
                Object element = iter.next();

                Long id = new Long((String) element);
                if (id != null) {

                    ToDo todo = this.getToDoServiceDelegate().get(id);
                    if (SUBMIT_ADD.equals(this.getSubmit())) {
                        todo.setTag(todo.getTag() + newTag);
                    } else if (SUBMIT_REPLACE.equals(this.getSubmit())) {
                        todo.setTag(newTag);

                    }

                    this.getToDoServiceDelegate().save(todo);
                }
            }

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

    public Collection getTodosToTag() {
        return todosToTag;
    }

    public void setTodosToTag(Collection todosToTag) {
        this.todosToTag = todosToTag;
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