/*
 * Created on Mar 31, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;

/**
 * @author REID
 */
public class ShowFormAction extends SessionContextAwareSupport implements
        EbayItemModifyContextAware, CustomerModifyContextAware {

    private EbayItemModifyContext ebayItemModifyContext;
    
    private CustomerModifyContext customerModifyContext;
    
    private String form;
    
    public String execute() throws Exception {
        if (this.getForm() == null)
            return INPUT;
        
        return SUCCESS;
    }
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }
    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
}
