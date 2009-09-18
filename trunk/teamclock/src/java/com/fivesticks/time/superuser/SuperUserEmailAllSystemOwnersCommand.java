/*
 * Created on Oct 12, 2005
 *
 * 
 */
package com.fivesticks.time.superuser;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.system.messages.SystemMessageException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

public class SuperUserEmailAllSystemOwnersCommand {

    private String message;

    private String subject;

    private SystemOwner superUser;

    private static final String SPRING_MESSAGE_NAME = "generalSuperUserMessage";

    public void execute() throws Exception {
        for (Iterator iter = this.getSystemOwners().iterator(); iter.hasNext();) {
            SystemOwner element = (SystemOwner) iter.next();
            this.sendEmailMessage(element);
        }

    }

    private void sendEmailMessage(SystemOwner systemOwner)
            throws SystemMessageException {

        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME);

        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setOwnerKey(superUser.getKey());
        qm.setToName(systemOwner.getContactName());
        qm.setToAddress(systemOwner.getContactEmail());
        qm.setSubject(this.getSubject());
        qm.setMessage(this.getMessage());

        try {
            QueuedMessageServiceDelegateFactory.factory.build(
                    this.getSuperUser().getKey()).addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection getSystemOwners()
            throws SystemOwnerServiceDelegateException {
        return SystemOwnerServiceDelegateFactory.factory.build().findAll();

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public SystemOwner getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SystemOwner superUser) {
        this.superUser = superUser;
    }

}
