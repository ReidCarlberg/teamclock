/*
 * Created on Nov 9, 2005
 *
 */
package com.fivesticks.time.ws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContextFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.authen.AuthenticatedUserFactoryImpl;
import com.fstx.stdlib.authen.Authenticator;
import com.fstx.stdlib.authen.users.User;

public abstract class AbstractAuthBasedAuthenticationService extends
        AbstractTeamClockService {

    private static Log log = LogFactory
            .getLog(AbstractAuthBasedAuthenticationService.class);

    public String handleCommonPrep(String token, String username,
            String password) {
        String ret = null;
        if (!this.areParametersValid(token)) {
            ret = FAILED_VALIDATION;
            return ret;
        }

        try {
            if (this.areParametersValid(username, password)) {
                // system token
                configureOwnerAndUser(token, username, password);
            } else {
                // user token
                configureOwnerAndUser(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = FAILED_USER_OR_TOKEN;
            return ret;
        }

        if (!sessionContext.getSettings().isSystemTokenAllowed()) {
            return FAILED_SETTINGS;
        }

        return ret;
    }

    protected boolean areParametersValid(String token) {
        boolean ret = true;

        if (!isValidParameter(token, 49, 100)) {
            log.info("failed: " + token.length());
            ret = false;
        }

        return ret;
    }

    protected boolean isValidParameter(String parameter, int minLength,
            int maxLength) {

        if (parameter == null ||  parameter.trim().length() == 0 || parameter.length() < minLength
                || parameter.length() > maxLength) {
            return false;
        }

        return true;

    }

    protected boolean areParametersValid(String token, String username,
            String password) {
        boolean ret = true;

        if (token == null || token.length() < 49 || token.length() > 100
                || username == null || username.length() > 20
                || password == null || password.length() > 20) {
            log.info("failed: " + token.length() + " - " + username.length()
                    + " - " + password.length());
            ret = false;
        }

        return ret;
    }

    protected boolean areParametersValid(String username, String password) {
        boolean ret = true;

        if (!isValidParameter(username, 0, 20)
                || !isValidParameter(password, 0, 20)) {

            ret = false;
        }

        return ret;
    }

    protected void configureOwnerAndUser(String token, String username,
            String password) throws Exception {

        // validate the Token.
        TokenServiceDelegate tokenSD = TokenServiceDelegateFactory.factory
                .build();
        SystemOwner so;
        so = tokenSD.getSystemOwnerFromToken(token);

        SystemUser systemUser = SystemUserServiceDelegateFactory.factory
                .build().getBySystemAndUser(so, username);

        AuthenticatedUser a = Authenticator.singleton.authenticate(username,
                password);

        this.systemOwner = so;
        this.user = a.getUser();
        this.sessionContext = SessionContextFactory.factory.build(a, so);
        // this.user = user;
    }

    
    protected void configureOwnerAndUser(String token) throws Exception {

        // validate the Token.
        TokenServiceDelegate tokenSD = TokenServiceDelegateFactory.factory
                .build();
        User user = tokenSD.getUserFromToken(token);

        if (user.isBooleanInactive()) {
            throw new RuntimeException("Inactive users.");
        }
        
        
        SystemUser systemUser = SystemUserServiceDelegateFactory.factory
                .build().get(user);
        
        
        SystemOwner so = SystemOwnerServiceDelegateFactory.factory.build().get(systemUser.getOwnerKey());

        AuthenticatedUser a = AuthenticatedUserFactoryImpl.factory.build(user);

        this.systemOwner = so;
        this.user = a.getUser();
        this.sessionContext = SessionContextFactory.factory.build(a, so);
        // this.user = user;
    }
}
