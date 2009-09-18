/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.systemowner.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageCriteriaParameters;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateException;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;
import com.fivesticks.time.messages.customer.xwork.SendMessageContext;
import com.fivesticks.time.messages.customer.xwork.SendMessageContextAware;
import com.fivesticks.time.messages.merge.MessageDataMerger;
import com.fivesticks.time.messages.merge.MessageMergerFactory;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.util.forms.FormDataMergerException;

public class SendSystemOwnerMessageAction extends SessionContextAwareSupport
        implements SendMessageContextAware {

    private SendMessageContext sendMessageContext;

    private String submitSend;

    private Long messageId;

    private MessageServiceDelegate messageServiceDelegate;

    private SystemOwnerServiceDelegate systemOwnerServiceDelegate;

    // private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    private Collection undeliverable = new ArrayList();

    // private FstxCustomerBD fstxCustomerBD;

    public String execute() throws Exception {

        if (!this.isSubmitSendSet()) {
            
            return INPUT;
        }

        if (this.getSendMessageContext().getRecipients() == null
                || this.getSendMessageContext().getRecipients().size() == 0) {
             this.addActionMessage("Please select a recipients.");
            return INPUT;
        }

        if (this.getMessageId() == null) {
            this.addActionMessage("Please select a message.");
            return INPUT;
        }
        Message message = this.getMessage();
        if (message == null) {

            return INPUT;
        }

        for (Iterator iter = this.getSendMessageContext().getRecipients()
                .iterator(); iter.hasNext();) {
            Object element = iter.next();

            if (element instanceof SystemOwner) {
                SystemOwner systemOwnerRecip = (SystemOwner) element;

                MessageDataMerger messageDataMerger = MessageMergerFactory
                        .build(this.getSystemOwner(), systemOwnerRecip, message);

                this.handlePlaceMessageInQueue(systemOwnerRecip,
                        messageDataMerger);
            }

        }
        this.getSendMessageContext().resetRecipients();
        return SUCCESS;

    }

    public Collection getSystemOwners()
            throws SystemOwnerServiceDelegateException {
        return this.getSystemOwnerServiceDelegate().findAll();
    }

    public SystemOwnerServiceDelegate getSystemOwnerServiceDelegate() {
        if (this.systemOwnerServiceDelegate == null) {
            this.systemOwnerServiceDelegate = SystemOwnerServiceDelegateFactory.factory
                    .build();
        }
        return systemOwnerServiceDelegate;
    }

    public void setSystemOwnerServiceDelegate(
            SystemOwnerServiceDelegate systemOwnerServiceDelegate) {
        this.systemOwnerServiceDelegate = systemOwnerServiceDelegate;
    }

    // public Collection getCustomers() throws CustomerListProviderException {
    // return this.getCustomerListProvider().getCustomers();
    //
    // }
    //
    // public Collection getContacts() throws Exception {
    //
    // Collection contacts = new ArrayList();
    // for (Iterator iter = this.getCustomers().iterator(); iter.hasNext();) {
    // FstxCustomer customer = (FstxCustomer) iter.next();
    // Collection temp = ContactServiceDelegate.factory.build(
    // this.getSystemOwner()).getByCustomer(customer);
    //
    // for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
    // Contact contact = (Contact) iterator.next();
    // ContactAndCustomerVO vo = new ContactAndCustomerVO(contact,
    // customer);
    // contacts.add(vo);
    // }
    // }
    //
    // return contacts;
    //
    // }

    public Collection getMessages() throws MessageServiceDelegateException {
        return this.getMessageServiceDelegate().find(
                new MessageCriteriaParameters());

    }

    // private FstxCustomer getCustomer(Contact contact)
    // throws FstxCustomerBDException {
    // return this.getFstxCustomerBD().getFstxCustomer(contact.getCustId());
    //
    // }

    private void handlePlaceMessageInQueue(SystemOwner systemOwnerRecip,
            MessageDataMerger messageDataMerger) {
        String emailAddress;
        if (systemOwnerRecip != null
                && StringUtils.hasText(systemOwnerRecip.getContactEmail())) {
            emailAddress = systemOwnerRecip.getContactEmail();
        } else {
            this.undeliverable.add(systemOwnerRecip);
            return;
        }

        this.handleQueueAdd(systemOwnerRecip, messageDataMerger, emailAddress);
    }

    private void handleQueueAdd(SystemOwner systemOwnerRecip,
            MessageDataMerger messageDataMerger, String emailAddress) {

        SystemMessageBean message = new SystemMessageBean();
        message.setFromEmail(this.getSystemOwner().getContactEmail());
        message.setFromName(this.getSystemOwner().getContactName());
        message.setModifiedByUser(this.getSessionContext().getUser()
                .getUsername());
        message.setOwnerKey(this.getSystemOwner().getKey());

        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName(systemOwnerRecip.getContactName());
        qm.setToAddress(emailAddress);

        try {
            qm.setSubject(messageDataMerger.getMergedSubject());
            qm.setMessage(messageDataMerger.getMergedData());
        } catch (FormDataMergerException e1) {
            this.getSessionContext().setMessage(
                    this.getSessionContext().getMessage()
                            + " Error merging message");
            e1.printStackTrace();
            this.undeliverable.add(systemOwnerRecip);
            return;
        }

        try {
            QueuedMessageServiceDelegateFactory.factory.build(
                    this.getSystemOwner().getKey()).addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            this.getSessionContext().setMessage(
                    this.getSessionContext().getMessage()
                            + " Error Adding message to queue.");
            this.undeliverable.add(systemOwnerRecip);
            return;
        }

    }

    // private CustomerSettingVO getCustomerSettingVO(FstxCustomer fstxCustomer)
    // {
    // try {
    // return this.getCustomerSettingServiceDelegate().find(fstxCustomer);
    // } catch (ObjectMetricServiceDelegateException e) {
    //
    // e.printStackTrace();
    // } catch (ObjectMetricNotFoundException e) {
    //
    // e.printStackTrace();
    // }
    //
    // return null;
    // }

    private Message getMessage() {
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

    public boolean isSubmitSendSet() {
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

    public Collection getUndeliverable() {
        return undeliverable;
    }

    public void setUndeliverable(Collection undeliverable) {
        this.undeliverable = undeliverable;
    }

    // public FstxCustomerBD getFstxCustomerBD() {
    // if (fstxCustomerBD == null) {
    // fstxCustomerBD = FstxCustomerBD.factory
    // .build(this.getSystemOwner());
    //
    // }
    // return fstxCustomerBD;
    // }
    //
    // public void setFstxCustomerBD(FstxCustomerBD fstxCustomerBD) {
    // this.fstxCustomerBD = fstxCustomerBD;
    // }
    //
    // public CustomerSettingServiceDelegate getCustomerSettingServiceDelegate()
    // {
    // if (this.customerSettingServiceDelegate == null) {
    // this.customerSettingServiceDelegate =
    // CustomerSettingServiceDelegate.factory
    // .build(this.getSystemOwner());
    //
    // }
    // return customerSettingServiceDelegate;
    // }
    //
    // public void setCustomerSettingServiceDelegate(
    // CustomerSettingServiceDelegate customerSettingServiceDelegate) {
    // this.customerSettingServiceDelegate = customerSettingServiceDelegate;
    // }

}
