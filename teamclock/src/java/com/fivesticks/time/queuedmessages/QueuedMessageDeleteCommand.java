/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Reid
 */
public class QueuedMessageDeleteCommand extends DeleteCommand {

    private static final String DELETE_SUCCESS = "delete-success-queued";

    private final QueuedMessage target;

    public QueuedMessageDeleteCommand(QueuedMessage target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(sessionContext.getSystemOwner());

        try {
            sd.delete(this.getTarget());
        } catch (QueuedMessageServiceDelegateException e) {

            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        return this.getTarget().getFromAddress() + ", "
                + this.getTarget().getToAddress() + ", "
                + this.getTarget().getSubject();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will delete this queued message.";
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
    public QueuedMessage getTarget() {
        return target;
    }
}