/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.EmployeeServiceDelegateFactory;

/**
 * @author Reid
 *
 */
public class ListAction extends SessionContextAwareSupport {

    public Collection employees;
    
    public String execute() throws Exception {
        
        this.setEmployees(EmployeeServiceDelegateFactory.factory.build(this.getSystemOwner()).getAll());
        
        return SUCCESS;
    }
    /**
     * @return Returns the employees.
     */
    public Collection getEmployees() {
        return employees;
    }
    /**
     * @param employees The employees to set.
     */
    public void setEmployees(Collection employees) {
        this.employees = employees;
    }
}
