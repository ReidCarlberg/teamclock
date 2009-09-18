/*
 * Created on Oct 17, 2005
 *
 * 
 */
package com.fivesticks.time.messages.merge;

import java.util.Date;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class CustomerMessageDataMergerTest extends AbstractTimeTestCase {

    public void testCustomerMessageDataMerger() throws Exception,
            AccountTransactionException {
        AccountTransactionServiceDelegate atsd = AccountTransactionServiceDelegateFactory.factory
                .build(this.systemOwner,
                        CustomerAccountTransactionType.TIME_ACCOUNT);

        AccountTransaction accountTransaction = new AccountTransaction();

        accountTransaction.setAmount(new Double(100));
        accountTransaction.setComments("Comments");
        accountTransaction.setDate(new Date());
        accountTransaction
                .setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT
                        .toString());
        accountTransaction.setObjectType(Customer.class.getName());
        
        accountTransaction.setCustomerId(this.customer.getId());
         accountTransaction.setObjectId(this.customer.getId());
         accountTransaction.setType("ADD_VALUE");
         accountTransaction.setEnteredBy("ME");
        atsd.save(accountTransaction);

        accountTransaction = new AccountTransaction();
        accountTransaction.setAmount(new Double(200));
        accountTransaction.setComments("Comments2");
        accountTransaction.setDate(new Date());
        accountTransaction
                .setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT
                        .toString());
        accountTransaction.setObjectType(Customer.class.getName());
        accountTransaction.setCustomerId(this.customer.getId());
        accountTransaction.setObjectId(this.customer.getId());
         accountTransaction.setType("ADD_VALUE");
            accountTransaction.setEnteredBy("ME");
        atsd.save(accountTransaction);

        accountTransaction = new AccountTransaction();
        accountTransaction.setAmount(new Double(300));
        accountTransaction.setComments("Comments3");
        accountTransaction.setDate(new Date());
        accountTransaction
                .setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT
                        .toString());
        accountTransaction.setObjectType(Customer.class.getName());
         accountTransaction.setCustomerId(this.customer.getId());
         accountTransaction.setObjectId(this.customer.getId());
          accountTransaction.setType("ADD_VALUE");
             accountTransaction.setEnteredBy("ME");
        atsd.save(accountTransaction);

        Message message = new Message();
        message.setMessage("This is a message! "
                + CustomerMessageDataMerger.CUSTOMER_NAME_TAG + "Balance:"
                + CustomerMessageDataMerger.CUSTOMER_BALANCE_TAG
                + "Transaction"
                + CustomerMessageDataMerger.CUSTOMER_TRANSACTIONS_TAG);
        message.setSubject("Hello "
                + CustomerMessageDataMerger.CUSTOMER_NAME_TAG);

        CustomerMessageDataMerger merger = new CustomerMessageDataMerger(
                this.systemOwner, this.customer, message);

        String messageString = merger.getMergedData();
        String subject = merger.getMergedSubject();

        System.out.println(messageString);
        System.out.println(subject);
    }

}
