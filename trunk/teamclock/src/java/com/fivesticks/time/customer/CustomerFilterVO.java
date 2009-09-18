/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Nick
 * @author Reid
 *  
 */
public class CustomerFilterVO extends Customer implements CriteriaParameters {

    private Boolean showHidden;
    
    private String nameLike;
    
    private int returnMaximum;

    /**
     * @return Returns the nameLike.
     */
    public String getNameLike() {
        return nameLike;
    }

    /**
     * @param nameLike The nameLike to set.
     */
    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    /**
     * @return Returns the returnMaximum.
     */
    public int getReturnMaximum() {
        return returnMaximum;
    }

    /**
     * @param returnMaximum The returnMaximum to set.
     */
    public void setReturnMaximum(int returnMaximum) {
        this.returnMaximum = returnMaximum;
    }

    /**
     * @return Returns the showHidden.
     */
    public Boolean getShowHidden() {
        return showHidden;
    }

    /**
     * @param showHidden The showHidden to set.
     */
    public void setShowHidden(Boolean showHidden) {
        this.showHidden = showHidden;
    }
    
    
}