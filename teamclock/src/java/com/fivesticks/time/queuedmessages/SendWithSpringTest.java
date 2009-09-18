/*
 * Created on Nov 26, 2005
 *
 */
package com.fivesticks.time.queuedmessages;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class SendWithSpringTest extends AbstractTimeTestCase {

    /* (non-Javadoc)
     * @see com.fivesticks.time.testutil.AbstractTimeTestCase#setUp()
     */
    protected void setUp() throws Exception {
        
        super.setUp();
    }

    
    public void testMessage() throws Exception {
        
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");

        sender.setJavaMailProperties(props);
        
        sender.setHost("smtp.emailsrvr.com");
        sender.setPort(587);
        sender.setProtocol("smtp");
        sender.setUsername("rsc1@fivesticks.com");
        sender.setPassword("warui%75");
        
        
        
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                mimeMessage.setRecipient(Message.RecipientType.TO, 
                        new InternetAddress("reid@fivesticks.com"));
                mimeMessage.setFrom(new InternetAddress("rsc1@fivesticks.com"));
                mimeMessage.setText(
                    "Dear "
                        + "first "
                        + "last "
                        + ", thank you for placing order. Your order number is "
                        + " 123");
            }
        };       
        
        sender.send(preparator);
        
    }
}
