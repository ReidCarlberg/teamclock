/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;

/**
 * @author Reid
 */
public interface ContactServiceDelegate extends SystemOwnerAware, OwnerKeyValidatorAndDecoratorAware {
    
    /**
     * @param cust1
     * @return
     */
    public Collection getByCustomer(Customer cust1) throws ContactServiceDelegateException;
    
    public Collection getList(ContactFilter filter) throws ContactServiceDelegateException;
    
    public void delete(Contact contact) throws ContactServiceDelegateException;
    
    public void save(Contact contact) throws ContactServiceDelegateException;
    
    public Contact get(Long id) throws ContactServiceDelegateException;
}
