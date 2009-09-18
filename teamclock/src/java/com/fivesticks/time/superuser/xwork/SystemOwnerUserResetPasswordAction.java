/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.system.messages.PasswordHelp;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SystemOwnerUserResetPasswordAction extends ActionSupport implements
        SessionContextAware, SystemOwnerManagerContextAware {

    private SessionContext sessionContext;

    private SystemOwnerManagerContext systemOwnerManagerContext;

    private String targetUser;

    public String execute() throws Exception {

        if (this.getTargetUser() == null
                || this.getTargetUser().trim().length() == 0
                || this.getSystemOwnerManagerContext().getActiveSystemOwner() == null) {
            return INPUT;
        }

        SystemUser targetUser = SystemUserServiceDelegateFactory.factory.build().get(
                this.getTargetUser());

        if (!targetUser.getOwnerKey().equals(
                this.getSystemOwnerManagerContext().getActiveSystemOwner()
                        .getKey())) {
            return INPUT;
        }

        UserBD bd = UserBDFactory.factory.build();

        User user = bd.getByUsername(targetUser.getUsername());

        
        bd.changePassword(user, new RandomStringBuilder().buildRandomString(8));

        user = bd.getByUsername(targetUser.getUsername());

        new PasswordHelp().sendPasswordHelp(user);

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

    /**
     * @return Returns the target.
     */
    public String getTargetUser() {
        return targetUser;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTargetUser(String target) {
        this.targetUser = target;
    }
}