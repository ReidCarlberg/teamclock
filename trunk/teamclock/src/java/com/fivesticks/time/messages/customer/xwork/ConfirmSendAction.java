/*
 * Created on Nov 24, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerSettingVO;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.merge.MessageDataMerger;
import com.fivesticks.time.messages.merge.MessageMergerFactory;
import com.fivesticks.time.messages.util.ConvertMessageAndAddToQueueHelper;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.util.forms.FormDataMergerException;

public class ConfirmSendAction extends SendMessageContextAwareSupport {

    private String submitConfirm;

    private String submitCancel;

    private CustomerSettingServiceDelegate customerSettingServiceDelegate;

    private Collection undeliverable = new ArrayList();

    public String execute() throws Exception {

        if (this.submitConfirm == null) {
            return INPUT;
        }

        if (this.submitCancel != null) {
            return CANCEL;
        }

        if (this.getSendMessageContext().getRecipientCount() == 0
                || this.getSendMessageContext().getMessage() == null) {
            return INPUT;
        }

        CustomerServiceDelegate custBd = CustomerServiceDelegateFactory.factory
                .build(this.getSessionContext());

        QueuedMessageServiceDelegate queuedMessageServiceDelegate = QueuedMessageServiceDelegateFactory.factory
                .build(this.getSessionContext());

        ConvertMessageAndAddToQueueHelper queueHelper = new ConvertMessageAndAddToQueueHelper();

        for (Iterator iter = this.getSendMessageContext().getRecipients()
                .iterator(); iter.hasNext();) {
            ContactAndCustomerVO element = (ContactAndCustomerVO) iter.next();

            Message toSend = new Message();
            toSend.setOwnerKey(element.getCustomer().getOwnerKey());

            /*
             * merge customer information
             */
            MessageDataMerger messageDataMerger = MessageMergerFactory.build(
                    this.getSystemOwner(), element.getCustomer(), this
                            .getSendMessageContext().getMessage());

            toSend.setSubject(messageDataMerger.getMergedSubject());
            toSend.setMessage(messageDataMerger.getMergedData());

            /*
             * merge contact information
             */
            messageDataMerger = MessageMergerFactory.build(this
                    .getSystemOwner(), element.getContactV2(), toSend);

            toSend.setSubject(messageDataMerger.getMergedSubject());
            toSend.setMessage(messageDataMerger.getMergedData());

            boolean success = queueHelper.convertAndAdd(this.getSystemOwner(), this
                    .getSessionContext().getSettings(), toSend, element
                    .getContactV2(), queuedMessageServiceDelegate, this
                    .getSessionContext().getUser().getUsername());

            if (!success) {
                this.undeliverable.add(element);
            }
            // 2006-09-04 switched so I cand use elsewhere
            // SystemMessageBean outboundMessage = new SystemMessageBean();
            //
            // outboundMessage.setFromEmail(this.getSessionContext().getSettings()
            // .getMailFromAddress());
            // outboundMessage.setFromName(this.getSessionContext().getSettings()
            // .getMailFromName());
            //
            // outboundMessage.setModifiedByUser(this.getSessionContext().getUser()
            // .getUsername());
            //
            // outboundMessage.setOwnerKey(this.getSystemOwner().getKey());
            //            
            // outboundMessage.setSubject(toSend.getSubject());
            //            
            // outboundMessage.setMessage(toSend.getMessage());
            //
            // QueuedMessage qm = new
            // QueuedMessageBuilder().build(outboundMessage);
            //            
            // qm.setToName(element.getContactV2().getNameFirst() + " "
            // + element.getContactV2().getNameLast());
            //            
            // String email = element.getContactV2().getOrganizationEmail();
            //            
            // if (email == null && element.getContactV2().getHomeEmail() !=
            // null) {
            // email = element.getContactV2().getHomeEmail();
            // }
            // qm.setToAddress(email);
            //
            //
            // try {
            // if (StringUtils.hasText(qm.getToAddress())) {
            // queuedMessageServiceDelegate.add(qm);
            // } else {
            // this.undeliverable.add(element);
            // }
            // } catch (QueuedMessageServiceDelegateException e) {
            //
            // }

        }
        this.getSendMessageContext().resetRecipients();

        return SUCCESS;
    }

    // private void handlePlaceMessageInQueue(Contact contact,
    // FstxCustomer fstxCustomer, MessageDataMerger messageDataMerger) {
    //
    // String emailAddress;
    // if (StringUtils.hasText(contact.getPrimaryEmail())) {
    // emailAddress = contact.getPrimaryEmail();
    // } else {
    // this.undeliverable.add(fstxCustomer);
    // return;
    // }
    //
    // this.handleQueueAdd(fstxCustomer, messageDataMerger, emailAddress);
    // }
    //
    // private void handlePlaceMessageInQueue(FstxCustomer fstxCustomer,
    // MessageDataMerger messageDataMerger) {
    // CustomerSettingVO settings = this.getCustomerSettingVO(fstxCustomer);
    // String emailAddress;
    // if (settings != null
    // && StringUtils.hasText(settings
    // .getAccountBalanceNotifyEmailAddress())) {
    // emailAddress = settings.getAccountBalanceNotifyEmailAddress();
    // } else {
    // this.undeliverable.add(fstxCustomer);
    // return;
    // }
    //
    // this.handleQueueAdd(fstxCustomer, messageDataMerger, emailAddress);
    // }

    private void handleQueueAdd(Customer fstxCustomer,
            MessageDataMerger messageDataMerger, String emailAddress) {

        SystemMessageBean message = new SystemMessageBean();
        /*
         * 2005-11-27 RSC instead of the system owner information
         */
        message.setFromEmail(this.getSessionContext().getSettings()
                .getMailFromAddress());
        message.setFromName(this.getSessionContext().getSettings()
                .getMailFromName());

        message.setModifiedByUser(this.getSessionContext().getUser()
                .getUsername());

        message.setOwnerKey(this.getSystemOwner().getKey());

        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName(fstxCustomer.getName());
        qm.setToAddress(emailAddress);

        try {
            qm.setSubject(messageDataMerger.getMergedSubject());
            qm.setMessage(messageDataMerger.getMergedData());
        } catch (FormDataMergerException e1) {
            this.getSessionContext().setMessage(
                    this.getSessionContext().getMessage()
                            + " Error merging message");
            e1.printStackTrace();
            this.undeliverable.add(fstxCustomer);
            return;
        }

        try {
            QueuedMessageServiceDelegateFactory.factory.build(
                    this.getSystemOwner().getKey()).addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            this.getSessionContext().setMessage(
                    this.getSessionContext().getMessage()
                            + " Error Adding message to queue.");
            this.undeliverable.add(fstxCustomer);
            return;
        }

    }

    private CustomerSettingVO getCustomerSettingVO(Customer fstxCustomer) {
        try {
            return this.getCustomerSettingServiceDelegate().find(fstxCustomer);
        } catch (ObjectMetricServiceDelegateException e) {

            e.printStackTrace();
        } catch (ObjectMetricNotFoundException e) {

            e.printStackTrace();
        }

        return null;
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

    public Collection getUndeliverable() {
        return undeliverable;
    }

    public void setUndeliverable(Collection undeliverable) {
        this.undeliverable = undeliverable;
    }

    /**
     * @return Returns the submitCancel.
     */
    public String getSubmitCancel() {
        return submitCancel;
    }

    /**
     * @param submitCancel
     *            The submitCancel to set.
     */
    public void setSubmitCancel(String submitCancel) {
        this.submitCancel = submitCancel;
    }

    /**
     * @return Returns the submitConfirm.
     */
    public String getSubmitConfirm() {
        return submitConfirm;
    }

    /**
     * @param submitConfirm
     *            The submitConfirm to set.
     */
    public void setSubmitConfirm(String submitConfirm) {
        this.submitConfirm = submitConfirm;
    }
}
