/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author REID
 */
public class GeneralEventQueue implements Serializable {

    public static final GeneralEventQueue singleton = new GeneralEventQueue();

    private Collection queue;

    protected GeneralEventQueue() {
        queue = new ArrayList();
    }

    public void add(final GeneralEvent event) {
        try {
            EventChannelBroker.singleton.getChannel(event.getGeneralEventType()).send(event);
            //queue.add(event);
        } catch (EventChannelException e) {
            
            e.printStackTrace();
        }
    }

    boolean hasEvents() {
        return queue.size() > 0;
    }

    int getEventCount() {
        return queue.size();
    }

    synchronized Collection getQueue() {
        Collection ret = queue;
        queue = new ArrayList();
        return ret;
    }

}