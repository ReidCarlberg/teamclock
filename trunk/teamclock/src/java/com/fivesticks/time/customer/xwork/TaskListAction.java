/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;

/**
 * @author Reid
 *  
 */
public class TaskListAction extends SessionContextAwareSupport 
         {

    private Collection tasks;

    public String execute() throws Exception {

       
        
        this.setTasks(TaskServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).getAllTaskTypes());

        return SUCCESS;
    }

    /**
     * @return
     */
    public Collection getTasks() {
        return tasks;
    }

    /**
     * @param collection
     */
    public void setTasks(Collection collection) {
        tasks = collection;
    }


}