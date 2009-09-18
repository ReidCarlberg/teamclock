/*
 * Created on Nov 17, 2004 by Reid
 */
package com.fivesticks.time.account;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Reid
 */
public class AccountTransactionDeleteCommand extends DeleteCommand {

    private static final String XWORKSUCCESS = "delete-success-accounttxn";

    private AccountTransaction target;
    
    

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {

        ObjectTransactionType targetType = ObjectTransactionType.getByName(target.getType());
        
        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(sessionContext.getSystemOwner(), targetType);

        try {
            sd.delete(target);
        } catch (AccountTransactionException e) {
            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        StringBuffer ret = new StringBuffer();

        ret.append(target.getDate() + ", " + target.getType() + ", "
                + target.getAmount() + " (" + target.getObjectTransactionType() + ")");

        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will premanently delete this account transaction.";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return AccountTransactionDeleteCommand.XWORKSUCCESS;
    }

    /**
     * @return Returns the target.
     */
    public AccountTransaction getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(AccountTransaction target) {
        this.target = target;
    }
}