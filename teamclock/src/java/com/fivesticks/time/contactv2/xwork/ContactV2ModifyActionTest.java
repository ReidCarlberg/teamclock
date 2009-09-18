
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2TestFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ContactV2ModifyActionTest extends AbstractTimeTestCase {


    
    public void testBasic() throws Exception {
        
        ContactV2 contact = ContactV2TestFactory.singleton.getPersisted(systemOwner, this.customer);
        
        ContactV2ModifyAction action = new ContactV2ModifyAction();
        
        action.setSessionContext(sessionContext);
        
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        
        action.setContactV2ModifyContext(new ContactV2ModifyContext());
        action.getContactV2ModifyContext().setTarget(contact);
        
        action.setContactV2(new ContactV2());

        action.getContactV2().setOrganizationPhone(contact.getOrganizationPhone());
        action.getContactV2().setNameFirst(contact.getNameFirst());
        action.getContactV2().setNameLast(contact.getNameLast());
        
        action.setContactSubmit("submit");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,s);
        
        
        assertNotNull(action.getContactV2().getId());
        
    }

}
