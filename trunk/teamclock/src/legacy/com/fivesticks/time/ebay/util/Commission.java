/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.util;

import java.util.Collection;

/**
 * @author Reid
 *
 */
public class Commission {

    private double minimum;
    private Collection tiers;
    
    
    
    /**
     * @return Returns the minimum.
     */
    public double getMinimum() {
        return minimum;
    }
    /**
     * @param minimum The minimum to set.
     */
    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }
    /**
     * @return Returns the tiers.
     */
    public Collection getTiers() {
        return tiers;
    }
    /**
     * @param tiers The tiers to set.
     */
    public void setTiers(Collection tiers) {
        this.tiers = tiers;
    }
}
