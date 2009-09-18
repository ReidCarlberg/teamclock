/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.account.AccountTransactionServiceDelegateImpl;
import com.fivesticks.time.account.ObjectTransactionType;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class CustomerAccountTransactionServiceDelegateFactory {

    public AccountTransactionServiceDelegate buildTimeAccount(
            SystemOwner systemOwner) {
//        AccountTransactionServiceDelegateImpl ret = (AccountTransactionServiceDelegateImpl) SpringBeanBroker
//                .getBeanFactory().getBean(
//                        AccountTransactionServiceDelegate.SPRING_BEAN_NAME);
//        ret.setSystemOwner(systemOwner);
//        ret
//                .setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT);
        return this.build(systemOwner, CustomerAccountTransactionType.TIME_ACCOUNT);
    }

    public AccountTransactionServiceDelegate buildAuctionAccount(
            SystemOwner systemOwner) {
//        AccountTransactionServiceDelegateImpl ret = (AccountTransactionServiceDelegateImpl) SpringBeanBroker
//                .getBeanFactory().getBean(
//                        AccountTransactionServiceDelegate.SPRING_BEAN_NAME);
//        ret.setSystemOwner(systemOwner);
//        ret
//                .setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT);
//        return ret;
        
        return this.build(systemOwner, CustomerAccountTransactionType.EBAY_ACCOUNT);
    }

    private AccountTransactionServiceDelegate build(SystemOwner systemOwner,
            ObjectTransactionType objectTransactionType) {
        AccountTransactionServiceDelegateImpl ret = (AccountTransactionServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        AccountTransactionServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        ret.setObjectTransactionType(objectTransactionType);
        return ret;
    }
}