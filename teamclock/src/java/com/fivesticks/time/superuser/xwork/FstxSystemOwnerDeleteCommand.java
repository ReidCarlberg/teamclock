/*
 * Created on Dec 2, 2004
 *
 * 
 */
package com.fivesticks.time.superuser.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Nick
 * 
 *  
 */
public class FstxSystemOwnerDeleteCommand extends DeleteCommand {
    private final SystemOwner target;

    public FstxSystemOwnerDeleteCommand(SystemOwner target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#handleDelete()
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        try {
            SystemOwnerServiceDelegateFactory.factory.build().delete(target,
                    sessionContext);
        } catch (SystemOwnerServiceDelegateException e) {
            throw new DeleteCommandFailedException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        StringBuffer ret = new StringBuffer();

        ret.append("entry for: " + target.getName() + ", ");

        ret.append(target.getContactName() + ", ");

        ret.append(target.getKey());

        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {
        return "This will permanently remove this System Owner and all related data(Time records, activity records, ect.).";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return "delete-success-system-owner";
    }

}