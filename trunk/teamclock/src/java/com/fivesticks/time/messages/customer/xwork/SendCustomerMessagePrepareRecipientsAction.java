/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegate;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateFactory;
import com.fivesticks.time.customer.providers.CustomerListProviderException;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;

public class SendCustomerMessagePrepareRecipientsAction extends
        SessionContextAwareSupport implements SendMessageContextAware {

    private SendMessageContext sendMessageContext;

    private String submitSend;

    private String selectRecipientsBy;

    private Collection customerIdList = new ArrayList();

    private Collection contactIdList = new ArrayList();

    private String messageId;

    private MessageServiceDelegate messageServiceDelegate;

    private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    private CustomerServiceDelegate fstxCustomerBD;
    
//    private Long customerStatusLookupId;
    

    public String execute() throws Exception {

        if (!this.isSubmitSendSet()) {

            return INPUT;
        }

        if (selectRecipientsBy == null) {
            this.getSessionContext().setMessage(
                    "Please select a recipient strategy");

            return INPUT;
        }

// 2005-11-23 RSC
//        if (this.getSelectRecipientsBy().equals("Select All Customers")) {
//            this.getSendMessageContext().setRecipients(this.getCustomers());
//        }
//        if (this.getSelectRecipientsBy().equals("Select All Contacts")) {
//            for (Iterator iter = this.getContacts().iterator(); iter.hasNext();) {
//                ContactAndCustomerVO element = (ContactAndCustomerVO) iter
//                        .next();
//                this.getSendMessageContext().getRecipients().add(
//                        element.getContact());
//            }
//        }

        /*
         * by the time we're here, we've already setup the list of recipients
         */
        if (this.getSelectRecipientsBy().equals("Select By Customer")) {
            for (Iterator iter = this.getCustomerIdList().iterator(); iter
                    .hasNext();) {
                Long element = (Long) iter.next();

                Customer customer = CustomerServiceDelegateFactory.factory.build(
                        this.getSystemOwner()).getFstxCustomer(
                        element);
                this.getSendMessageContext().getRecipients().add(customer);
            }

        }
        
        if (this.getSelectRecipientsBy().equals("Select By Contact")) {
            ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory.build(this.getSystemOwner());
            
            for (Iterator iter = this.getContactIdList().iterator(); iter
                    .hasNext();) {
                Long element = new Long((String)iter.next());
                ContactV2 contact = sd.get(element);
                this.getSendMessageContext().getRecipients().add(contact);
            }
        }

      
        return SUCCESS;

    }

    public Collection getCustomers() throws CustomerListProviderException {
         
        return this.getCustomerListProvider().getCustomers();

    }

    public Collection getContacts() throws Exception {

        ContactServiceDelegate sd = ContactServiceDelegateFactory.factory.build(this.getSystemOwner());
        
        
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



    public boolean isSubmitSendSet() {
        System.out.println("is Submit?" + this.getSubmitSend());
        return StringUtils.hasText(this.getSubmitSend());
    }

    public String getSubmitSend() {
        return submitSend;
    }

    public void setSubmitSend(String submitSend) {
        this.submitSend = submitSend;
    }

    public SendMessageContext getSendMessageContext() {
        return sendMessageContext;
    }

    public void setSendMessageContext(SendMessageContext sendMessageContext) {
        this.sendMessageContext = sendMessageContext;
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

    public Collection getContactIdList() {
        return contactIdList;
    }

    public void setContactIdList(Collection contactIdList) {
        this.contactIdList = contactIdList;
    }

    public Collection getCustomerIdList() {
        return customerIdList;
    }

    public void setCustomerIdList(Collection customerIdList) {
        this.customerIdList = customerIdList;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSelectRecipientsBy() {
        return selectRecipientsBy;
    }

    public void setSelectRecipientsBy(String selectRecipientsBy) {
        this.selectRecipientsBy = selectRecipientsBy;
    }

    /**
     * @return Returns the customersStatusCodes.
     */




}
