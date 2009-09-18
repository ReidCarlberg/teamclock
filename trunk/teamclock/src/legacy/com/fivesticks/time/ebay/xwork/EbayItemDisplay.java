/*
 * Created on May 19, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import java.io.Serializable;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.ebay.EbayItem;

/**
 * @author Reid
 */
public class EbayItemDisplay implements Serializable {

    private EbayItem item;
    
    private FstxCustomer customer;
    
    
    /**
     * @return Returns the customer.
     */
    public FstxCustomer getCustomer() {
        return customer;
    }
    /**
     * @param customer The customer to set.
     */
    public void setCustomer(FstxCustomer customer) {
        this.customer = customer;
    }
    /**
     * @return Returns the item.
     */
    public EbayItem getItem() {
        return item;
    }
    /**
     * @param item The item to set.
     */
    public void setItem(EbayItem item) {
        this.item = item;
    }
    
    public String getShortDescriptionShort() {
        String ret = item.getDescriptionShort();
        
//        if (ret.length() > 18)
//            ret = item.getDescriptionShort().substring(0,17) + "..";
        
        return ret;
    }
    
}
