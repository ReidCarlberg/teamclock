/*
 * Created on Aug 24, 2004 by REID
 */
package com.fivesticks.time.systemowner;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class SystemUserDAOImplReidTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSearch() throws Exception {
        /*
         * can probably count on an "admin"
         */
        SystemUserSearchParameters params = new SystemUserSearchParameters();

        params.setUsername("admin");

        assertTrue(SystemUserDAOFactory.factory.build().search(params).size() == 1);

    }

}