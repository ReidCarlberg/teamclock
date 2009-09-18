/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class BoxTestFactory {

    private static int count;
    
    public static Box getPersisted(SystemOwner owner) throws Exception {
        count++;
        
        Box ret = new Box();
        ret.setName("Box Name " + count);
        ret.setActive(true);
        ret.setCost(new Double(1.23));
        ret.setDefaultHandlingCost(new Double(4.95));
        ret.setHeight(new Integer(4));
        ret.setLength(new Integer(12));
        ret.setQtyOnHand(new Integer(1));
        ret.setWeight(new Double (1.2));
        ret.setWidth(new Integer(24));
        
        BoxServiceDelegate sd = BoxServiceDelegate.factory.build(owner);
        
        sd.save(ret);
        
        
        return ret;
    }
}
