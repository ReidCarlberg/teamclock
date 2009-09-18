/*
 * Created on Oct 27, 2003
 *
 */
package com.fivesticks.time.system.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * send in an email, find the user associated with it, then send that user their
 * password.
 * 
 * @author Reid
 *  
 */
public class NotifyUserOfCustomerAssociation {

    private static final String SPRING_MESSAGE_NAME = "newCustomerAssociation";

    public void sendCustomerAssociationNotification(SystemOwner systemOwner,
            User user, Customer fstxCustomer) throws SystemMessageException {
        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME);

        QueuedMessage qm = new QueuedMessageBuilder().build(message);

        qm.setToName(user.getEmail());
        qm.setToAddress(user.getEmail());

        /*
         * customer name
         */
        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",
                fstxCustomer.getName()));

        /*
         * username
         */
        qm
                .setMessage(qm.getMessage().replaceAll("\\{1\\}",
                        user.getUsername()));

        /*
         * password
         */
        qm
                .setMessage(qm.getMessage().replaceAll("\\{2\\}",
                        user.getPassword()));

        /*
         * login
         */
        qm.setMessage(qm.getMessage().replaceAll("\\{3\\}",
                systemOwner.getKey()));

        try {
            QueuedMessageServiceDelegateFactory.factory.build(message.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    protected static Log log = LogFactory
            .getLog(NotifyUserOfCustomerAssociation.class.getName());

}