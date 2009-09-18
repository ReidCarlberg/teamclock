/*
 * Created on Aug 31, 2006 by Reid
 */
package com.fivesticks.time.contactv2.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2CriteriaParameters;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.menu.MenuSection;
import com.opensymphony.xwork.Action;

public class ContactV2ListAction extends SessionContextAwareSupport implements
        ContactV2ListContextAware, CustomerModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private ContactV2ListContext contactV2ListContext;

    private ContactV2CriteriaParameters params = new ContactV2CriteriaParameters();

    private String searchContacts;

    private String resetContacts;

    private Collection contacts;
    
    private Boolean associate;


    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.CONTACTS);

        // 2006-09-02 reid useful for managing post delete sections
        // 2007-01-05 reid need for associations
//        this.getCustomerModifyContext().setTargetCustomer(null);

        if (this.getAssociate() != null) {
            this.getContactV2ListContext().setAssociate(this.getAssociate());
        }
        
        if (this.getContactV2ListContext().getAssociate() != null) {
            this.setAssociate(this.getContactV2ListContext().getAssociate());
        }
        
        if (this.getResetContacts() != null) {
            this.getContactV2ListContext().setParams(
                    new ContactV2CriteriaParameters());

            return Action.SUCCESS;
        }

        if (this.getSearchContacts() != null) {
            this.getContactV2ListContext().setParams(this.getParams());
            ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                    .build(this.getSessionContext());
            this.setContacts(sd.getListCri(this.getParams()));

        } else if (this.getContactV2ListContext().getParams() != null) {
            ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                    .build(this.getSessionContext());
            this.params = this.getContactV2ListContext().getParams();
            this.setContacts(sd.getListCri(this.getContactV2ListContext()
                    .getParams()));
        }

        return Action.SUCCESS;
    }

    public ContactV2ListContext getContactV2ListContext() {
        return contactV2ListContext;
    }

    public void setContactV2ListContext(
            ContactV2ListContext contactV2ListContext) {
        this.contactV2ListContext = contactV2ListContext;
    }

    public Collection getContacts() {
        return contacts;
    }

    public void setContacts(Collection contacts) {
        this.contacts = contacts;
    }

    public String getResetContacts() {
        return resetContacts;
    }

    public void setResetContacts(String resetContacts) {
        this.resetContacts = resetContacts;
    }

    public String getSearchContacts() {
        return searchContacts;
    }

    public void setSearchContacts(String searchContacts) {
        this.searchContacts = searchContacts;
    }

    public ContactV2CriteriaParameters getParams() {
        return params;
    }

    public void setParams(ContactV2CriteriaParameters params) {
        this.params = params;
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

    /**
     * @return Returns the statusLookups.
     */
    public Collection getClassLookups() throws Exception {
        return LookupServiceDelegateFactory.factory
                .build(this.getSystemOwner()).getAll(LookupType.CONTACT_CLASS);
    }

    public Collection getSourceLookups() throws Exception {
        return LookupServiceDelegateFactory.factory
                .build(this.getSystemOwner()).getAll(LookupType.CONTACT_SOURCE);
    }

    public Collection getInterestLookups() throws Exception {
        return LookupServiceDelegateFactory.factory
                .build(this.getSystemOwner()).getAll(
                        LookupType.CONTACT_INTEREST);
    }

    /**
     * @return the associate
     */
    public Boolean getAssociate() {
        return associate;
    }

    /**
     * @param associate the associate to set
     */
    public void setAssociate(Boolean associate) {
        this.associate = associate;
    }
    

}
