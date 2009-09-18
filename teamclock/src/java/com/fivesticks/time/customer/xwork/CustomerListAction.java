/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerFilterVO;
import com.fivesticks.time.customer.util.CustomerListBuilder;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.menu.MenuSection;

/**
 * @author shuji
 * 
 */
public class CustomerListAction extends SessionContextAwareSupport implements
        CustomerListContextAware {

    private CustomerListContext customerListContext;

    private Collection customers;

    private String submitReset;

    private String special;
    
    private Boolean showHidden = Boolean.FALSE;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);
        
        this.getSessionContext().setMenuSection(MenuSection.CUSTOMERS);
        
        
        if (this.getCustomerListContext().getFilter() == null
                || this.getSubmitReset() != null) {
            this.getCustomerListContext().setFilter(new CustomerFilterVO());
            this.getCustomerListContext().getFilter().setReturnMaximum(25);
            return SUCCESS;
        }

        // Collection raw = FstxCustomerBD.factory.build(
        // this.getSessionContext().getSystemOwner()).searchByFilter(
        // this.getCustomerListContext().getFilter());
        //
        // this.setCustomers(new CustomerDisplayWrapperBuilder().build(this
        // .getSessionContext().getSystemOwner(), raw));


        this.getCustomerListContext().getFilter().setShowHidden(this.getShowHidden());
        
        this.setCustomers(new CustomerListBuilder().build(
                this.getSystemOwner(), this.getCustomerListContext()
                        .getFilter(), this.getSpecial()));
        return SUCCESS;
    }

    public Collection getCustomers() {
        return customers;
    }

    public void setCustomers(Collection customers) {
        this.customers = customers;
    }

    /**
     * @return Returns the customerListContext.
     */
    public CustomerListContext getCustomerListContext() {
        return customerListContext;
    }

    /**
     * @param customerListContext
     *            The customerListContext to set.
     */
    public void setCustomerListContext(CustomerListContext customerListContext) {
        this.customerListContext = customerListContext;
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

    public Collection getStatusLookups() throws Exception {
        return LookupServiceDelegateFactory.factory.build(this.getSystemOwner())
                .getAll(LookupType.CUSTOMER_STATUS);

    }

    /**
     * @return Returns the special.
     */
    public String getSpecial() {
        return special;
    }

    /**
     * @param special
     *            The special to set.
     */
    public void setSpecial(String special) {
        this.special = special;
    }

    /**
     * @return Returns the includeHidden.
     */
    public Boolean getShowHidden() {
        return showHidden;
    }

    /**
     * @param includeHidden The includeHidden to set.
     */
    public void setShowHidden(Boolean includeHidden) {
        this.showHidden = includeHidden;
    }

}