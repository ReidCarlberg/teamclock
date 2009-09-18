/*
 * Created on Aug 24, 2004 by REID
 */
package com.fivesticks.time.systemowner;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class SystemOwnerDAOImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testSearch() throws Exception {

        SystemOwner one = new SystemOwner();
        one.setKey("key1");
        one.setName("name");
        one.setAddress1("add1");
        one.setCity("city");
        one.setState("state");
        one.setPostalCode("post");
        one.setContactEmail("rsc1@fivesticks.com");
        one.setContactName("cont name");
        one.setContactPhone("777");
        one.setCountry("USA");

        one = SystemOwnerServiceDelegateFactory.factory.build().save(one);

        SystemOwner two = new SystemOwner();
        two.setKey("key2");
        two.setName("name");
        two.setAddress1("add1");
        two.setCity("city");
        two.setState("state");
        two.setPostalCode("post");
        two.setContactEmail("rsc1@fivesticks.com");
        two.setContactName("cont name");
        two.setContactPhone("777");
        two.setCountry("USA");

        two = SystemOwnerServiceDelegateFactory.factory.build().save(two);

        SystemOwnerCriteriaParameters params = new SystemOwnerCriteriaParameters();

        params.setKey(one.getKey());

        assertTrue(SystemOwnerDAOFactory.factory.build().search(params).size() == 1);

    }

}