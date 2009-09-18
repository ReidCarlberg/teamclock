/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.xwork.AbstractTokenRevokeAction;

public class RevokeUserTokenAction extends AbstractTokenRevokeAction {

    public String handleExecute(TokenServiceDelegate sd) throws Exception {


        
        String t = sd.findToken(this.getSessionContext().getUser().getUser());
        
        sd.revokeToken(t);
        
        return SUCCESS;
    }

    public String getAction() {
        
        return "userRevokeToken.action";
    }
}
