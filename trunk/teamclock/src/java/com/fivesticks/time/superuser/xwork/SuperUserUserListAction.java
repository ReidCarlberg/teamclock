/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class SuperUserUserListAction extends SessionContextAwareSupport {

//    private SessionContext sessionContexts;

    private Collection users;

    public String execute() throws Exception {

        /*
         * 2006-05-22 RSC What's this here for?
         */
//        throw new Exception("here");
        
        this.getSessionContext().setSuccessOverride(null);

        this.setUsers(UserBDFactory.factory.build().getAll());

        return SUCCESS;
    }

//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
//    }

    /**
     * @return Returns the systemOwners.
     */
    public Collection getUsers() {
        return users;
    }

    /**
     * @param systemOwners
     *            The systemOwners to set.
     */
    public void setUsers(Collection systemOwners) {
        this.users = systemOwners;
    }
}