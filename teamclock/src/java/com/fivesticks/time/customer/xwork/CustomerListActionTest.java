/*
 * Created on Aug 26, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author shuji
 */
public class CustomerListActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testList() throws Exception {
//        ActionContext context = new ActionContext(new HashMap());
//        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        CustomerListAction cla = new CustomerListAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        cla.setSessionContext(sessionContext);
        
        cla.setCustomerListContext(new CustomerListContext());
        cla.getCustomerListContext().setFilter(new CustomerFilterVO());
        
        
        assertTrue(cla.execute().equals(CustomerListAction.SUCCESS));
        assertTrue(cla.getCustomers() != null);
    }

}