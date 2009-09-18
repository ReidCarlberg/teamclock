/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.ebay.ItemShipping;

/**
 * @author Reid
 */
public class ItemShippingModifyContext {

    private ItemShipping target;
    
    
    /**
     * @return Returns the target.
     */
    public ItemShipping getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(ItemShipping target) {
        this.target = target;
    }
}
