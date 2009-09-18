/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.contactv2;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ContactV2ServiceDelegateFactory extends
        AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "customerContactV2ServiceDelegate";

    public static final ContactV2ServiceDelegateFactory factory = new ContactV2ServiceDelegateFactory();

    public ContactV2ServiceDelegate build(SessionContext sessionContext) {
        ContactV2ServiceDelegateImpl ret = (ContactV2ServiceDelegateImpl) this
                .getBean(SPRING_BEAN_NAME);
        ret.setSessionContext(sessionContext);
        return ret;

    }

    public ContactV2ServiceDelegate build(SystemOwner systemOwner) {
        ContactV2ServiceDelegateImpl ret = (ContactV2ServiceDelegateImpl) this
                .getBean(SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}