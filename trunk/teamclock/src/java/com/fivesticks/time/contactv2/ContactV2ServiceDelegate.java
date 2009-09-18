/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.contactv2;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;

/**
 * @author Reid
 */
public interface ContactV2ServiceDelegate extends SystemOwnerAware, OwnerKeyValidatorAndDecoratorAware {
    
    /**
     * @param cust1
     * @return
     */
    public Collection getByCustomer(Customer cust1) throws ContactV2ServiceDelegateException;
    
//    public Collection getByLookup(Lookup lookup) throws ContactV2ServiceDelegateException;
    
    public Collection getCustomersByContact(ContactV2 contact) throws ContactV2ServiceDelegateException;
    
//    public Collection getLookupsByContact(ContactV2 contact, LookupType lookupType) throws ContactV2ServiceDelegateException;
    
//    public Collection getList(ContactV2CriteriaParameters filter) throws ContactV2ServiceDelegateException;
    
    public Collection getListCri(ContactV2CriteriaParameters params) throws ContactV2ServiceDelegateException;
    
    public void delete(ContactV2 contact) throws ContactV2ServiceDelegateException;
    
    public void save(ContactV2 contact) throws ContactV2ServiceDelegateException;
    
    public ContactV2 get(Long id) throws ContactV2ServiceDelegateException;
    
    public boolean isAssociated(ContactV2 contactV2, Customer customer) throws ContactV2ServiceDelegateException;
    
    public void associate(ContactV2 contactV2, Customer customer) throws ContactV2ServiceDelegateException;
    
    public void dissociate(ContactV2 contactV2, Customer customer) throws ContactV2ServiceDelegateException;
    
    public boolean hasAssociation(ContactV2 contactV2) throws ContactV2ServiceDelegateException;

//    public boolean isAssociated(ContactV2 contactV2, Lookup lookup) throws ContactV2ServiceDelegateException;
//    
//    public void associate(ContactV2 contactV2, Lookup lookup) throws ContactV2ServiceDelegateException;
//    
//    public void dissociate(ContactV2 contactV2, Lookup lookup) throws ContactV2ServiceDelegateException;
//
//    public void associateClass(ContactV2 contactV2, Lookup lookup) throws ContactV2ServiceDelegateException;

    public void add(ContactV2 contactV2, ContactV2KeyValue contactV2KeyValue) throws ContactV2ServiceDelegateException;

    public Collection getKeyValues(ContactV2 contactV2) throws ContactV2ServiceDelegateException;
}
