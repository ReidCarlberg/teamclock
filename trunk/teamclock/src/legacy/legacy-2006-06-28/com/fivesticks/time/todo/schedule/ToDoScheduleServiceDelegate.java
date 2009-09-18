/*
 * Created on Jun 4, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Reid
 *  
 */
public interface ToDoScheduleServiceDelegate extends SystemOwnerAware {

    /**
     * <p>
     * gets our business object based on an ID
     * 
     * @param id
     * @return @throws
     *         WorkOrderScheduleServiceDelegateException
     */
    public ToDoSchedule find(Long id) throws ServiceDelegateException;

    /**
     * <p>
     * Simple save or persist.
     * 
     * @param workOrderSchedule
     * @return @throws
     *         WorkOrderScheduleServiceDelegateException
     */
    public void save(ToDoSchedule todoSchedule) throws ServiceDelegateException;

    public void delete(ToDoSchedule workOrderSchedule)
            throws ServiceDelegateException;

    public Collection search(ToDoScheduleQueryParameters params)
            throws ServiceDelegateException;

}