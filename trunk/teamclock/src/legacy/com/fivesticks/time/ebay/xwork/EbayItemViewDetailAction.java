/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.contact.ContactServiceDelegate;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemShippingServiceDelegate;
import com.fivesticks.time.ebay.setup.commission.SimpleCommission;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormType;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;

/**
 * @author REID
 */
public class EbayItemViewDetailAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, EbayItemModifyContextAware,
        CustomerModifyContextAware {

    public static final String SUCCESS_DETAIL = "success.detail";

    private EbayItemListContext ebayItemListContext;

    private EbayItemModifyContext ebayItemModifyContext;

    private CustomerModifyContext customerModifyContext;

    private Long target;

public String execute() throws Exception {

        if (this.getTarget() == null
                && this.getEbayItemModifyContext().getTarget() == null)
            return INPUT;

        if (this.getTarget() != null) {
            this.getEbayItemModifyContext().setTarget(
                    EbayItemServiceDelegate.factory.build(
                            this.getSessionContext()).load(this.getTarget()));

            this.getEbayItemModifyContext().setImages(null);

            this.getEbayItemModifyContext().setShipping(null);

            this.getCustomerModifyContext().setTargetCustomer(
                    CustomerServiceDelegate.factory.build(this.getSessionContext())
                            .getFstxCustomer(
                                    this.getEbayItemModifyContext().getTarget()
                                            .getCustId()));

            this.getCustomerModifyContext().setContacts(
                    ContactServiceDelegate.factory.build(this.getSystemOwner())
                            .getByCustomer(
                                    this.getCustomerModifyContext()
                                            .getTargetCustomer()));
        }

        if (this.getEbayItemModifyContext().getImages() == null) {
            this.getEbayItemModifyContext().setImages(
                    EbayItemImageServiceDelegate.factory.build(
                            this.getSessionContext().getSystemOwner()).getAll(
                            this.getEbayItemModifyContext().getTarget()));
        }

        if (this.getEbayItemModifyContext().getShipping() == null) {
            this.getEbayItemModifyContext().setShipping(
                    ItemShippingServiceDelegate.factory.build(
                            this.getSystemOwner()).findByEbayItem(
                            this.getEbayItemModifyContext().getTarget()));
        }
        this.getSessionContext().setSuccessOverride(SUCCESS_DETAIL);

        if (this.getEbayItemModifyContext().getTarget()
                .getCommissionDiscountTypeLuId() != null) {
            Lookup l = LookupServiceDelegate.factory.build(this.getSystemOwner()).get(this.getEbayItemModifyContext().getTarget()
                .getCommissionDiscountTypeLuId());
            
            this.getEbayItemModifyContext().setDiscountReason(l.getFullName());
        }
        
        if (this.getEbayItemModifyContext().getTarget().getSimpleCommissionId()
             != null) {
                 SimpleCommission sc = SimpleCommissionServiceDelegate.factory.build(this.getSystemOwner()).get(this.getEbayItemModifyContext().getTarget().getSimpleCommissionId());
                 
                 if (sc != null)
                     this.getEbayItemModifyContext().setCommissionOverride(sc.getName());
             } else {
                 this.getEbayItemModifyContext().setCommissionOverride(null);
             }
        return SUCCESS;

    }    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }

    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    /**
     * @return Returns the ebayItemListContext.
     */
    public EbayItemListContext getEbayItemListContext() {
        return ebayItemListContext;
    }

    /**
     * @param ebayItemListContext
     *            The ebayItemListContext to set.
     */
    public void setEbayItemListContext(EbayItemListContext ebayItemListContext) {
        this.ebayItemListContext = ebayItemListContext;
    }

    public Collection getListingEbayForms() throws Exception {
        return EbayFormServiceDelegate.factory.build(this.getSystemOwner())
                .find(FormType.LISTING);
    }

}