/*
 * Created on Aug 31, 2004 by REID
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * Used to login when there is a system owner target as part of the login.
 * 
 * @author REID
 * @version 2004-08-10
 */
public class LoginWithOwnerKeySetupAction extends ActionSupport implements
        SessionContextAware {

    private String target;

    private SessionContext sessionContext;

    public String execute() throws Exception {
        /*
         * INPUT is the general login action. SUCCESS is the system owner
         * specific login screen.
         */
        if (this.getTarget() == null) {

            return INPUT;
        }

        SystemOwner selected = SystemOwnerServiceDelegateFactory.factory.build().get(
                this.getTarget());

        if (selected != null) {
            this.getSessionContext().setSystemOwner(selected);

            return SUCCESS;
        }
        return INPUT;

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