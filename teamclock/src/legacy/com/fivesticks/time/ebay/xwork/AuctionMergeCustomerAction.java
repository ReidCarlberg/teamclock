/*
 * Created on Jun 8, 2005
 *
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.customer.contact.ContactServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormDataMerger;
import com.fivesticks.time.ebay.setup.forms.util.FormDataMergerFactory;

/**
 * @author Reid
 *  
 */
public class AuctionMergeCustomerAction extends SessionContextAwareSupport
        implements EbayItemModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;

    private Long targetCustomer;

    private Long target;

    private String mergedData;

    public String execute() throws Exception {

        EbayForm t = EbayFormServiceDelegate.factory.build(
                this.getSystemOwner()).load(this.getTarget());

        FstxCustomer cust = CustomerServiceDelegate.factory.build(
                this.getSessionContext()).getFstxCustomer(
                this.getTargetCustomer());

        Collection contacts = ContactServiceDelegate.factory.build(
                this.getSystemOwner()).getByCustomer(cust);

        Contact cont = new Contact();

        if (contacts.size() > 0)
            cont = (Contact) contacts.toArray()[0];

        FormDataMerger merger = FormDataMergerFactory.build( t, cust, cont);

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
     * @return Returns the targetCustomer.
     */
    public Long getTargetCustomer() {
        return targetCustomer;
    }

    /**
     * @param targetCustomer
     *            The targetCustomer to set.
     */
    public void setTargetCustomer(Long targetCustomer) {
        this.targetCustomer = targetCustomer;
    }
}
