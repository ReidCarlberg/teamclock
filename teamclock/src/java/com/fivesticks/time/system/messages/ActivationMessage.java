package com.fivesticks.time.system.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.settings.broker.SettingsBroker;
import com.fivesticks.time.settings.broker.SettingsBrokerAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * send in an email, find the user associated with it, then send that user their
 * password.
 * 
 * @author Reid
 *  
 */
public class ActivationMessage implements SettingsBrokerAware {

    private static final String SPRING_MESSAGE_NAME = "emailVerification";

    private static final String SPRING_MESSAGE_NAME_NOTIFY = "registrationNotify";

    private SettingsBroker settingsBroker;

    //    private static final String TEXT = "Hello, \n\n"
    //            + "Thank you for registering with FSTX TIME. \n\n";
    //
    //    private static final String TEXT_SIGN = "\n\nThank you!
    // \nsupport@fstxtime.com\n\n";
    //
    //    private static final String INFO_AT_FIVESTICKS = "info@fivesticks.com";

    //    public void sendActivationMessage(SystemOwner systemOwner)
    //            throws PasswordHelpException {
    //
    //        Sender sender = null;
    //
    //        sender = Sender.factory.build("FstxTime Activation",
    //                INFO_AT_FIVESTICKS, INFO_AT_FIVESTICKS);
    //
    //        Recipient recipient = Recipient.factory.build(systemOwner
    //                .getContactName(), systemOwner.getContactEmail());
    //
    //        Message message = Message.factory
    //                .build(
    //                        "FstxTime Activation",
    //                        TEXT
    //                                + "http://time.fivesticks.com/fstxtime/activate.action?activate="
    //                                + systemOwner.getKey() + "\n\n" + TEXT_SIGN);
    //        MailManager.singleton.sendSingleMessage(sender, recipient, message);
    //        /*
    //         * We also want to send a message to info@fivesticks.com
    //         */
    //        this.notifyFiveSticks(systemOwner);
    //
    //    }

    public void sendActivationMessage(SystemOwner systemOwner, User user, String password)
            throws SystemMessageException {

        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME);

        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName(systemOwner.getName());
        qm.setToAddress(systemOwner.getContactEmail());

        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",
                systemOwner.getKey()));

        qm
                .setMessage(qm.getMessage().replaceAll("\\{1\\}",
                        user.getUsername()));

        qm
                .setMessage(qm.getMessage().replaceAll("\\{2\\}",
                        password));

        try {
            QueuedMessageServiceDelegateFactory.factory.build(message.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    public void notifyFiveSticks(SystemOwner systemOwner)
            throws SystemMessageException {

        String out = "Name: " + systemOwner.getName() + "\nContact Name:  "
                + systemOwner.getContactName() + "\nEmail:  "
                + systemOwner.getContactEmail() + "\nAddress 1:  "
                + systemOwner.getAddress1() + "\nAddress 2:  "
                + systemOwner.getAddress2() + "\nCity:  "
                + systemOwner.getCity() + "\nState:  " + systemOwner.getState()
                + "\nPostal Code:  " + systemOwner.getPostalCode()
                + "\nCounty:  " + systemOwner.getCountry() + "\nKey:  "
                + systemOwner.getKey() + "\n\n";

        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME_NOTIFY);

        QueuedMessage qm = new QueuedMessageBuilder().build(message);

        //        qm.setToName(systemOwner.getName());
        //        qm.setToAddress(systemOwner.getContactEmail());

        /*
         * 2005-02-06 Replaced just the class file on time.fivesticks.com
         */
        qm.setToName("Reid Carlberg");
        qm.setToAddress("rsc1@fivesticks.com");

        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}", out));

        try {
            QueuedMessageServiceDelegateFactory.factory.build(message.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    protected static Log log = LogFactory.getLog(ActivationMessage.class
            .getName());

    /**
     * @return Returns the settingsBroker.
     */
    public SettingsBroker getSettingsBroker() {
        return settingsBroker;
    }

    /**
     * @param settingsBroker
     *            The settingsBroker to set.
     */
    public void setSettingsBroker(SettingsBroker settingsBroker) {
        this.settingsBroker = settingsBroker;
    }
}