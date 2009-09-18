/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.customer.contact.ContactServiceDelegate;
import com.fivesticks.time.customer.contact.ContactServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class CustomerAndContactVOBuilder {

    public Collection build(SystemOwner owner, Collection customers) throws CustomerAndContactVOBuildException {

        Collection ret = new ArrayList();
        
        ContactServiceDelegate sd = ContactServiceDelegate.factory.build(owner);
        
        for (Iterator iter = customers.iterator(); iter.hasNext();) {
            FstxCustomer element = (FstxCustomer) iter.next();
            CustomerAndContactVO vo = new CustomerAndContactVO();
            vo.setCustomer(element);
            
            Collection c1;
            try {
                c1 = sd.getByCustomer(element);
            } catch (ContactServiceDelegateException e) {
                throw new CustomerAndContactVOBuildException(e);
            }
            if (c1.size() > 0) {
                vo.setContact((Contact) c1.toArray()[0]);
            }
            
            ret.add(vo);
            
        }
        
        return ret;
    }
}
