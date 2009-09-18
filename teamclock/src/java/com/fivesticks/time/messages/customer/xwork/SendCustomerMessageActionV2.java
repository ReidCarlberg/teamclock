/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupServiceDelegateException;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageCriteriaParameters;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateException;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;

public class SendCustomerMessageActionV2 extends SendMessageContextAwareSupport {

    private String submitSend;

    private String updateList;

    private Long messageId;

    private MessageServiceDelegate messageServiceDelegate;

    private Long customerStatusLookupId;

    private CustomerServiceDelegate fstxCustomerBD;

    public String execute() throws Exception {

        if (this.getSubmitSend() == null && this.getUpdateList() == null) {
            return INPUT;
        } else if (this.getSubmitSend() == null) {
            // basically we're doing all contacts for selected customers
            this.getSendMessageContext().setRecipients(
                    new CustomerAndContactCollectionBuilder()
                            .build(this.getSystemOwner(), this
                                    .getCustomerStatusLookupId()));
            return INPUT;
        }

        if (this.getSendMessageContext().getRecipients() == null
                || this.getSendMessageContext().getRecipients().size() == 0) {
            this.addActionMessage("Please select recipients.");
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

        this.getSendMessageContext().setMessage(message);

        return SUCCESS;

    }


    public Collection getMessages() throws MessageServiceDelegateException {
        return this.getMessageServiceDelegate().find(
                new MessageCriteriaParameters());

    }

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

//    public boolean isSubmitSendSet() {
//        return StringUtils.hasText(this.getSubmitSend());
//    }

    public String getSubmitSend() {
        return submitSend;
    }

    public void setSubmitSend(String submitSend) {
        this.submitSend = submitSend;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public MessageServiceDelegate getMessageServiceDelegate() {
        if (this.messageServiceDelegate == null) {
            messageServiceDelegate = MessageServiceDelegateFactory.factory
                    .build(this.getSystemOwner());
        }

        return messageServiceDelegate;
    }

    public void setMessageServiceDelegate(
            MessageServiceDelegate messageServiceDelegate) {
        this.messageServiceDelegate = messageServiceDelegate;
    }

    public CustomerServiceDelegate getFstxCustomerBD() {
        if (fstxCustomerBD == null) {
            fstxCustomerBD = CustomerServiceDelegateFactory.factory.build(this
                    .getSystemOwner());

        }
        return fstxCustomerBD;
    }

    public void setFstxCustomerBD(CustomerServiceDelegate fstxCustomerBD) {
        this.fstxCustomerBD = fstxCustomerBD;
    }

    public Collection getCustomerStatusCodes() {
        try {
            return LookupServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).getAll(LookupType.CUSTOMER_STATUS);
        } catch (LookupServiceDelegateException e) {
            // e.printStackTrace();
        }
        return null;
    }

    /**
     * @return Returns the customerStatusLookupId.
     */
    public Long getCustomerStatusLookupId() {
        return customerStatusLookupId;
    }

    /**
     * @param customerStatusLookupId
     *            The customerStatusLookupId to set.
     */
    public void setCustomerStatusLookupId(Long customerStatusLookupId) {
        this.customerStatusLookupId = customerStatusLookupId;
    }

    /**
     * @return Returns the updateList.
     */
    public String getUpdateList() {
        return updateList;
    }

    /**
     * @param updateList
     *            The updateList to set.
     */
    public void setUpdateList(String updateList) {
        this.updateList = updateList;
    }

}
