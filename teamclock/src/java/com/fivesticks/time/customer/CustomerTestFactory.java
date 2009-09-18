/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 *  
 */
public class CustomerTestFactory {

    private static int counter = 0;

    public static Customer getPersisted(SystemOwner systemOwner)
            throws Exception {
        counter++;

        Customer ret = new Customer();
        ret.setName("testCust"          + counter);

        ret = CustomerServiceDelegateFactory.factory.build(systemOwner).save(ret);

        return ret;
    }
}