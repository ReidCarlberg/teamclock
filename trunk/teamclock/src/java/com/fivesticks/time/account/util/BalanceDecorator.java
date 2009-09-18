/*
 * Created on Jun 1, 2006
 *
 */
package com.fivesticks.time.account.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionTypeEnum;

public class BalanceDecorator {

    public Double getAndDecorateBalance(Collection c) {
        
        double balance = 0.0;

        Collection sorted = new TreeSet(new AccountTransactionDateComparator());
        sorted.addAll(c);
        
        for (Iterator iter = sorted.iterator(); iter.hasNext();) {
            AccountTransaction element = (AccountTransaction) iter.next();
            if (element.getType().equals(
                    AccountTransactionTypeEnum.ADD_VALUE.getName())) {
                balance += element.getAmount().doubleValue();
            } else if (element.getType().equals(
                    AccountTransactionTypeEnum.USE_VALUE.getName())) {
                balance -= element.getAmount().doubleValue();
            } else if (element.getType().equals(
                    AccountTransactionTypeEnum.BALANCE_SET.getName())) {
                balance = element.getAmount().doubleValue();
            }
            element.setDecoratedBalance(new Double(balance));
        }
        return new Double(balance);
    }
}
