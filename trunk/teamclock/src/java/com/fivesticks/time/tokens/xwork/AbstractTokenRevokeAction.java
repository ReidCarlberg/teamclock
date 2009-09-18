/*
 * Created on Apr 30, 2006
 *
 */
package com.fivesticks.time.tokens.xwork;

import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;

public abstract class AbstractTokenRevokeAction extends
        AbstractTokenAction {

    
    private String revokeCancel;
    private String revokeConfirm;

    public String execute() throws Exception {
        
        if (this.getRevokeCancel() != null) 
            return SUCCESS;

        if (this.getRevokeConfirm() == null)
            return INPUT;
        
        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();
        
     return this.handleExecute(sd);   
    }
    
    public abstract String handleExecute(TokenServiceDelegate sd) throws Exception ;
    /**
     * @return Returns the cancelRevoke.
     */
    public String getRevokeCancel() {
        return revokeCancel;
    }

    /**
     * @return Returns the confirmRevoke.
     */
    public String getRevokeConfirm() {
        return revokeConfirm;
    }

    /**
     * @param cancelRevoke
     *            The cancelRevoke to set.
     */
    public void setRevokeCancel(String cancelRevoke) {
        this.revokeCancel = cancelRevoke;
    }

    /**
     * @param confirmRevoke
     *            The confirmRevoke to set.
     */
    public void setRevokeConfirm(String confirmRevoke) {
        this.revokeConfirm = confirmRevoke;
    }

    
}
