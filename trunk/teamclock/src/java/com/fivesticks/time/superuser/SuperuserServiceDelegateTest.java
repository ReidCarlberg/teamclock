/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.superuser;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;
import com.fivesticks.time.superuser.xwork.OwnerMetricVO;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class SuperuserServiceDelegateTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testMostActive() throws Exception {

        SystemOwner own1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(own1).setValue(own1,
                ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT, "5");

        SystemOwner own2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(own2).setValue(own2,
                ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT, "9");

        SystemOwner own3 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(own3).setValue(own3,
                ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT, "7");

        Collection desc = new SuperuserServiceDelegate().getMostActiveUsers();

        assertTrue(desc.size() == 3);

        assertTrue(((OwnerMetricVO) desc.toArray()[0]).getMetric()
                .getMetricValue().equals("9"));

        assertTrue(((OwnerMetricVO) desc.toArray()[1]).getMetric()
                .getMetricValue().equals("7"));

        assertTrue(((OwnerMetricVO) desc.toArray()[2]).getMetric()
                .getMetricValue().equals("5"));

    }

}