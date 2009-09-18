/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.system;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionCriteriaParameters;
import com.fivesticks.time.account.AccountTransactionDAO;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;


/**
 * @author REID
 */
public class Update2005_June_AccountTransactionUpdateCommand {

    public void execute() throws Exception {

        /*
         * get the account transaction dao
         */
        GenericDAO dao = AccountTransactionDAO.factory.build();

        AccountTransactionCriteriaParameters filter = new AccountTransactionCriteriaParameters();
        
        Collection c = dao.find(filter);
        
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            AccountTransaction element = (AccountTransaction) iter.next();
            
            element.setObjectType(Customer.class.getName());
            element.setObjectId(element.getCustomerId());
            element.setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT.getName());
            
            dao.save(element);
            
        }

    }
}