/*
 * Created on May 2, 2006
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

public class CustomerAndContactCollectionBuilder {

    public Collection build(SystemOwner systemOwner, Long lookupStatusId) throws Exception {
        
        Collection ret = new ArrayList();
        
        CustomerFilterVO params = new CustomerFilterVO();
        params.setStatus(lookupStatusId);
        
        Collection customers = CustomerServiceDelegateFactory.factory.build(systemOwner).searchByFilter(params);
        
        ContactV2ServiceDelegate contactServiceDelegate = ContactV2ServiceDelegateFactory.factory.build(systemOwner);
        
        for (Iterator iter = customers.iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();
            
            Collection contacts = contactServiceDelegate.getByCustomer(element);
            
            for (Iterator iterator = contacts.iterator(); iterator.hasNext();) {
                ContactV2 element2 = (ContactV2) iterator.next();
                
                ret.add(new ContactAndCustomerVO(element2,element));
            }
            
        }
        
        return ret;
        
    }
}
