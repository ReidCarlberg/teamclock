/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.ItemShippingStats;
import com.fivesticks.time.ebay.ItemShippingStatsBuilder;

/**
 * @author REID
 */
public class EbayItemModifyContext implements Serializable {

    private EbayItem target;
    
    private Collection images;
    
    private Collection shipping;
    
    private ItemShippingStats shippingStats;
    
    private boolean standardView = true;
    
    private String discountReason;
    
    private String commissionOverride;
    
    
    public EbayItem getTarget() {
        return target;
    }
    public void setTarget(EbayItem target) {
        this.target = target;
    }
    public Collection getImages() {
        
        return images;
    }
    public void setImages(Collection images) {
        this.images = images;
    }
    
    public String getFormattedListing() {
        String ret = this.getTarget().getListingDetail().replaceAll("\r\n\r\n", "\r\n<p style=\"font-family: arial, helvetica;\" >");
//        System.out.println(ret);
        return ret;
    }
    /**
     * @return Returns the shipping.
     */
    public Collection getShipping() {
        return shipping;
    }
    /**
     * @param shipping The shipping to set.
     */
    public void setShipping(Collection shipping) {
        
        this.shipping = shipping;
        
        if (shipping == null)
            this.shippingStats = null;
    }
    
    public ItemShippingStats getShippingStats() {
        if (shippingStats == null && this.getShipping() != null)
            shippingStats = ItemShippingStatsBuilder.build(this.getShipping());
        
        return shippingStats;
    }
    /**
     * @return Returns the standardView.
     */
    public boolean isStandardView() {
        return standardView;
    }
    /**
     * @param standardView The standardView to set.
     */
    public void setStandardView(boolean standardView) {
        this.standardView = standardView;
    }
    
    /**
     * @return Returns the discountType.
     */
    public String getDiscountReason() {
        return discountReason;
    }
    /**
     * @param discountType The discountType to set.
     */
    public void setDiscountReason(String discountType) {
        this.discountReason = discountType;
    }
    /**
     * @return Returns the commissionOverride.
     */
    public String getCommissionOverride() {
        return commissionOverride;
    }
    /**
     * @param commissionOverride The commissionOverride to set.
     */
    public void setCommissionOverride(String commissionOverride) {
        this.commissionOverride = commissionOverride;
    }
}
