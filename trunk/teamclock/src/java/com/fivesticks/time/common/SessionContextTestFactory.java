/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.common;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class SessionContextTestFactory {

    public static SessionContext getContext(SystemOwner systemOwner, User user) {
        SessionContext ret = new SessionContext();
        ret.setSystemOwner(systemOwner);
        ret.setUser(AuthenticatedUserFactory.factory.build(user));
        return ret;
    }
}