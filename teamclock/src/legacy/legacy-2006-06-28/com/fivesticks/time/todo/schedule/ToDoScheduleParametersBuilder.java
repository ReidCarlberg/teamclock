/*
 * Created on Sep 20, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import java.util.Date;

/**
 * @author Nick
 * 
 *  
 */
public class ToDoScheduleParametersBuilder {

    public static ToDoScheduleParametersBuilder singleton = new ToDoScheduleParametersBuilder();

    public ToDoScheduleParametersBuilder() {
        super();

    }

    public ToDoScheduleQueryParameters buildAll() {
        ToDoScheduleQueryParameters workOrderScheduleQueryParameters = new ToDoScheduleQueryParameters();

        return workOrderScheduleQueryParameters;
    }

    public ToDoScheduleQueryParameters findDueForInitiation() {
        ToDoScheduleQueryParameters workOrderScheduleQueryParameters = new ToDoScheduleQueryParameters();
        workOrderScheduleQueryParameters.setBeforeInitiationDate(new Date());
        return workOrderScheduleQueryParameters;
    }

}