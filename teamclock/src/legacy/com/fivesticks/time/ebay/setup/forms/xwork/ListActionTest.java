/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.EbayFormTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 *
 */
public class ListActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner2 = SystemOwnerTestFactory.getOwner();
        EbayForm fa = EbayFormTestFactory.build(owner2);
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayForm f1 = EbayFormTestFactory.build(owner);
        EbayForm f2 = EbayFormTestFactory.build(owner);
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        SessionContext context = SessionContextTestFactory.getContext(owner, user);
        
        ListAction action = new ListAction();
        
        action.setSessionContext(context);
        
        String s = action.execute();
        
        assertTrue(s.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getForms() != null);
        
        assertTrue(action.getForms().size() == 2);
        
    }

}
