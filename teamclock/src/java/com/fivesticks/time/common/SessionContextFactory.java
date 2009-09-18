/*
 * Created on Nov 12, 2005
 *
 */
package com.fivesticks.time.common;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;

public class SessionContextFactory {

    public static final SessionContextFactory factory = new SessionContextFactory();

    public synchronized SessionContext build(User user, SystemOwner systemOwner) {
        AuthenticatedUser auser = AuthenticatedUserFactory.singleton
                .build(user);

       return this.build(auser,systemOwner);
    }
    
    public synchronized SessionContext build(AuthenticatedUser auser, SystemOwner systemOwner) {
        
        return new SessionContext(auser, systemOwner);

    }
}
