/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoDisplayBuilder;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 *  
 */
public class ListAction extends SessionContextAwareSupport implements 
        ToDoListContextAware {

    private ToDoCriteriaParameters params = new ToDoCriteriaParameters();

    private ToDoListContext toDoListContext;

    private Collection todos;

    private String submitList;

    private String resetList;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.TODO);

        if (submitList == null && resetList == null
                && this.getToDoListContext().getParams() == null)
            return INPUT;

        if (this.getSubmitList() == null
                && this.getToDoListContext().getParams() != null)
            this.setParams(this.getToDoListContext().getParams());

        if (resetList != null) {
            this.setParams(new ToDoCriteriaParameters());
            return INPUT;
        }

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            params.setAssignedToUser(this.getSessionContext().getUser()
                    .getUsername());
        }

        ToDoListContext context = new ToDoListContext();

        context.setParams(params);

        ActionContext.getContext().getSession().put(ToDoListContext.KEY,
                context);

        Collection raw = ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).find(params);

        todos = new ToDoDisplayBuilder(this.getSessionContext()
                .getSystemOwner()).build(raw);

        this.getToDoListContext().setParams(params);

        return SUCCESS;
    }

    /**
     * @return
     */
    public Collection getTodos() {
        return todos;
    }

//    /**
//     * @return
//     */
//    public Collection getCustomers() {
//        try {
//            customers = FstxCustomerBD.factory.build(this.getSessionContext())
//                    .getAll();
//        } catch (FstxCustomerBDException e) {
//            throw new RuntimeException("customers", e);
//        }
//        return customers;
//    }

    /**
     * @return
     */
    public ToDoCriteriaParameters getParams() {
        return params;
    }

//    /**
//     * @return
//     */
//    public Collection getProjects() {
//        try {
//            projects = FstxProjectBD.factory.build(this.getSessionContext())
//                    .getAllActive();
//        } catch (FstxProjectBDException e) {
//            throw new RuntimeException("projects", e);
//        }
//
//        return projects;
//    }

    /**
     * @return
     */
    public String getSubmitList() {
        return submitList;
    }

    /**
     * @return
     */
    public Collection getUsers() {


        /*
         * RSC 2005-07-02
         */
        return this.getUserListProvider().getInternalUsersAll();
        
        
    }

    public Collection getExternalUsers() {

        

        Collection ret =  this.getUserListProvider().getExternalUsers();
        
        return ret;
    }


    /**
     * @param parameters
     */
    public void setParams(ToDoCriteriaParameters parameters) {
        params = parameters;
    }

//    /**
//     * @param collection
//     */
//    public void setProjects(Collection collection) {
//        projects = collection;
//    }

    /**
     * @param string
     */
    public void setSubmitList(String string) {
        submitList = string;
    }

    public String getSurrogateComplete() {
        if (this.getParams().getTodoComplete() == null)
            return "";
        else
            return this.getParams().getTodoComplete().toString();
    }

    /**
     * @return
     */
    public String getResetList() {
        return resetList;
    }

    /**
     * @param string
     */
    public void setResetList(String string) {
        resetList = string;
    }

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
     * @return Returns the toDoListContext.
     */
    public ToDoListContext getToDoListContext() {
        return toDoListContext;
    }

    /**
     * @param toDoListContext
     *            The toDoListContext to set.
     */
    public void setToDoListContext(ToDoListContext toDoListContext) {
        this.toDoListContext = toDoListContext;
    }
}