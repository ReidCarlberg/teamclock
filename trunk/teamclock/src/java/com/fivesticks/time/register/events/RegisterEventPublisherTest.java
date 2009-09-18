/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.register.events;

import java.util.Collection;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.queuedmessages.QueuedMessageDAOFactory;
import com.fivesticks.time.queuedmessages.QueuedMessageFilter;
import com.fivesticks.time.register.xwork.OtherInterestAction;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class RegisterEventPublisherTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testOtherInterestsNoExceptions() throws Exception {

        OtherInterestAction action = new OtherInterestAction();

        action.setComments("comments");
        action.setContactEmail("reidtest1@fivesticks.com");
        action.setContactName("ReidTest2");
        action.setName("Name");
        action.setInterestedInAutomation(true);
        action.setInterestedInEmail(true);
        action.setInterestedInUpdates(true);

        RegisterEventPublisher pub = new RegisterEventPublisher();

        pub.publishOtherInterestsEvent(action);

        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        filter.setSendTimeRangeStop(new DateTime(DateTimeZone.UTC));

        Collection col = QueuedMessageDAOFactory.factory.build().find(filter);
        assertTrue(col.size() == 1);

    }

}