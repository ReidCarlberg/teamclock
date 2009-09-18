/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.providers.CustomerListProviderException;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageCriteriaParameters;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateException;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;

public class SendCustomerMessagePreviewAction extends SessionContextAwareSupport
        implements SendMessageContextAware {

    private SendMessageContext sendMessageContext;

    private Long messageId;

    private MessageServiceDelegate messageServiceDelegate;

    private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    private CustomerServiceDelegate fstxCustomerBD;

    public String execute() throws Exception {

        System.out.println("Recieved Request");
        
        Message message = this.getMessage();
        if (message == null) {

            return INPUT;
        }

        return SUCCESS;

    }

    public Collection getCustomers() throws CustomerListProviderException {
        return this.getCustomerListProvider().getCustomers();

    }

    public Collection getContacts() throws Exception {

        Collection contacts = new ArrayList();
        for (Iterator iter = this.getCustomers().iterator(); iter.hasNext();) {
            Customer customer = (Customer) iter.next();
            Collection temp = ContactV2ServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).getByCustomer(customer);

            for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
                ContactV2 contact = (ContactV2) iterator.next();
                ContactAndCustomerVO vo = new ContactAndCustomerVO(contact,
                        customer);
                contacts.add(vo);
            }
        }

        return contacts;

    }

    public Collection getMessages() throws MessageServiceDelegateException {
        return this.getMessageServiceDelegate().find(
                new MessageCriteriaParameters());

    }

//    private FstxCustomer getCustomer(Contact contact)
//            throws FstxCustomerBDException {
//        return this.getFstxCustomerBD().getFstxCustomer(contact.getCustId());
//
//    }
//
//    private CustomerSettingVO getCustomerSettingVO(FstxCustomer fstxCustomer) {
//        try {
//            return this.getCustomerSettingServiceDelegate().find(fstxCustomer);
//        } catch (ObjectMetricServiceDelegateException e) {
//
//            e.printStackTrace();
//        } catch (ObjectMetricNotFoundException e) {
//
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public Message getMessage() {
        try {
            return this.getMessageServiceDelegate().get(this.getMessageId());
        } catch (MessageServiceDelegateException e) {
            this.getSessionContext().setMessage(
                    this.getSessionContext().getMessage()
                            + " Error finding the message requested.");
            e.printStackTrace();
        }

        return null;

    }

    public SendMessageContext getSendMessageContext() {
        return sendMessageContext;
    }

    public void setSendMessageContext(SendMessageContext sendMessageContext) {
        this.sendMessageContext = sendMessageContext;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public MessageServiceDelegate getMessageServiceDelegate() {
        if (this.messageServiceDelegate == null) {
            messageServiceDelegate = MessageServiceDelegateFactory.factory.build(this
                    .getSystemOwner());
        }

        return messageServiceDelegate;
    }

    public void setMessageServiceDelegate(
            MessageServiceDelegate messageServiceDelegate) {
        this.messageServiceDelegate = messageServiceDelegate;
    }

    public CustomerServiceDelegate getFstxCustomerBD() {
        if (fstxCustomerBD == null) {
            fstxCustomerBD = CustomerServiceDelegateFactory.factory
                    .build(this.getSystemOwner());

        }
        return fstxCustomerBD;
    }

    public void setFstxCustomerBD(CustomerServiceDelegate fstxCustomerBD) {
        this.fstxCustomerBD = fstxCustomerBD;
    }

    public CustomerSettingServiceDelegate getCustomerSettingServiceDelegate() {
        if (this.customerSettingServiceDelegate == null) {
            this.customerSettingServiceDelegate = CustomerSettingServiceDelegateFactory.factory
                    .build(this.getSystemOwner());

        }
        return customerSettingServiceDelegate;
    }

    public void setCustomerSettingServiceDelegate(
            CustomerSettingServiceDelegate customerSettingServiceDelegate) {
        this.customerSettingServiceDelegate = customerSettingServiceDelegate;
    }

}




