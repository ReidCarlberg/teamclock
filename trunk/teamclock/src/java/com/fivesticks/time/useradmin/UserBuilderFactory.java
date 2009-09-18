/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class UserBuilderFactory {

    public static final String SPRING_BEAN_NAME = "userAdminServiceDelegate";
    public static final UserBuilderFactory factory = new UserBuilderFactory();

    public UserBuilder build() {
        return (UserBuilder) SpringBeanBroker.getBeanFactory().getBean(
                UserBuilderFactory.SPRING_BEAN_NAME);
    }
}