/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.io.Serializable;

/**
 * @author REID
 */
public class EbayItemCollectionStatistics implements Serializable {

    private int count;
    private double totalSale;
    private double totalCommission;
    private double totalNet;
    private double totalNetDeduction;
    private double totalDiscount;
    private double totalPrepay;
    
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getTotalCommission() {
        return totalCommission;
    }
    public Double getCommission() {
        return new Double(this.getTotalCommission());
    }
    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }
    public double getTotalNet() {
        return totalNet;
    }
    public Double getNet() {
        return new Double(this.getTotalNet());
    }
    public void setTotalNet(double totalNet) {
        this.totalNet = totalNet;
    }
    public double getTotalSale() {
        return totalSale;
    }
    public Double getSale() {
        return new Double(this.getTotalSale());
    }
    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }
    /**
     * @return Returns the totalDiscount.
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }
    /**
     * @param totalDiscount The totalDiscount to set.
     */
    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    
    public Double getDiscount() {
        return new Double(this.getTotalDiscount());
    }
	/**
	 * @return Returns the totalPrepay.
	 */
	public double getTotalPrepay() {
		return totalPrepay;
	}
	/**
	 * @param totalPrepay The totalPrepay to set.
	 */
	public void setTotalPrepay(double totalPrepay) {
		this.totalPrepay = totalPrepay;
	}
	
	public Double getPrepay() {
		return new Double(this.getTotalPrepay());
	}
    /**
     * @return Returns the totalNetDeduction.
     */
    public double getTotalNetDeduction() {
        return totalNetDeduction;
    }
    /**
     * @param totalNetDeduction The totalNetDeduction to set.
     */
    public void setTotalNetDeduction(double totalNetDeduction) {
        this.totalNetDeduction = totalNetDeduction;
    }

	public Double getNetDeduction() {
		return new Double(this.getTotalNetDeduction());
	}

}
