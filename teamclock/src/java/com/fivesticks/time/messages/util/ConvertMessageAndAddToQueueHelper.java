/*
 * Created on Sep 4, 2006
 *
 */
package com.fivesticks.time.messages.util;

import org.springframework.util.StringUtils;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.systemowner.SystemOwner;

public class ConvertMessageAndAddToQueueHelper {

    public boolean convertAndAdd(SystemOwner systemOwner,
            FstxTimeSettings settings, Message toSend, ContactV2 contactV2,
            QueuedMessageServiceDelegate queuedMessageServiceDelegate,
            String sendingUserName) {

        String toName = contactV2.getNameFirst() + " " + contactV2.getNameLast();
        
        String email = contactV2.getOrganizationEmail();

        if (email == null && contactV2.getHomeEmail() != null) {
            email = contactV2.getHomeEmail();
        }

        return convertAndAdd(systemOwner, settings, toSend,
                queuedMessageServiceDelegate, sendingUserName, email, toName);

    }

    public boolean convertAndAdd(SystemOwner systemOwner,
            FstxTimeSettings settings, Message toSend,
            QueuedMessageServiceDelegate queuedMessageServiceDelegate,
            String sendingUserName, String toEmailAddress, String toName) {

        boolean ret = false;

        SystemMessageBean outboundMessage = new SystemMessageBean();

        outboundMessage.setFromEmail(settings.getMailFromAddress());
        outboundMessage.setFromName(settings.getMailFromName());

        outboundMessage.setModifiedByUser(sendingUserName);

        outboundMessage.setOwnerKey(systemOwner.getKey());

        outboundMessage.setSubject(toSend.getSubject());

        outboundMessage.setMessage(toSend.getMessage());

        QueuedMessage qm = new QueuedMessageBuilder().build(outboundMessage);

        qm.setToName(toName);

        qm.setToAddress(toEmailAddress);

        try {
            if (StringUtils.hasText(qm.getToAddress())) {
                queuedMessageServiceDelegate.add(qm);
                ret = true;
            }
        } catch (QueuedMessageServiceDelegateException e) {
            // no really going anythign
        }

        return ret;
    }

}
