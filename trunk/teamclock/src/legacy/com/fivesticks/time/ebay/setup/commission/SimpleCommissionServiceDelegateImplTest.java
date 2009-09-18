/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 *
 */
public class SimpleCommissionServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        SimpleCommission sc = new SimpleCommission();
        
        sc.setName("name1");
        
        SimpleCommissionServiceDelegate.factory.build(owner).save(sc);
        
        assertTrue(sc.getId() != null);
      
       
        
    }

}
