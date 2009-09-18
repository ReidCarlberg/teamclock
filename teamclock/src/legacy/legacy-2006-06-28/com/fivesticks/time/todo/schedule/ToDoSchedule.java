/*
 * Created on May 22, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.todo.ToDo;

/**
 * <p>
 * A Labor object represents a unit of human work performed for a workorder.
 * 
 * 
 * @author Nick
 *  
 */
public class ToDoSchedule implements Serializable, SystemOwnerKeyAware {

    private Long id;

    private String description;

    /*
     * The date the a new work Order should be created for.
     */
    private Date initiationDate;

    /*
     * The frequency in which the workorders should be created.
     */
    private String frequency;

    /*
     * The multiplyer allows us to specify every 2 months.
     *  
     */
    private int multiplier = 1;

    /*
     * The todo that will be used as the basis for the generated todos.
     */
    private ToDo todo;

    private String ownerKey;

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    public ToDo getToDo() {
        return todo;
    }

    public void setToDo(ToDo todo) {
        this.todo = todo;
    }

    public boolean equals(Object arg0) {
        if (arg0 instanceof ToDoSchedule) {
            ToDoSchedule labor = (ToDoSchedule) arg0;
            if (this.getId() != null && this.getId().equals(labor.getId())) {
                return true;
            }
        }
        return false;
    }

    public void incrementInitiationDate() {

        Date newDate = ScheduleUtility.increment(this.getInitiationDate(), this
                .getFrequency(), multiplier);

        this.setInitiationDate(newDate);

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}