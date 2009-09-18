/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.util;

/**
 * @author Reid
 *
 */
public class CommissionTier {

    private double start;
    private double end;
    private double rate;
    
    
    /**
     * @return Returns the end.
     */
    public double getEnd() {
        return end;
    }
    /**
     * @param end The end to set.
     */
    public void setEnd(double end) {
        this.end = end;
    }
    /**
     * @return Returns the rate.
     */
    public double getRate() {
        return rate;
    }
    /**
     * @param rate The rate to set.
     */
    public void setRate(double rate) {
        this.rate = rate;
    }
    /**
     * @return Returns the start.
     */
    public double getStart() {
        return start;
    }
    /**
     * @param start The start to set.
     */
    public void setStart(double start) {
        this.start = start;
    }
}
