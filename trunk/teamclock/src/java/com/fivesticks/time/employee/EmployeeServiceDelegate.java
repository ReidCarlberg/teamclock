/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import java.util.Collection;

/**
 * @author Reid
 *
 */
public interface EmployeeServiceDelegate {

    public void save(Employee target) throws EmployeeServiceDelegateException;
    
    public void delete(Employee target) throws EmployeeServiceDelegateException;
    
    public Employee get(Long id) throws EmployeeServiceDelegateException;
    
    public Employee get(String username) throws EmployeeServiceDelegateException;
    
    public Collection getAll() throws EmployeeServiceDelegateException;
    
    public Collection find(EmployeeCriteriaParameters parameters) throws EmployeeServiceDelegateException;
}
