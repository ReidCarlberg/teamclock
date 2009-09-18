/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fstx.stdlib.common.messages;

import junit.framework.TestCase;

/**
 * @author Reid
 */
public class MailBrokerTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        MailHost host = MailHostFactory.factory.build("smtp.emailsrvr.com",
                "rsc1@fivesticks.com", "mark1969", "587");


        MailHostBroker.singleton.setHost(host);
    }

    public void testSendMailThroughAuth() throws Exception {

        Sender sender = null;

        sender = Sender.factory.build("FstxTime Activation", "test@test.com",
                "test@test.com");

        Recipient recipient = RecipientFactory.factory.build("HI",
                "reid@fivesticks.com");

        Message message = MessageFactory.factory
                .build(
                        "From  com.fstx.stdlib.common.messages.MailBrokerTest FstxTime Activation",
                        "HI"
                                + "http://time.fivesticks.com/fstxtime/activate.action?activate="
                                + " Whoo Hoo" + "\n\n");

        MailManager.singleton.sendSingleMessage(sender, recipient, message);

        Thread.sleep(11111);
    }

}