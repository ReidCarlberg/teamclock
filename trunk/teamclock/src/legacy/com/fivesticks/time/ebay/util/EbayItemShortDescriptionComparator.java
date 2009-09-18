/*
 * Created on Mar 28, 2005 by Reid
 */
package com.fivesticks.time.ebay.util;

import java.io.Serializable;
import java.util.Comparator;

import com.fivesticks.time.ebay.EbayItem;

/**
 * @author Reid
 */
public class EbayItemShortDescriptionComparator implements Comparator, Serializable {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        
        EbayItem one = (EbayItem) o1;
        EbayItem two = (EbayItem) o2;
        
        int ret = 0;
        
        ret = one.getDescriptionShort().compareToIgnoreCase(two.getDescriptionShort());
        
        if (ret == 0)
            ret = one.getId().compareTo(two.getId());
        
        return ret;
    }

}
