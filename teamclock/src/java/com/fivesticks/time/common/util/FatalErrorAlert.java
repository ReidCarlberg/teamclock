/*
 * Created on Oct 27, 2003
 *
 */
package com.fivesticks.time.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;

/**
 * send in an email,
 * 
 * @author Reid
 * 
 */
public class FatalErrorAlert {

     private static final String SPRING_MESSAGE_NAME = "systemError";
    //
    // private static final String TEXT = "Error Message:";
    //
    // private static final String TEXT_SIGN = "\n\nThank you! ";

    public void sendFatalErrorAlert(String sessionId, Throwable t,
            SessionContext sessionContext) {

        StringBuffer text = new StringBuffer();

        text.append("Menumine Error: \n\n");

        if (sessionContext == null) {
            text.append("User: Session context is null!");
        } else if (sessionContext.getUser() == null) {
            text.append("User: Session context exists but user is null!");
        } else {
            text.append("User: " + sessionContext.getUser().getEmail()
                    + ", Username: " + sessionContext.getUser().getUsername()
                    + "\n\n");
        }
        text.append("Timestamp: " + new Date() + "\n\n");

        text.append("Session Id: " + sessionId + "\n\n");

        text.append("Throwable: " + t.toString() + "\n\n");

        StringWriter sw = new StringWriter();

        StringBuffer sb = new StringBuffer();

        if (t != null) {
            t.printStackTrace(new PrintWriter(sw));

            text.append("Fullstack trace: " + sw.toString() + "\n\n");
        } else {
            text.append("Throwable is coming back null.");
        }

        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage("systemError");

        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName("Reid Carlberg");
        qm.setToAddress("rsc1@fivesticks.com");

        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",text.toString()));
        
        try {
            QueuedMessageServiceDelegateFactory.factory.build(
                    message.getOwnerKey()).addSystemMessage(qm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected static Log log = LogFactory.getLog(FatalErrorAlert.class
            .getName());

}