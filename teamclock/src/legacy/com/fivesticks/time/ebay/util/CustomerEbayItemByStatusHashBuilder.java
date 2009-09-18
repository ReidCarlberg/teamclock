/*
 * Created on Mar 31, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class CustomerEbayItemByStatusHashBuilder {

    public Map build(SystemOwner systemOwner, EbayItemFilter filter) throws Exception {
        Map cust = new HashMap();
        

        Collection items = EbayItemServiceDelegate.factory.build(systemOwner).find(filter);
        
        Map custItems = new HashMap();
        
        CustomerServiceDelegate bd = CustomerServiceDelegate.factory.build(systemOwner);
        for (Iterator iter = items.iterator(); iter.hasNext();) {
            EbayItem element = (EbayItem) iter.next();
        
            FstxCustomer curr = null;
            
            curr = (FstxCustomer) cust.get(element.getId());
            
            if (curr == null) {
                curr = bd.getFstxCustomer(element.getCustId());
                cust.put(element.getId(), curr);
            }
            
            Collection custElement = (Collection) custItems.get(curr);
            if (custElement == null) {
                custElement = new TreeSet(new EbayItemShortDescriptionComparator());
                custItems.put(curr, custElement);
            }
            custElement.add(element);
            
            
        }        
        
        return custItems;
    }
    
    public Collection buildCustItems(Map map) {
        
        Collection ret = new TreeSet(new CustomerAndItemsVOComparator());
        
        for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
            FstxCustomer element = (FstxCustomer) iter.next();
            
            CustomerAndItemsVO vo = new CustomerAndItemsVO();
            
            vo.setCustomer(element);
            vo.setItems((Collection) map.get(element));
            vo.setStats(new EbayItemCollectionStatisticsBuilder().build(vo.getItems()));
            
            ret.add(vo);
            
        }
        
        return ret;
    }
}
