/*
 * Created on Jan 19, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.externaluser.ExternalUser;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class ExternalUserRelatedCustomerCollectionBuilder {

    public Collection getRelatedCustomers(SystemOwner systemOwner, User user)
            throws Exception {

        Collection external = ExternalUserServiceDelegateFactory.factory.build(
                systemOwner).getCustomers(user.getUsername());

        Collection related = new ArrayList();

        CustomerServiceDelegate bd = CustomerServiceDelegateFactory.factory.build(systemOwner);
        for (Iterator iter = external.iterator(); iter.hasNext();) {
            ExternalUser element = (ExternalUser) iter.next();
            Customer customer = bd.getFstxCustomer(element.getCustomerId());
            related.add(customer);
        }

        return related;
    }

}