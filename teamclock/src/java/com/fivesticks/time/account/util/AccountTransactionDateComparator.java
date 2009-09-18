/*
 * Created on Jun 1, 2006
 *
 */
package com.fivesticks.time.account.util;

import java.util.Comparator;

import com.fivesticks.time.account.AccountTransaction;

public class AccountTransactionDateComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        
        if (!(o1 instanceof AccountTransaction) || !(o2 instanceof AccountTransaction)) {
            return 0;
        }
        
        AccountTransaction a1 = (AccountTransaction) o1;
        AccountTransaction a2 = (AccountTransaction) o2;
        
        int ret = 0;
        
        ret = a1.getDate().compareTo(a2.getDate());
        
        if (ret == 0) {
            ret = a1.getId().compareTo(a2.getId());
        }
        
        //want it descending, so reverse whatever it is.
        return ret;
    }

}
