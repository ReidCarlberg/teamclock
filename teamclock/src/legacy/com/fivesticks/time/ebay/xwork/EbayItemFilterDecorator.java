/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import com.fivesticks.time.ebay.EbayItemFilter;
import com.fivesticks.time.ebay.ItemStatusType;

/**
 * @author REID
 */
public class EbayItemFilterDecorator {

    public void build(EbayItemListContext context) {

        EbayItemFilter filter = context.getFilter();

        if (context.getFilter().getItemStatus() != null
                && context.getFilter().getItemStatus().trim().length() == 0)
            filter.setItemStatus(null);
        // else
        // filter.setItemStatus(null);

        if (context.getFilter().getCustId() != null
                && context.getFilter().getCustId().longValue() == 0)
            filter.setCustId(null);

        /*
         * open closed
         */

        if (context.getOpenClosedType() == OpenClosedType.OPEN) {
            String[] excluded = { ItemStatusType.CLOSED_CANCELLED.getName(),
                    ItemStatusType.CLOSED_NOT_SOLD.getName(),
                    ItemStatusType.CLOSED_SOLD.getName() };
            filter.setExcluded(excluded);
        } else if (context.getOpenClosedType() == OpenClosedType.READY_OR_WAITING) {
            String[] excluded = { ItemStatusType.CLOSED_CANCELLED.getName(),
                    ItemStatusType.CLOSED_NOT_SOLD.getName(),
                    ItemStatusType.CLOSED_SOLD.getName(),
                    ItemStatusType.ACTIVE_ITEM.getName(),
                    ItemStatusType.ADDED.getName(),
                    ItemStatusType.ITEM_IN_TRANSIT.getName(),
                    ItemStatusType.NOT_SOLD_RELIST.getName(),
                    ItemStatusType.POSTED_TO_ACCOUNT.getName(),
                    ItemStatusType.WAITING_FOR_PAYMENT.getName() };
            filter.setExcluded(excluded);

        } else if (context.getOpenClosedType() == OpenClosedType.OPEN_NOT_READY) {
            String[] excluded = { ItemStatusType.CLOSED_CANCELLED.getName(),
                    ItemStatusType.CLOSED_NOT_SOLD.getName(),
                    ItemStatusType.CLOSED_SOLD.getName(),
                    ItemStatusType.POSTED_TO_ACCOUNT.getName(),
                    ItemStatusType.READY_TO_PAY_NET.getName() };
            filter.setExcluded(excluded);
        } else if (context.getOpenClosedType() == OpenClosedType.CLOSED) {
            String[] excluded = { ItemStatusType.ACTIVE_ITEM.getName(),
                    ItemStatusType.ADDED.getName(),
                    ItemStatusType.ITEM_IN_TRANSIT.getName(),
                    ItemStatusType.NOT_SOLD_RELIST.getName(),
                    ItemStatusType.NOT_SOLD_WAITING_FOR_PICKUP.getName(),
                    ItemStatusType.READY_TO_PAY_NET.getName(),
                    ItemStatusType.POSTED_TO_ACCOUNT.getName(),
                    ItemStatusType.WAITING_FOR_PAYMENT.getName() };
            filter.setExcluded(excluded);
        } else {
            filter.setExcluded(null);
        }

        /*
         * 2005-08-30 REID
         */

        if (context.getDateRangeStart() != null
                && context.getDateRangeStop() != null) {
            filter.setDateAddedRangeStart(context.getDateRangeStart());
            filter.setDateAddedRangeStop(context.getDateRangeStop());
        } else {
            filter.setDateAddedRangeStart(null);
            filter.setDateAddedRangeStop(null);

        }

        // return filter;
    }
}