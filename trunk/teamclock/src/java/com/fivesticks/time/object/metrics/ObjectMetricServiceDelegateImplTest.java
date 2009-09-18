/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.object.metrics;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class ObjectMetricServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSpring() throws Exception {
        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
//        ObjectMetricDAO.factory.build();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner);
    }

    public void testGetMetrics() throws Exception {
        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetric one = new ObjectMetric();
        one.setOwnerKey(systemOwner.getKey());
        one.setObjectType(SystemOwner.class.getName());
        one.setObjectId(systemOwner.getId());
        one.setMetricType("test1");
        one.setMetricValue("value");
        
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner).save(one);
        
        assertTrue(one.getId() != null);

        Collection oneMetrics = ObjectMetricServiceDelegateFactory.factory.build(
                systemOwner).getMetrics();
        assertTrue(oneMetrics != null && oneMetrics.size() == 1);

        SystemOwner ownerTwo = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Collection twoMetrics = ObjectMetricServiceDelegateFactory.factory.build(
                ownerTwo).getMetrics();
        assertTrue(twoMetrics != null && twoMetrics.size() == 0);

    }

    //    public void testGetMetric() throws Exception {
    //        String testValue1 = "testValue1";
    //        String testValue2 = "testValue2";
    //
    //        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner1).setValue(
    //                systemOwner1.getClass(), systemOwner1.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue1);
    //
    //        SystemOwner systemOwner2 = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner2).setValue(
    //                systemOwner2.getClass(), systemOwner2.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue2);
    //
    //        ObjectMetric m1 = ObjectMetricServiceDelegate.factory.build(
    //                systemOwner1).getMetric(systemOwner1.getClass(),
    //                systemOwner1.getId(), ObjectMetricTypeEnum.__TEST);
    //        assertTrue(m1.getMetricValue().equals(testValue1));
    //        ObjectMetric m2 = ObjectMetricServiceDelegate.factory.build(
    //                systemOwner2).getMetric(systemOwner2.getClass(),
    //                systemOwner2.getId(), ObjectMetricTypeEnum.__TEST);
    //        assertTrue(m2.getMetricValue().equals(testValue2));
    //
    //    }

    public void testGetMetric2() throws Exception {
        String testValue1 = "testValue1";
        String testValue2 = "testValue2";

        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner1).setValue(
                systemOwner1, ObjectMetricTypeEnum.__TEST, testValue1);

        SystemOwner systemOwner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner2).setValue(
                systemOwner2, ObjectMetricTypeEnum.__TEST, testValue2);

        ObjectMetric m1 = ObjectMetricServiceDelegateFactory.factory.build(
                systemOwner1).getMetric(systemOwner1,
                ObjectMetricTypeEnum.__TEST);

        assertTrue(m1.getMetricValue().equals(testValue1));
        ObjectMetric m2 = ObjectMetricServiceDelegateFactory.factory.build(
                systemOwner2).getMetric(systemOwner2,
                ObjectMetricTypeEnum.__TEST);
        assertTrue(m2.getMetricValue().equals(testValue2));

    }

    //    public void testGetMetricValue() throws Exception {
    //        String testValue1 = "testValue1";
    //        String testValue2 = "testValue2";
    //
    //        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner1).setValue(
    //                systemOwner1.getClass(), systemOwner1.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue1);
    //
    //        SystemOwner systemOwner2 = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner2).setValue(
    //                systemOwner2.getClass(), systemOwner2.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue2);
    //
    //        assertTrue(ObjectMetricServiceDelegate.factory.build(systemOwner1)
    //                .getMetricValue(systemOwner1.getClass(), systemOwner1.getId(),
    //                        ObjectMetricTypeEnum.__TEST).equals(testValue1));
    //        assertTrue(ObjectMetricServiceDelegate.factory.build(systemOwner2)
    //                .getMetricValue(systemOwner2.getClass(), systemOwner2.getId(),
    //                        ObjectMetricTypeEnum.__TEST).equals(testValue2));
    //    }

    public void testGetMetricValue2() throws Exception {
        String testValue1 = "testValue1";
        String testValue2 = "testValue2";

        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner1).setValue(
                systemOwner1, ObjectMetricTypeEnum.__TEST, testValue1);

        SystemOwner systemOwner2 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner2).setValue(
                systemOwner2, ObjectMetricTypeEnum.__TEST, testValue2);

        assertTrue(ObjectMetricServiceDelegateFactory.factory.build(systemOwner1)
                .getMetricValue(systemOwner1, ObjectMetricTypeEnum.__TEST)
                .equals(testValue1));
        assertTrue(ObjectMetricServiceDelegateFactory.factory.build(systemOwner2)
                .getMetricValue(systemOwner2, ObjectMetricTypeEnum.__TEST)
                .equals(testValue2));
    }

    //    public void testSetValue() throws Exception {
    //
    //        String testValue = "testValue";
    //
    //        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner).setValue(
    //                systemOwner.getClass(), systemOwner.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue);
    //
    //        ObjectMetric m = ObjectMetricServiceDelegate.factory.build(systemOwner)
    //                .getMetric(systemOwner.getClass(), systemOwner.getId(),
    //                        ObjectMetricTypeEnum.__TEST);
    //
    //        assertTrue(m != null
    //                && m.getMetricType().equals(
    //                        ObjectMetricTypeEnum.__TEST.getName()));
    //
    //        assertTrue(m.getMetricValue().equals(testValue));
    //    }

    public void testSetValue2() throws Exception {

        String testValue = "testValue";

        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        ObjectMetricServiceDelegateFactory.factory.build(systemOwner).setValue(
                systemOwner, ObjectMetricTypeEnum.__TEST, testValue);

        ObjectMetric m = ObjectMetricServiceDelegateFactory.factory.build(systemOwner)
                .getMetric(systemOwner, ObjectMetricTypeEnum.__TEST);

        assertTrue(m != null
                && m.getMetricType().equals(
                        ObjectMetricTypeEnum.__TEST.getName()));

        assertTrue(m.getMetricValue().equals(testValue));
    }

    //    public void testSetValueWithUpdate() throws Exception {
    //
    //        String testValue = "testValue";
    //
    //        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.build();
    //
    //        ObjectMetricServiceDelegate.factory.build(systemOwner).setValue(
    //                systemOwner.getClass(), systemOwner.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue);
    //
    //        ObjectMetric m = ObjectMetricServiceDelegate.factory.build(systemOwner)
    //                .getMetric(systemOwner.getClass(), systemOwner.getId(),
    //                        ObjectMetricTypeEnum.__TEST);
    //
    //        assertTrue(m != null
    //                && m.getMetricType().equals(
    //                        ObjectMetricTypeEnum.__TEST.getName()));
    //
    //        assertTrue(m.getMetricValue().equals(testValue));
    //
    //        Collection allBefore = ObjectMetricServiceDelegate.factory.build(
    //                systemOwner).getMetrics();
    //
    //        assertTrue(allBefore.size() == 1);
    //
    //        String testValue2 = "testValue2";
    //
    //        ObjectMetricServiceDelegate.factory.build(systemOwner).setValue(
    //                systemOwner.getClass(), systemOwner.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue2);
    //
    //        ObjectMetric m2 = ObjectMetricServiceDelegate.factory
    //                .build(systemOwner).getMetric(systemOwner.getClass(),
    //                        systemOwner.getId(), ObjectMetricTypeEnum.__TEST);
    //
    //        assertTrue(m2 != null
    //                && m2.getMetricType().equals(
    //                        ObjectMetricTypeEnum.__TEST.getName()));
    //
    //        assertTrue(m2.getMetricValue().equals(testValue2));
    //
    //        Collection allAfter = ObjectMetricServiceDelegate.factory.build(
    //                systemOwner).getMetrics();
    //
    //        assertTrue(allAfter.size() == 1);
    //    }

    public void testSetValueWithUpdate2() throws Exception {

        String testValue = "testValue";

        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        ObjectMetricServiceDelegateFactory.factory.build(systemOwner).setValue(
                systemOwner, ObjectMetricTypeEnum.__TEST, testValue);

        ObjectMetric m = ObjectMetricServiceDelegateFactory.factory.build(systemOwner)
                .getMetric(systemOwner, ObjectMetricTypeEnum.__TEST);

        assertTrue(m != null
                && m.getMetricType().equals(
                        ObjectMetricTypeEnum.__TEST.getName()));

        assertTrue(m.getMetricValue().equals(testValue));

        Collection allBefore = ObjectMetricServiceDelegateFactory.factory.build(
                systemOwner).getMetrics();

        assertTrue(allBefore.size() == 1);

        String testValue2 = "testValue2";

        ObjectMetricServiceDelegateFactory.factory.build(systemOwner).setValue(
                systemOwner, ObjectMetricTypeEnum.__TEST, testValue2);

        ObjectMetric m2 = ObjectMetricServiceDelegateFactory.factory
                .build(systemOwner).getMetric(systemOwner,
                        ObjectMetricTypeEnum.__TEST);

        assertTrue(m2 != null
                && m2.getMetricType().equals(
                        ObjectMetricTypeEnum.__TEST.getName()));

        assertTrue(m2.getMetricValue().equals(testValue2));

        Collection allAfter = ObjectMetricServiceDelegateFactory.factory.build(
                systemOwner).getMetrics();

        assertTrue(allAfter.size() == 1);
    }

    //    public void testGetMetricsPart2() throws Exception {
    //
    //        String testValue = "testValue";
    //
    //        SystemOwner systemOwner = SystemOwnerTestFactory.singleton.build();
    //        ObjectMetricServiceDelegate.factory.build(systemOwner).setValue(
    //                systemOwner.getClass(), systemOwner.getId(),
    //                ObjectMetricTypeEnum.__TEST, testValue);
    //
    //        ObjectMetric m = ObjectMetricServiceDelegate.factory.build(systemOwner)
    //                .getMetric(systemOwner.getClass(), systemOwner.getId(),
    //                        ObjectMetricTypeEnum.__TEST);
    //
    //        // assertTrue(m != null
    //        // && m.getMetricType().equals(
    //        // SystemOwnerMetricTypeEnum.__TEST.getName()));
    //        //
    //        // assertTrue(m.getMetricValue().equals(testValue));
    //
    //        Collection metrics = ObjectMetricServiceDelegate.factory.build(
    //                systemOwner).getMetrics();
    //
    //        assertTrue(metrics != null);
    //        assertTrue(metrics.size() == 1);
    //
    //    }

    public void testGetMetricsForObject() throws Exception {

        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer customer1 = CustomerTestFactory
                .getPersisted(systemOwner1);

        ObjectMetricServiceDelegate sd = ObjectMetricServiceDelegateFactory.factory
                .build(systemOwner1);

        sd.setValue(systemOwner1, ObjectMetricTypeEnum.__TEST, "1");

        sd.setValue(systemOwner1, ObjectMetricTypeEnum.SYSTEM_LASTLOGIN_DATE,
                "2");

        Collection count1 = sd.getMetrics(systemOwner1);

        assertTrue(count1.size() == 2);

        sd.setValue(customer1, ObjectMetricTypeEnum.__TEST, "X");

        Collection count2 = sd.getMetrics(customer1);

        assertTrue(count2.size() == 1);

        Collection count3 = sd.getMetrics();

        assertTrue(count3.size() == 3);
    }

    public void testGetObjectMetricsByTypeMetricAndValue() throws Exception {

        SystemOwner systemOwner1 = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(systemOwner1);
        Customer cust2 = CustomerTestFactory.getPersisted(systemOwner1);
        Customer cust3 = CustomerTestFactory.getPersisted(systemOwner1);

        ObjectMetricServiceDelegate sd = ObjectMetricServiceDelegateFactory.factory
                .build(systemOwner1);

        sd.setValue(cust1, CustomerSettingType.IS_AUCTION_CUSTOMER, "test1");
        sd.setValue(cust2, CustomerSettingType.IS_AUCTION_CUSTOMER, "test1");
        sd.setValue(cust3, CustomerSettingType.IS_AUCTION_CUSTOMER, "test2");

        Collection r1 = sd.getMatchingObjectsByMetricValue(Customer.class,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "test1");
        
        assertTrue(r1 != null);
        assertTrue(r1.size() == 2);
        
        Collection r2 = sd.getMatchingObjectsByMetricValue(Customer.class,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "test2");
        
        assertTrue(r2.size() == 1);

        Collection r3 = sd.getMatchingObjectsByMetricValue(Customer.class,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "test3");
        
        assertTrue(r3.size() == 0);

    }
}