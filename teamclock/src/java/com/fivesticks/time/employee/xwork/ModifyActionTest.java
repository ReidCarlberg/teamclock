/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.employee.EmployeeServiceDelegateFactory;
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
public class ModifyActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasicAdd() throws Exception {
        
        SystemOwner systemOwner = SystemOwnerTestFactory.getOwner();
        
        User user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
               
        ModifyAction action = new ModifyAction();
        
        action.setModifyContext(new ModifyContext());
        
        action.setSessionContext(SessionContextTestFactory.getContext(systemOwner, user));
        
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.INPUT));
        
        action.getTargetEmployee().setNameFirst("first");
        
        action.getTargetEmployee().setNameLast("last");
        
        action.getTargetEmployee().setCode("code");
        
        action.setSaveEmployee("save");
        
        action.getTargetEmployee().setUsername(user.getUsername());
        
        r = action.execute();
        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        
        assertTrue(EmployeeServiceDelegateFactory.factory.build(systemOwner).getAll().size() == 1);
        
        
        
        
    }

}
