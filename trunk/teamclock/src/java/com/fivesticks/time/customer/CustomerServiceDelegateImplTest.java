/*
 * Created on Aug 24, 2004 by shuji
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author shuji
 */
public class CustomerServiceDelegateImplTest extends TestCase {
    private SystemOwner systemOwner;

    protected void setUp() throws Exception {

        super.setUp();
        JunitSettings.initializeTestSystem();

        /*
         * this sets up an Admin user.
         */
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testDaoAdd() throws Exception {

        Customer t1 = new Customer();
        t1.setName("name");
        t1.setOwnerKey(systemOwner.getKey());

//        GenericDAO dao = FstxCustomerDAO.factory.build();

        CustomerServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

    }

    public void testBDFind() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Customer t1 = new Customer();
        t1.setName("name");

        t1.setOwnerKey(systemOwner.getKey());

//        GenericDAO dao = FstxCustomerDAO.factory.build();

        CustomerServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

        Customer t2 = CustomerServiceDelegateFactory.factory.build(sampleContext)
                .getFstxCustomer(t1.getId());

        assertTrue(t2 != null);

        assertTrue(t2.getId().equals(t1.getId()));

        assertTrue(t2.getName().equals(t1.getName()));

    }

    public void testGetAll() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Collection before = CustomerServiceDelegateFactory.factory.build(sampleContext)
                .getAll();

        assertTrue(before != null && before.size() == 1);

        Customer t1 = new Customer();
        t1.setName("name");

        t1.setOwnerKey(systemOwner.getKey());

//        GenericDAO dao = FstxCustomerDAO.factory.build();

        CustomerServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

        Collection after = CustomerServiceDelegateFactory.factory.build(sampleContext).getAll();

        assertTrue(after != null && after.size() == before.size() + 1);
    }

    public void testGetUnassignedCustomer() throws Exception {

    }

    public void testGetCustomerBySetting() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        ObjectMetricServiceDelegateFactory.factory.build(owner).setValue(cust1,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "true");

        Collection r1 = CustomerServiceDelegateFactory.factory.build(owner)
                .getCustomerBySetting(CustomerSettingType.IS_AUCTION_CUSTOMER,
                        "true");

        assertTrue(r1.size() == 1);

        assertTrue(r1.toArray()[0].getClass().getName().equals(
                Customer.class.getName()));
    }

    public void testGetCustomerBySettingAndFilter() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        CustomerFilterVO filter = new CustomerFilterVO();
        filter.setId(cust1.getId());

        ObjectMetricServiceDelegateFactory.factory.build(owner).setValue(cust1,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "true");

        ObjectMetricServiceDelegateFactory.factory.build(owner).setValue(cust2,
                CustomerSettingType.IS_AUCTION_CUSTOMER, "true");

        Collection r1 = CustomerServiceDelegateFactory.factory.build(owner)
                .getCustomerBySetting(filter, CustomerSettingType.IS_AUCTION_CUSTOMER,
                        "true");

        assertTrue(r1.size() == 1);

        assertTrue(r1.toArray()[0].getClass().getName().equals(
                Customer.class.getName()));
    }
}