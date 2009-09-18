/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.Collection;
import java.util.List;

import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 *  
 */
public interface ToDoServiceDelegate  {

    public Collection findIncomplete() throws ToDoServiceDelegateException;

    public Collection findIncomplete(Customer fstxCustomer)
            throws ToDoServiceDelegateException;



    public Collection findIncompleteByAssignee(User user1)
            throws ToDoServiceDelegateException;

    public Collection findIncompleteByAssignee(String username, String priority)
            throws ToDoServiceDelegateException;

    public Collection findIncompleteByAssignee(String username,
            String priority, String unprioritized)
            throws ToDoServiceDelegateException;

    public Collection findIncompleteAssignedToSomeoneElse(String enteredby)
            throws ToDoServiceDelegateException;
    
        public Collection findIncompleteAssignedToSomeoneElse(String enteredby, ToDoCriteriaParameters params)
            throws ToDoServiceDelegateException;

    public Collection find(ToDoCriteriaParameters params)
            throws ToDoServiceDelegateException;

    public ToDo get(Long id) throws ToDoServiceDelegateException;

    public ToDo save(ToDo target) throws ToDoServiceDelegateException;

    public void delete(ToDo target) throws ToDoServiceDelegateException;

    public List findDistinctToDoProjects(DashboardContext dashboardContext);
    
    public void decorateWithTotalActivityTotals(ToDo targetToDo) throws ToDoServiceDelegateException;
    
    public Collection findRelatedTodos(IdReadable linkedObject) throws ToDoServiceDelegateException;
}