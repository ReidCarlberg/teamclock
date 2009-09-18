/*
 * Created on Oct 12, 2005
 *
 * 
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.queuedmessages.QueuedMessageFilter;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class SuperUserEmailAllSystemOwnersActionTest extends
        AbstractTimeTestCase {

    public void testSuperUserEmailAllSystemOwnerAction() throws Exception {
        SuperUserEmailAllSystemOwnersAction action = new SuperUserEmailAllSystemOwnersAction();
        action.setSessionContext(this.sessionContext);
        action.setMessage("Test");
        action.setSubject("Test Subject");
        action.setSubmit("submit");
        assertEquals(ActionSupport.ERROR, action.execute());

        action
                .setMessage("123456789012345678901234567890123456789012345678901234567890");

         assertEquals(ActionSupport.SUCCESS, action.execute());
        QueuedMessageServiceDelegate qmsd = QueuedMessageServiceDelegateFactory.factory
                .build(this.sessionContext.getSystemOwner().getKey());

        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        filter.setSendTimeRangeStop(new DateTime(DateTimeZone.UTC));

        Collection queuedMessages = qmsd.search(filter);

        assertEquals(2, queuedMessages.size());

    }
}
