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
public class UserDAOFactory {

    public static final String SPRING_BEAN_NAME = "userDAO";
    public static final UserDAOFactory factory = new UserDAOFactory();

    public UserDAO build() {
        return (UserDAO) SpringBeanBroker.getBeanFactory().getBean(
                UserDAOFactory.SPRING_BEAN_NAME);
    }
}