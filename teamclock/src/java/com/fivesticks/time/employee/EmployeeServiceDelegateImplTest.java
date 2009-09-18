/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 *
 */
public class EmployeeServiceDelegateImplTest extends TestCase {

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
        
        Employee t1 = EmployeeTestFactory.getUnpersisted(systemOwner, user);
        
        EmployeeServiceDelegateFactory.factory.build(systemOwner).save(t1);
        
        assertTrue(t1.getId() != null);
        
    }

}
