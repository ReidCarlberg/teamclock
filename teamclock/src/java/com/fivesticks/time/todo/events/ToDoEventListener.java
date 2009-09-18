/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.todo.events;

import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.EventListener;
import com.fivesticks.time.events.EventListenerException;
import com.fivesticks.time.events.GeneralEvent;

/**
 * @author Reid
 */
public class ToDoEventListener implements EventListener {

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventListener#tell(com.fivesticks.time.events.GeneralEvent)
     */
    public void tell(GeneralEvent event) throws EventListenerException {
        try {
            new ToDoEventHandler().handleEvent(event);
        } catch (EventHandlerException e) {
            throw new EventListenerException(e);
        }
    }

}
