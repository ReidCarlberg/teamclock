/*
 * Created on May 12, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Reid
 */
public class ItemShippingStatsBuilder {

    public static ItemShippingStats build(Collection target) {
        ItemShippingStats ret = new ItemShippingStats();
        
        if (target != null) {
	        for (Iterator iterator = target.iterator(); iterator.hasNext();) {
	            ItemShipping element = (ItemShipping) iterator.next();
	            ret.incrementCount();
	            ret.acrueWeight(element.getWeight());
	            ret.acrueHandling(element.getHandlingCharge());
	            ret.acrueShipping(element.getShippingCost());
	        }
        }
        
        return ret;
    }
}
