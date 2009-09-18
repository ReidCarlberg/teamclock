/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemShipping;
import com.fivesticks.time.ebay.ItemShippingDeleteCommand;
import com.fivesticks.time.ebay.ItemShippingServiceDelegate;
import com.fivesticks.time.ebay.setup.boxes.Box;
import com.fivesticks.time.ebay.setup.boxes.BoxFilterBuilder;
import com.fivesticks.time.ebay.setup.boxes.BoxServiceDelegate;
import com.fivesticks.time.ebay.util.EbayItemCalculator;

/**
 * @author Reid
 */
public class ItemShippingModifyAction extends SessionContextAwareSupport
        implements EbayItemModifyContextAware, ItemShippingModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;
    private ItemShippingModifyContext itemShippingModifyContext;    
    
    private ItemShipping targetShipping;
    
    private Long target;
    
    private String saveShipping;
    private String cancelShipping;
    private String deleteShipping;
    private String copyShipping;
    
    public String execute() throws Exception {
        
        if (this.getCancelShipping() != null) 
            return SUCCESS;
        
        if (this.getDeleteShipping() != null && this.getItemShippingModifyContext().getTarget() != null) {
            ItemShippingDeleteCommand cmd = new ItemShippingDeleteCommand(this.getItemShippingModifyContext().getTarget());
            this.getSessionContext().setSuccessOverride(null);
            this.getSessionContext().setDeleteContext(
                    DeleteContext.factory.build(cmd, this
                            .getSessionContext().getSuccessOverride()));
            this.getEbayItemModifyContext().setShipping(null);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;                
        }
        
        if (this.getCopyShipping() != null && this.getItemShippingModifyContext().getTarget() != null) {
            this.setTargetShipping(this.getItemShippingModifyContext().getTarget());
            this.getItemShippingModifyContext().setTarget(null);
            this.getTargetShipping().setId(null);
            return INPUT;
        }
        
        ItemShippingServiceDelegate sd = ItemShippingServiceDelegate.factory.build(this.getSystemOwner());
        
        if (this.getTarget() != null) {
            this.getItemShippingModifyContext().setTarget(sd.get(this.getTarget()));
            this.setTargetShipping(this.getItemShippingModifyContext().getTarget());
            return INPUT;
        }
        
        if (this.getSaveShipping() == null) {
            this.setTargetShipping(new ItemShipping());
            this.getItemShippingModifyContext().setTarget(null);
            return INPUT;
        }
        
        validate();
        
        if (this.hasErrors())
            return INPUT;
        
        if (this.getItemShippingModifyContext().getTarget() != null) {
            this.getTargetShipping().setId(this.getItemShippingModifyContext().getTarget().getId());
        }
        
        /*
         * persisting...
         */
        
        if (this.getTargetShipping().getBoxId() != null && this.getTargetShipping().getBoxId().intValue() > 0) {

            decorateItemFromBox();
            
        }
        this.getTargetShipping().setEbayId(this.getEbayItemModifyContext().getTarget().getId());
        
        sd.save(this.getTargetShipping());
        
        this.getEbayItemModifyContext().setShipping(null);
        
        /*
         * Calculation for the item
         */
        new EbayItemCalculator().handleCalculate(this.getEbayItemModifyContext().getTarget(),this.getSystemOwner(), this.getSessionContext().getSettings().getEbayDefaultCommission());
        
        EbayItemServiceDelegate.factory.build(this.getSessionContext()).save(this.getEbayItemModifyContext().getTarget());
        
        return SUCCESS;
    }
    
    private void decorateItemFromBox() throws Exception {
        Box box = BoxServiceDelegate.factory.build(this.getSystemOwner()).getBox(this.getTargetShipping().getBoxId());
        /*
         * the goal here is to use the default box information if the shipping specific are set to blank
         */
        if (box != null) {
            ItemShipping is = this.getTargetShipping();
            if (is.getLength() == null) {
                is.setLength(box.getLength());
            }
            if (is.getHeight() == null) {
                is.setHeight(box.getHeight());
            }
            if (is.getWidth() == null) {
                is.setWidth(box.getWidth());
            }
            if (is.getHandlingCharge() == null || is.getHandlingCharge().doubleValue() == 0.0) {
                is.setHandlingCharge(box.getDefaultHandlingCost());
            }
            
        }        
    }
    
    
    
    public void validate() {
        
    }
    /**
     * @return Returns the cancelShipping.
     */
    public String getCancelShipping() {
        return cancelShipping;
    }
    /**
     * @param cancelShipping The cancelShipping to set.
     */
    public void setCancelShipping(String cancelShipping) {
        this.cancelShipping = cancelShipping;
    }
    /**
     * @return Returns the copyShipping.
     */
    public String getCopyShipping() {
        return copyShipping;
    }
    /**
     * @param copyShipping The copyShipping to set.
     */
    public void setCopyShipping(String copyShipping) {
        this.copyShipping = copyShipping;
    }
    /**
     * @return Returns the deleteShipping.
     */
    public String getDeleteShipping() {
        return deleteShipping;
    }
    /**
     * @param deleteShipping The deleteShipping to set.
     */
    public void setDeleteShipping(String deleteShipping) {
        this.deleteShipping = deleteShipping;
    }
    /**
     * @return Returns the ebayItemModifyContext.
     */
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }
    /**
     * @param ebayItemModifyContext The ebayItemModifyContext to set.
     */
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }
    /**
     * @return Returns the saveShipping.
     */
    public String getSaveShipping() {
        return saveShipping;
    }
    /**
     * @param saveShipping The saveShipping to set.
     */
    public void setSaveShipping(String saveShipping) {
        this.saveShipping = saveShipping;
    }
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
    /**
     * @return Returns the targetShipping.
     */
    public ItemShipping getTargetShipping() {
        return targetShipping;
    }
    /**
     * @param targetShipping The targetShipping to set.
     */
    public void setTargetShipping(ItemShipping targetShipping) {
        this.targetShipping = targetShipping;
    }
    /**
     * @return Returns the itemShippingModifyContext.
     */
    public ItemShippingModifyContext getItemShippingModifyContext() {
        return itemShippingModifyContext;
    }
    /**
     * @param itemShippingModifyContext The itemShippingModifyContext to set.
     */
    public void setItemShippingModifyContext(
            ItemShippingModifyContext itemShippingModifyContext) {
        this.itemShippingModifyContext = itemShippingModifyContext;
    }
    
    public Collection getBoxes() throws Exception {
        Collection ret = BoxServiceDelegate.factory.build(this.getSystemOwner()).find(BoxFilterBuilder.buildActiveByLength());
        
        return ret;
    }
}
