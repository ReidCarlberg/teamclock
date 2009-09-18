/*
 * Created on Mar 4, 2005
 *
 * 
 */
package com.fivesticks.time.todo.queue;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Nick
 * 
 *  
 */

public class QueueServiceDelegateImpl implements SystemOwnerAware,
        QueueServiceDelegate {

    private SystemOwner systemOwner;

//    private ToDoServiceDelegate toDoServiceDelegate;

    /*
     * The queue is implemented as a todo with a user named Queue
     */
    public Collection getAll() throws QueueServiceDelegateException {

        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
        params.setAssignedToUser(QUEUE_USERNAME);
        try {
            return this.getToDoServiceDelegate().find(params);
        } catch (ToDoServiceDelegateException e) {
            throw new QueueServiceDelegateException(e);
        }

    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public ToDoServiceDelegate getToDoServiceDelegate() {
//        toDoServiceDelegate.setSystemOwner(this.getSystemOwner());
//
//        return toDoServiceDelegate;
        
        return ToDoServiceDelegateFactory.factory.build(this.getSystemOwner());
    }

//    public void setToDoServiceDelegate(ToDoServiceDelegate toDoServiceDelegate) {
//        this.toDoServiceDelegate = toDoServiceDelegate;
//    }
}