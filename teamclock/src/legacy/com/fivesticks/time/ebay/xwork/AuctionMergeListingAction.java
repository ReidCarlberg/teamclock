/*
 * Created on Jun 8, 2005
 *
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormDataMerger;
import com.fivesticks.time.ebay.setup.forms.util.FormDataMergerFactory;

/**
 * @author Reid
 * 
 */
public class AuctionMergeListingAction extends SessionContextAwareSupport
        implements EbayItemModifyContextAware, CustomerModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;

    private CustomerModifyContext customerModifyContext;

    private Long target;

    private String mergedData;

    public String execute() throws Exception {

        EbayForm t = EbayFormServiceDelegate.factory.build(
                this.getSystemOwner()).load(this.getTarget());

        FormDataMerger merger = FormDataMergerFactory.build(this
                .getSystemOwner(), t, this.getEbayItemModifyContext()
                .getTarget(), this.getSessionContext().getSettings()
                .getEbayImagesSrc(), this.getCustomerModifyContext()
                .getTargetCustomer());

        this.setMergedData(merger.getMergedData());

        return SUCCESS;
    }

    /**
     * @return Returns the ebayItemModifyContext.
     */
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }

    /**
     * @param ebayItemModifyContext
     *            The ebayItemModifyContext to set.
     */
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }

    /**
     * @return Returns the mergedData.
     */
    public String getMergedData() {
        return mergedData;
    }

    /**
     * @param mergedData
     *            The mergedData to set.
     */
    public void setMergedData(String mergedData) {
        this.mergedData = mergedData;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
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
}
