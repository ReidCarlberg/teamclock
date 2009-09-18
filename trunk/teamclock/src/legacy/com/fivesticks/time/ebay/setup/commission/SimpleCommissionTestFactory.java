/*
 * Created on Jun 15, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *
 */
public class SimpleCommissionTestFactory {

    private static int count = 1;
    
    public static SimpleCommission buildUnpersistedTest() {
        
        SimpleCommission ret = new SimpleCommission();
        
        ret.setOneMax(1000.0);
        ret.setOneRate(25.0);
        ret.setTwoMin(1000.01);
        ret.setTwoMax(150000.0);
        ret.setTwoRate(10.0);
        ret.setMinimum(9.95);
        
        return ret;
    }
    
    public static SimpleCommission buildPersistedTest(SystemOwner systemOwner, double percent, double minimum) throws Exception {
        
        
        SimpleCommission ret = new SimpleCommission();
        
        ret.setOneMax(10000.0);
        ret.setOneRate(percent);
        ret.setMinimum(minimum);
        ret.setName("name" + (++count));
        
        SimpleCommissionServiceDelegate.factory.build(systemOwner).save(ret);
        
        return ret;        
    }
}
