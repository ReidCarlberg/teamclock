/*
 * Created on Jun 20, 2005
 *
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.account.ObjectTransactionType;

/**
 * @author Reid
 *
 */
public class CustomerAccountTransactionType extends ObjectTransactionType {

    public static final CustomerAccountTransactionType TIME_ACCOUNT = new CustomerAccountTransactionType("TIME_ACCOUNT");
    public static final CustomerAccountTransactionType EBAY_ACCOUNT = new CustomerAccountTransactionType("EBAY_ACCOUNT");
    
    /**
     * @param arg0
     */
    public CustomerAccountTransactionType(String arg0) {
        super(arg0);

    }
    
    public static CustomerAccountTransactionType get(String target) {
        return (CustomerAccountTransactionType) getEnum(CustomerAccountTransactionType.class, target);
    }

}
