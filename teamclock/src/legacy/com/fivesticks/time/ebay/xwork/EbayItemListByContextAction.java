/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormType;
import com.fivesticks.time.ebay.util.DisplayableEbayItemCustomerComparator;
import com.fivesticks.time.ebay.util.EbayItemCollectionStatistics;
import com.fivesticks.time.ebay.util.EbayItemCollectionStatisticsBuilder;
import com.fivesticks.time.ebay.util.EbayItemShortDescriptionComparator;

/**
 * @author REID
 */
public class EbayItemListByContextAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, CustomerModifyContextAware {

    private static Log log = LogFactory
            .getLog(EbayItemListByContextAction.class);

    private EbayItemListContext ebayItemListContext;

    private CustomerModifyContext customerModifyContext;

    private Collection forms;

    private Collection items;

    private Collection customers;

    private Collection itemStatusTypes = ItemStatusType.getAll();

    private Collection updatableItemStatusTypes = ItemStatusType.getUpdatable();

    private Collection openClosedTypes = OpenClosedType.getAll();

    private Collection viewTypes = ViewType.getAll();

    private EbayItemCollectionStatistics stats;

    private String submitReset;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.setCustomers(new EbayCustomerCollectionBuilder()
                .buildSortedByContact(this.getSessionContext()));

        /*
         * RSC 2005-08-13 if this is the first time here, don't bother listing
         * anything.
         */
        if (this.getEbayItemListContext().getFilter() == null
                || this.getSubmitReset() != null) {
            log.info("setting filter to a new filter.");
            this.getEbayItemListContext().setFilter(new EbayItemFilter());
            this.getEbayItemListContext().getFilter().setReturnMaximum(10);

            if (this.getCustomerModifyContext().getTargetCustomer() != null) {
                this.getEbayItemListContext().getFilter().setCustId(
                        this.getCustomerModifyContext().getTargetCustomer()
                                .getId());
                log.info("set custid to "
                        + this.getCustomerModifyContext().getTargetCustomer()
                                .getId());
            } else {
                log.info("didn't initialize filter.");
            }
            return SUCCESS;
        }

        /*
         * RSC 2005-08-13 viewing the detail sets something into the cust mod
         * context but not into the filter cust id. so we should reset those.
         */

        if (this.getEbayItemListContext().getFilter().getCustId() == null) {
            this.getCustomerModifyContext().setTargetCustomer(null);
            this.getCustomerModifyContext().setTargetContact(null);
        }

        log.info("return maximum "
                + this.getEbayItemListContext().getFilter().getReturnMaximum());

        // if (this.getEbayItemListContext().getCustomerId() == null
        // || this.getEbayItemListContext().getCustomerId().longValue() == 0) {
        // this.getCustomerModifyContext().setTargetCustomer(null);
        // }

        new EbayItemFilterDecorator().build(this.getEbayItemListContext());

        if (this.getEbayItemListContext().getViewType() == ViewType.STANDARD_DUMP) {
            this.getEbayItemListContext().getFilter().setReturnMaximum(0);
        } 
        
        Collection temp = EbayItemServiceDelegate.factory.build(
                this.getSessionContext()).find(
                this.getEbayItemListContext().getFilter());

        stats = new EbayItemCollectionStatisticsBuilder().build(temp);

        Collection sorted = null;

            sorted = new TreeSet(new EbayItemShortDescriptionComparator());

        sorted.addAll(temp);

        items = new EbayItemDisplayCollectionBuilder().getDisplayable(sorted,
                this.getSystemOwner());
        
        if (this.getEbayItemListContext().getViewType() == ViewType.STANDARD_DUMP) {
            Collection tempItems = new TreeSet(new DisplayableEbayItemCustomerComparator());
            tempItems.addAll(items);
            items = tempItems;
            this.getEbayItemListContext().setViewType(ViewType.STANDARD);
        }

        
        this.getEbayItemListContext().setResults(items);

        this.setForms(EbayFormServiceDelegate.factory.build(
                this.getSystemOwner()).find(FormType.ITEM));

        return SUCCESS;
    }

    public EbayItemListContext getEbayItemListContext() {
        return ebayItemListContext;
    }

    public void setEbayItemListContext(EbayItemListContext ebayItemListContext) {
        this.ebayItemListContext = ebayItemListContext;
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }

    public Collection getCustomers() {
        return customers;
    }

    public void setCustomers(Collection customers) {
        this.customers = customers;
    }

    public Collection getItemStatusTypes() {
        return itemStatusTypes;
    }

    public void setItemStatusTypes(Collection itemStatus) {
        this.itemStatusTypes = itemStatus;
    }

    public EbayItemCollectionStatistics getStats() {
        return stats;
    }

    public void setStats(EbayItemCollectionStatistics stats) {
        this.stats = stats;
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
     * @return Returns the openClosed.
     */
    public Collection getOpenClosedTypes() {
        return openClosedTypes;
    }

    /**
     * @param openClosed
     *            The openClosed to set.
     */
    public void setOpenClosedTypes(Collection openClosed) {
        this.openClosedTypes = openClosed;
    }

    /**
     * @return Returns the view.
     */
    public Collection getViewTypes() {
        return viewTypes;
    }

    /**
     * @param view
     *            The view to set.
     */
    public void setViewTypes(Collection view) {
        this.viewTypes = view;
    }

    public Collection getItemEbayForms() throws Exception {

        return EbayFormServiceDelegate.factory.build(this.getSystemOwner())
                .find(FormType.ITEM);
    }

    /**
     * @return Returns the forms.
     */
    public Collection getForms() {
        return forms;
    }

    /**
     * @param forms
     *            The forms to set.
     */
    public void setForms(Collection forms) {
        this.forms = forms;
    }

    public Collection getCustomerForms() throws Exception {
        return EbayFormServiceDelegate.factory.build(this.getSystemOwner())
                .find(FormType.CUSTOMER);
    }

    /**
     * @return Returns the submitReset.
     */
    public String getSubmitReset() {
        return submitReset;
    }

    /**
     * @param submitReset
     *            The submitReset to set.
     */
    public void setSubmitReset(String submitReset) {
        this.submitReset = submitReset;
    }

    /**
     * @return Returns the updatableItemStatusTypes.
     */
    public Collection getUpdatableItemStatusTypes() {
        return updatableItemStatusTypes;
    }

    /**
     * @return Returns the okToPost.
     */
    public boolean isOkToPost() {
        return this.getEbayItemListContext().getFilter().getItemStatus()
                .equals(ItemStatusType.READY_TO_PAY_NET.getName());
    }

    /**
     * @return Returns the okToPost.
     */
    public boolean isOkToClose() {
        return this.getEbayItemListContext().getFilter().getItemStatus()
                .equals(ItemStatusType.POSTED_TO_ACCOUNT.getName());
    }

}