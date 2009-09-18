/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.externaluser.events;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.events.GeneralEvent;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface ExternalUserEvent extends GeneralEvent {

    public User getUser();

    public Customer getFstxCustomer();

    public ExternalUserEventType getExternalUserEventType();
}