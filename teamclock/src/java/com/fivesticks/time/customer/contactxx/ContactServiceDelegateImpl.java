/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

/**
 * @author Reid
 */
public class ContactServiceDelegateImpl extends AbstractServiceDelegate implements ContactServiceDelegate {

    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#getByCustomer(com.fivesticks.time.customer.FstxCustomer)
     */
    public Collection getByCustomer(Customer cust1) throws ContactServiceDelegateException {
        
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(cust1,this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactServiceDelegateException(e);
        }
        
        ContactFilter filter = new ContactFilter();
        filter.setCustId(cust1.getId());
        
        return this.getList(filter);
        
    }
    
    public Collection getList(ContactFilter filter) throws ContactServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(filter, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactServiceDelegateException(e);
        }
        
        return this.getDao().find(filter);
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#delete(com.fivesticks.time.customer.contact.Contact)
     */
    public void delete(Contact contact) throws ContactServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(contact, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactServiceDelegateException(e);
        }
        
        this.getDao().delete(contact);
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#save(com.fivesticks.time.customer.contact.Contact)
     */
    public void save(Contact contact) throws ContactServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(contact, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactServiceDelegateException(e);
        }
        
        this.getDao().save(contact);
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#get(java.lang.Long)
     */
    public Contact get(Long id) throws ContactServiceDelegateException {
        Contact ret = (Contact) this.getDao().get(id);
        
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(ret, this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ContactServiceDelegateException(e);
//        }
        
        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new ContactServiceDelegateException(e);
        }
        
        return ret;
    }

}
