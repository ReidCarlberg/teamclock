/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class SystemUserServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "systemUserServiceDelegate";
    public static final SystemUserServiceDelegateFactory factory = new SystemUserServiceDelegateFactory();

    public SystemUserServiceDelegate build() {
        return (SystemUserServiceDelegate) SpringBeanBroker.getBeanFactory()
                .getBean(SystemUserServiceDelegateFactory.SPRING_BEAN_NAME);
    }
}