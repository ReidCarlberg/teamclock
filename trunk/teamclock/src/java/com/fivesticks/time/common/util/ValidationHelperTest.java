/*
 * Created on Jan 17, 2005 by REID
 */
package com.fivesticks.time.common.util;

import junit.framework.TestCase;

/**
 * @author REID
 */
public class ValidationHelperTest extends TestCase {

    public void testEmail() throws Exception {

        ValidationHelper helper = new ValidationHelper();
        assertTrue(!helper.isEmailAddress("a"));
        assertTrue(!helper.isEmailAddress("a@aaaaa"));
        assertTrue(helper.isEmailAddress("aa@aa.aa"));

        assertTrue(!helper.isEmailAddress("aa@aaa.a"));
        assertTrue(!helper.isEmailAddress("a@aaa.aa"));

        assertTrue(!helper.isEmailAddress("aaaa@aaa.aa@aa"));
    }

    public void testUsername() throws Exception {

        ValidationHelper helper = new ValidationHelper();

        assertTrue(helper.isValidUsername("usr45"));
        assertTrue(helper.isValidUsername("user1"));

        assertTrue(!helper.isValidUsername("user"));
        assertTrue(!helper.isValidUsername(""));
        assertTrue(!helper.isValidUsername("xx xx"));
    }

    public void testPassword() throws Exception {

        ValidationHelper helper = new ValidationHelper();

        assertTrue(helper.isValidPassword("usr45"));
        assertTrue(helper.isValidPassword("user1"));

        assertTrue(!helper.isValidPassword("user"));
        assertTrue(!helper.isValidPassword(""));
        assertTrue(!helper.isValidPassword("xx xx"));
    }
}