package com.fstx.stdlib.authen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Nick
 *  
 */
class AuthenticatorImpl implements Authenticator {

    /*
     * (non-Javadoc)
     * 
     * @see com.ycs.ems.auth.Authenticator#authenticate(java.lang.String,
     *      java.lang.String)
     */
    public AuthenticatedUser authenticate(String username, String password)
            throws UnableToAuthenticateException {
        // TODO Auto-generated method stub

        if (username == null || password == null) {
            log.info("unexpected nulls");
            throw new UnableToAuthenticateException();
        }

        User user = null;
        try {
            //user = UserDAO.factory.build().authenticateUser(username, password);
            
            user = UserBDFactory.factory.build().authenticateUser(username, password);
            
        } catch (UserBDException e) {
            throw new UnableToAuthenticateException(e);
        }

        if (user == null)
            throw new UnableToAuthenticateException();

        if (user.isBooleanInactive())
            throw new UnableToAuthenticateException("Account is inactive");

        return AuthenticatedUserFactory.factory.build(user);

    }

    private static Log log = LogFactory.getLog(AuthenticatedUser.class
            .getName());
}