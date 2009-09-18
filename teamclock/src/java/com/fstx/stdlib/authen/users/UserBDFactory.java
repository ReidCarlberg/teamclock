/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class UserBDFactory {

    public static final String SPRING_BEAN_NAME = "userBD";
    public static final UserBDFactory factory = new UserBDFactory();

    public UserBD build() {
        return (UserBD) SpringBeanBroker.getBeanFactory().getBean(
                UserBDFactory.SPRING_BEAN_NAME);
    }
}