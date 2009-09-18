/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.account.AbstractAccountTransactionServiceDelegateIF;

/**
 * @author Reid
 */
public interface CustomerAccountTransactionServiceDelegate extends AbstractAccountTransactionServiceDelegateIF {

    public static final String SPRING_BEAN_NAME = "customerAccountTransactionServiceDelegate";

    public static final CustomerAccountTransactionServiceDelegateFactory factory = new CustomerAccountTransactionServiceDelegateFactory();

}