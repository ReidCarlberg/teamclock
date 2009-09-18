/*
 * Created on Nov 9, 2005
 *
 */
package com.fivesticks.time.ws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContextFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;
import com.fstx.stdlib.authen.users.UserBDFactory;

public abstract class AbstractTokenBasedAuthenticationService extends
        AbstractTeamClockService {

    private static Log log = LogFactory.getLog(AbstractTokenBasedAuthenticationService.class);
    
    protected String  handleCommonPrep(String token, String username) {
        String ret = null;
        if (!this.areParametersValid(token, username)) {
            ret = FAILED_VALIDATION;
            return ret;
        }
        

        try {
            configureOwnerAndUser(token,username);
        } catch (Exception e) {
            ret = FAILED_USER_OR_TOKEN;
            return ret;
        }        
        
        
        
        if (!sessionContext.getSettings().isSystemTokenAllowed()) {
            return FAILED_SETTINGS;
        }
        
        
        
        return ret;
    }
    protected boolean areParametersValid(String token, String username) {
        boolean ret = true;

        if (token == null || token.length() < 49 || token.length() > 100
                || username == null || username.length() > 20) {
            log.info("failed: " + token.length() + " - " + username.length() );
            ret = false;
        }

        return ret;
    }

    protected void configureOwnerAndUser(String token, String username)
            throws Exception {
        // validate the Token.
        TokenServiceDelegate tokenSD = TokenServiceDelegateFactory.factory.build();
        SystemOwner so;
        so = tokenSD.getSystemOwnerFromToken(token);

        SystemUser systemUser = SystemUserServiceDelegateFactory.factory.build()
                .getBySystemAndUser(so, username);

        user = UserBDFactory.factory.build().getByUsername(username);

        this.systemOwner = so;
        
        this.sessionContext = SessionContextFactory.factory.build(user, systemOwner);
//        this.user = user;
    }

}
