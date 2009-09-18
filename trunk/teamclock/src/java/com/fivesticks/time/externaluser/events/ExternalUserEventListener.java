/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.externaluser.events;

import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.EventListener;
import com.fivesticks.time.events.EventListenerException;
import com.fivesticks.time.events.GeneralEvent;

/**
 * @author Reid
 */
public class ExternalUserEventListener implements EventListener {

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventListener#tell(com.fivesticks.time.events.GeneralEvent)
     */
    public void tell(GeneralEvent event) throws EventListenerException {
        try {
            new ExternalUserEventHandler().handleEvent(event);
        } catch (EventHandlerException e) {
            throw new EventListenerException(e);
        }
    }

}
