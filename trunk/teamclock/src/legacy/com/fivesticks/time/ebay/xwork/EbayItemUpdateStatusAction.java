/*
 * Created on Mar 28, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;

/**
 * @author Reid
 */
public class EbayItemUpdateStatusAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, CustomerModifyContextAware {

    private EbayItemListContext ebayItemListContext;

    private CustomerModifyContext customerModifyContext;

    private String targetStatus;

    private Long target;

    public String execute() throws Exception {

        if (this.getTargetStatus() != null && this.getTarget() != null) {

            ItemStatusType status = ItemStatusType.getByName(this
                    .getTargetStatus());

            if (status != null) {

                EbayItemServiceDelegate sd = EbayItemServiceDelegate.factory
                        .build(this.getSessionContext());

                EbayItem item = sd.load(this.getTarget());

                /*
                 * rsc 2005-08-17 this is just a quick check.
                 */
                if (item.getItemStatus().equals(
                        ItemStatusType.POSTED_TO_ACCOUNT.getName())
                        || item.getItemStatus().equals(
                                ItemStatusType.CLOSED_SOLD.getName())) {
                    this.getSessionContext().setMessage(
                            "Cannot update status from here.");
                }

                item.setItemStatus(this.getTargetStatus());

                sd.save(item);

            }

        }

        return SUCCESS;

    }

    /**
     * @return Returns the customerModifyContextAware.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContextAware
     *            The customerModifyContextAware to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContextAware) {
        this.customerModifyContext = customerModifyContextAware;
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

    /**
     * @return Returns the targetStatus.
     */
    public String getTargetStatus() {
        return targetStatus;
    }

    /**
     * @param targetStatus
     *            The targetStatus to set.
     */
    public void setTargetStatus(String targetStatus) {
        this.targetStatus = targetStatus;
    }

    /**
     * @return Returns the id.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setTarget(Long id) {
        this.target = id;
    }
}