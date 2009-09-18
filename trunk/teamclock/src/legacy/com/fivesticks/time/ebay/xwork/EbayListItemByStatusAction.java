/*
 * Created on Mar 31, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.ItemStatusType;
import com.fivesticks.time.ebay.util.CustomerEbayItemByStatusHashBuilder;

/**
 * @author REID
 */
public class EbayListItemByStatusAction extends SessionContextAwareSupport {

    private String targetStatus;

    private Collection custItems;

    public String execute() throws Exception {

        EbayItemFilter filter = new EbayItemFilter();
        filter.setItemStatus(this.getTargetStatus());

        this.setCustItems(new CustomerEbayItemByStatusHashBuilder()
                .buildCustItems(new CustomerEbayItemByStatusHashBuilder()
                        .build(this.getSessionContext().getSystemOwner(),
                                filter)));

        return SUCCESS;
    }

    public String getTargetStatus() {
        return targetStatus;
    }

    public void setTargetStatus(String targetStatus) {
        this.targetStatus = targetStatus;
    }

    public Collection getCustItems() {
        return custItems;
    }

    public void setCustItems(Collection custItems) {
        this.custItems = custItems;
    }

    public ItemStatusType getItemStatusType() {
        return ItemStatusType.getByName(this.getTargetStatus());
    }
}