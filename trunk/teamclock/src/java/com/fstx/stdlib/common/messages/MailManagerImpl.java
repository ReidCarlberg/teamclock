/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
package com.fstx.stdlib.common.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid S. Carlberg
 * @version 1.0.0
 *  
 */
public class MailManagerImpl implements MailManager {

//    private static int threadCount = 0;

    //private static ThreadGroup threads = new ThreadGroup("mail");

//    private boolean isSingle;

    private Sender from;

    private Recipient to;

//    private RecipientCollection toColl;

    private Message message;

    protected MailManagerImpl() {

    }

    /**
     * @see com.ess.hotel.mail.MailManager#sendSingleMessage(Recipient, Message)
     */
    public boolean sendSingleMessage(final Sender from, final Recipient to,
            final Message message) {
        this.from = from;
        this.to = to;
        this.message = message;

//        isSingle = true;
        log.info("sendSingleMessge!");
        sendMessageAsynchronously();

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.common.messages.MailManager#sendSingleMessageSynchronously(com.fstx.stdlib.common.messages.Sender,
     *      com.fstx.stdlib.common.messages.Recipient,
     *      com.fstx.stdlib.common.messages.Message)
     */
    public boolean sendSingleMessageSynchronously(MailHost host, Sender from, Recipient to,
            Message message) {

        return getMailSender(host, from, to, message).send();

    }

    /**
     * Actually sends the message
     */
    private boolean sendMessageAsynchronously() {

        new Thread(getMailSender(from, to, message)).start();

        return true;

    }

    private MailSender getMailSender(Sender from, Recipient to, Message message) {

        MailHost host = MailHostBroker.singleton.getHost();

        MailSender sender = MailSenderFactory.factory.build(host, from, to, message);

        return sender;
    }

    private MailSender getMailSender(MailHost host, Sender from, Recipient to, Message message) {

        

        MailSender sender = MailSenderFactory.factory.build(host, from, to, message);

        return sender;
    }

    protected static Log log = LogFactory.getLog(MailManagerImpl.class
            .getName());

}