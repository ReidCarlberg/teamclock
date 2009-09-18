/*
 * Created on Jan 18, 2005 by REID
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class SystemOwnerDeleteCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem(2);
    }

    public void testDelete() throws Exception {
        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();

        assertTrue(owners.size() == 2);

        SystemOwner first = (SystemOwner) owners.toArray()[0];

        FstxCommand txnCommand = (FstxCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        SystemOwnerServiceDelegate.SPRING_BEAN_NAME_DELETE);

        txnCommand.setTarget(first);

        txnCommand.execute();

        Collection ownersAfter = SystemOwnerServiceDelegateFactory.factory
                .build().findAll();

        assertTrue(ownersAfter.size() == 1);

    }

    public void testDeleteCannotDeleteFiveSticks() throws Exception {

        SystemOwner toBeFiveSticks = SystemOwnerTestFactory.getOwner();
        toBeFiveSticks.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(toBeFiveSticks);

        FstxCommand txnCommand = (FstxCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        SystemOwnerServiceDelegate.SPRING_BEAN_NAME_DELETE);

        txnCommand.setTarget(toBeFiveSticks);

        try {
            txnCommand.execute();
            assertTrue(false); // should have thrown an exception.
        } catch (Exception e) {
            // good caught the exception
        }

    }

}