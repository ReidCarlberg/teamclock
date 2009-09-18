/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Reid
 */
public class BoxDeleteCommand extends DeleteCommand {

    private static final String DELETE_SUCCESS = "delete-success-ebay-setup-box";

    private final Box target;

    public BoxDeleteCommand(Box target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        BoxServiceDelegate sd = BoxServiceDelegate.factory
                .build(sessionContext.getSystemOwner());

        try {
            sd.delete(this.getTarget());
        } catch (BoxServiceDelegateException e) {

            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        return this.getTarget().getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will delete this Box record.";
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
    public Box getTarget() {
        return target;
    }
}