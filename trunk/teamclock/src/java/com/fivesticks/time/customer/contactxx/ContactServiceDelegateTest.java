/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class ContactServiceDelegateTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {

        ContactServiceDelegate sd = ContactServiceDelegateFactory.factory.build(null);

        assertTrue(sd != null);

    }

    public void testFindByCustomer() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        Contact con1 = ContactTestFactory.singleton.getPersisted(owner, cust1);
        Contact con2 = ContactTestFactory.singleton.getPersisted(owner, cust1);
        Contact con3 = ContactTestFactory.singleton.getPersisted(owner, cust2);

        Collection col1 = ContactServiceDelegateFactory.factory.build(owner)
                .getByCustomer(cust1);

        assertTrue(col1.size() == 2);

        Collection col2 = ContactServiceDelegateFactory.factory.build(owner)
                .getByCustomer(cust2);

        assertTrue(col2.size() == 1);

    }
    
    public void testFindByCustomerShouldFail() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemOwner owner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        Contact con1 = ContactTestFactory.singleton.getPersisted(owner, cust1);
        Contact con2 = ContactTestFactory.singleton.getPersisted(owner, cust1);
        Contact con3 = ContactTestFactory.singleton.getPersisted(owner, cust2);

        try {
            Collection col1 = ContactServiceDelegateFactory.factory.build(owner2)
            .getByCustomer(cust1);
            assertTrue(false);
        } catch (Exception e) {
            //do nothing;
        }


    }    

    public void testDelete() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        Contact con1 = ContactTestFactory.singleton.getPersisted(owner, cust1);

        long originalId = con1.getId().longValue();

        ContactServiceDelegate sd = ContactServiceDelegateFactory.factory.build(owner);

        sd.delete(con1);

        Contact con2 = ContactServiceDelegateFactory.factory.build(owner).get(new Long(originalId));
        
        assertTrue(con2 == null);
    }
    
    public void testSave() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        
        Contact con1 = new Contact();
       
        con1.setNameFirst("name");
        
        con1.setCustId(cust1.getId());
        
        ContactServiceDelegateFactory.factory.build(owner).save(con1);
        
        assertTrue(con1.getId() != null);
        
        assertTrue(con1.getOwnerKey() != null);
    }
    
    public void testGet() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        
        Contact con1 = ContactTestFactory.singleton.getPersisted(owner,cust1);
       
        Contact con2 = ContactServiceDelegateFactory.factory.build(owner).get(con1.getId());
        
        assertTrue(con2 != null);
    }
    
    public void testGetFailsValidation() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        SystemOwner owner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        
        Contact con1 = ContactTestFactory.singleton.getPersisted(owner,cust1);
       
        try {
            Contact con2 = ContactServiceDelegateFactory.factory.build(owner2).get(con1.getId());
            assertTrue(false); //should be here
        } catch (Exception e) {
            //do nothing.
        }
        
        
    }
}