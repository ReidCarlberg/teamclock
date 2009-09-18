/*
 * Created on Dec 21, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 */
public class SendQueuedMessagesCommandProxy {

    private static Log log = LogFactory
            .getLog(SendQueuedMessagesCommandProxy.class);

    public void execute() {
        SendQueuedMessagesCommand command = (SendQueuedMessagesCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        SendQueuedMessagesCommandFactory.SPRING_BEAN_NAME);
        try {
            command.execute();
        } catch (SendQueuedMessagesCommandException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public Collection getUnsentMessages() {
        SendQueuedMessagesCommand command = (SendQueuedMessagesCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        SendQueuedMessagesCommandFactory.SPRING_BEAN_NAME);
        try {
            return command.getMessagesToSend();
        } catch (SendQueuedMessagesCommandException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}