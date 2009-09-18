/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 */
public class BoxCriteriaParameters extends Box implements CriteriaParameters {

    private Integer lengthMinimum;
    private Integer widthMinimum;
    private Integer heightMinimum;
    
    private Boolean instockOnly;
    private Boolean activeOnly;
    
    private Boolean sortByName;
    private Boolean sortByLength;
    
    /**
     * @return Returns the instock.
     */
    public Boolean getInstockOnly() {
        return instockOnly;
    }
    /**
     * @param instock The instock to set.
     */
    public void setInstockOnly(Boolean instock) {
        this.instockOnly = instock;
    }
    /**
     * @return Returns the heightMinimum.
     */
    public Integer getHeightMinimum() {
        return heightMinimum;
    }
    /**
     * @param heightMinimum The heightMinimum to set.
     */
    public void setHeightMinimum(Integer heightMinimum) {
        this.heightMinimum = heightMinimum;
    }
    /**
     * @return Returns the lengthMinimum.
     */
    public Integer getLengthMinimum() {
        return lengthMinimum;
    }
    /**
     * @param lengthMinimum The lengthMinimum to set.
     */
    public void setLengthMinimum(Integer lengthMinimum) {
        this.lengthMinimum = lengthMinimum;
    }
    /**
     * @return Returns the widthMinimum.
     */
    public Integer getWidthMinimum() {
        return widthMinimum;
    }
    /**
     * @param widthMinimum The widthMinimum to set.
     */
    public void setWidthMinimum(Integer widthMinimum) {
        this.widthMinimum = widthMinimum;
    }
    /**
     * @return Returns the activeOnly.
     */
    public Boolean getActiveOnly() {
        return activeOnly;
    }
    /**
     * @param activeOnly The activeOnly to set.
     */
    public void setActiveOnly(Boolean activeOnly) {
        this.activeOnly = activeOnly;
    }
    /**
     * @return Returns the sortByName.
     */
    public Boolean getSortByName() {
        return sortByName;
    }
    /**
     * @param sortByName The sortByName to set.
     */
    public void setSortByName(Boolean sortByName) {
        this.sortByName = sortByName;
    }
    /**
     * @return Returns the sortByLength.
     */
    public Boolean getSortByLength() {
        return sortByLength;
    }
    /**
     * @param sortByLength The sortByLength to set.
     */
    public void setSortByLength(Boolean sortByLength) {
        this.sortByLength = sortByLength;
    }
}
