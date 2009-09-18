/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class CustomerContactProjectAddActionTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBasic_Input() throws Exception {

        CustomerContactProjectAddAction action = new CustomerContactProjectAddAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());

        String s = action.execute();

        assertEquals(ActionSupport.INPUT, s);

    }

    public void testBasic_Success() throws Exception {

        CustomerContactProjectAddAction action = new CustomerContactProjectAddAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());

        action.getCustomer().setName("name");
        action.getCustomer().setStreet1("street1");
        action.getCustomer().setStreet2("street2");
        action.getCustomer().setCity("city");
        action.getCustomer().setState("states");
        action.getCustomer().setZip("zip");

        action.getContactV2().setNameFirst("first");
        action.getContactV2().setNameLast("last");
        action.getContactV2().setOrganizationEmail("reeed@fivesticks.com");
        action.getContactV2().setOrganizationPhone("phone");

        action.getProject().setLongName("long name");
        action.getProject().setShortName("shortName");

        action.getTime().setAmount(new Double(180.0));
        action.getTime().setComments("this is from a test");

        action.setSaveCustomer("save");

        String s = action.execute();
        assertTrue(!action.hasErrors());
        assertEquals(ActionSupport.SUCCESS, s);

        assertEquals(new Double(180.0),
                CustomerAccountTransactionServiceDelegate.factory
                        .buildTimeAccount(systemOwner).getCurrentBalance(
                                action.getCustomer()));

        assertEquals(1, ProjectServiceDelegateFactory.factory.build(this.systemOwner)
                .getAllForCustomer(action.getCustomer()).size());

        assertEquals(1, ContactV2ServiceDelegateFactory.factory.build(this.systemOwner)
                .getByCustomer(action.getCustomer()).size());

    }
    
    public void testBasic_Save_WithErrors() throws Exception {

        CustomerContactProjectAddAction action = new CustomerContactProjectAddAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());

        action.getCustomer().setName("name");
        //action.getCustomer().setStreet1("street1");
        action.getCustomer().setStreet2("street2");
        action.getCustomer().setCity("city");
        action.getCustomer().setState("states");
        action.getCustomer().setZip("zip");

        action.getContactV2().setNameFirst("first");
        //action.getContact().setNameLast("last");
        action.getContactV2().setOrganizationEmail("reeed@fivesticks.com");
        action.getContactV2().setOrganizationPhone("phone");

        action.getProject().setLongName("long name");
        //action.getProject().setShortName("shortName");

        action.getTime().setAmount(new Double(-180.0));
        action.getTime().setComments("this is from a test");

        action.setSaveCustomer("save");
        
        String s = action.execute();
        assertTrue(action.hasErrors());
        assertEquals(4,action.getFieldErrors().size());
        assertEquals(ActionSupport.INPUT, s);



    }
}
