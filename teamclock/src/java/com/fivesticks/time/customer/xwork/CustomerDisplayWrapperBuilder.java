/*
 * Created on Dec 5, 2004 by REID
 */
package com.fivesticks.time.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateException;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class CustomerDisplayWrapperBuilder {

    
    public Collection buildForTimeAccount(SystemOwner systemOwner, Collection original) {

        Collection ret = new ArrayList();

        AccountTransactionServiceDelegate sd = CustomerAccountTransactionServiceDelegate.factory
                .buildTimeAccount(systemOwner);

        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory
                .build(systemOwner);

        for (Iterator iter = original.iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();

            CustomerDisplayWrapper c = new CustomerDisplayWrapper(element);
            try {
                c.setBalance(sd.getCurrentBalance(element));
            } catch (AccountTransactionException e) {
                c.setBalance(new Double(0.0));
            }

            try {
                c.setStatus(lsd.get(element.getStatus()));
            } catch (LookupServiceDelegateException e1) {
                //do nothing.
            }

            ret.add(c);
        }

        return ret;
    }
    
    public Collection buildForAuctionAccount(SystemOwner systemOwner, Collection original) {

        Collection ret = new ArrayList();

        AccountTransactionServiceDelegate sd = CustomerAccountTransactionServiceDelegate.factory
               .buildAuctionAccount(systemOwner);

        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory
                .build(systemOwner);

        for (Iterator iter = original.iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();

            CustomerDisplayWrapper c = new CustomerDisplayWrapper(element);
            try {
                c.setBalance(sd.getCurrentBalance(element));
            } catch (AccountTransactionException e) {
                c.setBalance(new Double(0.0));
            }

            try {
                c.setStatus(lsd.get(element.getStatus()));
            } catch (LookupServiceDelegateException e1) {
                //do nothing.
            }

            ret.add(c);
        }

        return ret;
    }

    public CustomerDisplayWrapper buildSingle(SessionContext sessionContext,
            Customer customer) {

        AccountTransactionServiceDelegate sd = CustomerAccountTransactionServiceDelegate.factory
                .buildTimeAccount(sessionContext.getSystemOwner());

        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory
                .build(sessionContext.getSystemOwner());

        CustomerDisplayWrapper c = new CustomerDisplayWrapper(customer);
        try {
            c.setBalance(sd.getCurrentBalance(customer));
        } catch (AccountTransactionException e) {
            c.setBalance(new Double(0.0));
        }

        try {
            c.setStatus(lsd.get(customer.getStatus()));
        } catch (LookupServiceDelegateException e1) {
            //do nothing.
        }
        
        return c;

    }
}