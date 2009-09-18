/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * <p>
 * 2004-08-24 Updated to include sessionContext handling.
 * 
 * @author Nick
 * @author Reid
 * 
 */
public class TaskServiceDelegateImpl extends AbstractServiceDelegate implements
        TaskServiceDelegate {

    private SystemOwner systemOwner;

    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // com.fivesticks.time.customer.FstxTaskDAOAware#setFstxTaskDAO(com.fivesticks.time.customer.FstxTaskDAO)
    // */
    // public void setFstxTaskDAO(FstxTaskDAO fstxTaskDAO) {
    // this.dao = fstxTaskDAO;
    // }
    //
    // /**
    // * @return
    // */
    // public FstxTaskDAO getDao() {
    // return dao;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxTaskBD#addTaskType(java.lang.String)
     */
    public void addTaskType(String taskType) throws TaskBDException {
        Task temp = null;

        temp = getTaskType(taskType);

        if (temp != null)
            throw new TaskBDException("already exists.");

        temp = new Task();
        temp.setNameShort(taskType);
        temp.setNameLong(taskType);

        /*
         * owner
         */
        temp.setOwnerKey(this.getSystemOwner().getKey());

        this.getDao().save(temp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxTaskBD#getAllTaskTypes()
     */
    public Collection getAllTaskTypes() throws TaskBDException {
        TaskCriteriaParameters params = new TaskCriteriaParameters();
        params.setOwnerKey(this.getSystemOwner().getKey());

        // return this.getFstxTaskDAO()
        // .searchFstxTasks(FstxTaskDAO.SELECT_ALL);

        return this.getDao().find(params);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxTaskBD#getTaskType(java.lang.String)
     */
    public Task getTaskType(String taskType) throws TaskBDException {

        Collection list;

        // list = this.getFstxTaskDAO().searchFstxTasks(
        // FstxTaskDAO.SELECT_BY_NAME, taskType);

        TaskCriteriaParameters params = new TaskCriteriaParameters();
        params.setNameShort(taskType);
        params.setOwnerKey(this.getSystemOwner().getKey());

        list = this.getDao().find(params);

        Task ret = null;

        if (list.size() == 1) {
            ret = (Task) list.toArray()[0];
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxTaskBD#getTaskType(java.lang.Long)
     */
    public Task getTaskType(Long id) throws TaskBDException {
        Task ret = null;
        ret = (Task) this.getDao().get(id);

        if (ret != null)
            validate(ret);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxTaskBD#save(com.fivesticks.time.customer.FstxTask)
     */
    public Task save(Task fstxTask) throws TaskBDException {

        if (fstxTask.getId() == null)
            decorate(fstxTask);
        else
            validate(fstxTask);

        this.getDao().save(fstxTask);

        return fstxTask;
    }

    private void validate(Task fstxTask) throws TaskBDException {
        if (!fstxTask.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new TaskBDException(
                    "Couldn't validate that the task in question belongs to the current SystemOwner");
        }
    }

    private void decorate(Task fstxTask) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No session context to decorate with.");
        }
        fstxTask.setOwnerKey(this.getSystemOwner().getKey());
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public Collection search(TaskCriteriaParameters params)
            throws TaskBDException {
        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new TaskBDException(e);
        }
        return this.getDao().find(params);
    }

    public void delete(Task fstxTask) throws TaskBDException {

        try {
            this.handleValidate(fstxTask);
        } catch (AbstractServiceDelegateException e) {
            throw new TaskBDException(e);
        }

        this.getDao().delete(fstxTask);
    }
}