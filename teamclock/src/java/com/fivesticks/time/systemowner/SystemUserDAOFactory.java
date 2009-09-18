/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class SystemUserDAOFactory {

    public static final String SPRING_BEAN_NAME = "systemUserDAO";
    public static final SystemUserDAOFactory factory = new SystemUserDAOFactory();

    public SystemUserDAO build() {
        return (SystemUserDAO) SpringBeanBroker.getBeanFactory().getBean(
                SystemUserDAOFactory.SPRING_BEAN_NAME);
    }

}