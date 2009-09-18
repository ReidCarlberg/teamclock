/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.register.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.register.events.RegisterEventPublisher;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.AuthenticatedUserFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class ActivateAction extends SessionContextAwareSupport {

    private String activate;

    public String execute() throws Exception {

        if (this.getActivate() == null
                || this.getActivate().trim().length() == 0)
            return ERROR;

        SystemOwner systemOwner = null;

        SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory
                .build();

        try {
            systemOwner = sd.get(this.getActivate());
        } catch (Exception e) {
            return ERROR;
        }
        if (systemOwner == null) {
            return ERROR;
        }

        systemOwner.setActivated(true);

        /*
         * 2006-07-07 tracking 1 month from activation date.
         */
        SimpleDate exp = SimpleDate.factory.buildEndOfDay();
        exp.advanceMonth(1);
        systemOwner.setExpirationDate(exp.getDate());
        systemOwner.setDemo(true);
        sd.save(systemOwner);

        new RegisterEventPublisher().publishActivateEvent(systemOwner);
        
        /*
         * 2006-05-22 RSC deal with the session context and user
         */
        Collection users = SystemUserServiceDelegateFactory.factory.build().list(systemOwner);
        
        if (users.size() != 1) {
            return ERROR;
        }
        
        SystemUser systemUser = (SystemUser) users.toArray()[0];
        
        User user = UserBDFactory.factory.build().getByUsername(systemUser.getUsername());
        
        this.getSessionContext().setSystemOwner(systemOwner);
        
        this.getSessionContext().setUser(AuthenticatedUserFactory.factory.build(user));
        
        

        return SUCCESS;
    }

    /**
     * @return Returns the activationKey.
     */
    public String getActivate() {
        return activate;
    }

    /**
     * @param activationKey
     *            The activationKey to set.
     */
    public void setActivate(String activationKey) {
        this.activate = activationKey;
    }
}