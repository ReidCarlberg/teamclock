/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SystemOwnerUserListAction extends ActionSupport implements
        SessionContextAware, SystemOwnerManagerContextAware {

    private SessionContext sessionContext;

    private SystemOwnerManagerContext systemOwnerManagerContext;

    private Collection systemUsers;

    private Long target;

    public String execute() throws Exception {

        if ((this.getTarget() == null || this.getTarget().longValue() == 0)
                && this.getSystemOwnerManagerContext().getActiveSystemOwner() == null)
            return INPUT;

        if (this.getTarget() != null && this.getTarget().longValue() > 0) {
            this.getSystemOwnerManagerContext().setActiveSystemOwner(
                    SystemOwnerServiceDelegateFactory.factory.build().get(
                            this.getTarget()));
        }

        this.setSystemUsers(UserServiceDelegateFactory.factory.build(
                this.getSystemOwnerManagerContext().getActiveSystemOwner())
                .listUserAndType());

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
     * @return Returns the systemUsers.
     */
    public Collection getSystemUsers() {
        return systemUsers;
    }

    /**
     * @param systemUsers
     *            The systemUsers to set.
     */
    public void setSystemUsers(Collection systemUsers) {
        this.systemUsers = systemUsers;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

}