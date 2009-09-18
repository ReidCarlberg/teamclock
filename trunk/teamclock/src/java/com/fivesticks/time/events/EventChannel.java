/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

import java.util.Collection;

/**
 * @author REID
 */
public interface EventChannel {

    public void send(GeneralEvent event) throws EventChannelException;
    
    public void subscribe(EventListener listener);
    
    public void unsubscribe(EventListener listener);
    
    public void process() throws EventChannelException;
    
    public boolean hasSubscribers();
    
    public boolean hasEvents();
    
    public Collection getEvents();
}
