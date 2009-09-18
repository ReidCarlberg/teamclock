/*
 * Created on Jun 6, 2005
 *
 */
package com.fivesticks.time.messages.merge;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionCriteriaParameters;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.common.util.FstxSorter;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.util.forms.FormDataMergerException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class CustomerMessageDataMerger implements MessageDataMerger {

    public static String CUSTOMER_NAME_TAG = "<customer:name />";

    public static String CUSTOMER_BALANCE_TAG = "<customer:balance />";

    public static String CUSTOMER_TRANSACTIONS_TAG = "<customer:transactions />";

    private SystemOwner systemOwner;

    private Customer customer;

    private Double balance;

    private Collection transactions;

    private Message message;

    public CustomerMessageDataMerger(SystemOwner systemOwner,
            Customer customer, Message message) {
        this.systemOwner = systemOwner;
        this.message = message;
        this.customer = customer;

        AccountTransactionServiceDelegate atsd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwner, CustomerAccountTransactionType.TIME_ACCOUNT);

        try {
            this.balance = atsd.getCurrentBalance(this.customer);
        } catch (AccountTransactionException e) {
            this.balance = new Double(0.0);
            e.printStackTrace();
        }
        AccountTransactionCriteriaParameters param = new AccountTransactionCriteriaParameters();
        param.setObjectId(customer.getId());
        SimpleDate start = SimpleDate.factory.build();
        start.advanceMonth(-1);
        param.setDateRangeStart(start);
        try {
            this.transactions = atsd.findAll(param);
        } catch (AccountTransactionException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.util.FormDataMerger#getMergedData()
     */
    public String getMergedData() throws FormDataMergerException {

        String ret = this.message.getMessage();

        return this.merge(ret);
    }

    public String getMergedSubject() throws FormDataMergerException {

        String ret = this.message.getSubject();

        return this.merge(ret);

    }

    private String merge(String ret) throws FormDataMergerException {
        

        try {
            ret = ret.replaceAll(CUSTOMER_BALANCE_TAG, this.getBalance()
                    .toString());
            ret = ret.replaceAll(CUSTOMER_NAME_TAG, this.getCustomer()
                    .getName());
            ret = ret.replaceAll(CUSTOMER_TRANSACTIONS_TAG, this
                    .getTransactionList());
//            System.out.println(CUSTOMER_TRANSACTIONS_TAG);
        } catch (RuntimeException e) {
            throw new FormDataMergerException("Error merging message."
                    + e.getMessage());

        }
        return ret;
    }
    private static int MAX_TRANSACTIONS = 10;
    
    private String getTransactionList() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n").append("\n");
        TreeSet sorted = new TreeSet(new FstxSorter().getDescendingAllUnique("date", "id"));
        
        sorted.addAll(this.getTransactions());
        int i = 0;
        for (Iterator iter = sorted.iterator(); iter.hasNext() && i < MAX_TRANSACTIONS;i++) {
            AccountTransaction element = (AccountTransaction) iter.next();

            buffer.append(element.getType());
            buffer.append(element.getType());
            buffer.append(element.getDate());
            buffer.append(element.getAmount());
            buffer.append(element.getComments());
            buffer.append("\n");
        }
        buffer.append("\n").append("\n");
        return buffer.toString();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Collection getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection transactions) {
        this.transactions = transactions;
    }
    
    public Message getMergedMessage() throws FormDataMergerException {
        
        Message ret = new Message();
        
        ret.setMessage(this.getMergedData());
        ret.setSubject(this.getMergedSubject());
        ret.setOwnerKey(this.getSystemOwner().getKey());
        
        return ret;
    }
}
