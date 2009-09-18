/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.contactxx.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegate;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateException;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author shuji
 */
public class ContactModifyAction extends ActionSupport implements
        SessionContextAware, CustomerModifyContextAware,
        ContactModifyContextAware {

    private SessionContext sessionContext;

    private CustomerModifyContext customerModifyContext;

    private ContactModifyContext contactModifyContext;

    private Contact contact;

    private String contactSubmit;

    private String contactCancel;

    private String contactDelete;

    private Long target;

    public String execute() throws Exception {

        if (this.getContactCancel() != null)
            return SUCCESS;

        if (this.getContactDelete() != null && this.getContactModifyContext().getTarget() != null) {
            
            ContactDeleteCommand dc = new ContactDeleteCommand(this
                    .getContactModifyContext().getTarget());
            DeleteContext deleteContext = DeleteContextFactory.factory.build(dc);
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;            
        }
        
        if (this.getContactSubmit() == null) {
            if (this.getTarget() != null) {
                ContactServiceDelegate sd = ContactServiceDelegateFactory.factory
                        .build(this.getSessionContext().getSystemOwner());
                this.getContactModifyContext().setTarget(
                        sd.get(this.getTarget()));
                contact = this.getContactModifyContext().getTarget();
            } else {
                contact = new Contact();
                this.getContactModifyContext().setTarget(null);
            }

            return INPUT;
        }

        validate();

        if (this.hasErrors())
            return INPUT;

        if (this.getContactModifyContext().getTarget() != null) {
            contact.setId(this.getContactModifyContext().getTarget().getId());
            contact.setCustId(this.getContactModifyContext().getTarget().getCustId());
        } else {
            contact.setCustId(this.getCustomerModifyContext().getTargetCustomer().getId());
        }
        persist();

        return SUCCESS;
    }

    public void validate() {
        ValidationHelper helper = new ValidationHelper();
        if (helper.isStringEmpty(this.getContact().getNameFirst())) {
            this.addFieldError("contact.nameFirst", "Name is required.");
        }
    }

    private void persist() throws ContactServiceDelegateException {
        ContactServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).save(contact);
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.authen.webwork.SessionContextAware#setSessionContext(com.fstx.stdlib.common.struts.SessionContext)
     */

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getContactSubmit() {
        return contactSubmit;
    }

    public void setContactSubmit(String submit) {
        this.contactSubmit = submit;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the customerCancel.
     */
    public String getContactCancel() {
        return contactCancel;
    }

    /**
     * @param customerCancel
     *            The customerCancel to set.
     */
    public void setContactCancel(String contactCancel) {
        this.contactCancel = contactCancel;
    }

    /**
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public Contact getContact() {
        //   return this.getCustomerModifyContext().getTargetContact();
        if (contact == null) {
            contact = new Contact();
        }
        return contact;
    }

    public void setContact(Contact contactTarget) {
        // this.getCustomerModifyContext().setTargetContact(contactTarget);

        this.contact = contactTarget;
    }

    /**
     * @return Returns the contactModifyContext.
     */
    public ContactModifyContext getContactModifyContext() {
        return contactModifyContext;
    }

    /**
     * @param contactModifyContext
     *            The contactModifyContext to set.
     */
    public void setContactModifyContext(
            ContactModifyContext contactModifyContext) {
        this.contactModifyContext = contactModifyContext;
    }

    /**
     * @return Returns the contactDelete.
     */
    public String getContactDelete() {
        return contactDelete;
    }

    /**
     * @param contactDelete
     *            The contactDelete to set.
     */
    public void setContactDelete(String contactDelete) {
        this.contactDelete = contactDelete;
    }
}