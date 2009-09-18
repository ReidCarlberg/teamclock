/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class AccountTransactionServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "accountTransactionServiceDelegate";
    public static final AccountTransactionServiceDelegateFactory factory = new AccountTransactionServiceDelegateFactory();

    public AccountTransactionServiceDelegate build(SystemOwner systemOwner,
            ObjectTransactionType objectTransactionType) {
        
        AccountTransactionServiceDelegateImpl ret = (AccountTransactionServiceDelegateImpl) 
        	SpringBeanBroker
                .getBeanFactory().getBean(
                        AccountTransactionServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);

        ret.setObjectTransactionType(objectTransactionType);

        return ret;

    }
}