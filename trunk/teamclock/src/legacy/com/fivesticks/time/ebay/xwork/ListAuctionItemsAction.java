/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ListType;

/**
 * @author REID
 */
public class ListAuctionItemsAction extends SessionContextAwareSupport implements
        CustomerModifyContextAware {


    private CustomerModifyContext customerModifyContext;

    private Collection items;

    private Long target;
    
    /*
     * A= all
     * B= added not listed
     * C= listed open
     * D= listed closed not paid
     * E= completed
     */
    private String type;

    public String execute() throws Exception {


        items = EbayItemServiceDelegate.factory.build(
                this.getSessionContext().getSystemOwner()).find(this.getListType());

        return SUCCESS;
    }
    
    public ListType getListType() {
        String type = this.getType();
        ListType ret = ListType.ALL;
        if (type == null) {
            
        } else if (type.equalsIgnoreCase("b")) {
            ret = ListType.TOLIST;
        } else if (type.equalsIgnoreCase("c")) {
            ret = ListType.OPEN;
        } else if (type.equalsIgnoreCase("d")) {
            ret = ListType.TOPAY;
        } else if (type.equalsIgnoreCase("e")) {
            ret = ListType.CLOSED;
        }
        return ret;
    }

    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }



    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}