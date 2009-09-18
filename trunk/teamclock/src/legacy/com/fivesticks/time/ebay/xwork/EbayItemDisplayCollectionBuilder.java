/*
 * Created on May 19, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class EbayItemDisplayCollectionBuilder {

    public Collection getDisplayable(Collection original, SystemOwner owner) throws Exception {
        Collection ret = new ArrayList();
        
        CustomerServiceDelegate cust = CustomerServiceDelegate.factory.build(owner);
        
        for (Iterator iter = original.iterator(); iter.hasNext();) {
            EbayItem element = (EbayItem) iter.next();
            
            EbayItemDisplay d = new EbayItemDisplay();
            d.setItem(element);
            d.setCustomer(cust.getFstxCustomer(element.getCustId()));
            
            ret.add(d);
            
        }
        
        
        return ret;
    }
    
}
