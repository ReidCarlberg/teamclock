/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ContactServiceDelegateFactory extends AbstractSpringObjectFactory{

    public static final String SPRING_BEAN_NAME = "customerContactServiceDelegate";
    public static final ContactServiceDelegateFactory factory = new ContactServiceDelegateFactory();

    public ContactServiceDelegate build(SystemOwner systemOwner) {
        ContactServiceDelegateImpl ret = (ContactServiceDelegateImpl) this.getBean(SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}