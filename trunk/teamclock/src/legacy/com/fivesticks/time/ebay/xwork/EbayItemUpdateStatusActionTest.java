/*
 * Created on Aug 17, 2005
 *
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class EbayItemUpdateStatusActionTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,cust1);
        
        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        EbayItemUpdateStatusAction action = new EbayItemUpdateStatusAction();
        
        action.setSessionContext(context);
        
        action.setTarget(item1.getId());
        
        action.setTargetStatus(ItemStatusType.ITEM_IN_TRANSIT.getName());
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
        
        assertTrue(action.getSessionContext().getMessage() == null);
        
    }
    
    public void testBasicFails() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,cust1);
        item1.setItemStatus(ItemStatusType.POSTED_TO_ACCOUNT.getName());
        EbayItemServiceDelegate.factory.build(owner).save(item1);
        
        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        EbayItemUpdateStatusAction action = new EbayItemUpdateStatusAction();
        
        action.setSessionContext(context);
        
        action.setTarget(item1.getId());
        
        action.setTargetStatus(ItemStatusType.CLOSED_CANCELLED.getName());
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
        
        assertTrue(action.getSessionContext().getMessage() != null);
        
    }

}
