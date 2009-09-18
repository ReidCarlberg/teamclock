/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.customer.contact.ContactServiceDelegate;
import com.fivesticks.time.customer.contact.ContactServiceDelegateException;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;

/**
 * @author REID
 */
public class ModifyEbayCustomerAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware, EbayItemListContextAware {

    private CustomerModifyContext customerModifyContext;
    
    private EbayItemListContext ebayItemListContext;

    private Long target;

    private String saveCustomer;

    private String cancelCustomer;

    private String deleteCustomer;

    private Long customerCommission;

    private FstxCustomer customer = new FstxCustomer();

    private Contact contact = new Contact();

    private boolean added;

    public String execute() throws Exception {

        if (this.getCancelCustomer() != null)
            return SUCCESS;

        if (this.getCustomerModifyContext().getTargetCustomer() != null
                && this.getDeleteCustomer() != null) {
            CustomerServiceDelegate bd = CustomerServiceDelegate.factory.build(this
                    .getSystemOwner());
            try {
                bd.delete(this.getCustomerModifyContext().getTargetCustomer());
            } catch (CustomerServiceDelegateException e) {
                this.getSessionContext().setMessage(
                        "Can't delete the customer -- has projects or items.");
                return INPUT;
            }

            ContactServiceDelegate.factory.build(this.getSystemOwner()).delete(
                    this.getCustomerModifyContext().getTargetContact());
            
            return SUCCESS;
        }

        if (this.getTarget() != null) {
            FstxCustomer c = CustomerServiceDelegate.factory.build(
                    this.getSessionContext()).getFstxCustomer(this.getTarget());
            Collection contacts = ContactServiceDelegate.factory.build(
                    this.getSessionContext().getSystemOwner()).getByCustomer(c);
            Contact cont = new Contact();
            if (contacts.size() > 0) {
                cont = (Contact) contacts.toArray()[0];
            }
            this.getCustomerModifyContext().setTargetCustomer(c);
            this.setCustomer(c);
            this.getCustomerModifyContext().setTargetContact(cont);
            this.setContact(cont);

            Long over = null;

            try {
                over = new Long(ObjectMetricServiceDelegate.factory.build(
                        this.getSystemOwner()).getMetricValue(c,
                        CustomerSettingType.AUCTION_COMMISSION_OVERRIDE));
            } catch (Exception e) {

            }

            if (over != null && over.longValue() > 0)
                this.setCustomerCommission(over);
            else
                this.setCustomerCommission(new Long(this.getSessionContext()
                        .getSettings().getEbayDefaultCommission()));

            return INPUT;
        }

        if (this.getSaveCustomer() == null && this.getDeleteCustomer() == null) {
            this.getCustomerModifyContext().setTargetContact(null);
            this.getCustomerModifyContext().setTargetCustomer(null);
            return INPUT;
        }

        validate();

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
         * RSC 2005-08-13 Seems like a good idea to simply go to the items page.
         */
        if (this.isAdded())
            return "success.added";

        return SUCCESS;
    }

    public void validate() {
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
        if (help.isStringEmpty(this.getContact().getNameFirst())) {
            this.addFieldError("contact.nameFirst", "First name is required");
        }
        if (help.isStringEmpty(this.getContact().getNameLast())) {
            this.addFieldError("contact.nameLast", "Last name is missing");
        }

    }

