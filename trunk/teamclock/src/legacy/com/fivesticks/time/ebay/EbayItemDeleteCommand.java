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
public class EbayItemDeleteCommand extends DeleteCommand {

    private static final String DELETE_SUCCESS = "delete-success-ebay";

    private final EbayItem target;

    public EbayItemDeleteCommand(EbayItem target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory
                .build(sessionContext.getSystemOwner());

        
        try {
            sd.delete(this.getTarget());
        } catch (EbayItemServiceDelegateException e) {

            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        return this.getTarget().getDescriptionShort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will delete this eBay item record.";
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
    public EbayItem getTarget() {
        return target;
    }
}