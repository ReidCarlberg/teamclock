/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class EbayItemImageServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);        
        
        EbayItemImage image1 = EbayItemImageTestFactory.factory.getPersisted(owner, item1);
        
        EbayItemImage image2 = EbayItemImageTestFactory.factory.getPersisted(owner, item1);
        
        Collection r = EbayItemImageServiceDelegate.factory.build(owner).getAll(item1);
        
        assertTrue(r.size() == 2);
        
        Collection r2 = EbayItemImageServiceDelegate.factory.build(owner).getAll(item2);
        
        assertTrue(r2.size() == 0);
        
        
        
    }

}
