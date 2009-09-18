/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SystemOwnerDeactivateAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private Long target;

    public String execute() throws Exception {

        if (this.getTarget() == null || this.getTarget().longValue() == 0)
            return INPUT;

        SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory
                .build();

        SystemOwner targetOwner = null;
        try {
            targetOwner = sd.get(this.getTarget());
        } catch (SystemOwnerServiceDelegateException e) {
            return INPUT;
        }

        if (!targetOwner.isActivated()) {
            //
        } else {
            targetOwner.setActivated(false);
            sd.save(targetOwner);
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