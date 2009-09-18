/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * 2005-12-21 Updated to abstract...
 * 
 * @author Reid
 * @author Nick
 * 
 */
public class CustomerServiceDelegateFactory extends AbstractSpringObjectFactory {
    public static final String SPRING_BEAN_NAME = "customerServiceDelegate";

    public static final CustomerServiceDelegateFactory factory = new CustomerServiceDelegateFactory();

    public CustomerServiceDelegate build(SessionContext sessionContext) {
        CustomerServiceDelegateImpl ret = (CustomerServiceDelegateImpl) this
                .getBean(CustomerServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSessionContext(sessionContext);
        ret.setSystemOwner(sessionContext.getSystemOwner());
        return ret;
    }

    /*
     * 2006-07-06 shouldn't need this but there are a fair number of service
     * that depend on it.  Leaving it for now.
     */
    public CustomerServiceDelegate build(SystemOwner systemOwner) {
        CustomerServiceDelegate ret = (CustomerServiceDelegate) this
                .getBean(CustomerServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);
        return ret;

    }

    /*
     * 2006-07-06 reid none of them should be empty.
     * used by the registration command  leaving for now
     */
    public CustomerServiceDelegate buildEmpty() {
        return (CustomerServiceDelegate) this
                .getBean(CustomerServiceDelegateFactory.SPRING_BEAN_NAME);
    }

}