/*
 * Created on Nov 24, 2005
 *
 */
package com.fivesticks.time.messages.customer.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public class SendMessageContextAwareSupport extends SessionContextAwareSupport
        implements SendMessageContextAware {

    private SendMessageContext sendMessageContext;
    
    public void setSendMessageContext(SendMessageContext sendMessageContext) {
        this.sendMessageContext = sendMessageContext;
    }

    /**
     * @return Returns the sendMessageContext.
     */
    public SendMessageContext getSendMessageContext() {
        return sendMessageContext;
    }

}
