/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author REID
 */
public class EbayItem extends AbstractTimeObject implements  Serializable {

//    private Long id;
//    private String ownerKey;
    private Long custId;
    
    private String itemStatus;
    private String descriptionShort;
    private String description;
    
    private String listingHeadline;
    private String listingDetail;
    
    private Double grossProfit= new Double(0.0);
    private Double shippingCharge= new Double(0.0);
    
    private Double commission = new Double(0.0);
    private Double priceStart= new Double(0.0);
    private Double priceBuyItNow= new Double(0.0);
    private Double priceReserve= new Double(0.0);
    private Double priceFinal= new Double(0.0);
    private Integer length;
    
    private Double customerNet= new Double(0.0);
    private Double customerNetDeduction= new Double(0.0);
    
    private String customerNetDeductionReason;
    
    private Double handlingCharge= new Double(0.0);
    private Integer weight;
    private Integer shippingWeight;
    
    private Long location;
    
    private String ebayItemId;
    private String ebayItemId2;
    private String ebayCategoryId;
    private Date dateAdded;
    private Date dateStart;
    private Date dateEnded;
    private Date dateNetPaid;
    private Double feeEbayListing= new Double(0.0);
    private Double feeEbayFinal= new Double(0.0);
    private Double feePayPal= new Double(0.0);
    private Double feeOther= new Double(0.0);
    private boolean complete;
    
    private Boolean lockedCosts;
    private Boolean lockedShipping;
    
    private Double prepayAmount= new Double(0.0);
    
    private Long commissionDiscountTypeLuId;
    private String commissionDiscountMethod;
    private Double commissionDiscountBase= new Double(0.0);
    private Double commissionDiscount= new Double(0.0);
    
    private Long simpleCommissionId;
    
