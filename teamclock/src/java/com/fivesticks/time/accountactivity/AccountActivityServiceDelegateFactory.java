/*
 * Created on Nov 22, 2004 by Reid
 */
package com.fivesticks.time.accountactivity;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class AccountActivityServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "accountActivityServiceDelegate";
    public static final AccountActivityServiceDelegateFactory factory = new AccountActivityServiceDelegateFactory();

    public AccountActivityServiceDelegate build(SessionContext sessionContext) {

        AccountActivityServiceDelegateImpl ret = (AccountActivityServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        AccountActivityServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSessionContext(sessionContext);
        ret.setSystemOwner(sessionContext.getSystemOwner());

        return ret;
    }
}