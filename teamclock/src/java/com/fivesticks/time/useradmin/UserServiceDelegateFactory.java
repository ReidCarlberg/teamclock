/*
 * Created on Sep 15, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class UserServiceDelegateFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "userServiceDelegate";
    public static final UserServiceDelegateFactory factory = new UserServiceDelegateFactory();

    public UserServiceDelegate build(SystemOwner systemOwner) {

        UserServiceDelegate ret = (UserServiceDelegate) SpringBeanBroker
                .getBeanFactory().getBean(UserServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);

        return ret;
    }

    public UserServiceDelegate build(SessionContext sessionContext) {

        UserServiceDelegateImpl ret = (UserServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(UserServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(sessionContext.getSystemOwner());

        ret.setOriginatingUser(sessionContext.getUser().getUser());

        return ret;
    }
    
    public UserServiceDelegate buildEmpty() {
        return (UserServiceDelegate) this.getBean(SPRING_BEAN_NAME);
    }

}