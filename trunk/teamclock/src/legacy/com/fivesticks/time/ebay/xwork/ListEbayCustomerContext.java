/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.ebay.xwork;

import java.io.Serializable;

import com.fivesticks.time.customer.FstxCustomerFilterVO;

public class ListEbayCustomerContext implements Serializable{
    
    private FstxCustomerFilterVO filter;

    public ListEbayCustomerContext() { super(); }
    
    public ListEbayCustomerContext(FstxCustomerFilterVO filter) {
        this.filter = filter;
    }
    /**
     * @return Returns the filter.
     */
    public FstxCustomerFilterVO getFilter() {
        return filter;
    }

    /**
     * @param filter The filter to set.
     */
    public void setFilter(FstxCustomerFilterVO filter) {
        this.filter = filter;
    }
    
    
    
}
