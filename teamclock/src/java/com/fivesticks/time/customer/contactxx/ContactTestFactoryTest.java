/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import junit.framework.TestCase;

import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class ContactTestFactoryTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        Contact contact = ContactTestFactory.singleton.getPersisted(owner, CustomerTestFactory.getPersisted(owner));
        
        assertTrue(contact != null);
        
    }

}
