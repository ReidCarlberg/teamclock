/*
 * Created on Aug 17, 2005
 *
 */
package com.fivesticks.time.customer.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerDisplayWrapper;
import com.fivesticks.time.customer.xwork.CustomerDisplayWrapperBuilder;
import com.fivesticks.time.systemowner.SystemOwner;

public class CustomerListBuilder {

    private static String NEGATIVE = "negative";
    
    private static String NOCONTACT = "nocontact";
    
    private static String AUCTIONBAL = "auctionbal";
    
    public Collection build(SystemOwner systemOwner, CustomerFilterVO params, String special) throws Exception {
        
        if (special == null)
            special="";
        
        if (special.equals(NEGATIVE)) {
            return buildNegative(systemOwner, params);
        }
        
        if (special.equals(NOCONTACT)) {
            return buildNoContact(systemOwner, params);
        }
        
        if (special.equals(AUCTIONBAL)) {
            return buildNonZeroAuctionBalance(systemOwner, params);
        }
        


        Collection raw = CustomerServiceDelegateFactory.factory.build(
                systemOwner).searchByFilter(
                params);

        return new CustomerDisplayWrapperBuilder().buildForTimeAccount(systemOwner, raw);        
        
    }

    private Collection buildNonZeroAuctionBalance(SystemOwner systemOwner, CustomerFilterVO params) throws Exception{
        
        int max = params.getReturnMaximum();
        params.setReturnMaximum(0);
        
        Collection raw = CustomerServiceDelegateFactory.factory.build(
                systemOwner).searchByFilter(params);
        
        Collection wrap = new CustomerDisplayWrapperBuilder().buildForAuctionAccount(
                systemOwner, raw);
                
        Collection ret = new ArrayList();
        
        for (Iterator iter = wrap.iterator(); iter.hasNext();) {
            CustomerDisplayWrapper element = (CustomerDisplayWrapper) iter.next();
            
            if (element.getBalance().doubleValue() != 0.0) {
                ret.add(element);
            }
            
            
        }
        
        params.setReturnMaximum(max);
        return ret;
    }

    private Collection buildNegative(SystemOwner systemOwner, CustomerFilterVO params) throws Exception {
        int max = params.getReturnMaximum();
        params.setReturnMaximum(0);
        
        Collection raw = CustomerServiceDelegateFactory.factory.build(
                systemOwner).searchByFilter(params);
        
        Collection wrap = new CustomerDisplayWrapperBuilder().buildForTimeAccount(
                systemOwner, raw);
                
        Collection ret = new ArrayList();
        
        for (Iterator iter = wrap.iterator(); iter.hasNext();) {
            CustomerDisplayWrapper element = (CustomerDisplayWrapper) iter.next();
            
            if (element.getBalance().doubleValue() < 0.0) {
                ret.add(element);
            }
            
            
        }
        params.setReturnMaximum(max);
        return ret;
    }

    private Collection buildNoContact(SystemOwner systemOwner, CustomerFilterVO params) throws Exception {

        Collection raw = CustomerServiceDelegateFactory.factory.build(
                systemOwner).searchByFilter(params);
        
        Collection wrap = new CustomerDisplayWrapperBuilder().buildForTimeAccount(systemOwner, raw);
                
        Collection ret = new ArrayList();
        
        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory.build(systemOwner);
        
        for (Iterator iter = wrap.iterator(); iter.hasNext();) {
            CustomerDisplayWrapper element = (CustomerDisplayWrapper) iter.next();

            
            Collection c = sd.getByCustomer(element.getFstxCustomer());
            
            if (c.size() == 0) {
                ret.add(element);
            }
            
            
        }
        
        return ret;
    }

}
