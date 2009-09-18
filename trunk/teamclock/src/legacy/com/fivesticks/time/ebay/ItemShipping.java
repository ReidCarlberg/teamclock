/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author Reid
 */
public class ItemShipping extends AbstractTimeObject {
    
//    private Long id;
//    private String ownerKey;
    private Long ebayId;
    private Integer length;
    private Integer width;
    private Integer height;
    private Long boxId;
    
    private String carrier;
    private String tracking;
    
    private Double weight;
    private Double handlingCharge;
    private Double shippingCost;
    
    
     

    /**
     * @return Returns the boxId.
     */
    public Long getBoxId() {
        return boxId;
    }
    /**
     * @param boxId The boxId to set.
     */
    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }
    /**
     * @return Returns the carrier.
     */
    public String getCarrier() {
        return carrier;
    }
    /**
     * @param carrier The carrier to set.
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    /**
     * @return Returns the ebayId.
     */
    public Long getEbayId() {
        return ebayId;
    }
    /**
     * @param ebayId The ebayId to set.
     */
    public void setEbayId(Long ebayId) {
        this.ebayId = ebayId;
    }
    /**
     * @return Returns the handlingCharge.
     */
    public Double getHandlingCharge() {
        return handlingCharge;
    }
    /**
     * @param handlingCharge The handlingCharge to set.
     */
    public void setHandlingCharge(Double handlingCharge) {
        this.handlingCharge = handlingCharge;
    }
    /**
     * @return Returns the height.
     */
    public Integer getHeight() {
        return height;
    }
    /**
     * @param height The height to set.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }
//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//    /**
//     * @param id The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
    /**
     * @return Returns the length.
     */
    public Integer getLength() {
        return length;
    }
    /**
     * @param length The length to set.
     */
    public void setLength(Integer length) {
        this.length = length;
    }
//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//    /**
//     * @param ownerKey The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }
    /**
     * @return Returns the shippingCost.
     */
    public Double getShippingCost() {
        return shippingCost;
    }
    /**
     * @param shippingCost The shippingCost to set.
     */
    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }
    /**
     * @return Returns the tracking.
     */
    public String getTracking() {
        return tracking;
    }
    /**
     * @param tracking The tracking to set.
     */
    public void setTracking(String tracking) {
        this.tracking = tracking;
    }
    /**
     * @return Returns the width.
     */
    public Integer getWidth() {
        return width;
    }
    /**
     * @param width The width to set.
     */
    public void setWidth(Integer width) {
        this.width = width;
    }
    /**
     * @return Returns the weight.
     */
    public Double getWeight() {
        return weight;
    }
    /**
     * @param weight The weight to set.
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
