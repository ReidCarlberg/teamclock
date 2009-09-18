/*
 * Created on Jul 5, 2006 by Reid
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDo2JSONConverter;

public class GetDelegatedToDoListAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {

        Collection delegated = ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).findIncompleteAssignedToSomeoneElse(
                this.getSessionContext().getUser().getUsername());

        this.setJsonResult(new ToDo2JSONConverter(this.getSessionContext())
                .convert(delegated));

        return SUCCESS;

    }
}
