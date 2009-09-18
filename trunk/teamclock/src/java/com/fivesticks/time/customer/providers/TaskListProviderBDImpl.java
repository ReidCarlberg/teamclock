/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.customer.providers;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.common.xwork.AbstractSystemOwnerAwareProvider;
import com.fivesticks.time.customer.TaskBDException;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;

/**
 * @author Reid
 *
 */
public class TaskListProviderBDImpl extends AbstractSystemOwnerAwareProvider
        implements TaskListProvider, Serializable {

    private Collection tasks;
    
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.providers.TaskListProvider#getTasks()
     */
    public Collection getTasks() throws TaskListProviderException {

        if (this.tasks == null) {
            try {
                tasks = TaskServiceDelegateFactory.factory.build(this.getSystemOwner()).getAllTaskTypes();
            } catch (TaskBDException e) {
                throw new TaskListProviderException(e);
            }
        }
        return tasks;
    }

}
