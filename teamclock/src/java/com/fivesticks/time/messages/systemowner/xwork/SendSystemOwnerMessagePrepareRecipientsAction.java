/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.systemowner.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;
import com.fivesticks.time.messages.customer.xwork.SendMessageContext;
import com.fivesticks.time.messages.customer.xwork.SendMessageContextAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

public class SendSystemOwnerMessagePrepareRecipientsAction extends
        SessionContextAwareSupport implements SendMessageContextAware {

    private SendMessageContext sendMessageContext;

    private String submitSend;

    private String selectRecipientsBy;

   // private Collection customerIdList = new ArrayList();

   // private Collection contactIdList = new ArrayList();
 private Collection systemOwnerIdList = new ArrayList();
    
    private String messageId;

    private MessageServiceDelegate messageServiceDelegate;

   // private CustomerSettingServiceDelegate customerSettingServiceDelegate;

   // private FstxCustomerBD fstxCustomerBD;

    public String execute() throws Exception {

        if (!this.isSubmitSendSet()) {

            return INPUT;
        }

        if (selectRecipientsBy == null) {
            this.getSessionContext().setMessage(
                    "Please select a recipient strategy");

            return INPUT;
        }

        if (this.getSelectRecipientsBy().equals("Select All System Owners")) {
            this.getSendMessageContext().setRecipients(this.getSystemOwners());
        }
        

        if (this.getSelectRecipientsBy().equals("Select By System Owner")) {
            for (Iterator iter = this.getSystemOwnerIdList().iterator(); iter
                    .hasNext();) {
                String element = (String) iter.next();

               SystemOwner so =  this.getSystemOwnerServiceDelegate().get(
                        new Long(element));
                this.getSendMessageContext().getRecipients().add(so);
            }

        }
        
       

      
        return SUCCESS;

    }

//    public Collection getCustomers() throws CustomerListProviderException {
//        return this.getCustomerListProvider().getCustomers();
//
//    }
//
//    public Collection getContacts() throws Exception {
//
//        Collection contacts = new ArrayList();
//        for (Iterator iter = this.getCustomers().iterator(); iter.hasNext();) {
//            FstxCustomer customer = (FstxCustomer) iter.next();
//            Collection temp = ContactServiceDelegate.factory.build(
//                    this.getSystemOwner()).getByCustomer(customer);
//
//            for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
//                Contact contact = (Contact) iterator.next();
//
//                ContactAndCustomerVO vo = new ContactAndCustomerVO(contact,
//                        customer);
//                contacts.add(vo);
//            }
//        }
//
//        return contacts;
//
//    }

//    private FstxCustomer getCustomer(Contact contact)
//            throws FstxCustomerBDException {
//        return this.getFstxCustomerBD().getFstxCustomer(contact.getCustId());
//
//    }
//
//    private CustomerSettingVO getCustomerSettingVO(FstxCustomer fstxCustomer) {
//        try {
//            return this.getCustomerSettingServiceDelegate().find(fstxCustomer);
//        } catch (ObjectMetricServiceDelegateException e) {
//
//            e.printStackTrace();
//        } catch (ObjectMetricNotFoundException e) {
//
//            e.printStackTrace();
//        }
//
//        return null;
//    }

   private  SystemOwnerServiceDelegate systemOwnerServiceDelegate;
    
    private Collection getSystemOwners() throws SystemOwnerServiceDelegateException {
        return this.getSystemOwnerServiceDelegate().findAll();
    }

    public boolean isSubmitSendSet() {
        System.out.println("is Submit?" + this.getSubmitSend());
        return StringUtils.hasText(this.getSubmitSend());
    }

    public String getSubmitSend() {
        return submitSend;
    }

    public void setSubmitSend(String submitSend) {
        this.submitSend = submitSend;
    }

    public SendMessageContext getSendMessageContext() {
        return sendMessageContext;
    }

    public void setSendMessageContext(SendMessageContext sendMessageContext) {
        this.sendMessageContext = sendMessageContext;
    }

    public MessageServiceDelegate getMessageServiceDelegate() {
        if (this.messageServiceDelegate == null) {
            messageServiceDelegate = MessageServiceDelegateFactory.factory.build(this
                    .getSystemOwner());
        }

        return messageServiceDelegate;
    }

    public void setMessageServiceDelegate(
            MessageServiceDelegate messageServiceDelegate) {
        this.messageServiceDelegate = messageServiceDelegate;
    }

//    public FstxCustomerBD getFstxCustomerBD() {
//        if (fstxCustomerBD == null) {
//            fstxCustomerBD = FstxCustomerBD.factory
//                    .build(this.getSystemOwner());
//
//        }
//        return fstxCustomerBD;
//    }
//
//    public void setFstxCustomerBD(FstxCustomerBD fstxCustomerBD) {
//        this.fstxCustomerBD = fstxCustomerBD;
//    }
//
//    public CustomerSettingServiceDelegate getCustomerSettingServiceDelegate() {
//        if (this.customerSettingServiceDelegate == null) {
//            this.customerSettingServiceDelegate = CustomerSettingServiceDelegate.factory
//                    .build(this.getSystemOwner());
//
//        }
//        return customerSettingServiceDelegate;
//    }
//
//    public void setCustomerSettingServiceDelegate(
//            CustomerSettingServiceDelegate customerSettingServiceDelegate) {
//        this.customerSettingServiceDelegate = customerSettingServiceDelegate;
//    }
//
//    public Collection getContactIdList() {
//        return contactIdList;
//    }
//
//    public void setContactIdList(Collection contactIdList) {
//        this.contactIdList = contactIdList;
//    }
//
//    public Collection getCustomerIdList() {
//        return customerIdList;
//    }
//
//    public void setCustomerIdList(Collection customerIdList) {
//        this.customerIdList = customerIdList;
//    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSelectRecipientsBy() {
        return selectRecipientsBy;
    }

    public void setSelectRecipientsBy(String selectRecipientsBy) {
        this.selectRecipientsBy = selectRecipientsBy;
    }

    public Collection getSystemOwnerIdList() {
        return systemOwnerIdList;
    }

    public void setSystemOwnerIdList(Collection systemOwnerIdList) {
        this.systemOwnerIdList = systemOwnerIdList;
    }

   
    public SystemOwnerServiceDelegate getSystemOwnerServiceDelegate() {
        if(this.systemOwnerServiceDelegate == null)
        {
            this.systemOwnerServiceDelegate = SystemOwnerServiceDelegateFactory.factory.build();  
        }
        return systemOwnerServiceDelegate;
    }

    public void setSystemOwnerServiceDelegate(
            SystemOwnerServiceDelegate systemOwnerServiceDelegate) {
        this.systemOwnerServiceDelegate = systemOwnerServiceDelegate;
    }

}
