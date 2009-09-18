/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ExternalUserServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "externalUserServiceDelegate";
    public static final ExternalUserServiceDelegateFactory factory = new ExternalUserServiceDelegateFactory();

    public ExternalUserServiceDelegate build(SystemOwner systemOwner) {
        ExternalUserServiceDelegateImpl ret = (ExternalUserServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        ExternalUserServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}