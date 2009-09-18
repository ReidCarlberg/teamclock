/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 *
 */
public class EbayFormCriteriaParameters extends EbayForm implements CriteriaParameters {

    private Boolean orderById;
    /**
     * @return Returns the orderById.
     */
    public Boolean getOrderById() {
        return orderById;
    }
    /**
     * @param orderById The orderById to set.
     */
    public void setOrderById(Boolean orderById) {
        this.orderById = orderById;
    }
}
