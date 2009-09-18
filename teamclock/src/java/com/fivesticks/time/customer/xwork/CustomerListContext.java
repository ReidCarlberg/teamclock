/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.customer.CustomerFilterVO;

public class CustomerListContext {

    private CustomerFilterVO filter;

    /**
     * @return Returns the filter.
     */
    public CustomerFilterVO getFilter() {
        return filter;
    }

    /**
     * @param filter The filter to set.
     */
    public void setFilter(CustomerFilterVO filter) {
        this.filter = filter;
    }
}