    private void persist() throws CustomerServiceDelegateException,
            ContactServiceDelegateException,
            ObjectMetricServiceDelegateException {
        if (this.getCustomerModifyContext().getTargetCustomer() != null) {
            this.getCustomer()
                    .setId(
                            this.getCustomerModifyContext().getTargetCustomer()
                                    .getId());
            this.getCustomer().setOwnerKey(
                    this.getCustomerModifyContext().getTargetCustomer()
                            .getOwnerKey());
            this.getContact().setId(
                    this.getCustomerModifyContext().getTargetContact().getId());
            this.getContact().setOwnerKey(
                    this.getCustomerModifyContext().getTargetContact()
                            .getOwnerKey());
        } else {
            this.setAdded(true);
            

            
        }

        if (this.getCustomer().getName() == null
                || this.getCustomer().getName().length() == 0) {
            this.getCustomer().setName(
                    contact.getNameFirst() + " " + contact.getNameLast());
        }

        CustomerServiceDelegate.factory.build(this.getSessionContext()).save(
                this.getCustomer());

        this.getContact().setCustId(this.getCustomer().getId());

        ContactServiceDelegate.factory.build(
                this.getSessionContext().getSystemOwner()).save(
                this.getContact());

        if (this.getCustomerModifyContext().getTargetCustomer() == null) {
            ObjectMetricServiceDelegate.factory.build(
                    this.getSessionContext().getSystemOwner()).setValue(
                    this.getCustomer(),
                    CustomerSettingType.IS_AUCTION_CUSTOMER, "true");
        }

        /*
         * commission
         */
        if (this.getCustomerCommission() == null) {
            this.setCustomerCommission(new Long(0));
        }
        ObjectMetricServiceDelegate omsd = ObjectMetricServiceDelegate.factory
                .build(this.getSystemOwner());

        if (this.getCustomerCommission().longValue() == 0
                || this.getCustomerCommission().longValue() == this
                        .getSessionContext().getSettings()
                        .getEbayDefaultCommission()) {
            omsd.setValue(this.getCustomer(),
                    CustomerSettingType.AUCTION_COMMISSION_OVERRIDE, "");
        } else {
            omsd.setValue(this.getCustomer(),
                    CustomerSettingType.AUCTION_COMMISSION_OVERRIDE, this
                            .getCustomerCommission().toString());
        }

        /*
         * RSC 2005-08-13 Updating the id and setting the item filter since
         * we're heading over to the filter from here.
         */
        this.getCustomerModifyContext().setTargetCustomer(this.getCustomer());

        this.getCustomerModifyContext().setTargetContact(this.getContact());
        
        if (this.isAdded()) {
            this.getEbayItemListContext().setFilter(new EbayItemFilter());
            this.getEbayItemListContext().getFilter().setCustId(this.getCustomer().getId());
            this.getEbayItemListContext().setViewType(ViewType.AGREEMENT);
        }

    }

    public String getCancelCustomer() {
        return cancelCustomer;
    }

    public void setCancelCustomer(String cancelCustomer) {
        this.cancelCustomer = cancelCustomer;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public FstxCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(FstxCustomer customer) {
        this.customer = customer;
    }

    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public String getDeleteCustomer() {
        return deleteCustomer;
    }

    public void setDeleteCustomer(String deleteCustomer) {
        this.deleteCustomer = deleteCustomer;
    }

    public String getSaveCustomer() {
        return saveCustomer;
    }

    public void setSaveCustomer(String saveCustomer) {
        this.saveCustomer = saveCustomer;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Collection getCommissions() throws Exception {

        return SimpleCommissionServiceDelegate.factory.build(
                this.getSystemOwner()).getAll();

    }

    /**
     * @return Returns the customerCommission.
     */
    public Long getCustomerCommission() {
        return customerCommission;
    }

    /**
     * @param customerCommission
     *            The customerCommission to set.
     */
    public void setCustomerCommission(Long customerCommission) {
        this.customerCommission = customerCommission;
    }

    /**
     * @return Returns the added.
     */
    public boolean isAdded() {
        return added;
    }

    /**
     * @param added
     *            The added to set.
     */
    public void setAdded(boolean added) {
        this.added = added;
    }

    /**
     * @return Returns the ebayItemListContext.
     */
    public EbayItemListContext getEbayItemListContext() {
        return ebayItemListContext;
    }

    /**
     * @param ebayItemListContext The ebayItemListContext to set.
     */
    public void setEbayItemListContext(EbayItemListContext ebayItemListContext) {
        this.ebayItemListContext = ebayItemListContext;
    }
}