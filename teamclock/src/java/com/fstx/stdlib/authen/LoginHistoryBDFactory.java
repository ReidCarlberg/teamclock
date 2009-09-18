/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class LoginHistoryBDFactory {

    public static final String SPRING_BEAN_NAME = "loginHistoryBD";
    public static final LoginHistoryBDFactory factory = new LoginHistoryBDFactory();

    public LoginHistoryBD build() {
        return (LoginHistoryBD) SpringBeanBroker.getBeanFactory().getBean(
                LoginHistoryBDFactory.SPRING_BEAN_NAME);
    }
}