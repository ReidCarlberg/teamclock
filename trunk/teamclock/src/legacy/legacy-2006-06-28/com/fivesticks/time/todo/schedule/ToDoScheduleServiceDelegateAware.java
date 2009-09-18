/*
 * Created on Aug 18, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

/**
 * @author Nick
 * 
 *  
 */
public interface ToDoScheduleServiceDelegateAware {
    public void setWorkOrderServiceDelegate(
            ToDoScheduleServiceDelegate workOrderScheduleServiceDelegate);
}