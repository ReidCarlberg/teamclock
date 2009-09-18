/*
 * Created on Aug 31, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class ListActionTest extends TestCase {

    SystemOwner systemOwner;
    
    User user;
    
    SessionContext sessionContext;
    
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        
        systemOwner = SystemOwnerTestFactory.getOwner();
        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        sessionContext = SessionContextTestFactory.getContext(systemOwner, user);
    }
    
    public void testList() throws Exception {
        
        ListAction action = new ListAction();
        action.setSessionContext(sessionContext);
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, r);
        
        assertTrue(action.getTeams() != null);
        
    }

}
