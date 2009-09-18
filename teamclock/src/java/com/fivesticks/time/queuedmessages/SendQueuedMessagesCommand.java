/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.system.messages.MailSettingsBean;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.messages.MailHost;
import com.fstx.stdlib.common.messages.MailHostFactory;
import com.fstx.stdlib.common.messages.MailManager;
import com.fstx.stdlib.common.messages.Message;
import com.fstx.stdlib.common.messages.MessageFactory;
import com.fstx.stdlib.common.messages.Recipient;
import com.fstx.stdlib.common.messages.RecipientFactory;
import com.fstx.stdlib.common.messages.Sender;

/**
 * Independent of System owner.
 * 
 * @author REID
 */
public class SendQueuedMessagesCommand implements QueuedMessageDAOAware {

    private Map mailSettings = new HashMap();
    
    private QueuedMessageDAO queuedMessageDAO;

//    private static Log log = LogFactory.getLog(SendQueuedMessagesCommand.class);

    public void execute() throws SendQueuedMessagesCommandException {

        for (Iterator iter = getMessagesToSend().iterator(); iter.hasNext();) {
            QueuedMessage element = (QueuedMessage) iter.next();

            try {
                handleSend(element);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            element.setSent(true);
            try {
                this.getQueuedMessageDAO().save(element);
            } catch (DAOException e1) {
                throw new SendQueuedMessagesCommandException(e1);
            }
        }

    }

    /**
     * @param element
     * @throws SendQueuedMessagesCommandException
     */
    private void handleSend(QueuedMessage element)
            throws SendQueuedMessagesCommandException {

        /*
         * 2005-11-27 RSC need to send using customer specific smtp settings
         */
        MailSettingsBean settings = this.getMailSettingsBean(element.getOwnerKey());
        
        MailHost host = MailHostFactory.factory.build(settings.getSmtpServer(), settings.getUsername(), settings.getPassword(), settings.getPort());
        
        Message message = MessageFactory.factory.build(element.getSubject(), element
                .getMessage());

        Sender sender = Sender.factory.build(element.getFromName(), element
                .getFromAddress(), element.getFromAddress());

        Recipient recipient = RecipientFactory.factory.build(element.getToName(),
                element.getToAddress());

        try {
            if (!MailManager.singleton.sendSingleMessageSynchronously(host, sender,
                    recipient, message)) {
                throw new SendQueuedMessagesCommandException(
                        "mail sender failed.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    
    public Collection getMessagesToSend() throws SendQueuedMessagesCommandException {
        //      log.info("Now executing the send queued messages command.");

        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        
        DateTime dt = new DateTime(DateTimeZone.UTC);
//        
//        Calendar t = dt.toCalendar(Locale.US);
        
        filter.setSendTimeRangeStop(dt);

        Collection unsent = null;
        try {
            unsent = this.getQueuedMessageDAO().find(filter);
        } catch (DAOException e) {
            throw new SendQueuedMessagesCommandException(e);
        }
        return unsent;
    }

    private MailSettingsBean getMailSettingsBean(String ownerKey) {
        
        MailSettingsBean ret = null;
        
        ret = (MailSettingsBean) mailSettings.get(ownerKey);
        
        if (ret == null) {
            
            SystemOwner owner;
            try {
                owner = SystemOwnerServiceDelegateFactory.factory.build().get(ownerKey);
            } catch (SystemOwnerServiceDelegateException e) {
                throw new RuntimeException("failed in send command. " + e);
            }
            
            SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory.build(owner);
            
            ret = new MailSettingsBean();
            
            ret.setSmtpServer(sd.getSettingAsString(SettingTypeEnum.SETTING_SMTP_HOST));
            ret.setPort(sd.getSettingAsString(SettingTypeEnum.SETTING_SMTP_PORT));
            ret.setUsername(sd.getSettingAsString(SettingTypeEnum.SETTING_SMTP_USERNAME));
            ret.setPassword(sd.getSettingAsString(SettingTypeEnum.SETTING_SMTP_PASSWORD));
            
           mailSettings.put(ownerKey, ret);            
            
        }
        
        return ret;
    }
    /**
     * @return Returns the queuedMessageDAO.
     */
    public QueuedMessageDAO getQueuedMessageDAO() {
        return queuedMessageDAO;
    }

    /**
     * @param queuedMessageDAO
     *            The queuedMessageDAO to set.
     */
    public void setQueuedMessageDAO(QueuedMessageDAO queuedMessageDAO) {
        this.queuedMessageDAO = queuedMessageDAO;
    }
}