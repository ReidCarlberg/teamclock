/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.employee.Employee;
import com.fivesticks.time.employee.EmployeeTestFactory;
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
        
        SystemOwner systemOwner = SystemOwnerTestFactory.getOwner();
        
        User user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        
        Employee emp1 = EmployeeTestFactory.getPersisted(systemOwner, user);
        
        ListAction action = new ListAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(systemOwner, user));
        
        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getEmployees().size() == 1);
        
    }

}
