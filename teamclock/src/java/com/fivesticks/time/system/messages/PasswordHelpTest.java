/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.system.messages;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class PasswordHelpTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        User user = SystemUserTestFactory.singleton.buildOwner(owner);
        
        StringBuffer orig = new StringBuffer();
        orig.append(user.getPasswordHash());
        
        PasswordHelp help = new PasswordHelp();
        
        help.sendPasswordHelp(user);
        
        User testUser = UserBDFactory.factory.build().getByUsername(user.getUsername());
        
        assertTrue(testUser.getPassword() != null && testUser.getPassword().length() == 0);
        
        assertTrue(!testUser.getPasswordHash().equals(orig.toString()));
        
    }

}
