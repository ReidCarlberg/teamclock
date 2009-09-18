/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Reid
 */
public class ItemShippingDeleteCommand extends DeleteCommand {

    private static final String DELETE_SUCCESS = "delete-success-ebay-image";

    private final ItemShipping target;

    public ItemShippingDeleteCommand(ItemShipping target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        
        ItemShippingServiceDelegate sd = ItemShippingServiceDelegate.factory.build(sessionContext.getSystemOwner());
        

        try {
            sd.delete(this.getTarget());
        } catch (ItemShippingServiceDelegateException e) {

            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        return "eBay Item Shipping " + this.getTarget().getTracking();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will delete this eBay item shipping record.";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return DELETE_SUCCESS;
    }

    /**
     * @return Returns the target.
     */
    public ItemShipping getTarget() {
        return target;
    }
}