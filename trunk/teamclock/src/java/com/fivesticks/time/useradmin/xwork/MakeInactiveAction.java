/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class MakeInactiveAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private String target;

    public String execute() throws Exception {

        try {
            UserServiceDelegateFactory.factory.build(
                    this.getSessionContext().getSystemOwner()).makeInactive(
                    this.getTarget());
        } catch (UserServiceDelegateException e) {
            this.getSessionContext().setMessage(
                    "Make inactive failed.  " + e.getMessage());
        }
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

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }
}