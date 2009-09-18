/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.CommissionDiscountType;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemDeleteCommand;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;
import com.fivesticks.time.ebay.util.EbayItemCalculator;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author REID
 */
public class ModifyAction extends SessionContextAwareSupport implements
        CustomerModifyContextAware, EbayItemModifyContextAware {

    // private NumberFormat format = new DecimalFormat("0.00");

    private CustomerModifyContext customerModifyContext;

    private EbayItemModifyContext ebayItemModifyContext;

    private Long target;

    private String ebaySubmit;

    private String ebayCancel;

    private String ebayDelete;

    private String ebayCopy;

    private Collection itemStatus = ItemStatusType.getAll();

    private EbayItem targetItem = new EbayItem();

    public String execute() throws Exception {

        if (this.getEbayCancel() != null) {
            return this.getSuccess();
        }
        if (this.getEbayDelete() != null) {
            if (this.getEbayItemModifyContext().getTarget() == null)
                return ERROR;

            EbayItemDeleteCommand dc = new EbayItemDeleteCommand(this
                    .getEbayItemModifyContext().getTarget());
            this.getSessionContext().setDeleteContext(
                    DeleteContext.factory.build(dc, this.getSessionContext()
                            .getSuccessOverride()));
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        }
        if (this.getTarget() != null) {
            EbayItem i = EbayItemServiceDelegate.factory.build(
                    this.getSessionContext().getSystemOwner()).load(
                    this.getTarget());
            this.setTargetItem(i);
            this.getEbayItemModifyContext().setTarget(i);
            this.getCustomerModifyContext().setTargetCustomer(
                    CustomerServiceDelegate.factory.build(this.getSessionContext())
                            .getFstxCustomer(i.getCustId()));
            return INPUT;
        }
        if (this.getEbaySubmit() == null) {
            // adding
            if (this.getCustomerModifyContext().getTargetCustomer() == null)
                return ERROR;

            this.getEbayItemModifyContext().setTarget(null);
            this.getTargetItem().setDateAdded(new Date());
            this.getTargetItem().setLength(new Integer(7));
            this.getTargetItem().setPriceStart(new Double(0.99));
            this.getTargetItem().setItemStatus(ItemStatusType.ADDED.getName());
            return INPUT;
        }

        if (this.getEbayCopy() != null) {
            this.setTargetItem(this.getEbayItemModifyContext().getTarget());
            this.getTargetItem().setDescriptionShort(
                    "COPY OF " + this.getTargetItem().getDescriptionShort());
            this.getEbayItemModifyContext().setTarget(null);
            this.getTargetItem().setId(null);
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getEbayItemModifyContext().getTarget() != null) {
            this.getTargetItem().setId(
                    this.getEbayItemModifyContext().getTarget().getId());

            if (!ItemStatusType.isModifiable(this.getEbayItemModifyContext()
                    .getTarget().getItemStatus())) {
                this.getTargetItem().setItemStatus(
                        this.getEbayItemModifyContext().getTarget()
                                .getItemStatus());
            }

            new EbayItemCalculator().handleCalculate(this.getTargetItem(), this
                    .getSystemOwner(), this.getCustomerModifyContext()
                    .getTargetCustomer(), this.getSessionContext().getSettings().getEbayDefaultCommission());

        }
        this.getTargetItem().setCustId(
                this.getCustomerModifyContext().getTargetCustomer().getId());

        EbayItemServiceDelegate.factory.build(this.getSessionContext()).save(
                this.getTargetItem());

        /*
         * update the item in the context
         */
        this.getEbayItemModifyContext().setTarget(this.getTargetItem());

        // if (this.getSessionContext().getSuccessOverride() != null)
        // return this.getSessionContext().getSuccessOverride();
        // else
        // return SUCCESS;

        return this.getSuccess();
    }

    public void validate() {
        if (this.getTargetItem().getDescriptionShort() == null
                || this.getTargetItem().getDescriptionShort().trim().length() == 0) {
            this.addFieldError("targetItem.descriptionShort",
                    "Short Description is Required");
        }
    }

    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public String getEbayCancel() {
        return ebayCancel;
    }

    public void setEbayCancel(String ebayCancel) {
        this.ebayCancel = ebayCancel;
    }

    public String getEbayDelete() {
        return ebayDelete;
    }

    public void setEbayDelete(String ebayDelete) {
        this.ebayDelete = ebayDelete;
    }

    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }

    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }

    public String getEbaySubmit() {
        return ebaySubmit;
    }

    public void setEbaySubmit(String ebaySubmit) {
        this.ebaySubmit = ebaySubmit;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public EbayItem getTargetItem() {
        return targetItem;
    }

    public void setTargetItem(EbayItem targetItem) {
        this.targetItem = targetItem;
    }

    /**
     * @return Returns the itemStatus.
     */
    public Collection getItemStatus() {
        return itemStatus;
    }

    /**
     * @param itemStatus
     *            The itemStatus to set.
     */
    public void setItemStatus(Collection itemStatus) {
        this.itemStatus = itemStatus;
    }

    // public String getFormatted(Double value) {
    // return this.format.format(value);
    // }
    /**
     * @return Returns the ebayCopy.
     */
    public String getEbayCopy() {
        return ebayCopy;
    }

    /**
     * @param ebayCopy
     *            The ebayCopy to set.
     */
    public void setEbayCopy(String ebayCopy) {
        this.ebayCopy = ebayCopy;
    }

    public Collection getDiscountTypeLookups() throws Exception {
        return LookupServiceDelegate.factory.build(this.getSystemOwner())
                .getAll(LookupType.AUC_DISC_REASON);
    }

    public Collection getDiscountMethods() throws Exception {
        return CommissionDiscountType.getDiscountTypes();
    }

    public Collection getCommissions() throws Exception {
        return SimpleCommissionServiceDelegate.factory.build(
                this.getSystemOwner()).getAll();
    }

}