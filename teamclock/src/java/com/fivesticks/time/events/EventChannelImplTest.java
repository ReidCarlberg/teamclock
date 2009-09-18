/*
 * Created on Mar 19, 2005 by REID
 */
package com.fivesticks.time.events;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class EventChannelImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testProcess() throws Exception {
        
        EventChannel ch = EventChannelBroker.singleton.getChannel(GeneralEventType.MOCK);
        
        GeneralEventMockListener ml = new GeneralEventMockListener();
        ch.subscribe(ml);
        
        ch.send(new GeneralEventMockImpl());
        
        assertTrue(!ml.isTold());
        
        ch.process();
        
        assertTrue(ml.isTold());
        
        
    }
    
    public void testProcessMultipleSubscribers() throws Exception {
        
        EventChannel ch = EventChannelBroker.singleton.getChannel(GeneralEventType.MOCK);
        
        GeneralEventMockListener ml = new GeneralEventMockListener();
        GeneralEventMockListener m2 = new GeneralEventMockListener();
        GeneralEventMockListener m3 = new GeneralEventMockListener();
        
        ch.subscribe(ml);
        ch.subscribe(m2);
        ch.subscribe(m3);
        
        ch.send(new GeneralEventMockImpl());
        
        assertTrue(!ml.isTold());
        assertTrue(!m2.isTold());
        assertTrue(!m3.isTold());
        
        ch.process();
        
        assertTrue(ml.isTold());
        assertTrue(m2.isTold());
        assertTrue(m3.isTold());
        
        
    }    

}
