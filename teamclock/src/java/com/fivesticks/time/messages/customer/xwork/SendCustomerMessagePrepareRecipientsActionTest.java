/*
 * Created on Oct 17, 2005
 *
 * 
 */
package com.fivesticks.time.messages.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class SendCustomerMessagePrepareRecipientsActionTest extends
        AbstractTimeTestCase {

    public void testSendCustomerMessagePrepareRecipientsActionByCust()
            throws Exception {
        SendCustomerMessagePrepareRecipientsAction action = new SendCustomerMessagePrepareRecipientsAction();
        action.setSessionContext(this.sessionContext);
        SendMessageContext sendMessageContext = new SendMessageContext();
        action.setSendMessageContext(sendMessageContext);

        Collection idList = new ArrayList();
        idList.add(this.customer.getId());
        action.setCustomerIdList(idList);
        action.setSubmitSend("Send");
        action.setSelectRecipientsBy("Select By Customer");

        assertEquals(ActionSupport.SUCCESS, action.execute());

        assertEquals(1, sendMessageContext.getRecipients().size());
        Customer customer = (Customer) sendMessageContext
                .getRecipients().toArray()[0];
        assertEquals(this.customer.getId(), customer.getId());
        assertEquals(this.customer.getName(), customer.getName());
    }

     public void testSendCustomerMessagePrepareRecipientsActionByCustNoneSelected()
            throws Exception {
        SendCustomerMessagePrepareRecipientsAction action = new SendCustomerMessagePrepareRecipientsAction();
        action.setSessionContext(this.sessionContext);
        SendMessageContext sendMessageContext = new SendMessageContext();
        action.setSendMessageContext(sendMessageContext);

       
        action.setSubmitSend("Send");
        action.setSelectRecipientsBy("Select By Customer");

        assertEquals(ActionSupport.SUCCESS, action.execute());

        assertEquals(0, sendMessageContext.getRecipients().size());
       
    }
    
    public void testSendCustomerMessagePrepareRecipientsActionByCont()
            throws Exception {
        SendCustomerMessagePrepareRecipientsAction action = new SendCustomerMessagePrepareRecipientsAction();
        action.setSessionContext(this.sessionContext);
        SendMessageContext sendMessageContext = new SendMessageContext();
        action.setSendMessageContext(sendMessageContext);

        Collection idList = new ArrayList();
        idList.add(this.contact.getId().toString());
        action.setContactIdList(idList);
        action.setSubmitSend("Send");
        action.setSelectRecipientsBy("Select By Contact");

        assertEquals(ActionSupport.SUCCESS, action.execute());

        assertEquals(1, sendMessageContext.getRecipients().size());
        ContactV2 contact = (ContactV2) sendMessageContext
                .getRecipients().toArray()[0];
        assertEquals(this.contact.getId(), contact.getId());
        assertEquals(this.contact.getNameLast(), contact.getNameLast());
    }

//     public void testSendCustomerMessagePrepareRecipientsActionByAllCont()
//            throws Exception {
//        SendCustomerMessagePrepareRecipientsAction action = new SendCustomerMessagePrepareRecipientsAction();
//        action.setSessionContext(this.sessionContext);
//        SendMessageContext sendMessageContext = new SendMessageContext();
//        action.setSendMessageContext(sendMessageContext);
//
//       
//        action.setSubmitSend("Send");
//        action.setSelectRecipientsBy("Select All Contacts");
//
//        assertEquals(ActionSupport.SUCCESS, action.execute());
//
//        assertEquals(1, sendMessageContext.getRecipients().size());
//        Contact contact = (Contact) sendMessageContext
//                .getRecipients().toArray()[0];
//        assertEquals(this.contact.getId(), contact.getId());
//        assertEquals(this.contact.getNameLast(), contact.getNameLast());
//    }
     
//     public void testSendCustomerMessagePrepareRecipientsActionByAllCust()
//            throws Exception {
//        SendCustomerMessagePrepareRecipientsAction action = new SendCustomerMessagePrepareRecipientsAction();
//        action.setSessionContext(this.sessionContext);
//        SendMessageContext sendMessageContext = new SendMessageContext();
//        action.setSendMessageContext(sendMessageContext);
//
//       
//        action.setSubmitSend("Send");
//        action.setSelectRecipientsBy("Select All Customers");
//
//        assertEquals(ActionSupport.SUCCESS, action.execute());
//
//        assertEquals(1, sendMessageContext.getRecipients().size());
//        FstxCustomer customer = (FstxCustomer) sendMessageContext
//                .getRecipients().toArray()[0];
//        assertEquals(this.customer.getId(), customer.getId());
//        assertEquals(this.customer.getName(), customer.getName());
//    }
    
}
