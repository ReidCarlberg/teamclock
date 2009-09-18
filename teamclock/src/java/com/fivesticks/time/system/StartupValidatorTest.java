/*
 * Created on Jul 17, 2005
 *
 */
package com.fivesticks.time.system;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 *
 */
public class StartupValidatorTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testStartup() throws Exception {
        
        StartupValidator validator = new StartupValidator();
        
        assertTrue(validator.validate());
        
        
    }

}
