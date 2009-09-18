/*
 * Created on Aug 31, 2004 by REID
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class LoginWithOwnerKeyAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    public String execute() throws Exception {
        /*
         * INPUT is the system owner username and password page ERROR is the
         * general login action. SUCCESS is the dashboard
         */

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