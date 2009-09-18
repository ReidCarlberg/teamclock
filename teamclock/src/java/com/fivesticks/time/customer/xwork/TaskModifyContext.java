/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.customer.Task;

/**
 * @author Reid
 *  
 */
public class TaskModifyContext {

    public static final String KEY = "context.modify.task";

    private Task targetTask;

    /**
     * @return
     */
    public Task getTargetTask() {
        return targetTask;
    }

    /**
     * @param task
     */
    public void setTargetTask(Task task) {
        targetTask = task;
    }

}