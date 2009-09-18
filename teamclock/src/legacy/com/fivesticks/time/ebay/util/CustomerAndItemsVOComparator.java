/*
 * Created on Mar 31, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.util.Comparator;

/**
 * @author REID
 */
public class CustomerAndItemsVOComparator implements Comparator {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        
        int ret = 0;
        
        CustomerAndItemsVO one = (CustomerAndItemsVO) arg0;
        CustomerAndItemsVO two = (CustomerAndItemsVO) arg1;
        
        ret = one.getCustomer().getName().compareToIgnoreCase(two.getCustomer().getName());
        
        if (ret == 0) { 
            ret = one.getCustomer().getId().compareTo(two.getCustomer().getId());
        }
        
        return ret;
    }

}
