/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.system.update.august;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class August_BuildDefaultSystemOwnerCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    /**
     * tests to be sure we actually add a default system owner.
     * 
     * @throws Exception
     */
    public void testExecute() throws Exception {

        Collection sysOwners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();

        assertTrue(sysOwners != null);

        August_BuildDefaultSystemOwnerCommand command = new August_BuildDefaultSystemOwnerCommand();

        command.execute();

        Collection afterOwners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();

        assertTrue(afterOwners.size() == sysOwners.size() + 1);

    }
}