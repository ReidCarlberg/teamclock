/*
 * Created on Mar 28, 2005 by Reid
 */
package com.fivesticks.time.ebay.util;

import java.io.Serializable;
import java.util.Comparator;

import com.fivesticks.time.ebay.xwork.EbayItemDisplay;

/**
 * @author Reid
 */
public class DisplayableEbayItemCustomerComparator implements Comparator, Serializable {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        
        EbayItemDisplay one = (EbayItemDisplay) o1;
        EbayItemDisplay two = (EbayItemDisplay) o2;
        
        int ret = 0;
        
        ret = one.getCustomer().getName().compareToIgnoreCase(two.getCustomer().getName());
        
        if (ret == 0)
            ret = one.getCustomer().getId().compareTo(two.getCustomer().getId());
        
        if (ret == 0)
            ret = one.getItem().getDescriptionShort().compareToIgnoreCase(two.getItem().getDescriptionShort());
        
        if (ret == 0)
            ret = one.getItem().getId().compareTo(two.getItem().getId());
        
        
        return ret;
    }

}
