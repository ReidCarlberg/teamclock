/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageTestFactory;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class EbayItemViewDetailActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testInput() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        item1.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item1);

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust2);
        item2.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item2);

        
        EbayItemViewDetailAction action = new EbayItemViewDetailAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemModifyContext(new EbayItemModifyContext());
        
        
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.INPUT));
        
    }
    
    public void testSuccess() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        item1.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item1);

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust2);
        item2.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item2);

        
        EbayItemViewDetailAction action = new EbayItemViewDetailAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemModifyContext(new EbayItemModifyContext());
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        action.setTarget(item1.getId());
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getEbayItemModifyContext().getTarget() != null);
        
        assertTrue(action.getEbayItemModifyContext().getImages() != null);
        
    }    
    
    public void testSuccessWithImages() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        item1.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item1);
        
        EbayItemImage im1 = new EbayItemImageTestFactory().getPersisted(owner, item1);

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust2);
        item2.setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item2);

        
        EbayItemViewDetailAction action = new EbayItemViewDetailAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemModifyContext(new EbayItemModifyContext());
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        action.setTarget(item1.getId());
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getEbayItemModifyContext().getTarget() != null);
        
        assertTrue(action.getEbayItemModifyContext().getImages() != null);
        
        assertTrue(action.getEbayItemModifyContext().getImages().size() == 1);
        
    }        

}
