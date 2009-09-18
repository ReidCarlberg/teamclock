/*
 * Created on Sep 4, 2006
 *
 */
package com.fivesticks.time.contactv2.forms.external;

import java.util.Collection;

import com.fivesticks.time.contactv2.ContactV2CriteriaParameters;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.contactv2.forms.WebForm;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegateFactory;
import com.fivesticks.time.contactv2.forms.WebFormTestFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageTestFactory;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.Action;

public class InputFromWebFormActionTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        Message thankYou = MessageTestFactory.getPersisted(this.systemOwner);
        Message notification = MessageTestFactory
                .getPersisted(this.systemOwner);

        Lookup contactClass = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);

        Lookup contactSource = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_SOURCE);

        Lookup contactInterest = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_INTEREST);

        WebForm webForm = WebFormTestFactory.getPersisted(this.sessionContext,
                contactClass, contactInterest, contactSource, thankYou,
                notification);

        webForm.setSendNotification(false);
        webForm.setSendThankYouMessage(false);
        WebFormServiceDelegateFactory.factory.build(this.sessionContext).save(webForm);
        
        InputFromWebFormAction action = new InputFromWebFormAction();

        action.setContactform(webForm.getKey());

        action.setContactdetail("here are some notes");

        String s = action.execute();

        assertEquals(Action.SUCCESS + ".redirect", s);

        QueuedMessageServiceDelegate qsd = QueuedMessageServiceDelegateFactory.factory
                .build(this.sessionContext);

        Collection unsent = qsd.findAllUnsent();

        assertNotNull(unsent);

        // because there is no email address
        assertEquals(0, unsent.size());

    }
    
    public void testBasicWithContactData() throws Exception {

        Message thankYou = MessageTestFactory.getPersisted(this.systemOwner);
        Message notification = MessageTestFactory
                .getPersisted(this.systemOwner);

        Lookup contactClass = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);

        Lookup contactSource = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_SOURCE);

        Lookup contactInterest = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_INTEREST);

        WebForm webForm = WebFormTestFactory.getPersisted(this.sessionContext,
                contactClass, contactInterest, contactSource, thankYou,
                notification);

        InputFromWebFormAction action = new InputFromWebFormAction();

        action.setContactform(webForm.getKey());

        action.setContactdetail("here are some notes");

        action.getContact().setNameFirst("john");
        action.getContact().setNameLast("smith");
        action.getContact().setOrganizationEmail("john@smith.com");
        String s = action.execute();

        assertEquals(Action.SUCCESS + ".redirect", s);
        
        Collection contacts = ContactV2ServiceDelegateFactory.factory.build(this.systemOwner).getListCri(new ContactV2CriteriaParameters());
        
        assertNotNull(contacts);
        
        assertEquals(2, contacts.size());  //setup plus this new one.

        QueuedMessageServiceDelegate qsd = QueuedMessageServiceDelegateFactory.factory
                .build(this.sessionContext);

        Collection unsent = qsd.findAllUnsent();

        assertNotNull(unsent);

        // because there are email addresses
        assertEquals(3, unsent.size());
        
        assertNotNull(action.getResultLocationURL());

    }

}
