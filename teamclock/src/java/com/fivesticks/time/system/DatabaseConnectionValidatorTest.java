/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import junit.framework.TestCase;

import com.fivesticks.time.common.Settings;

/**
 * @author Reid
 *  
 */
public class DatabaseConnectionValidatorTest extends TestCase {

    /**
     * Constructor for DatabaseConnectionValidatorTest.
     * 
     * @param arg0
     */
    public DatabaseConnectionValidatorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty(Settings.KEY_PATH, "c:/java/eclipseData/fstxtime");
    }

    public void testDatabase() throws Exception {
        new DatabaseConnectionValidator().validate();
    }

}