/*
 * Created on Aug 24, 2004 by REID
 */
package com.fivesticks.time.systemowner;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author REID
 */
public class SystemOwnerServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasicAssociation() throws Exception {
        /*
         * relies on the data in test settings.
         * 
         * we should always have an "admin" user.
         */

        User user = UserBDFactory.factory.build().getByUsername("admin");

        assertTrue(user != null);

        SystemOwner test = SystemOwnerServiceDelegateFactory.factory.build().get(user);

        assertTrue(test != null);

    }

}