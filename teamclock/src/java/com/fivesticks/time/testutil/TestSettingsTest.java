/*
 * Created on Jun 12, 2004
 *
 */
package com.fivesticks.time.testutil;

import junit.framework.TestCase;

/**
 * @author REID
 *  
 */
public class TestSettingsTest extends TestCase {

    /**
     * Constructor for TestSettingsTest.
     * 
     * @param arg0
     */
    public TestSettingsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testSettings() throws Exception {
        JunitSettings.initializeTestSystem();
    }

}