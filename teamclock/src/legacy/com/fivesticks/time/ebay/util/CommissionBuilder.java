/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.util;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.ebay.setup.commission.SimpleCommission;

/**
 * @author Reid
 *
 */
public class CommissionBuilder {

    public Commission buildStandard() {
        
        Commission ret = new Commission();
        
        ret.setMinimum(9.95);
        
        Collection tiers = new ArrayList();
        
        CommissionTier tier1 = new CommissionTier();
        tier1.setStart(0.0);
        tier1.setEnd(1000.0);
        tier1.setRate(.25);
        
        CommissionTier tier2 = new CommissionTier();
        tier2.setStart(1000.01);
        tier2.setEnd(10000.0);
        tier2.setRate(.10);
        
        tiers.add(tier1);
        tiers.add(tier2);
        
        ret.setTiers(tiers);
        
        return ret;
        
    }
    
    public Commission build(SimpleCommission in) {
        
        Commission ret = new Commission();
        
        ret.setMinimum(in.getMinimum());
        
        ret.setTiers(new ArrayList());
        
        this.handleTier(ret.getTiers(), in.getOneMin(), in.getOneMax(), in.getOneRate());
        this.handleTier(ret.getTiers(), in.getTwoMin(), in.getTwoMax(), in.getTwoRate());
        this.handleTier(ret.getTiers(), in.getThreeMin(), in.getThreeMax(), in.getThreeRate());
        this.handleTier(ret.getTiers(), in.getFourMin(), in.getFourMax(), in.getFourRate());
        this.handleTier(ret.getTiers(), in.getFiveMin(), in.getFiveMax(), in.getFiveRate());
        this.handleTier(ret.getTiers(), in.getSixMin(), in.getSixMax(), in.getSixRate());
        
        
        return ret;
    }
    
    private void handleTier(Collection tiers, double min, double max, double rate) {
        
        if (max > min && rate > 0.0) {
            CommissionTier tier = new CommissionTier();
            
            tier.setStart(min);
            tier.setEnd(max);
            tier.setRate(rate/100.0);
            
            tiers.add(tier);
        }
    }
}
