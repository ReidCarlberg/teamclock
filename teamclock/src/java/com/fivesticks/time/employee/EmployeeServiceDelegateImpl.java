/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

/**
 * @author Reid
 *
 */
public class EmployeeServiceDelegateImpl extends AbstractServiceDelegate
        implements EmployeeServiceDelegate {

    /* (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#save(com.fivesticks.time.employee.Employee)
     */
    public void save(Employee target) throws EmployeeServiceDelegateException {
        
        try {
            this.handleDecorate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new EmployeeServiceDelegateException(e);
        }
        
        this.getDao().save(target);
        
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#delete(com.fivesticks.time.employee.Employee)
     */
    public void delete(Employee target) throws EmployeeServiceDelegateException {
        
        try {
            this.handleValidate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new EmployeeServiceDelegateException(e);
        }
        
        this.getDao().delete(target);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#get(java.lang.Long)
     */
    public Employee get(Long id) throws EmployeeServiceDelegateException {
        
        Employee ret = (Employee) this.getDao().get(id);
        
        try {
            handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new EmployeeServiceDelegateException(e);
        }
        
        return ret;
    }
    
    /*
     * 2006-08-16 added for reports.
     * (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#get(java.lang.String)
     */
    public Employee get(String username) throws EmployeeServiceDelegateException {
        
        EmployeeCriteriaParameters p = new EmployeeCriteriaParameters();
        p.setUsername(username);
        
        Collection c = this.find(p);
        
        Employee ret = null;
        
        if (c.size() == 1) {
            ret = (Employee) c.toArray()[0];
        }
        
        return ret;
    }    

    /* (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#getAll()
     */
    public Collection getAll() throws EmployeeServiceDelegateException {
        
        EmployeeCriteriaParameters p = new EmployeeCriteriaParameters();
        
        return this.find(p);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.employee.EmployeeServiceDelegate#find(com.fivesticks.time.employee.EmployeeCriteriaParameters)
     */
    public Collection find(EmployeeCriteriaParameters parameters) throws EmployeeServiceDelegateException {
        
        try {
            this.handleDecorate(parameters);
        } catch (AbstractServiceDelegateException e) {
            throw new EmployeeServiceDelegateException(e);
        }
        
        return this.getDao().find(parameters);
    }

}
