/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.util.MaxActiveUserValidator;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class MakeActiveAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private String target;

    public String execute() throws Exception {

        SystemUser systemUser = SystemUserServiceDelegateFactory.factory.build()
                .getBySystemAndUser(this.getSessionContext().getSystemOwner(),
                        this.getTarget());
        if (!new MaxActiveUserValidator().isValid(this.getSessionContext()
                .getSystemOwner(), this.getSessionContext().getSettings()
                .getMaxActiveUsers(), UserTypeEnum.getByName(systemUser
                .getUserType()))) {
            this.getSessionContext().setMessage(
                    "Too many active users. (Max: "
                            + this.getSessionContext().getSettings()
                                    .getMaxActiveUsers() + ")");
        } else {
            UserServiceDelegateFactory.factory.build(
                    this.getSessionContext().getSystemOwner()).makeActive(
                    this.getTarget());
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