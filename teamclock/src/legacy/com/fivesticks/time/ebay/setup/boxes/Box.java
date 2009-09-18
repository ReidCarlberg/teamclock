/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import java.io.Serializable;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Reid
 */
public class Box implements SystemOwnerKeyAware, Serializable {

    private Long id;
    private String ownerKey;
    private String name;
    private Integer length;
    private Integer width;
    private Integer height;
    private Double weight;
    private Double cost;
    private Double defaultHandlingCost;
    private Integer qtyOnHand;
    private boolean active;
    
    
    
    /**
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * @return Returns the cost.
     */
    public Double getCost() {
        return cost;
    }
    /**
     * @param cost The cost to set.
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }
    /**
     * @return Returns the defaultHandlingCost.
     */
    public Double getDefaultHandlingCost() {
        return defaultHandlingCost;
    }
    /**
     * @param defaultHandlingCost The defaultHandlingCost to set.
     */
    public void setDefaultHandlingCost(Double defaultHandlingCost) {
        this.defaultHandlingCost = defaultHandlingCost;
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
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
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
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }
    /**
     * @param ownerKey The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
    /**
     * @return Returns the qtyOnHand.
     */
    public Integer getQtyOnHand() {
        return qtyOnHand;
    }
    /**
     * @param qtyOnHand The qtyOnHand to set.
     */
    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
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
