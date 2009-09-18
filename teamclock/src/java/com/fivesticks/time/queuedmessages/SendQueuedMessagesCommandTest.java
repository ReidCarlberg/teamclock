/*
 * Created on Dec 20, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * @author Reid
 */
public class SendQueuedMessagesCommandTest extends AbstractTimeTestCase {

//    SystemOwner systemOwner;
//
//    FstxProject project;
//
//    User assocUser;

//    /*
//     * @see TestCase#setUp()
//     */
//    protected void setUp() throws Exception {
//        super.setUp();
//        JunitSettings.initializeTestSystem();
//        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
//                UserBDFactory.factory.build().getByUsername("admin"));
//        FstxCustomer customer = FstxCustomerTestFactory
//                .getPersisted(systemOwner);
//        project = FstxProjectTestFactory.getPersisted(systemOwner, customer);
//        assocUser = UserServiceDelegateFactory.factory.build(systemOwner)
//                .createNewUser("user1", "user1", "user1@fivesticks.com",
//                        UserTypeEnum.OWNERADMIN);
//    }

    public void testFindMessagesHasMessages() throws Exception {

        QueuedMessage mess = QueuedMessageTestFactory.singleton
                .getTestPersistedSendNow(systemOwner);
        assertTrue(mess != null);

        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        filter.setSendTimeRangeStop(new DateTime(DateTimeZone.UTC));

        Collection col = QueuedMessageServiceDelegateFactory.factory
                .build(systemOwner).search(filter);
        assertTrue(col.size() == 1);

    }

    public void testFindMessagesHasNoMessages() throws Exception {

        QueuedMessage mess = QueuedMessageTestFactory.singleton
                .getTestPersistedSendInAMinute(systemOwner);
        assertTrue(mess != null);

        log.info("send time is " + mess.getSendTime() + " id " + mess.getId());
        
        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        filter.setSendTimeRangeStop(new DateTime(DateTimeZone.UTC));

        log.info("filter time is " + filter.getSendTimeRangeStop().toDate());
        
        Collection col = QueuedMessageServiceDelegateFactory.factory
                .build(systemOwner).search(filter);
        
        log.info("collection size is " + col.size());
        if (col.size() == 1) {
            log.info("message id is " + ((QueuedMessage) col.toArray()[0]).getId());
            log.info("message sendtime is " + ((QueuedMessage) col.toArray()[0]).getSendTime());            
        }
        assertTrue(col.size() == 0);

    }

    public void testSendMessage() throws Exception {

        QueuedMessage mess = QueuedMessageTestFactory.singleton
                .getTestPersistedSendNow(systemOwner);
        assertTrue(mess != null);
        assertTrue(!mess.isSent());

        SendQueuedMessagesCommand command = SendQueuedMessagesCommandFactory.factory
                .build();
        command.execute();

        mess = QueuedMessageServiceDelegateFactory.factory.build(systemOwner).get(
                mess.getId());

        assertTrue(mess.isSent());

    }

    public void testSendMessageUsingSpring() throws Exception {

        QueuedMessage mess = QueuedMessageTestFactory.singleton
                .getTestPersistedSendNow(systemOwner);
        assertTrue(mess != null);
        assertTrue(!mess.isSent());

        SendQueuedMessagesCommand command = SendQueuedMessagesCommandFactory.factory
                .build();
        command.execute();

        mess = QueuedMessageServiceDelegateFactory.factory.build(systemOwner).get(
                mess.getId());

        assertTrue(mess.isSent());

        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(new SendQueuedMessagesCommandProxy());
        bean.setTargetMethod("execute");
        bean.prepare();
        bean.invoke();

    }

}