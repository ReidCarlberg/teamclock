/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SuperUserDashboardAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    public String execute() throws Exception {
        return SUCCESS;
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