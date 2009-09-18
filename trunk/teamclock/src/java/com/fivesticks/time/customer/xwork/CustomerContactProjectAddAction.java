/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author REID
 */
public class CustomerContactProjectAddAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware {

    private Collection projectClassTypes;

    private CustomerModifyContext customerModifyContext;

    private String saveCustomer;

    private String cancelCustomer;

    private Customer customer = new Customer();

    private ContactV2 contact = new ContactV2();

    private Project project = new Project();

    private AccountTransaction time = new AccountTransaction();

    public String execute() throws Exception {

        if (this.getCancelCustomer() != null)
            return ERROR;

        if (this.getSaveCustomer() == null) {
            this.getCustomerModifyContext().setTargetContact(null);
            this.getCustomerModifyContext().setTargetCustomer(null);
            return INPUT;
        }

        try {
            validateAdd();
        } catch (Exception e) {
            this
                    .addActionError("An unknown error occurred and your information was not saved.");
            return INPUT;
        }

        if (this.hasErrors()) {
            return INPUT;
        }

        try {
            persist();
        } catch (Exception e) {
            this.addActionError("Add failed.");
            throw e;
        }

        /*
         * RSC 2005-08-13 Updating the id and setting the item filter since
         * we're heading over to the filter from here. RSC 2005-09-22 Still
         * useful -- head from here to customer detail. Remember that the detail
         * action refreshes all of the information in here.
         */
        this.getCustomerModifyContext().setTargetCustomer(this.getCustomer());

        // this.getCustomerModifyContext().setTargetContact(this.getContact());

        /*
         * done!
         */
        return SUCCESS;
    }

    public void validateAdd() throws Exception {
        ValidationHelper help = new ValidationHelper();

        if (help.isStringEmpty(this.getCustomer().getStreet1())) {
            this
                    .addFieldError("customer.street1",
                            "Street address is required");
        }
        if (help.isStringEmpty(this.getCustomer().getCity())) {
            this.addFieldError("customer.city", "City is required");
        }
        if (help.isStringEmpty(this.getCustomer().getState())) {
            this.addFieldError("customer.state", "State is required");
        }
        if (help.isStringEmpty(this.getCustomer().getZip())) {
            this.addFieldError("customer.zip", "Zip is required");
        }
        if (help.isStringEmpty(this.getCustomer().getName())) {
            this.addFieldError("customer.name", "Name is required");
        }
        /*
         * RSC 2005-09-22 Contact
         */
        if (help.isStringEmpty(this.getContactV2().getNameFirst())) {
            this.addFieldError("contact.nameFirst", "First name is required");
        }
        if (help.isStringEmpty(this.getContactV2().getNameLast())) {
            this.addFieldError("contact.nameLast", "Last name is missing");
        }
        /*
         * RSC 2005-09-22 Project -- Projects made through this method are
         * ALWAYS active and postable.
         */
        if (help.isStringEmpty(this.getProject().getLongName())) {
            this.addFieldError("project.longName",
                    "Project long name is required.");
        }
        if (!help.isNonZero(this.getProject().getProjectClassId())) {
            this.addFieldError("project.projectClassId", "You must enter a project class.");
        }
        if (help.isStringEmpty(this.getProject().getShortName())) {
            this.addFieldError("project.shortName",
                    "Project short name is required.");
        } else if (this.getProject().getShortName().indexOf(" ") > -1) {
            this
                   .addFieldError("project.shortName",
                            "Project short name cannot contain a space.  Only letters and numbers.");
        } else {
            Project t = ProjectServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).getFstxProjectByShortName(
                    this.getProject().getShortName());
            if (t != null) {
                this.addFieldError("project.shortName",
                        "Project's short name must be unique.");
            }
        }

        /*
         * RSC 2005-09-22 Time Account -- if it exists, must be positive.
         */
        if (this.getTime().getAmount() != null
                && this.getTime().getAmount().doubleValue() < 0.0) {
            this.addFieldError("time.amount",
                    "Amount must be greater than zero.");
        }

    }

    private void persist() throws Exception {

        // save the customer
        CustomerServiceDelegateFactory.factory.build(this.getSessionContext())
                .save(this.getCustomer());

        // save the contact
        this.getContactV2().setNameFormatted(
                this.getContactV2().getNameFirst() + " "
                        + this.getContactV2().getNameLast());
        
        ContactV2ServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).save(
                this.getContactV2());

        ContactV2ServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).associate(
                this.getContactV2(), this.getCustomer());

        // save the project
        this.getProject().setActive(true);
        this.getProject().setPostable(new Boolean(true));
        this.getProject().setCustId(this.getCustomer().getId());
        ProjectServiceDelegateFactory.factory.build(this.getSystemOwner())
                .save(this.getProject());

        // time -- only post if it is non zero
        if (this.getTime().getAmount() != null
                && this.getTime().getAmount().doubleValue() > 0.0) {
            CustomerAccountTransactionServiceDelegate.factory.buildTimeAccount(
                    this.getSystemOwner()).increase(this.getCustomer(),
                    this.getTime().getAmount(), this.getTime().getComments(),
                    this.getSessionContext().getUser().getUsername());
        }

    }

    public String getCancelCustomer() {
        return cancelCustomer;
    }

    public void setCancelCustomer(String cancelCustomer) {
        this.cancelCustomer = cancelCustomer;
    }

    public ContactV2 getContactV2() {
        return contact;
    }

    public void setContactV2(ContactV2 contact) {
        this.contact = contact;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSaveCustomer() {
        return saveCustomer;
    }

    public void setSaveCustomer(String saveCustomer) {
        this.saveCustomer = saveCustomer;
    }

    /*
     * 2005-12-21 RSC Not sure why this is here...
     */
    // public Collection getCommissions() throws Exception {
    //
    // return SimpleCommissionServiceDelegate.factory.build(
    // this.getSystemOwner()).getAll();
    //
    // }
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
     * @return Returns the project.
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project
     *            The project to set.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return Returns the time.
     */
    public AccountTransaction getTime() {
        return time;
    }

    /**
     * @param time
     *            The time to set.
     */
    public void setTime(AccountTransaction time) {
        this.time = time;
    }

    public Collection getStatusLookups() throws Exception {
        return LookupServiceDelegateFactory.factory
                .build(this.getSystemOwner())
                .getAll(LookupType.CUSTOMER_STATUS);
    }

    public Collection getCountries() {
        return new GeographicCollection().getCountries();
    }

    /**
     * @return Returns the projectClassTypes.
     */
    public Collection getProjectClassTypes() throws Exception {
        if (projectClassTypes == null) {
            this.setProjectClassTypes(LookupServiceDelegateFactory.factory
                    .build(this.getSystemOwner()).getAll(
                            LookupType.PROJECT_CLASS));
        }
        return projectClassTypes;
    }

    /**
     * @param projectClassTypes
     *            The projectClassTypes to set.
     */
    public void setProjectClassTypes(Collection projectClassTypes) {
        this.projectClassTypes = projectClassTypes;
    }
}