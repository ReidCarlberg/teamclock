/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.settings.xwork;

import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.xwork.AbstractTokenRevokeAction;

public class RevokeSystemOwnerTokenAction extends AbstractTokenRevokeAction {
    
    public String handleExecute(TokenServiceDelegate sd) throws Exception {

        
        String t = sd.findToken(this.getSessionContext().getSystemOwner());
        
        sd.revokeToken(t);
        
        return SUCCESS;
    }

    public String getAction() {
        
        return "settingsRevokeToken.action";
    }

}
