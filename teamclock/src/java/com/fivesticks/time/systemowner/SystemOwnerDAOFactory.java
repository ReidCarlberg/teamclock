/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class SystemOwnerDAOFactory {

    public static final String SPRING_BEAN_NAME = "systemOwnerDAO";
    public static final SystemOwnerDAOFactory factory = new SystemOwnerDAOFactory();

    public SystemOwnerDAO build() {
        return (SystemOwnerDAO) SpringBeanBroker.getBeanFactory().getBean(
                SystemOwnerDAOFactory.SPRING_BEAN_NAME);
    }

}