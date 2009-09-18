/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.EventListener;
import com.fivesticks.time.events.EventListenerException;
import com.fivesticks.time.events.GeneralEvent;

/**
 * @author Reid
 */
public class SettingsEventListener implements EventListener {

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventListener#tell(com.fivesticks.time.events.GeneralEvent)
     */
    public void tell(GeneralEvent event) throws EventListenerException {
        try {
            new SettingsEventHandler().handleEvent(event);
        } catch (EventHandlerException e) {
            throw new EventListenerException(e);
        }
    }

}
