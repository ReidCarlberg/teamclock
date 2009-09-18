/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

public class MessageServiceDelegateImplTest extends TestCase {

    SystemOwner systemOwner;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        JunitSettings.initializeTestSystem();
        
        systemOwner = SystemOwnerTestFactory.getOwner();
    }
    
    public void testBasic() throws Exception {
        
        Message m = MessageTestFactory.getPersisted(systemOwner);
        
        assertTrue(m != null);
        
        assertTrue(m.getId() != null);
    }

}
