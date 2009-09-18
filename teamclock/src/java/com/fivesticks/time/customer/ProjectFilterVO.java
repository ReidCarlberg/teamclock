/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Nick
 *  
 */
public class ProjectFilterVO extends Project implements CriteriaParameters {

    private Boolean isActive;

    /**
     * @return
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param active
     */
    public void setIsActive(Boolean active) {
        isActive = active;
    }

}