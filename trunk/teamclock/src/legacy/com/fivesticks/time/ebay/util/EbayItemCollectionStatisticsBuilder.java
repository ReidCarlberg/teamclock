/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.ebay.EbayItem;

/**
 * @author REID
 */
public class EbayItemCollectionStatisticsBuilder {

    public EbayItemCollectionStatistics build(Collection items) {

        EbayItemCollectionStatistics ret = new EbayItemCollectionStatistics();

        this.handleBuild(ret, items);

        return ret;

    }

    private void handleBuild(EbayItemCollectionStatistics ret, Collection items) {
        ret.setCount(items.size());
        for (Iterator iter = items.iterator(); iter.hasNext();) {
            EbayItem element = (EbayItem) iter.next();

            if (element.getPriceFinal() != null)
                ret.setTotalSale(ret.getTotalSale()
                        + element.getPriceFinal().doubleValue());

            if (element.getCustomerNet() != null)
                ret.setTotalNet(ret.getTotalNet()
                        + element.getCustomerNet().doubleValue());

            if (element.getCommission() != null)
                ret.setTotalCommission(ret.getTotalCommission()
                        + element.getCommission().doubleValue());

            if (element.getCommissionDiscount() != null)
                ret.setTotalDiscount(ret.getTotalDiscount()
                        + element.getCommissionDiscount().doubleValue());

            if (element.getPrepayAmount() != null)
            	ret.setTotalPrepay(ret.getTotalPrepay() + element.getPrepayAmount().doubleValue());
        }
    }
}