/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;

/**
 * @author REID
 */
public class MarkItemsClosedByContextAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, CustomerModifyContextAware {

//    private static Log log = LogFactory
//            .getLog(MarkItemsClosedByContextAction.class);

    private EbayItemListContext ebayItemListContext;

    private CustomerModifyContext customerModifyContext;


    public String execute() throws Exception {

        if (this.getEbayItemListContext().getResults() == null) {
            this.getSessionContext().setMessage("No results to mark as closed.");
        }
        
//        FstxCustomerBD bd = FstxCustomerBD.factory.build(this.getSystemOwner());
        
        for (Iterator iter = this.getEbayItemListContext().getResults().iterator(); iter.hasNext();) {
            EbayItemDisplay element = (EbayItemDisplay) iter.next();
            
            /*
             * rsc 2005-08-17 just to be sure...
             */
            if (element.getItem().getItemStatus().equals(ItemStatusType.POSTED_TO_ACCOUNT.getName())) {
                
                element.getItem().setItemStatus(ItemStatusType.CLOSED_SOLD.getName());
                
                EbayItemServiceDelegate.factory.build(this.getSystemOwner()).save(element.getItem());
                
            }
            

        }

        return SUCCESS;
    }

    public EbayItemListContext getEbayItemListContext() {
        return ebayItemListContext;
    }

    public void setEbayItemListContext(EbayItemListContext ebayItemListContext) {
        this.ebayItemListContext = ebayItemListContext;
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