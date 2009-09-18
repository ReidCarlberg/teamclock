/*
 * Created on Sep 10, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class UserListAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private Collection users;

    public String execute() throws Exception {

        /*
         * remove 2004-11-25 RSC
         */
        //        users = SystemUserServiceDelegate.factory.build().list(
        //                this.getSessionContext().getSystemOwner());
        users = UserServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).listUserAndType();

        return SUCCESS;
    }

    /**
     * @return Returns the users.
     */
    public Collection getUsers() {
        return users;
    }

    /**
     * @param users
     *            The users to set.
     */
    public void setUsers(Collection users) {
        this.users = users;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}