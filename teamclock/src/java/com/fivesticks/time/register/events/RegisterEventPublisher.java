/*
 * Created on Jan 15, 2005 by Reid
 */
package com.fivesticks.time.register.events;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.register.xwork.OtherInterestAction;
import com.fivesticks.time.system.messages.ActivationMessage;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.system.messages.SystemMessageException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class RegisterEventPublisher {

    public void publishActivateEvent(SystemOwner systemOwner)
            throws RegisterEventPublisherException {


        SystemMessageBean activate = SpringBeanBroker
                .getCommonMessage("activationFollowUp");

        QueuedMessage qm = new QueuedMessageBuilder().build(activate);
        qm.setToAddress(systemOwner.getContactEmail());
        qm.setToName(systemOwner.getContactName());
        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",
                systemOwner.getKey()));


        this.addToQueue(qm);
    }

    public void publishRegisterEvent(SystemOwner systemOwner, User user, String password)
            throws RegisterEventPublisherException {
        try {
            ActivationMessage m = new ActivationMessage();

            m.sendActivationMessage(systemOwner, user, password);
            m.notifyFiveSticks(systemOwner);

        } catch (SystemMessageException e) {
            throw new RegisterEventPublisherException(e);
        }

    }

    public void publishOtherInterestsEvent(OtherInterestAction action)
            throws RegisterEventPublisherException {

        StringBuffer message = new StringBuffer();
        message.append("Other interests: \n\n");
        message.append("Org Name: " + action.getName() + "\n");
        message.append("Contact Name: " + action.getContactName() + "\n");
        message.append("Email: " + action.getContactEmail() + "\n\n");
        message.append("Interested in email? " + action.isInterestedInEmail()
                + "\n");
        message.append("Interested in updates? "
                + action.isInterestedInUpdates() + "\n");
        message.append("Interested in automation? "
                + action.isInterestedInAutomation() + "\n\n");
        message.append("Comments:\n" + action.getComments() + "\n\n");

        SystemMessageBean activate = SpringBeanBroker
                .getCommonMessage("otherInterests");

        QueuedMessage qm = new QueuedMessageBuilder().build(activate);
        qm.setFromAddress(action.getContactEmail());
        qm.setFromName(action.getContactName());
        qm.setToAddress("rsc1@fivesticks.com");
        qm.setToName("Reid Carlberg");
        qm
                .setMessage(qm.getMessage().replaceAll("\\{0\\}",
                        message.toString()));

        this.addToQueue(qm);
    }

    private void addToQueue(QueuedMessage qm)
            throws RegisterEventPublisherException {
        try {
            QueuedMessageServiceDelegateFactory.factory.build(qm.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new RegisterEventPublisherException(e);
        }
    }
}