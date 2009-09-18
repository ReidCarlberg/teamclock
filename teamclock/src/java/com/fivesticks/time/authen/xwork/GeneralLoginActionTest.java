/*
 * Created on Aug 24, 2004 by REID
 */
package com.fivesticks.time.authen.xwork;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class GeneralLoginActionTest extends AbstractTimeTestCase {

    Log log = LogFactory.getLog(GeneralLoginActionTest.class);
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
//        JunitSettings.initializeTestSystem();
    }

    public void testBasicLogin() throws Exception {
        //        Map map = new HashMap();
        //        ActionContext actionContext = new ActionContext(map);
        //
        //        ActionContext.setContext(actionContext);

        SessionContext sessionContext = new SessionContext();
        GeneralLoginAction gla = new GeneralLoginAction();
        gla.setSessionContext(sessionContext);
        gla.setLoginButton("submit");
        gla.setUsername("admin");
        gla.setPassword("admin");

        assertEquals(gla.execute(), GeneralLoginAction.SUCCESS);

        sessionContext = gla.getSessionContext();

        assertTrue(sessionContext.getSystemOwner() != null);

        User user = UserBDFactory.factory.build().getByUsername("admin");

        SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(user);

        assertTrue(sessionContext.getSystemOwner().getKey().equals(
                systemOwner.getKey()));

        assertTrue(sessionContext.getUser() != null);

        assertTrue(sessionContext.getUser().getUser().getUsername().equals(
                user.getUsername()));
    }

    public void testExternalUserLogin() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        User user = SystemUserTestFactory.singleton.buildExternal(owner);
        
        String updatedPassword = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(user,updatedPassword);
        ExternalUserServiceDelegateFactory.factory.build(owner).associate(
                user.getUsername(), cust1.getId());

        GeneralLoginAction action = new GeneralLoginAction();
        action.setSessionContext(new SessionContext());

        action.setUsername(user.getUsername());
        action.setPassword(updatedPassword);
        action.setLoginButton("Login");

        String re = action.execute();

        log.info("Action result is " + re);
        
        assertTrue(re.equals(GlobalForwardStatics.GLOBAL_EXTERNAL_DASHBOARD));

        assertTrue(action.getSessionContext().getSystemOwner() != null);
        assertTrue(action.getSessionContext().getSystemOwner().getId().equals(
                owner.getId()));
    }
    
    public void testLogInWithEmailAddress() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        String updatedPassword = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(user,updatedPassword);
        
        assertTrue(user.getEmail() != null);
        
        assertTrue(!user.getEmail().equals(user.getUsername()));
        
        SessionContext sessionContext = new SessionContext();
        GeneralLoginAction gla = new GeneralLoginAction();
        gla.setSessionContext(sessionContext);
        gla.setLoginButton("submit");
        gla.setUsername(user.getEmail());
        gla.setPassword(updatedPassword);

        assertEquals(GeneralLoginAction.SUCCESS, gla.execute());
    }
    
    public void testBlankLogin() throws Exception {
        
        SessionContext sc = new SessionContext();
        
        GeneralLoginAction gla = new GeneralLoginAction();
        
        gla.setSessionContext(sc);
        
        String s = gla.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        
        assertTrue(sc.getSettings() != null);
        
        assertNotNull(sc.getSettings().getSystemName());
    }

}