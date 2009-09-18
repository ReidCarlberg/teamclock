/*
 * Created on Oct 27, 2003
 *
 */
package com.fivesticks.time.system.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fstx.stdlib.authen.users.ChangePasswordException;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;

/**
 * send in an email, find the user associated with it, then send that user their
 * password.
 * 
 * @author Reid
 *  
 */
public class PasswordHelp {

    private static final String SPRING_MESSAGE_NAME = "passwordHelp";
    
    private static final String SPRING_MESSAGE_NAME_LINK = "passwordHelpSendLink";

    //    private static final String TEXT = "Hello, \n\n"
    //            + "Someone has requested we send your password. \n\n"
    //            + "it is: \n\n\t\t";
    //
    //    private static final String TEXT_SIGN = "\n\nYou can login at:
    // http://time.fivesticks.com "
    //            + "\n\nThank you! \nservice@fivesticks.com\n\n";

    public void sendPasswordHelp(String email) throws SystemMessageException {
        handleSend(email);
    }

    private void handleSend(String email) throws SystemMessageException {
        User user;
        try {

            
            user = UserBDFactory.factory.build().getUserByEmail(email);
        } catch (DAOException e) {
            throw new SystemMessageException(e);
        }

        if (user == null)
            throw new SystemMessageException("unexpectedly null");

        sendPasswordHelp(user);

    }

    public void sendPasswordHelp(User user) throws SystemMessageException {
        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME);

        /*
         * 2005-03-12 
         * Now that we're using the hash, we have to do a few things.
         */
        
        String newPassword = new RandomStringBuilder().buildRandomString(10);
        try {
            UserBDFactory.factory.build().changePassword(user,newPassword);
        } catch (ChangePasswordException e1) {
            throw new SystemMessageException(e1);
        }
        
        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName(user.getEmail());
        qm.setToAddress(user.getEmail());

        qm
                .setMessage(qm.getMessage().replaceAll("\\{0\\}",
                        newPassword));

        try {
            QueuedMessageServiceDelegateFactory.factory.build(message.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    public void sendPasswordResetLink(User user) throws SystemMessageException {
        SystemMessageBean message = (SystemMessageBean) SpringBeanBroker
                .getCommonMessage(SPRING_MESSAGE_NAME_LINK);

        /*
         * 2005-11-27
         * Now that we're using the reset link, we have to do a few things.
         */
        
//        String newPassword = new RandomStringBuilder().buildRandomString(10);
//        try {
//            UserBD.factory.build().changePassword(user,newPassword);
//        } catch (ChangePasswordException e1) {
//            throw new SystemMessageException(e1);
//        }
        
        QueuedMessage qm = new QueuedMessageBuilder().build(message);
        qm.setToName(user.getEmail());
        qm.setToAddress(user.getEmail());

        qm
                .setMessage(qm.getMessage().replaceAll("\\{0\\}",
                        user.getResetKey()));

        try {
            QueuedMessageServiceDelegateFactory.factory.build(message.getOwnerKey())
                    .addSystemMessage(qm);
        } catch (QueuedMessageServiceDelegateException e) {
            throw new SystemMessageException(e);
        }

    }

    //    public void sendPasswordHelp(User user) throws PasswordHelpException {
    //
    //        Sender sender = null;
    //
    //        sender = Sender.factory.build("FstxTime Support",
    //                "service@fivesticks.com", "service@fivesticks.com");
    //
    //        Recipient recipient = Recipient.factory.build(user.getUsername(), user
    //                .getEmail());
    //
    //        Message message = Message.factory.build("Password Help", TEXT
    //                + user.getPassword() + TEXT_SIGN);
    //
    //        MailManager.singleton.sendSingleMessage(sender, recipient, message);
    //    }

    protected static Log log = LogFactory.getLog(PasswordHelp.class.getName());

}