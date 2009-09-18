/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class EbayItemListByContextActionTest extends TestCase {

    private static Log log = LogFactory.getLog(EbayItemListByContextActionTest.class);
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testCollections() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);

        EbayItemListByContextAction action = new EbayItemListByContextAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemListContext(new EbayItemListContext());
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        action.execute();
        
        assertTrue(action.getCustomers() != null);
        
        
        
        assertTrue(action.getItemStatusTypes() != null);
    }
    
    public void testBasicFilter() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        

        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);

        EbayItemListByContextAction action = new EbayItemListByContextAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemListContext(new EbayItemListContext());
        
        action.getEbayItemListContext().setFilter(new EbayItemFilter());
        action.getEbayItemListContext().getFilter().setCustId(cust1.getId());
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        log.info("r1 items size is " + action.getItems().size());
        
        assertTrue(action.getItems().size() == 2);

        action.getEbayItemListContext().getFilter().setCustId(cust2.getId());
        
        String r2 = action.execute();
        
        assertTrue(r2.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getItems().size() == 0);

    }
    
    public void testBasicFilterWithStatus() throws Exception {

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

        EbayItemListByContextAction action = new EbayItemListByContextAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        action.setEbayItemListContext(new EbayItemListContext());
        action.getEbayItemListContext().setFilter(new EbayItemFilter());
        action.getEbayItemListContext().getFilter().setItemStatus(ItemStatusType.ACTIVE_ITEM.getName());
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getItems().size() == 2);

        action.getEbayItemListContext().getFilter().setItemStatus(ItemStatusType.CLOSED_SOLD.getName());
        
        String r2 = action.execute();
        
        assertTrue(r2.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getItems().size() == 0);

    }    

    public void testListByDate() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);

        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        item1.setDateAdded(SimpleDate.factory.build().getDate());
        EbayItemServiceDelegate.factory.build(owner).save(item1);
        
        SimpleDate sd = SimpleDate.factory.build();
        sd.advanceDay(-5);
        EbayItem item2 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust2);   
        item2.setDateAdded(sd.getDate());
        EbayItemServiceDelegate.factory.build(owner).save(item2);
        
        
        SessionContext sessionContext = SessionContextTestFactory.getContext(owner,user);
        
        EbayItemListByContextAction action = new EbayItemListByContextAction();
        
        action.setSessionContext(sessionContext);
        
        action.setEbayItemListContext(new EbayItemListContext());
        action.getEbayItemListContext().setFilter(new EbayItemFilter());
        
        SimpleDate start = SimpleDate.factory.build();
        start.advanceDay(-1);
        
        action.getEbayItemListContext().setDateRangeStart(start.getDate());
        action.getEbayItemListContext().setDateRangeStop(SimpleDate.factory.build().getDate());
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getItems().size() == 1);
        
        
    }

}
