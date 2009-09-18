/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.settings.xwork;

import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateException;
import com.fivesticks.time.tokens.xwork.AbstractTokenGenerateAction;

public class GenerateSystemOwnerTokenAction extends AbstractTokenGenerateAction {

    
    public String handleExecute(TokenServiceDelegate sd) throws Exception {
        
        try {
            String t = sd.findToken(this.getSessionContext().getSystemOwner());
            this.addActionError("Already has a token.  Must revoke before adding a new one.");
            return INPUT;
        } catch (TokenServiceDelegateException e) {
            
        }
        
        String newToken = sd.generateSystemToken(this.getSessionContext().getSystemOwner());
        
        return SUCCESS;
    }

    public String getAction() {
        
        return "settingsGenerateToken.action";
    }


}
