/*
 * Created on Dec 20, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class SendQueuedMessagesCommandFactory {

    public static final String SPRING_BEAN_NAME = "queuedMessageSender";
    public static final SendQueuedMessagesCommandFactory factory = new SendQueuedMessagesCommandFactory();

    public SendQueuedMessagesCommand build() {
        return (SendQueuedMessagesCommand) SpringBeanBroker.getBeanFactory()
                .getBean(SendQueuedMessagesCommandFactory.SPRING_BEAN_NAME);
    }
}