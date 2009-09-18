/*
 * Created on Aug 26, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;
import java.util.HashMap;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * @author shuji
 */
public class CustomerModifyActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    /*
     * 2005-01-19 Reid Updated for use with components
     */
    public void testModifyWithoutSubmitAndTarget() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        CustomerModifyAction cma = new CustomerModifyAction();
        CustomerModifyContext cMcontext = new CustomerModifyContext();
        cma.setCustomerModifyContext(cMcontext);

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        cma.setSessionContext(sessionContext);

        // ActionContext.getContext().getSession().put(CustomerModifyContext.KEY,
        // cMcontext);
        // //testging just excute without submit
        assertTrue(cma.execute().equals(CustomerListAction.INPUT));

    }

    public void testModifyWithoutSubmitWithTarget() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        CustomerModifyAction cma = new CustomerModifyAction();
        cma.setCustomerModifyContext(new CustomerModifyContext());
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        cma.setSessionContext(sessionContext);
        cma.setTarget(new Long(1));
        // testging just excute without submit
        assertTrue(cma.execute().equals(CustomerListAction.INPUT));
        // testing if set target = 1 assuming data target 1 is already there
        /*
         * 2005-01-18 Reid added to make sure it's set
         */
        assertTrue(cma.getCustomerModifyContext().getTargetCustomer() != null);
        assertFalse(cma.getTargetCust().getName().equals(""));

    }

    public void testAddNewCustomer() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        CustomerModifyAction cma = new CustomerModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        cma.setSessionContext(sessionContext);
        cma.setCustomerModifyContext(new CustomerModifyContext());
        CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory
                .build(sessionContext);

        Collection c1 = customerBD.getAll();
        // suppose to create a new customer name test1 and save it
        cma.getTargetCust().setName("test1");
        cma.getTargetCust().setCountry("US");
        cma.setCustomerSubmit("");
        cma.execute();

        Collection c2 = customerBD.getAll();

        assertEquals(c1.size() + 1, c2.size());

    }

    public void testModifyACustomersName() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));

        CustomerModifyAction cma = new CustomerModifyAction();

        SessionContext sessionContext = new SessionContext();

        sessionContext.setSystemOwner(systemowner);

        cma.setSessionContext(sessionContext);
        CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory
                .build(sessionContext);
        // ActionContext.getContext().getSession().remove(
        // CustomerModifyContext.KEY);
        CustomerModifyContext cMcontext = new CustomerModifyContext();
        cma.setCustomerModifyContext(cMcontext);

        Customer aCustomer = customerBD.getFstxCustomer(new Long(1));

        cMcontext.setTargetCustomer(aCustomer);

        // ActionContext.getContext().getSession().put(CustomerModifyContext.KEY,
        // cMcontext);
        String test = aCustomer.getName();
        cma.getTargetCust().setName(test + "_ga");
         cma.getTargetCust().setCountry("US");
        cma.setCustomerSubmit("");
        cma.execute();

        Customer aCustomer2 = customerBD.getFstxCustomer(new Long(1));

        String test3 = aCustomer2.getName();
        assertEquals(aCustomer2.getName(), test + "_ga");

    }

}