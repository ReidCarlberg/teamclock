/*
 * Created on Dec 20, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class QueuedMessageTestFactory {

    public static final QueuedMessageTestFactory singleton = new QueuedMessageTestFactory();

    private static int count = 0;

    public QueuedMessage getTestPersistedSendNow(SystemOwner systemOwner)
            throws Exception {
        count++;

        QueuedMessage ret = getTestUnpersistedNoSend(systemOwner);
        ret.setSendTime(new Date());

        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(systemOwner);
        ret = sd.add(ret);

        return ret;
    }

    public QueuedMessage getTestPersistedSendInAMinute(SystemOwner systemOwner)
            throws Exception {
        count++;

        TimeZone.setDefault(TimeZone.getTimeZone("zulu"));
        QueuedMessage ret = getTestUnpersistedNoSend(systemOwner);

//        SimpleDate send = SimpleDate.factory.build();
//        send.advanceMinute(1);
//        ret.setSendTime(send.getDate());
        
        DateTime send = new DateTime(DateTimeZone.UTC);
        DateTime send2 = send.plusMinutes(10);
        ret.setSendTime(send2.toDate());
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(systemOwner);
        ret = sd.add(ret);

        return ret;
    }

    public QueuedMessage getTestUnpersistedNoSend(SystemOwner systemOwner)
            throws Exception {
        count++;

        QueuedMessage ret = new QueuedMessage();
        ret.setFromAddress("reidtest1@fivesticks.com");
        ret.setFromName("Reid Is Testing From " + count);
        ret.setToAddress("reidtest2@fivesticks.com");
        ret.setToName("Reid Is Testing To " + count);
        ret.setSubject("Here is the test subject. " + count);
        ret.setMessage("And here is the test message. " + count);
        ret.setModifiedByUsername("[system]");

        return ret;
    }

}