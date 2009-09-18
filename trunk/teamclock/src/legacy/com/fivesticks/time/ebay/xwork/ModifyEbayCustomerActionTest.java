/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.customer.contact.ContactTestFactory;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
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
public class ModifyEbayCustomerActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasicModify() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust1, CustomerSettingType.IS_AUCTION_CUSTOMER,"true");
        
        Contact cont = ContactTestFactory.singleton.getPersisted(owner, cust1);

        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        ModifyEbayCustomerAction action = new ModifyEbayCustomerAction();
        
        action.setSessionContext(context);
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        
        action.setTarget(cust1.getId());
        
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.INPUT));
        
        assertTrue(action.getCustomerModifyContext().getTargetCustomer() != null);
        
        assertTrue(action.getCustomerModifyContext().getTargetContact() != null);
    }
 
    public void testBasicAdd() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust1, CustomerSettingType.IS_AUCTION_CUSTOMER,"true");
        
        Contact cont = ContactTestFactory.singleton.getPersisted(owner, cust1);

        SessionContext context = SessionContextTestFactory.getContext(owner,user);
        
        ModifyEbayCustomerAction action = new ModifyEbayCustomerAction();
        
        action.setSessionContext(context);
        
        action.setCustomerModifyContext(new CustomerModifyContext());        
        
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.INPUT));
        
        assertTrue(action.getCustomerModifyContext().getTargetCustomer() == null);
        
        assertTrue(action.getCustomerModifyContext().getTargetContact() == null);        
    }
    
    public void testBasicModifySucceeds() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
              
        SessionContext context = SessionContextTestFactory.getContext(owner,user);


        FstxCustomer cust1 = FstxCustomerTestFactory.getPersisted(owner);
        ObjectMetricServiceDelegate.factory.build(owner).setValue(cust1, CustomerSettingType.IS_AUCTION_CUSTOMER,"true");
        
        Contact cont = ContactTestFactory.singleton.getPersisted(owner, cust1);

        
        Collection c1 = CustomerServiceDelegate.factory.build(context).getAll();
        
        assertTrue(c1.size() == 1);

        
        ModifyEbayCustomerAction action = new ModifyEbayCustomerAction();
        
        action.setSessionContext(context);
        
        action.setCustomerModifyContext(new CustomerModifyContext());   
        
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        action.getCustomerModifyContext().setTargetContact(cont);

        action.getCustomer().setName("no company");
        action.getCustomer().setStreet1("street1");
        action.getCustomer().setStreet2("street2");
        action.getCustomer().setState("ST");
        action.getCustomer().setZip("343");
        action.getCustomer().setCity("asdfada");
        
        action.getContact().setNameFirst("first name");
        action.getContact().setNameLast("last name");
        action.getContact().setPrimaryPhone("234242424");
        
        action.setSaveCustomer("dd");
        
        
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        
        Collection c2 = CustomerServiceDelegate.factory.build(context).getAll();
        
        assertTrue(c2.size() == 1);
        
    }
    
    public void testBasicAddSucceeds() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
              
        SessionContext context = SessionContextTestFactory.getContext(owner,user);

        
        Collection c1 = CustomerServiceDelegate.factory.build(context).getAll();
        
        assertTrue(c1.size() == 0);

        
        ModifyEbayCustomerAction action = new ModifyEbayCustomerAction();
        
        action.setSessionContext(context);
        
        action.setCustomerModifyContext(new CustomerModifyContext());  
        
        action.setEbayItemListContext(new EbayItemListContext());

        action.getCustomer().setName("no company");
        action.getCustomer().setStreet1("street1");
        action.getCustomer().setStreet2("street2");
        action.getCustomer().setState("ST");
        action.getCustomer().setZip("343");
        action.getCustomer().setCity("asdfada");
        
        action.getContact().setNameFirst("first name");
        action.getContact().setNameLast("last name");
        action.getContact().setPrimaryPhone("234242424");
        
        action.setSaveCustomer("dd");
        
        
        String r = action.execute();
        
        /*
         * RSC 2005-08-13 added this.
         */
        assertTrue(r.equals(ActionSupport.SUCCESS + ".added"));
        
        Collection c2 = CustomerServiceDelegate.factory.build(context).getAll();
        
        assertTrue(c2.size() == 1);
        
    }    
}
