/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.contactxx.ContactTestFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ContactModifyActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner systemOwner = SystemOwnerTestFactory.getOwner();
        
        User user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        
        SessionContext sessionContext = SessionContextTestFactory.getContext(systemOwner,user);
        
        Customer cust1 = CustomerTestFactory.getPersisted(systemOwner);
        
        Contact contact = ContactTestFactory.singleton.getPersisted(systemOwner, cust1);
        
        ContactModifyAction action = new ContactModifyAction();
        
        action.setSessionContext(sessionContext);
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(cust1);
        
        action.setContactModifyContext(new ContactModifyContext());
        action.getContactModifyContext().setTarget(contact);
        
        action.setContact(new Contact());
        action.getContact().setAlternateEmail1(contact.getAlternateEmail1());
        action.getContact().setAlternateEmail2(contact.getAlternateEmail2());
        action.getContact().setAlternateEmail3(contact.getAlternateEmail3());
        action.getContact().setAlternateEmail4(contact.getAlternateEmail4());
        action.getContact().setAlternatePhone(contact.getAlternatePhone());
        action.getContact().setNameFirst(contact.getNameFirst());
        action.getContact().setNameLast(contact.getNameLast());
        
        action.setContactSubmit("submit");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,s);
        
        
    }

}
