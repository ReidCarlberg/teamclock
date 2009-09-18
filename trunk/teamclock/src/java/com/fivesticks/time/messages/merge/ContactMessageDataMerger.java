/*
 * Created on Jun 6, 2005
 *
 */
package com.fivesticks.time.messages.merge;

import java.util.Collection;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.util.forms.FormDataMergerException;

/**
 * @author Reid
 * 
 */
public class ContactMessageDataMerger implements MessageDataMerger {

    
    public static String CONTACT_NAME_FIRST = "<contact:nameFirst />";
    
    public static String CONTACT_NAME_LAST = "<contact:nameLast />";

    private SystemOwner systemOwner;

    private ContactV2 contactV2;

    private Double balance;

    private Collection transactions;

    private Message message;

    public ContactMessageDataMerger(SystemOwner systemOwner,
            ContactV2 contact, Message message) {
        this.systemOwner = systemOwner;
        this.message = message;
        this.contactV2 = contact;


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
            ret = ret.replaceAll(CONTACT_NAME_FIRST, this.getContactV2().getNameFirst());
            ret = ret.replaceAll(CONTACT_NAME_LAST, this.getContactV2().getNameLast());

        } catch (RuntimeException e) {
            throw new FormDataMergerException("Error merging message."
                    + e.getMessage());

        }
        return ret;
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

    /**
     * @return Returns the contact.
     */
    public ContactV2 getContactV2() {
        return contactV2;
    }

    /**
     * @param contact The contact to set.
     */
    public void setContactV2(ContactV2 contact) {
        this.contactV2 = contact;
    }

    public Message getMergedMessage() throws FormDataMergerException {
        
        Message ret = new Message();
        
        ret.setMessage(this.getMergedData());
        ret.setSubject(this.getMergedSubject());
        ret.setOwnerKey(this.getSystemOwner().getKey());
        
        return ret;
    }
}
