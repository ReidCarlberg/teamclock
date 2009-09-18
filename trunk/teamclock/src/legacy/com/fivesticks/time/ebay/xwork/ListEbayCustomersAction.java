/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormType;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author REID
 */
public class ListEbayCustomersAction extends SessionContextAwareSupport implements ListEbayCustomerContextAware {

    

    private Collection customers;
    
    private Collection forms;
    
    private ListEbayCustomerContext listEbayCustomerContext;
    
    private String submitReset;
    
    private boolean detail;

    public String execute() throws Exception {

        if (this.getSubmitReset() != null)
            this.getListEbayCustomerContext().setFilter(null);
        
        /*
         * RSC 2005-08-13 Check that we have a filter.  If not create one
         * and return success.
         */
        if (this.getListEbayCustomerContext().getFilter() == null) {
            this.getListEbayCustomerContext().setFilter(new FstxCustomerFilterVO());
            this.getListEbayCustomerContext().getFilter().setReturnMaximum(25);
            return SUCCESS;
        }
        
    	this.getSessionContext().setSuccessOverride(null);
    	
    	this.setForms(EbayFormServiceDelegate.factory.build(this.getSystemOwner()).find(FormType.CUSTOMER));
    	
        this.setCustomers(new EbayCustomerCollectionBuilder().buildSortedByContact(this.getSessionContext(), this.getListEbayCustomerContext().getFilter()));

        if (this.isDetail()) {
            return SUCCESS + ".detail";
        }
        return SUCCESS;
    }



    public Collection getCustomers() {
        return customers;
    }

    public void setCustomers(Collection customers) {
        this.customers = customers;
    }
    /**
     * @return Returns the forms.
     */
    public Collection getForms() {
        return forms;
    }
    /**
     * @param forms The forms to set.
     */
    public void setForms(Collection forms) {
        this.forms = forms;
    }



    /**
     * @return Returns the listEbayCustomerContext.
     */
    public ListEbayCustomerContext getListEbayCustomerContext() {
        return listEbayCustomerContext;
    }



    /**
     * @param listEbayCustomerContext The listEbayCustomerContext to set.
     */
    public void setListEbayCustomerContext(
            ListEbayCustomerContext listEbayCustomerContext) {
        this.listEbayCustomerContext = listEbayCustomerContext;
    }
    
    public Collection getStatusLookups() throws Exception {
        return LookupServiceDelegate.factory.build(this.getSystemOwner()).getAll(LookupType.CUSTOMER_STATUS);
        
    }



    /**
     * @return Returns the submitReset.
     */
    public String getSubmitReset() {
        return submitReset;
    }



    /**
     * @param submitReset The submitReset to set.
     */
    public void setSubmitReset(String submitReset) {
        this.submitReset = submitReset;
    }



    /**
     * @return Returns the detail.
     */
    public boolean isDetail() {
        return detail;
    }



    /**
     * @param detail The detail to set.
     */
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    
}