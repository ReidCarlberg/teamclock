
package com.fivesticks.time.contactv2.forms.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;
import com.fivesticks.time.contactv2.forms.WebForm;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegateException;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegateFactory;

/**
 * @author REID
 *  
 */
public class WebFormDeleteCommand extends DeleteCommand {

    private final WebForm target;

    public WebFormDeleteCommand(WebForm target) {
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
            WebFormServiceDelegateFactory.factory.build(sessionContext).delete(target);
        } catch (WebFormServiceDelegateException e) {
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

        ret.append("WebForm entry for: " + target.getName() + " ");

        

        

        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {
        return "This will permanently remove this web form.  Any new requests for this form will fail.  This will not affect contacts or any other records.";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        if (super.getXWorkSuccess() != null) {
            return super.getXWorkSuccess();
        }
        return "delete-success-webform";
    }

}