/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import com.fivesticks.time.employee.Employee;
import com.fivesticks.time.employee.util.EmployeeAccountTransactionType;

/**
 * @author Reid
 *
 */
public class ModifyContext {

    private Employee target;
    private EmployeeAccountTransactionType employeeAccountTransactionType;
   
    
    /**
     * @return Returns the target.
     */
    public Employee getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Employee target) {
        this.target = target;
    }
    
    
    /**
     * @return Returns the employeeAccountTransactionType.
     */
    public EmployeeAccountTransactionType getEmployeeAccountTransactionType() {
        return employeeAccountTransactionType;
    }
    /**
     * @param employeeAccountTransactionType The employeeAccountTransactionType to set.
     */
    public void setEmployeeAccountTransactionType(
            EmployeeAccountTransactionType employeeAccountTransactionType) {
        this.employeeAccountTransactionType = employeeAccountTransactionType;
    }
}
