/*
 * Created on Jan 15, 2005 by Reid
 */
package com.fivesticks.time.register.xwork;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.queuedmessages.SendQueuedMessagesCommandProxy;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.opensymphony.xwork.ActionContext;

/**
 * @author Reid
 */
public class ActivateActionTest extends TestCase {

    Log log = LogFactory.getLog(ActivateActionTest.class);
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testActivateAction() throws Exception {
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
        ra.getSystemOwner().setContactEmail("email1@fivesticks.com");
        ra.getSystemOwner().setContactPhone("phone");
        ra.setEmail2("email1@fivesticks.com");
        ra.getUser().setUsername("testusername");
        //        ra.getUser().setPassword("password");
        //        ra.setPassword2("password");
        ra.setTimezone("CST");
        ra.setSubmitRegister("submit");
        
        ra.setAgreeToTerms(true);

        ra.execute();

        assertTrue(!ra.hasErrors());

        ActivateAction aa = new ActivateAction();
        
        aa.setSessionContext(new SessionContext());
        aa.setActivate(ra.getSystemOwner().getKey());

        aa.execute();

        assertTrue(!aa.hasErrors());

        SystemOwner newOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                ra.getUser());

        assertTrue(newOwner.isActivated());

        log.info("Queued Messages " + new SendQueuedMessagesCommandProxy().getUnsentMessages()
                .size());
        
        assertTrue(new SendQueuedMessagesCommandProxy().getUnsentMessages()
                .size() == 3);

    }

}