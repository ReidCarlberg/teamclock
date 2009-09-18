/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.messages.MessageCriteriaParameters;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;

public class ListAction extends SessionContextAwareSupport {

    private Collection messages;

    public String execute() throws Exception {

        this.setMessages(MessageServiceDelegateFactory.factory.build(
                this.getSystemOwner()).find(new MessageCriteriaParameters()));
        
        return SUCCESS;

    }

    /**
     * @return Returns the messages.
     */
    public Collection getMessages() {
        return messages;
    }

    /**
     * @param messages
     *            The messages to set.
     */
    public void setMessages(Collection messages) {
        this.messages = messages;
    }

}
