/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.useradmin.events;

import com.fivesticks.time.events.EventHandler;
import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class UseradminEventHandler implements EventHandler {

    public static final String SPRING_BEAN_NAME = "useradminEventHandler";

    public void handleEvent(GeneralEvent generalEvent) throws EventHandlerException {
        UseradminEvent event = null;

        try {
            event = (UseradminEvent) generalEvent;
        } catch (ClassCastException exception) {
            throw new EventHandlerException(exception);
        }
        
        if (event.getUseradminEventType() == UseradminEventType.PASSWORDCHANGED) {
            handlePasswordChangedEvent(event);
        } else {
            handleUseradminEvent(event);
        }
    }
    /**
     * @param event
     */
    private void handlePasswordChangedEvent(UseradminEvent event) {
        SimpleDate simpleDate = SimpleDate.factory.build();
        
        
    }
    
    public void handleUseradminEvent(UseradminEvent event) {

        /*
         * basically these just need to clear out the current user list so it
         * gets rebuilt.
         */
        UserListBroker.singleton.notifyOfChange(event.getSystemOwner());

    }
}