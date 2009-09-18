package com.fivesticks.time.contactv2.xwork;

import java.util.Collection;

import com.fivesticks.time.common.EmptyToStringFieldNullDecorator;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.util.TimeZoneListBuilder;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateException;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.opensymphony.xwork.Action;

/**
 * @author reid
 * 2006-08-30
 */
public class ContactV2ModifyAction extends SessionContextAwareSupport implements CustomerModifyContextAware,
        ContactV2ModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private ContactV2ModifyContext contactV2ModifyContext;

    private ContactV2 contactV2 = new ContactV2();

    private String contactSubmit;

    private String contactCancel;

    private String contactDelete;
    
    private String target;

    public String execute() throws Exception {

        
        if (this.getContactCancel() != null) {
            
            return Action.SUCCESS;
        }
            

        if (this.getContactDelete() != null && this.getContactV2ModifyContext().getTarget() != null) {
            
            ContactV2DeleteCommand dc = new ContactV2DeleteCommand(this
                    .getContactV2ModifyContext().getTarget());
            DeleteContext deleteContext = DeleteContextFactory.factory.build(dc);
            
            if (this.getCustomerModifyContext().getTargetCustomer() != null) {
                dc.setXWorkSuccess("delete-success-contact-customer");
            }
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;            
        }
        
        if (this.getContactSubmit() == null) {
            
            if (this.getTarget() != null || this.getContactV2ModifyContext().getTarget() == null) {
                contactV2 = new ContactV2();
                this.getContactV2ModifyContext().setTarget(null);                
            } else {
                contactV2 = this.getContactV2ModifyContext().getTarget();
            }

            return INPUT;
        }

        validate();

        if (this.hasErrors())
            return INPUT;

        if (this.getContactV2ModifyContext().getTarget() != null) {
            contactV2.setId(this.getContactV2ModifyContext().getTarget().getId());
            
        } 
        
        //convert empty strings to nulls
        new EmptyToStringFieldNullDecorator().convert(this.getContactV2());
        
        persist();

        this.getContactV2ModifyContext().setTarget(this.getContactV2());
        
        return Action.SUCCESS;
        
    }

    public void validate() {
        ValidationHelper helper = new ValidationHelper();
        if (helper.isStringEmpty(this.getContactV2().getNameFirst())) {
            this.addFieldError("contact.nameFirst", "Name is required.");
        }
    }

    private void persist() throws ContactV2ServiceDelegateException {
        
        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner());
        
        sd.save(contactV2);
        
        if (this.getCustomerModifyContext().getTargetCustomer() != null) {
          sd.associate(contactV2, this.getCustomerModifyContext().getTargetCustomer());  
        }
    }


    public String getContactSubmit() {
        return contactSubmit;
    }

    public void setContactSubmit(String submit) {
        this.contactSubmit = submit;
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

    public ContactV2 getContactV2() {
        //   return this.getCustomerModifyContext().getTargetContact();
        if (contactV2 == null) {
            contactV2 = new ContactV2();
        }
        return contactV2;
    }

    public void setContactV2(ContactV2 contactTarget) {
        // this.getCustomerModifyContext().setTargetContact(contactTarget);

        this.contactV2 = contactTarget;
    }

    /**
     * @return Returns the contactModifyContext.
     */
    public ContactV2ModifyContext getContactV2ModifyContext() {
        return contactV2ModifyContext;
    }

    /**
     * @param contactModifyContext
     *            The contactModifyContext to set.
     */
    public void setContactV2ModifyContext(
            ContactV2ModifyContext contactModifyContext) {
        this.contactV2ModifyContext = contactModifyContext;
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
    
    public Collection getTimeZones() {
        return TimeZoneListBuilder.singleton.build();
    }
    
    public Collection getCountries() {
        return new GeographicCollection().getCountries();
    }

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }
}