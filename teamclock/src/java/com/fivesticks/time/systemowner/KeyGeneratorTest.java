/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Date;
import java.util.Random;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class KeyGeneratorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        
    }
    public void testKey() throws Exception {

        Random random = new Random(new Date().getTime());

        for (int j = 0; j < 1000; j++) {
            int i = Math.abs(random.nextInt() % 26);

            i += 65;

            char c = (char) i;

            //	        System.out.println("it is: " + c + "(" + i + ")");

            assertTrue(i >= 65);
            assertTrue(i <= 90);
        }
    }

    public void testGeneratedKey() throws Exception {
        for (int j = 0; j < 1000; j++) {
            String key = SystemOwnerKeyGenerator.singleton.getGeneratedKey();
            assertTrue(key != null);
            assertTrue(key.length() >= 10);
            assertTrue(key.length() <= 20);
        }
    }

}