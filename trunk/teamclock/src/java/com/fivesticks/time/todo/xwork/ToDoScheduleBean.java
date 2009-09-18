/*
 * Created on Mar 3, 2003
 *
 * 
 */
package com.fivesticks.time.todo.xwork;

import java.util.Date;

/**
 * @author Nick
 * 
 *  
 */
public class ToDoScheduleBean {

    private boolean recurring;

    private String description;

    private Date initiationDate;

    private String frequency;

    private Integer multiplier;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}