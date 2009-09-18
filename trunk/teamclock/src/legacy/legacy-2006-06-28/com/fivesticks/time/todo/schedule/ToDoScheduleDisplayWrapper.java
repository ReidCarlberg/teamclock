/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDisplayWrapper;

/**
 * @author Reid
 *  
 */
public class ToDoScheduleDisplayWrapper implements Serializable {

//    private FstxCustomer customer;
//
//    private FstxProject project;

    private final ToDoSchedule target;

    private final SystemOwner systemOwner;

    public ToDoScheduleDisplayWrapper(ToDoSchedule target,
            SystemOwner systemOwner) {
        this.target = target;
        this.systemOwner = systemOwner;
    }

    public String getDescription() {
        return target.getDescription();
    }

    public String getFrequency() {
        return target.getFrequency();
    }

    public Long getId() {
        return target.getId();
    }

    public Date getInitiationDate() {
        return target.getInitiationDate();
    }

    public int getMultiplier() {
        return target.getMultiplier();
    }

    public ToDoDisplayWrapper getToDo() {
        return new ToDoDisplayWrapper(target.getToDo(), systemOwner);
    }

    public void setDescription(String description) {
        target.setDescription(description);
    }

    public void setFrequency(String frequency) {
        target.setFrequency(frequency);
    }

    public void setId(Long id) {
        target.setId(id);
    }

    public void setInitiationDate(Date initiationDate) {
        target.setInitiationDate(initiationDate);
    }

    public void setMultiplier(int multiplier) {
        target.setMultiplier(multiplier);
    }

    public void setToDo(ToDo todo) {
        target.setToDo(todo);
    }
}