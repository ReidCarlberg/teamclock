/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.messages.Message;

public class SendMessageContext {

    private Collection recipients = new ArrayList();

    private Message message;
    
    public Collection getRecipients() {
        return recipients;
    }

    public void setRecipients(Collection recipients) {
        this.recipients = recipients;
    }

    public void resetRecipients() {
        recipients = new ArrayList();
    }

    /**
     * @return Returns the message.
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    public int getRecipientCount() {
        return this.recipients.size();
    }
}
