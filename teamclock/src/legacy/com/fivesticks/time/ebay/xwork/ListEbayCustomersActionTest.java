/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ListEbayCustomersActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testExecute() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        ListEbayCustomersAction action = new ListEbayCustomersAction();
        
        action.setListEbayCustomerContext(new ListEbayCustomerContext(new FstxCustomerFilterVO()));
        action.setSessionContext(context);
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getCustomers().size() == 0);
    }
    
    public void testExecuteFindsCustomers() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust1, CustomerSettingType.IS_AUCTION_CUSTOMER,"true");
        
        FstxCustomer cust2 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust2, CustomerSettingType.IS_AUCTION_CUSTOMER,"true");
        
        FstxCustomer cust3 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust3, CustomerSettingType.IS_AUCTION_CUSTOMER,"false");
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        ListEbayCustomersAction action = new ListEbayCustomersAction();
        
        action.setListEbayCustomerContext(new ListEbayCustomerContext(new FstxCustomerFilterVO()));
        
        action.setSessionContext(context);
        
        String r1 = action.execute();
        
        assertTrue(r1.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getCustomers().size() == 2);
    }    
}
