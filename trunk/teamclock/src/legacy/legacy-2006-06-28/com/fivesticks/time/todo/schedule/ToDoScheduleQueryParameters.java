/*
 * Created on Oct 4, 2004
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
public class ToDoScheduleQueryParameters extends ToDoSchedule {

    private Date beforeInitiationDate;

    public ToDoScheduleQueryParameters() {
        super();

    }

    public void setBeforeInitiationDate(Date date) {
        beforeInitiationDate = date;
    }

    public Date getBeforeInitiationDate() {
        return beforeInitiationDate;
    }
}