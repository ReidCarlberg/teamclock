/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.authen.events;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 */
public class AuthenticationEventHandlerFactory {

    public static final String SPRING_BEAN_NAME = "authenticationEventHandler";
    public static final AuthenticationEventHandlerFactory factory = new AuthenticationEventHandlerFactory();

    public AuthenticationEventHandler build() {
        return (AuthenticationEventHandler) SpringBeanBroker.getBeanFactory()
                .getBean(AuthenticationEventHandlerFactory.SPRING_BEAN_NAME);
    }

}