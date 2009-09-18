/*
 * Created on Nov 17, 2004 by Reid
 */
package com.fivesticks.time.account;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class AccountTransactionDeleteCommandBuilder {

    public static final AccountTransactionDeleteCommandBuilder singleton = new AccountTransactionDeleteCommandBuilder();

    protected AccountTransactionDeleteCommandBuilder() {

    }

    public synchronized AccountTransactionDeleteCommand build(
            SystemOwner systemOwner, AccountTransaction target) {

        if (target == null || systemOwner == null
                || !systemOwner.getKey().equals(target.getOwnerKey())) {
            throw new RuntimeException("doesn't match.");
        }

        AccountTransactionDeleteCommand ret = new AccountTransactionDeleteCommand();

        ret.setTarget(target);

        return ret;

    }
}