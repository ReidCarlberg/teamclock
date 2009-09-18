/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.authen.events;

import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.EventListener;
import com.fivesticks.time.events.EventListenerException;
import com.fivesticks.time.events.GeneralEvent;

/**
 * @author REID
 */
public class AuthenticationEventListener implements EventListener {

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventListener#tell(com.fivesticks.time.events.GeneralEvent)
     */
    public void tell(GeneralEvent event) throws EventListenerException {
        try {
            AuthenticationEventHandlerFactory.factory.build().handleEvent(event);
        } catch (EventHandlerException e) {
            throw new EventListenerException(e);
        }
    }

}
