/*
 * Created on Mar 31, 2005 by REID
 */
package com.fivesticks.time.ebay.util;

import java.util.Collection;

import com.fivesticks.time.customer.FstxCustomer;

/**
 * @author REID
 */
public class CustomerAndItemsVO {

    private FstxCustomer customer;
    
    private Collection items;
    
    private EbayItemCollectionStatistics stats;
    
    public FstxCustomer getCustomer() {
        return customer;
    }
    public void setCustomer(FstxCustomer customer) {
        this.customer = customer;
    }
    public Collection getItems() {
        return items;
    }
    public void setItems(Collection items) {
        this.items = items;
    }
    public EbayItemCollectionStatistics getStats() {
        return stats;
    }
    public void setStats(EbayItemCollectionStatistics stats) {
        this.stats = stats;
    }
}
