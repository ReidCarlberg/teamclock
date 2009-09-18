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
public class EbayItemServiceDelegateTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testListAllByCustomer() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);

        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);

        Collection r = sd.findAll(cust1);
        
        assertTrue(r.size() ==2);



    }
    

    
/*
 * RSC 2005-05-23 These are all outdated since we list by explicit status now.
 * 
 * @author Reid
 */
    //    public void testListAllReadyToListByCustomer() throws Exception {
//        SystemOwner owner = SystemOwnerTestFactory.singleton.build();
//
//        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
//
//        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
//
//        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//        item1.setDateAdded(new Date());
//        EbayItemServiceDelegate.factory.build(owner).save(item1);
//
//        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//
//        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);
//
//        Collection r = sd.find(cust1, ListType.TOLIST);
//        
//        assertTrue(r.size() ==1);
//
//        
//    }
//    
//    public void testListAllOpenByCustomer() throws Exception {
//        SystemOwner owner = SystemOwnerTestFactory.singleton.build();
//
//        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
//
//        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
//
//        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//        item1.setDateAdded(new Date());
//        item1.setDateStart(new Date());
//        EbayItemServiceDelegate.factory.build(owner).save(item1);
//
//        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//
//        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);
//
//        Collection r = sd.find(cust1, ListType.OPEN);
//
//        
//        assertTrue(r.size() ==1);
//
//        
//    }    
//    
//    public void testListAllReadyToPayCustomer() throws Exception {
//        SystemOwner owner = SystemOwnerTestFactory.singleton.build();
//
//        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
//
//        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
//
//        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//        item1.setDateAdded(new Date());
//        item1.setDateStart(new Date());
//        item1.setDateEnded(new Date());
//        EbayItemServiceDelegate.factory.build(owner).save(item1);
//
//        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//
//        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);
//
//        Collection r = sd.find(cust1, ListType.TOPAY);
//
//        
//        assertTrue(r.size() ==1);
//
//        
//    }       
//    
//    public void testListAllClosedCustomer() throws Exception {
//        SystemOwner owner = SystemOwnerTestFactory.singleton.build();
//
//        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
//
//        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
//
//        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//        item1.setDateAdded(new Date());
//        item1.setDateStart(new Date());
//        item1.setDateEnded(new Date());
//        item1.setDateNetPaid(new Date());
//        EbayItemServiceDelegate.factory.build(owner).save(item1);
//
//        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//
//        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);
//
//        Collection r = sd.find(cust1, ListType.CLOSED);
//
//        
//        assertTrue(r.size() ==1);
//
//        
//    }          
//    
//    public void testListAllClosedCustomerShouldBeZero() throws Exception {
//        SystemOwner owner = SystemOwnerTestFactory.singleton.build();
//
//        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
//
//        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
//
//        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
////        item1.setDateAdded(new Date());
////        item1.setDateStart(new Date());
////        item1.setDateEnded(new Date());
////        item1.setDateNetPaid(new Date());
//        EbayItemServiceDelegate.factory.build(owner).save(item1);
//
//        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
//                cust1);
//
//        EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory.build(owner);
//
//        Collection r = sd.find(cust1, ListType.CLOSED);
//
//        
//        assertTrue(r.size() ==0);
//
//        
//    }      

}