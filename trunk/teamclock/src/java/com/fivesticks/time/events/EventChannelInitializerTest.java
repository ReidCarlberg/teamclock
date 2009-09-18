/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.events;

import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Reid
 */
public class EventChannelInitializerTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testBasic() throws Exception {
        
        new EventChannelInitializer().initialize();
        
        Map brokers = EventChannelBroker.singleton.getChannels();
        
        assertTrue(brokers.get(GeneralEventType.AUTHENTICATION_EVENT) != null);
        assertTrue(brokers.get(GeneralEventType.EXTERNAL_USER_EVENT) != null);
        assertTrue(brokers.get(GeneralEventType.SETTINGS_EVENT) != null);
        assertTrue(brokers.get(GeneralEventType.TODO_EVENT) != null);
        assertTrue(brokers.get(GeneralEventType.USER_EVENT) != null);
    
    }

}
