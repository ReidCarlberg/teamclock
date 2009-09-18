/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.messages.Message;

public class WebFormTestFactory {

    private static int counter = 0;

    public static WebForm getPersisted(SessionContext sessionContext)
            throws Exception {
        counter++;

        WebForm ret = new WebForm();

        ret.setActive(true);
        ret.setAssignToUsername(sessionContext.getUser().getUsername());
        ret.setContactInterest(new Long(1));
        ret.setContactSource(new Long(1));
        ret.setContactClass(new Long(1));

        ret.setName("sample" + counter);
        ret.setNotificationMessageId(new Long(2));
        ret
                .setNotificationMessageRecipient(sessionContext.getUser()
                        .getEmail());
        ret.setSendNotification(true);
        ret.setSendThankYouMessage(true);
        ret.setThankYouMessageId(new Long(3));
        ret.setSuccessURL("here is the success URL");
        ret.setFailureURL("here is the failure URL");

        WebFormServiceDelegateFactory.factory.build(sessionContext).save(ret);

        return ret;
    }

    public static WebForm getPersisted(SessionContext sessionContext,
            Lookup contactClass, Lookup contactInterest, Lookup contactSource, Message thankYou, Message notification)
            throws Exception {
        counter++;

        WebForm ret = new WebForm();

        ret.setActive(true);
        ret.setAssignToUsername(sessionContext.getUser().getUsername());
        ret.setContactInterest(contactInterest.getId());
        ret.setContactSource(contactSource.getId());
        ret.setContactClass(contactClass.getId());

        ret.setName("sample" + counter);
        ret.setNotificationMessageId(notification.getId());
        ret
                .setNotificationMessageRecipient(sessionContext.getUser()
                        .getEmail());
        ret.setSendNotification(true);
        ret.setSendThankYouMessage(true);
        ret.setThankYouMessageId(thankYou.getId());
        ret.setSuccessURL("here is the success URL");
        ret.setFailureURL("here is the failure URL");

        WebFormServiceDelegateFactory.factory.build(sessionContext).save(ret);

        return ret;
    }
}
