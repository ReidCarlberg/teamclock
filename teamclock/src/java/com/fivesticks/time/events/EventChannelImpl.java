package com.fivesticks.time.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author REID
 */
public class EventChannelImpl implements EventChannel {

    private List subscribers;

    private List events;

    public EventChannelImpl() {
        subscribers = new ArrayList();
        events = new ArrayList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventChannel#send(com.fivesticks.time.events.GeneralEvent)
     */
    public synchronized void send(GeneralEvent event)
            throws EventChannelException {

        events.add(event);

    }

    public synchronized void process() throws EventChannelException {

        List eventsCopy = events;
        events = new ArrayList();

        for (Iterator iterator = eventsCopy.iterator(); iterator.hasNext();) {
            GeneralEvent element = (GeneralEvent) iterator.next();
            
            for (Iterator sub = subscribers.iterator(); sub.hasNext();) {
                

                EventListener element2 = (EventListener) sub.next();
                try {
                    element2.tell(element);
                } catch (EventListenerException e) {
                    throw new EventChannelException(e);
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventChannel#subscribe(com.fivesticks.time.events.EventListener)
     */
    public void subscribe(EventListener listener) {
        if (!subscribers.contains(listener)) {
            subscribers.add(listener);
        }
    }

    public void unsubscribe(EventListener listener) {
        if (subscribers.contains(listener)) {
            subscribers.remove(listener);
        }
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventChannel#hasSubscribers()
     */
    public boolean hasSubscribers() {
        return subscribers.size() > 0;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventChannel#hasEvents()
     */
    public boolean hasEvents() {
        return events.size() > 0;
    }
    
    public Collection getEvents() {
        return this.events;
    }

}