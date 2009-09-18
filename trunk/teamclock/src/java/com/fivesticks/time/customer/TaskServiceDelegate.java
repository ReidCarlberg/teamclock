/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Nick
 *  
 */
public interface TaskServiceDelegate extends SystemOwnerAware {

    public Task save(Task fstxTask) throws TaskBDException;
    
    public void delete(Task fstxTask) throws TaskBDException;

    public void addTaskType(String taskType) throws TaskBDException;

    public Task getTaskType(String taskType) throws TaskBDException;

    public Task getTaskType(Long id) throws TaskBDException;

    public Collection getAllTaskTypes() throws TaskBDException;

    public Collection search(TaskCriteriaParameters params) throws TaskBDException;
}