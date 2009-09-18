/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.util;

import java.util.Iterator;

/**
 * @author Reid
 *
 */
public class CommissionCalculator {

    public double getCommission(Commission commission, double finalPrice) {
        
        double ret = 0.0;
        
        for (Iterator iter = commission.getTiers().iterator(); iter.hasNext();) {
            CommissionTier element = (CommissionTier) iter.next();
            
            if (finalPrice <= element.getEnd() ) {
                ret += (finalPrice - element.getStart()) * element.getRate();
                break;
            } else {
                ret += (element.getEnd() - element.getStart() ) * element.getRate();
            }
            
        }
        
        
        if (ret < commission.getMinimum()) {
            ret = commission.getMinimum();
        }
        
        ret = new DoubleRounder().round(ret, 2);
        
        return ret;
    }
}
