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

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Modified to get rid of extra logs.
 * 
 * @author Reid S. Carlberg
 * @version 1.0.1
 *  
 */
class MailSenderImpl implements MailSender {

    private final MailHost host;

    private final Sender from;

    private final Recipient to;

    private final Message message;

    protected MailSenderImpl(final MailHost host, final Sender from,
            final Recipient to, final Message message) {

        this.host = host;
        this.from = from;
        this.to = to;
        this.message = message;
    }

    /**
     * @see com.ess.messages.MailSender#send()
     */
    public boolean send() {

        try {
            // 2005-11-27 RSC always requires authentication.
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");

            props.put("mail.smtp.host", host.getAddress());
            /*
             * 2005-11-27 RSC
             * Since webmail.us is starting to make other ports available
             * as Comcast blocks port 25.
             */
            props.put("mail.smtp.port", host.getPort()); 
            


            Session s = Session.getInstance(props, null);

            MimeMessage messageOut = new MimeMessage(s);

            InternetAddress fromOut = new InternetAddress(from.getAddress());

            //reid 2004-12-20
            fromOut.setPersonal(from.getName());

            messageOut.setFrom(fromOut);

            InternetAddress toOut = new InternetAddress(this.to.getAddress());

            //reid 2004-12-20
            toOut.setPersonal(to.getName());

            messageOut.addRecipient(javax.mail.Message.RecipientType.TO, toOut);

            messageOut.setSubject(message.getSubject());

            messageOut.setText(message.getMessage());

            if (host.useAuthentication()) {

                Transport transport = s.getTransport("smtp");
                transport.connect(host.getAddress(), host.getUsername(), host
                        .getPassword());
                transport
                        .sendMessage(messageOut, messageOut.getAllRecipients());
                transport.close();
            } else {

                Transport.send(messageOut);
            }

        } catch (Exception e) {
            log.info("\n\nMailSenderIMPL3: " + host.getAddress());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {

        this.send();

    }

    protected static Log log = LogFactory
            .getLog(MailSenderImpl.class.getName());
}