/*
 * Created on Mar 17, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ModifyActionTest extends TestCase {

    Log log = LogFactory.getLog(ModifyActionTest.class);
    
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testError() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        ModifyAction action = new ModifyAction();
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        action.setCustomerModifyContext(new CustomerModifyContext());
//        action.setEbaySubmit("submit");
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.ERROR));
        
    }
    public void testInputEmptyContext() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);

        ModifyAction action = new ModifyAction();
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        action.setEbayItemModifyContext(new EbayItemModifyContext());
//        action.setEbaySubmit("submit");
        String r = action.execute();        
        assertTrue(action.getEbayItemModifyContext().getTarget() == null);
        assertTrue(r.equals(ActionSupport.INPUT));
        
    }
    
    public void testInput() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        ModifyAction action = new ModifyAction();
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        action.setEbayItemModifyContext(new EbayItemModifyContext());
        action.setTarget(item1.getId());
        action.setEbaySubmit("submit");
        String r = action.execute();        
        assertTrue(r.equals(ActionSupport.INPUT));
        assertTrue(action.getEbayItemModifyContext().getTarget() != null);
    }    
    
    public void testCancel() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        EbayItem item1 = EbayItemTestFactory.singleton.getPersisted(owner,
                cust1);
        ModifyAction action = new ModifyAction();
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        action.setEbayItemModifyContext(new EbayItemModifyContext());
        action.setTarget(item1.getId());
        action.setEbayCancel("camce;");
        String r = action.execute();        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        assertTrue(action.getEbayItemModifyContext().getTarget() == null);
        
    }
}
