/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.externaluser.events;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.events.AbstractGeneralEvent;
import com.fivesticks.time.events.GeneralEventType;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class ExternalUserEventImpl extends AbstractGeneralEvent implements ExternalUserEvent {

    private GeneralEventType generalEventType = GeneralEventType.EXTERNAL_USER_EVENT;

    
//    private SystemOwner systemOwner;

    private User user;

    private Customer fstxCustomer;

    private ExternalUserEventType externalUserEventType;

    /**
     * @return Returns the externalUserEventType.
     */
    public ExternalUserEventType getExternalUserEventType() {
        return externalUserEventType;
    }

    /**
     * @param externalUserEventType
     *            The externalUserEventType to set.
     */
    public void setExternalUserEventType(
            ExternalUserEventType externalUserEventType) {
        this.externalUserEventType = externalUserEventType;
    }

    /**
     * @return Returns the fstxCustomer.
     */
    public Customer getFstxCustomer() {
        return fstxCustomer;
    }

    /**
     * @param fstxCustomer
     *            The fstxCustomer to set.
     */
    public void setFstxCustomer(Customer fstxCustomer) {
        this.fstxCustomer = fstxCustomer;
    }

    /**
     * @return Returns the generalEventType.
     */
    public GeneralEventType getGeneralEventType() {
        return generalEventType;
    }

//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//
//    /**
//     * @param systemOwner
//     *            The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}