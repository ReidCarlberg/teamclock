/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import junit.framework.TestCase;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class ItemShippingServiceDelegateImplTest extends TestCase {

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
        EbayItem item = EbayItemTestFactory.singleton.getPersisted(owner,cust1);
        ItemShipping shipping = ItemShippingTestFactory.getPersisted(item,owner);
        
        assertTrue(shipping != null);
        assertTrue(shipping.getId() != null);
        assertTrue(shipping.getOwnerKey().equals(owner.getKey()));
    }
    
    public void testList() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        EbayItem item = EbayItemTestFactory.singleton.getPersisted(owner,cust1);
        
        ItemShippingServiceDelegate sd = ItemShippingServiceDelegate.factory.build(owner);
        assertTrue(sd.findByEbayItem(item).size() == 0);
        
        ItemShipping shipping = ItemShippingTestFactory.getPersisted(item,owner);
        
        assertTrue(sd.findByEbayItem(item).size() == 1);    }
    

}
