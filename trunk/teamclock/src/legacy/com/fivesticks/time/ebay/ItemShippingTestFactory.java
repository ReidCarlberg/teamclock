/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ItemShippingTestFactory {

    private static int count = 0;
    
    public static ItemShipping getUnpersisted(EbayItem ebayItem) {
        count++;
        ItemShipping ret = new ItemShipping();
        ret.setEbayId(ebayItem.getId());
        ret.setTracking("Tracking " + count);
        return ret;
    }
    
    public static ItemShipping getPersisted(EbayItem ebayItem, SystemOwner owner) throws Exception {
        ItemShipping ret = getUnpersisted(ebayItem);
        ItemShippingServiceDelegate sd = ItemShippingServiceDelegate.factory.build(owner);
        sd.save(ret);
        return ret;
    }
}
