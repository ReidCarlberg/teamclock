/*
 * Created on Jan 15, 2005 by Reid
 */
package com.fivesticks.time.register.xwork;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.queuedmessages.SendQueuedMessagesCommandProxy;
import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.settings.broker.SystemRights;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 */
public class RegisterActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testRegisterAction() throws Exception {
        // 2005-11-14 RSC this is extra.
//        Map map = new HashMap();
//        ActionContext actionContext = new ActionContext(map);
//        ActionContext.setContext(actionContext);

        RegisterAction ra = new RegisterAction();

        ra.getSystemOwner().setActivated(false);
        ra.getSystemOwner().setName("name");
        ra.getSystemOwner().setAddress1("Address1");
        ra.getSystemOwner().setAddress2("Address2");
        ra.getSystemOwner().setCity("city");
        ra.getSystemOwner().setState("state");
        ra.getSystemOwner().setPostalCode("post");
        ra.getSystemOwner().setCountry("country");
        ra.getSystemOwner().setContactName("contactname");
        ra.getSystemOwner().setContactEmail("email1@domain.com");
        ra.getSystemOwner().setContactPhone("phone");
        ra.setEmail2("email1@domain.com");
        ra.getUser().setUsername("testusername");
        //        ra.getUser().setPassword("password");
        //        ra.setPassword2("password");
        ra.setTimezone("CST");
        ra.setSubmitRegister("submit");
        ra.setAgreeToTerms(true);

        ra.execute();

        
        assertTrue(!ra.hasErrors());

        Collection messages = new SendQueuedMessagesCommandProxy()
                .getUnsentMessages();
        assertTrue(messages.size() == 2);

        SystemOwner newOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                ra.getUser());

        assertTrue(newOwner != null);
        assertTrue(!newOwner.isActivated());

    }

    public void testRegisterActionWithSampleToDos() throws Exception {
        // 2005-11-14 RSC this is extra.
//        Map map = new HashMap();
//        ActionContext actionContext = new ActionContext(map);
//        ActionContext.setContext(actionContext);

        RegisterAction ra = new RegisterAction();

        ra.getSystemOwner().setActivated(false);
        ra.getSystemOwner().setName("name");
        ra.getSystemOwner().setAddress1("Address1");
        ra.getSystemOwner().setAddress2("Address2");
        ra.getSystemOwner().setCity("city");
        ra.getSystemOwner().setState("state");
        ra.getSystemOwner().setPostalCode("post");
        ra.getSystemOwner().setCountry("country");
        ra.getSystemOwner().setContactName("contactname");
        ra.getSystemOwner().setContactEmail("email1@domain.com");
        ra.getSystemOwner().setContactPhone("phone");
        ra.setEmail2("email1@domain.com");
        ra.getUser().setUsername("testusername");
        //        ra.getUser().setPassword("password");
        //        ra.setPassword2("password");
        ra.setTimezone("CST");
        ra.setSubmitRegister("submit");
        ra.setAgreeToTerms(true);

        String  s = ra.execute();
        
        assertEquals(Action.SUCCESS, s);

        SessionContext sessionContext = SessionContextTestFactory.getContext(ra.getSystemOwner(), ra.getUser());
        
        Collection c = ToDoServiceDelegateFactory.factory.build(sessionContext).findIncomplete();
        assertNotNull(c);
        assertEquals(1,c.size());

    }
    
    public void testRegisterActionAndRights() throws Exception {
        Map map = new HashMap();
        ActionContext actionContext = new ActionContext(map);
        ActionContext.setContext(actionContext);

        RegisterAction ra = new RegisterAction();

        ra.getSystemOwner().setActivated(false);
        ra.getSystemOwner().setName("name");
        ra.getSystemOwner().setAddress1("Address1");
        ra.getSystemOwner().setAddress2("Address2");
        ra.getSystemOwner().setCity("city");
        ra.getSystemOwner().setState("state");
        ra.getSystemOwner().setPostalCode("post");
        ra.getSystemOwner().setCountry("country");
        ra.getSystemOwner().setContactName("contactname");
        ra.getSystemOwner().setContactEmail("email1@domain.com");
        ra.getSystemOwner().setContactPhone("phone");
        ra.setEmail2("email1@domain.com");
        ra.getUser().setUsername("testusername");
        //        ra.getUser().setPassword("password");
        //        ra.setPassword2("password");
        ra.setTimezone("CST");
        ra.setSubmitRegister("submit");
        ra.setAgreeToTerms(true);

        ra.execute();

        assertTrue(!ra.hasErrors());

        SystemOwner newOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                ra.getUser());

        assertTrue(newOwner != null);
        assertTrue(!newOwner.isActivated());

        SettingsInitializer init = new SettingsInitializer(newOwner);
//        init.setSystemOwner(newOwner); RSC 2005-11-08

        SystemRights rights = init.getRights();

        assertTrue(rights.isFreeSystem());
        assertTrue(!rights.isCanHaveExternalUsers());
        assertTrue(!rights.isCanUseAccountTransactions());
        assertTrue(!rights.isCanUseBetaFeatures());

    }

    public void testRegisterAction_ShouldFail() throws Exception {
        Map map = new HashMap();
        ActionContext actionContext = new ActionContext(map);
        ActionContext.setContext(actionContext);

        RegisterAction ra = new RegisterAction();

        ra.getSystemOwner().setActivated(false);
        ra.getSystemOwner().setName("name");
        ra.getSystemOwner().setAddress1("Address1");
        ra.getSystemOwner().setAddress2("Address2");
        ra.getSystemOwner().setCity("city");
        ra.getSystemOwner().setState("state");
        ra.getSystemOwner().setPostalCode("post");
        ra.getSystemOwner().setCountry("country");
        ra.getSystemOwner().setContactName("contactname");
        ra.getSystemOwner().setContactEmail("e");
        ra.getSystemOwner().setContactPhone("phone");
        ra.setEmail2("e");
        ra.getUser().setUsername("testusername");
        //        ra.getUser().setPassword("password");
        //        ra.setPassword2("password");
        ra.setTimezone("CST");
        ra.setSubmitRegister("submit");
        ra.setAgreeToTerms(true);

        ra.execute();

        assertTrue(ra.hasErrors());

    }
}