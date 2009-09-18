/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

import junit.framework.TestCase;

/**
 * @author REID
 */
public class EventHandlerCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testBasic() throws Exception {
        
        GeneralEventMockListener listA = new GeneralEventMockListener();
        GeneralEventQueue.singleton.add(new GeneralEventMockImpl());
        
        EventChannelBroker.singleton.getChannel(GeneralEventType.MOCK).subscribe(listA);
        
        EventHandlerCommand command = new EventHandlerCommand();
        
        command.execute();
        assertTrue(listA.isTold());
        
    }

}
