/*
 * Created on Jun 4, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * See also the <link>TemplatedWorkOrderScheduleServiceDelegate </link>.
 * 
 * @author Reid
 *  
 */
public class ToDoScheduleServiceDelegateImpl implements
        ToDoScheduleServiceDelegate {

//    private SessionContext sessionContext;

    private ToDoScheduleDAO todoScheduleDao;

    private SystemOwner systemOwner;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.cmms.core.workOrderSchedule.WorkOrderScheduleServiceDelegate#find(java.lang.Long)
     */
    public ToDoSchedule find(Long id) throws ServiceDelegateException {

        ToDoSchedule todoSchedule = (ToDoSchedule) this.getTodoScheduleDao()
                .get(id);
        try {
            validate(todoSchedule);
        } catch (ToDoScheduleServiceDelegateException e) {
            throw new ServiceDelegateException(e);

        }
        return todoSchedule;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.cmms.core.workOrderSchedule.WorkOrderScheduleServiceDelegate#save(com.fivesticks.cmms.core.workOrderSchedule.WorkOrderSchedule)
     */
    public void save(ToDoSchedule todoSchedule) {
        decorate(todoSchedule);
        this.getTodoScheduleDao().save(todoSchedule);

        return;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.cmms.core.workOrderSchedule.WorkOrderScheduleServiceDelegate#delete(com.fivesticks.cmms.core.workOrderSchedule.WorkOrderSchedule)
     */
    public void delete(ToDoSchedule todoSchedule)
            throws ServiceDelegateException {

        this.getTodoScheduleDao().delete(todoSchedule);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.cmms.core.workOrderSchedule.WorkOrderScheduleServiceDelegate#search(com.fivesticks.cmms.core.workOrderSchedule.WorkOrderScheduleQueryParameters)
     */
    public Collection search(ToDoScheduleQueryParameters params)
            throws ServiceDelegateException {
        decorate(params);
        Collection col = this.getTodoScheduleDao().find(params);
        return col;
    }

    public void setToDoScheduleDAO(ToDoScheduleDAO todoScheduleDao) {
        this.todoScheduleDao = todoScheduleDao;
    }

    public ToDoScheduleDAO getTodoScheduleDao() {
        return todoScheduleDao;
    }

    private void validate(SystemOwnerKeyAware systemOwnerKeyAware)
            throws ToDoScheduleServiceDelegateException {
        if (!systemOwnerKeyAware.getOwnerKey().equals(
                this.getSystemOwner().getKey())) {
            throw new ToDoScheduleServiceDelegateException(
                    "not a valid request given the current system owner");
        }

    }

    private void decorate(SystemOwnerKeyAware systemOwnerKeyAware) {
        systemOwnerKeyAware.setOwnerKey(this.getSystemOwner().getKey());
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}