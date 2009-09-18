/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.externaluser.events;

import com.fivesticks.time.events.EventHandler;
import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.system.messages.NotifyUserOfCustomerAssociation;
import com.fivesticks.time.system.messages.SystemMessageException;
import com.fivesticks.time.useradmin.xwork.UserListBroker;

/**
 * @author Reid
 */
public class ExternalUserEventHandler implements EventHandler {

    public static final String SPRING_BEAN_NAME = "externalUserEventHandler";

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventHandler#handleEvent(com.fivesticks.time.events.GeneralEvent)
     */
    public void handleEvent(GeneralEvent event) throws EventHandlerException {
        ExternalUserEvent userEvent = (ExternalUserEvent) event;

        UserListBroker.singleton.notifyOfChange(userEvent.getSystemOwner());

        if (userEvent.getExternalUserEventType() == ExternalUserEventType.ASSOCIATED) {
            try {
                new NotifyUserOfCustomerAssociation()
                        .sendCustomerAssociationNotification(userEvent
                                .getSystemOwner(), userEvent.getUser(),
                                userEvent.getFstxCustomer());
            } catch (SystemMessageException e) {
                throw new EventHandlerException(e);
            }
        }
    }

}