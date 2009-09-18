/*
 * Created on Jan 16, 2005 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.util.Date;

import com.fivesticks.time.system.messages.SystemMessageBean;

/**
 * @author REID
 */
public class QueuedMessageBuilder {

    public QueuedMessage build(SystemMessageBean systemMessageBean) {

        QueuedMessage qm = new QueuedMessage();
        qm.setSendTime(new Date());
        qm.setFromAddress(systemMessageBean.getFromEmail());
        qm.setFromName(systemMessageBean.getFromName());
        qm.setSubject(systemMessageBean.getSubject());
        qm.setMessage(systemMessageBean.getMessage());
        qm.setOwnerKey(systemMessageBean.getOwnerKey());
        qm.setModifiedByUsername(systemMessageBean.getModifiedByUser());
        return qm;
    }
}