/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

import junit.framework.TestCase;

/**
 * @author REID
 */
public class EventChannelTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testBasic() throws Exception {
        
        EventChannel ch1 = EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT);
        
        assertTrue(ch1 != null);
        
        EventChannel ch2 = EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT);
        
        assertTrue(ch1 == ch2);
        
        EventChannel ch3 = EventChannelBroker.singleton.getChannel(GeneralEventType.EXTERNAL_USER_EVENT);
        
        assertTrue(ch3 != ch1);
    }
    
    public void testSubscriber() throws Exception {
        
        EventChannel ch1 = EventChannelBroker.singleton.getChannel(GeneralEventType.AUTHENTICATION_EVENT);
        

        
    }

}
