/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class LookupServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Lookup test1 = LookupTestFactory.build(owner,LookupType.CUSTOMER_STATUS);
        
        assertTrue(test1 != null);
        
        assertTrue(test1.getId() != null && test1.getId().longValue() > 0);
    }

    public void testAllByType() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Lookup test1 = LookupTestFactory.build(owner,LookupType.CUSTOMER_STATUS);
        Lookup test2 = LookupTestFactory.build(owner,LookupType.CUSTOMER_STATUS);
        Lookup test3 = LookupTestFactory.build(owner,LookupType.CUSTOMER_STATUS);
        
        Collection all = LookupServiceDelegateFactory.factory.build(owner).getAll(LookupType.CUSTOMER_STATUS);
        
        assertTrue(all.size() == 3);
        
        Collection none = LookupServiceDelegateFactory.factory.build(owner).getAll(LookupType.VENDOR_TYPE);
        
        assertTrue(none.size() == 0);
        
    }
    
    public void testReplace() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        Lookup ct1 = LookupTestFactory.build(owner, LookupType.CUSTOMER_STATUS);
        Lookup ct2 = LookupTestFactory.build(owner, LookupType.CUSTOMER_STATUS);
        
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        
        Customer cust2 = CustomerTestFactory.getPersisted(owner);
        
        Customer cust3 = CustomerTestFactory.getPersisted(owner);
      
        cust1.setStatus(ct1.getId());
        
        cust2.setStatus(ct1.getId());
        
        cust3.setStatus(ct2.getId());
        
        CustomerServiceDelegateFactory.factory.build(owner).save(cust1);
        CustomerServiceDelegateFactory.factory.build(owner).save(cust2);
        CustomerServiceDelegateFactory.factory.build(owner).save(cust3);
        
        CustomerFilterVO filter = new CustomerFilterVO();
        filter.setStatus(ct1.getId());
        
        Collection before = CustomerServiceDelegateFactory.factory.build(owner).searchByFilter(filter);
        
        assertTrue(before.size() == 2);

        filter.setStatus(ct2.getId());
        
        Collection before2 = CustomerServiceDelegateFactory.factory.build(owner).searchByFilter(filter);
        
        assertTrue(before2.size() == 1);

        
        
        LookupServiceDelegate sd = LookupServiceDelegateFactory.factory.build(owner);
        
        sd.replace(ct1, ct2);
        
        Collection after = CustomerServiceDelegateFactory.factory.build(owner).searchByFilter(filter);
        
        assertTrue(after.size() == 3);
        
        filter.setStatus(ct1.getId());
        
        Collection after2 = CustomerServiceDelegateFactory.factory.build(owner).searchByFilter(filter);
        
        assertTrue(after2.size() == 0);
        
        
    }
}
