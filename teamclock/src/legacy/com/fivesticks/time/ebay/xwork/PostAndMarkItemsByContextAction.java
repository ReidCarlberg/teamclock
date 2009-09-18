/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.ebay.EbayItemServiceDelegate;
import com.fivesticks.time.ebay.ItemStatusType;

/**
 * @author REID
 */
public class PostAndMarkItemsByContextAction extends SessionContextAwareSupport
        implements EbayItemListContextAware, CustomerModifyContextAware {

//    private static Log log = LogFactory
//            .getLog(PostAndMarkItemsByContextAction.class);

    private EbayItemListContext ebayItemListContext;

    private CustomerModifyContext customerModifyContext;

    public String execute() throws Exception {

        if (this.getEbayItemListContext().getResults() == null) {
            this.getSessionContext().setMessage("No results to mark and post.");
        }

        // FstxCustomerBD bd =
        // FstxCustomerBD.factory.build(this.getSystemOwner());

        for (Iterator iter = this.getEbayItemListContext().getResults()
                .iterator(); iter.hasNext();) {
            EbayItemDisplay element = (EbayItemDisplay) iter.next();

            /*
             * rsc 2005-08-17 just to be sure...
             */
            if (element.getItem().getPriceFinal().doubleValue() > 0.0
                    && element.getItem().getItemStatus().equals(
                            ItemStatusType.READY_TO_PAY_NET.getName())) {

                StringBuffer comments = new StringBuffer();

                comments.append("Sold item: "
                        + element.getItem().getDescriptionShort());
                comments.append(" (" + element.getItem().getId() + ") ");
                comments.append(" Added: " + element.getItem().getDateAdded());
                comments.append(" Price: " + element.getItem().getPriceFinal());
                comments.append(" Customer Net: "
                        + element.getItem().getCustomerNet());

                if (element.getItem().getCustomerNet().doubleValue() != 0.0) {
                    CustomerAccountTransactionServiceDelegate.factory
                            .buildAuctionAccount(
                                    this.getSessionContext().getSystemOwner())
                            .decrease(
                                    element.getCustomer(),
                                    element.getItem().getCustomerNet(),
                                    comments.toString(),
                                    this.getSessionContext().getUser()
                                            .getUsername(),
                                    element.getItem().getId());
                }

                element.getItem().setItemStatus(
                        ItemStatusType.POSTED_TO_ACCOUNT.getName());

                element.getItem().setDateNetPaid(new Date());

                EbayItemServiceDelegate.factory.build(this.getSystemOwner())
                        .save(element.getItem());

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