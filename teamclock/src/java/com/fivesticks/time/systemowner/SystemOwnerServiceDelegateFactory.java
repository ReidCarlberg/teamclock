/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class SystemOwnerServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "systemOwnerServiceDelegate";
    public static final SystemOwnerServiceDelegateFactory factory = new SystemOwnerServiceDelegateFactory();

    public SystemOwnerServiceDelegate build() {
        return (SystemOwnerServiceDelegate) SpringBeanBroker.getBeanFactory()
                .getBean(SystemOwnerServiceDelegateFactory.SPRING_BEAN_NAME);
    }

}