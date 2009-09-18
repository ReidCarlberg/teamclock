/*
 * Created on Jun 10, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * Statement should have two lists of items on it;
 * First is a list of items ready to be settled (Status: ready to pay net,
 * waiting for pickup.
 * 
 * @author Reid
 */
public class EbayCustomerStatementActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        FstxCustomer cust = FstxCustomerTestFactory.getPersisted(owner);
        
        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,cust, ItemStatusType.ACTIVE_ITEM);
        
        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,cust,ItemStatusType.NOT_SOLD_WAITING_FOR_PICKUP);
        
        EbayItem item3 = EbayItemTestFactory.singleton.getPersisted(owner,cust,ItemStatusType.POSTED_TO_ACCOUNT);
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        SessionContext sc = SessionContextTestFactory.getContext(owner,user);
        
        EbayCustomerStatementAction action = new EbayCustomerStatementAction();
        
        action.setSessionContext(sc);
        
        action.setTarget(cust.getId());
        
        String s = action.execute();
        
        assertTrue(s.equals(ActionSupport.SUCCESS));
        
//        assertTrue(action.getReady().size() == 2);
        
        assertEquals(1, action.getReady().size());
        
        assertEquals(2, action.getOpen().size());
        
//        assertTrue(action.getOpen().size() ==1);
    }

}
