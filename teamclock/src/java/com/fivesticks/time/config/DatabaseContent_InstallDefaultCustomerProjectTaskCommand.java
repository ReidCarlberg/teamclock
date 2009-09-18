/*
 * Created on Aug 27, 2004 by Reid
 */
package com.fivesticks.time.config;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class DatabaseContent_InstallDefaultCustomerProjectTaskCommand {

    private SystemOwner defaultOwner;

    private int iterationNumber;

    public void execute() throws Exception {

        Customer customer = new Customer();
        customer.setName("Sample Customer" + this.getIterationSuffix());
        customer.setOwnerKey(this.getDefaultOwner().getKey());
        CustomerServiceDelegateFactory.factory.build(this.defaultOwner).save(customer);

        Project project = new Project();
        project.setCustId(customer.getId());
        project.setLongName("Sample Project" + this.getIterationSuffix());
        project.setShortName("sample" + this.getIterationSuffix());
        project.setActive(true);
        project.setOwnerKey(this.getDefaultOwner().getKey());
        //add link to system owner
        ProjectServiceDelegateFactory.factory.build(defaultOwner).save(project);

        Task task = new Task();
        task.setNameLong("Other" + this.getIterationSuffix());
        task.setNameShort("other" + this.getIterationSuffix());
        task.setOwnerKey(this.getDefaultOwner().getKey());
        TaskServiceDelegateFactory.factory.build(defaultOwner).save(task);

    }

    /**
     * @return Returns the defaultOwner.
     */
    public SystemOwner getDefaultOwner() {
        return defaultOwner;
    }

    /**
     * @param defaultOwner
     *            The defaultOwner to set.
     */
    public void setDefaultOwner(SystemOwner defaultOwner) {
        this.defaultOwner = defaultOwner;
    }

    private String getIterationSuffix() {
        if (iterationNumber == 0)
            return "";
        else
            return "" + (this.getIterationNumber() + 1);
    }

    /**
     * @return Returns the iterationNumber.
     */
    public int getIterationNumber() {
        return iterationNumber;
    }

    /**
     * @param iterationNumber
     *            The iterationNumber to set.
     */
    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }
}