/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.superuser.SuperuserServiceDelegate;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SystemOwnerMostActiveAction extends ActionSupport implements
        SessionContextAware, SystemOwnerManagerContextAware {

    private SessionContext sessionContext;

    private SystemOwnerManagerContext systemOwnerManagerContext;

    private Collection active;

    public String execute() throws Exception {

        this.setActive(new SuperuserServiceDelegate().getMostActiveUsers());

        return SUCCESS;
    }

    /**
     * @return Returns the active.
     */
    public Collection getActive() {
        return active;
    }

    /**
     * @param active
     *            The active to set.
     */
    public void setActive(Collection active) {
        this.active = active;
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

    /**
     * @return Returns the systemOwnerManagerContext.
     */
    public SystemOwnerManagerContext getSystemOwnerManagerContext() {
        return systemOwnerManagerContext;
    }

    /**
     * @param systemOwnerManagerContext
     *            The systemOwnerManagerContext to set.
     */
    public void setSystemOwnerManagerContext(
            SystemOwnerManagerContext systemOwnerManagerContext) {
        this.systemOwnerManagerContext = systemOwnerManagerContext;
    }
}