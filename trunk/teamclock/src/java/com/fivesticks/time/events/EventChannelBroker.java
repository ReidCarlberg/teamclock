/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author REID
 */
public class EventChannelBroker {

    public static final EventChannelBroker singleton=new EventChannelBroker();

    private Map channels;
    
    protected EventChannelBroker() {
        channels = new HashMap();
    }
    
    public EventChannel getChannel(final Object object) {
        EventChannel ret = (EventChannel) channels.get(object);
        if (ret == null) {
            ret = new EventChannelImpl();
            channels.put(object,ret);
        }
        
        return ret;
        
    }
    public EventChannel getChannel(final GeneralEventType type) {
        return this.getChannel((Object)type);
    }
    
    public Set getChannelKeys() {
        return channels.keySet();
    }
    
    Map getChannels() {
        return channels;
    }
    
    synchronized void clearChannels() {
        channels = new HashMap();
    }
    
     
    
}
