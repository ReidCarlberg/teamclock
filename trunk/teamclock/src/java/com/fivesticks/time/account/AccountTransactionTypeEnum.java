/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.enums.Enum;







/**
 * @author Reid
 */
public class AccountTransactionTypeEnum extends Enum {

    public static final AccountTransactionTypeEnum ADD_VALUE = new AccountTransactionTypeEnum(
            "ADD_VALUE");

    public static final AccountTransactionTypeEnum USE_VALUE = new AccountTransactionTypeEnum(
            "USE_VALUE");

    public static final AccountTransactionTypeEnum BALANCE_SET = new AccountTransactionTypeEnum(
            "BALANCE_SET");

    private static Collection allTypes;

    /**
     * @param arg0
     */
    protected AccountTransactionTypeEnum(String arg0) {
        super(arg0);
    }

    public static Collection getAllTypes() {
        if (allTypes == null) {
            allTypes = new ArrayList();
            allTypes.add(AccountTransactionTypeEnum.ADD_VALUE);
            allTypes.add(AccountTransactionTypeEnum.USE_VALUE);
            allTypes.add(AccountTransactionTypeEnum.BALANCE_SET);
        }
        return allTypes;
    }
}