/*
 * Created on Jan 19, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Collection;

import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.todo.ToDoDisplayBuilder;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author REID
 */
public class ExternalUserViewToDoListAction extends AbstractExternalCustomerAction {

    

    private Collection todos;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.TODO);

        if (this.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() == null) {
            return INPUT;
        }

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        this.setTodos(sd.findIncomplete(this.getSessionContext()
                .getExternalUserSessionContext().getActiveCustomer()));

        this.setTodos(new ToDoDisplayBuilder(this.getSessionContext()
                .getSystemOwner()).build(this.getTodos()));

        return SUCCESS;
    }


    /**
     * @return Returns the transactions.
     */
    public Collection getTodos() {
        return todos;
    }

    /**
     * @param transactions
     *            The transactions to set.
     */
    public void setTodos(Collection transactions) {
        this.todos = transactions;
    }
}