    public Double getCommission() {
        return commission;
    }
    public void setCommission(Double commission) {
        this.commission = commission;
    }
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public Long getCustId() {
        return custId;
    }
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    public Date getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    public Date getDateEnded() {
        return dateEnded;
    }
    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }
    public Date getDateNetPaid() {
        return dateNetPaid;
    }
    public void setDateNetPaid(Date dateNetPaid) {
        this.dateNetPaid = dateNetPaid;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescriptionShort() {
        return descriptionShort;
    }
    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }
    public String getEbayItemId() {
        return ebayItemId;
    }
    public void setEbayItemId(String ebayItemId) {
        this.ebayItemId = ebayItemId;
    }
    public Double getFeeEbayFinal() {
        return feeEbayFinal;
    }
    public void setFeeEbayFinal(Double feeEbayFinal) {
        this.feeEbayFinal = feeEbayFinal;
    }
    public Double getFeeEbayListing() {
        return feeEbayListing;
    }
    public void setFeeEbayListing(Double feeEbayListing) {
        this.feeEbayListing = feeEbayListing;
    }
    public Double getFeeOther() {
        return feeOther;
    }
    public void setFeeOther(Double feeOther) {
        this.feeOther = feeOther;
    }
    public Double getFeePayPal() {
        return feePayPal;
    }
    public void setFeePayPal(Double feePayPal) {
        this.feePayPal = feePayPal;
    }
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }
    public Double getPriceBuyItNow() {
        return priceBuyItNow;
    }
    public void setPriceBuyItNow(Double priceBuyItNow) {
        this.priceBuyItNow = priceBuyItNow;
    }
    public Double getPriceFinal() {
        return priceFinal;
    }
    public void setPriceFinal(Double priceFinal) {
        this.priceFinal = priceFinal;
    }
    public Double getPriceReserve() {
        return priceReserve;
    }
    public void setPriceReserve(Double priceReserve) {
        this.priceReserve = priceReserve;
    }
    public Double getPriceStart() {
        return priceStart;
    }
    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }
    public String getEbayCategoryId() {
        return ebayCategoryId;
    }
    public void setEbayCategoryId(String ebayCategoryId) {
        this.ebayCategoryId = ebayCategoryId;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getListingDetail() {
        return listingDetail;
    }
    public void setListingDetail(String listingDetail) {
        this.listingDetail = listingDetail;
    }
    public String getListingHeadline() {
        return listingHeadline;
    }
    public void setListingHeadline(String listingHeadline) {
        this.listingHeadline = listingHeadline;
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
     * @return Returns the itemStatus.
     */
    public String getItemStatus() {
        return itemStatus;
    }
    /**
     * @param itemStatus The itemStatus to set.
     */
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
    /**
     * @return Returns the shippingWeight.
     */
    public Integer getShippingWeight() {
        return shippingWeight;
    }
    /**
     * @param shippingWeight The shippingWeight to set.
     */
    public void setShippingWeight(Integer shippingWeight) {
        this.shippingWeight = shippingWeight;
    }
    public Double getCustomerNet() {
        return customerNet;
    }
    public void setCustomerNet(Double customerNet) {
        this.customerNet = customerNet;
    }
    /**
     * @return Returns the lockedCosts.
     */
    public Boolean getLockedCosts() {
        return lockedCosts;
    }
    /**
     * @param lockedCosts The lockedCosts to set.
     */
    public void setLockedCosts(Boolean lockedCosts) {
        this.lockedCosts = lockedCosts;
    }
    /**
     * @return Returns the lockedShipping.
     */
    public Boolean getLockedShipping() {
        return lockedShipping;
    }
    /**
     * @param lockedShipping The lockedShipping to set.
     */
    public void setLockedShipping(Boolean lockedShipping) {
        this.lockedShipping = lockedShipping;
    }
    /**
     * @return Returns the grossProfit.
     */
    public Double getGrossProfit() {
        return grossProfit;
    }
    /**
     * @param grossProfit The grossProfit to set.
     */
    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }
    /**
     * @return Returns the location.
     */
    public Long getLocation() {
        return location;
    }
    /**
     * @param location The location to set.
     */
    public void setLocation(Long location) {
        this.location = location;
    }
    /**
     * @return Returns the shippingCharge.
     */
    public Double getShippingCharge() {
        return shippingCharge;
    }
    /**
     * @param shippingCharge The shippingCharge to set.
     */
    public void setShippingCharge(Double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }
    /**
     * @return Returns the ebayItemId2.
     */
    public String getEbayItemId2() {
        return ebayItemId2;
    }
    /**
     * @param ebayItemId2 The ebayItemId2 to set.
     */
    public void setEbayItemId2(String ebayItemId2) {
        this.ebayItemId2 = ebayItemId2;
    }
    /**
     * @return Returns the prepayAmount.
     */
    public Double getPrepayAmount() {
        return prepayAmount;
    }
    /**
     * @param prepayAmount The prepayAmount to set.
     */
    public void setPrepayAmount(Double prepayAmount) {
        this.prepayAmount = prepayAmount;
    }
    /**
     * @return Returns the commissionDiscount.
     */
    public Double getCommissionDiscount() {
        return commissionDiscount;
    }
    /**
     * @param commissionDiscount The commissionDiscount to set.
     */
    public void setCommissionDiscount(Double commissionDiscount) {
        this.commissionDiscount = commissionDiscount;
    }
    /**
     * @return Returns the commissionDiscountBase.
     */
    public Double getCommissionDiscountBase() {
        return commissionDiscountBase;
    }
    /**
     * @param commissionDiscountBase The commissionDiscountBase to set.
     */
    public void setCommissionDiscountBase(Double commissionDiscountBase) {
        this.commissionDiscountBase = commissionDiscountBase;
    }
    /**
     * @return Returns the commissionDiscountMethod.
     */
    public String getCommissionDiscountMethod() {
        return commissionDiscountMethod;
    }
    /**
     * @param commissionDiscountMethod The commissionDiscountMethod to set.
     */
    public void setCommissionDiscountMethod(String commissionDiscountMethod) {
        this.commissionDiscountMethod = commissionDiscountMethod;
    }
    /**
     * @return Returns the commissionDiscountTypeLuId.
     */
    public Long getCommissionDiscountTypeLuId() {
        return commissionDiscountTypeLuId;
    }
    /**
     * @param commissionDiscountTypeLuId The commissionDiscountTypeLuId to set.
     */
    public void setCommissionDiscountTypeLuId(Long commissionDiscountTypeLuId) {
        this.commissionDiscountTypeLuId = commissionDiscountTypeLuId;
    }
    /**
     * @return Returns the customerNetDeduction.
     */
    public Double getCustomerNetDeduction() {
        return customerNetDeduction;
    }
    /**
     * @param customerNetDeduction The customerNetDeduction to set.
     */
    public void setCustomerNetDeduction(Double customerNetDeduction) {
        this.customerNetDeduction = customerNetDeduction;
    }
    /**
     * @return Returns the customerNetDeductionReason.
     */
    public String getCustomerNetDeductionReason() {
        return customerNetDeductionReason;
    }
    /**
     * @param customerNetDeductionReason The customerNetDeductionReason to set.
     */
    public void setCustomerNetDeductionReason(String customerNetDeductionReason) {
        this.customerNetDeductionReason = customerNetDeductionReason;
    }
    /**
     * @return Returns the simpleCommissionoId.
     */
    public Long getSimpleCommissionId() {
        return simpleCommissionId;
    }
    /**
     * @param simpleCommissionoId The simpleCommissionoId to set.
     */
    public void setSimpleCommissionId(Long simpleCommissionoId) {
        this.simpleCommissionId = simpleCommissionoId;
    }
}
