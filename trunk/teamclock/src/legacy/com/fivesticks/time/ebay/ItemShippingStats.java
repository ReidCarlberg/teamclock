/*
 * Created on May 12, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.io.Serializable;

/**
 * @author Reid
 */
public class ItemShippingStats implements Serializable {

    private int count;
    private double weight;
    private double handling;
    private double shipping;
    
    public void incrementCount() {
        count++;
    }
    
    public void acrueWeight(Double weight) {
        this.weight = this.weight + weight.doubleValue(); 
    }
    
    public void acrueHandling(Double handling) {
        this.handling = this.handling + handling.doubleValue();
    }
    
    public void acrueShipping(Double shipping) {
        this.shipping = this.shipping + shipping.doubleValue();
    }
    /**
     * @return Returns the count.
     */
    public int getCount() {
        return count;
    }
    /**
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * @return Returns the handling.
     */
    public double getHandling() {
        return handling;
    }
    /**
     * @param handling The handling to set.
     */
    public void setHandling(double handling) {
        this.handling = handling;
    }
    /**
     * @return Returns the shipping.
     */
    public double getShipping() {
        return shipping;
    }
    /**
     * @param shipping The shipping to set.
     */
    public void setShipping(double shipping) {
        this.shipping = shipping;
    }
    /**
     * @return Returns the weight.
     */
    public double getWeight() {
        return weight;
    }
    /**
     * @param weight The weight to set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
