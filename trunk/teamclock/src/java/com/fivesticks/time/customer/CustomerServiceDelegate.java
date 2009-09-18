/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Nick
 *  
 */
public interface CustomerServiceDelegate extends  SystemOwnerAware {

    public Customer save(Customer newCustomer)
            throws CustomerServiceDelegateException;

    /**
     * @return
     */
    Customer getUnassignedCustomer() throws CustomerServiceDelegateException;

    /**
     * @return
     */
    public Collection getAll() throws CustomerServiceDelegateException;

    /**
     * @param long1
     * @return
     */
    public Customer getFstxCustomer(Long long1)
            throws CustomerServiceDelegateException;

    /**
     * @param filterVO
     * @return
     */
    public Collection searchByFilter(CustomerFilterVO filterVO)
            throws CustomerServiceDelegateException;

    /**
     * @param target
     */
    public void delete(Customer target) throws CustomerServiceDelegateException;
    
    public Collection getCustomerBySetting(CustomerSettingType setting, String value) throws CustomerServiceDelegateException;
    
    public Collection getCustomerBySetting(CustomerFilterVO filter, CustomerSettingType setting, String value) throws CustomerServiceDelegateException;
}