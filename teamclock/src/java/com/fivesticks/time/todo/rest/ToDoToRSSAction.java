/*
 * Created on Dec 14, 2005
 *
 */
package com.fivesticks.time.todo.rest;

import java.util.Collection;

import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoDisplayBuilder;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.ws.xwork.AbstractRestAction;

public class ToDoToRSSAction extends AbstractRestAction {

    public Collection listResults;
    
    public ToDoToRSSAction() {
        super();
        this.setAction(RestToDoActionType.LIST.getName());
    }
    protected void handleActionTypeValidate() throws Exception {


    }

    protected void handleAction() throws Exception {


        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
        params
                .setAssignedToUser(this.getAuthenticationBasedServiceSupport().getSessionContext().getUser().getUsername());
        params.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        params.setTodoComplete(Boolean.FALSE.toString());

        Collection rawToDos = ToDoServiceDelegateFactory.factory
                .build(
                        this.getAuthenticationBasedServiceSupport()
                                .getSessionContext()).find(params);

        Collection ret = new ToDoDisplayBuilder(this
                .getAuthenticationBasedServiceSupport().getSessionContext())
                .build(rawToDos);

        this.setListResults(ret);

    }

    /**
     * @return Returns the listResults.
     */
    public Collection getListResults() {
        return listResults;
    }

    /**
     * @param listResults The listResults to set.
     */
    public void setListResults(Collection listResults) {
        this.listResults = listResults;
    }


}
