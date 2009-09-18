/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.todo.xwork;

import java.util.Collection;

import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDo2JSONConverter;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ListCurrentToDosAsJSONAction extends AbstractJSONAction {

    public String execute() throws Exception {

        Collection collection = ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).findIncompleteByAssignee(
                this.getSessionContext().getUser().getUser());
        
        this.setJsonDataAsString(new ToDo2JSONConverter(this.getSessionContext()).convert(collection).toString());

        return SUCCESS;
    }




}
