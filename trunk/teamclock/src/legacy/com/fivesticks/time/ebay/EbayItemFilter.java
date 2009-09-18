/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;


/**
 * @author REID
 */
public class EbayItemFilter extends EbayItem implements CriteriaParameters {

    private Boolean readyToList;
    private Boolean open;
    private Boolean readyToPay;
    private Boolean completed;
    
    private String[] excluded;
    
    private int returnMaximum;
    
    private String descriptionShortLike;
    private String listingHeadlineLike;
    
    private Date dateAddedRangeStart;
    private Date dateAddedRangeStop;
    
    private Date dateClosedRangeStart;
    private Date dateClosedRangeStop;
    
    
    
    
    
    /**
     * @return Returns the excluded.
     */
    public String[] getExcluded() {
        return excluded;
    }
    /**
     * @param excluded The excluded to set.
     */
    public void setExcluded(String[] excluded) {
        this.excluded = excluded;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public Boolean getOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }
    public Boolean getReadyToList() {
        return readyToList;
    }
    public void setReadyToList(Boolean readyToList) {
        this.readyToList = readyToList;
    }
    public Boolean getReadyToPay() {
        return readyToPay;
    }
    public void setReadyToPay(Boolean readyToPay) {
        this.readyToPay = readyToPay;
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
     * @return Returns the descriptionShortLike.
     */
    public String getDescriptionShortLike() {
        return descriptionShortLike;
    }
    /**
     * @param descriptionShortLike The descriptionShortLike to set.
     */
    public void setDescriptionShortLike(String descriptionShortLike) {
        this.descriptionShortLike = descriptionShortLike;
    }
    /**
     * @return Returns the listingHeadlineLike.
     */
    public String getListingHeadlineLike() {
        return listingHeadlineLike;
    }
    /**
     * @param listingHeadlineLike The listingHeadlineLike to set.
     */
    public void setListingHeadlineLike(String listingHeadlineLike) {
        this.listingHeadlineLike = listingHeadlineLike;
    }
    /**
     * @return Returns the dateAddedRangeStart.
     */
    public Date getDateAddedRangeStart() {
        return dateAddedRangeStart;
    }
    /**
     * @param dateAddedRangeStart The dateAddedRangeStart to set.
     */
    public void setDateAddedRangeStart(Date dateAddedRangeStart) {
        this.dateAddedRangeStart = dateAddedRangeStart;
    }
    /**
     * @return Returns the dateAddedRangeStop.
     */
    public Date getDateAddedRangeStop() {
        return dateAddedRangeStop;
    }
    /**
     * @param dateAddedRangeStop The dateAddedRangeStop to set.
     */
    public void setDateAddedRangeStop(Date dateAddedRangeStop) {
        this.dateAddedRangeStop = dateAddedRangeStop;
    }
    /**
     * @return Returns the dateClosedRangeStart.
     */
    public Date getDateClosedRangeStart() {
        return dateClosedRangeStart;
    }
    /**
     * @param dateClosedRangeStart The dateClosedRangeStart to set.
     */
    public void setDateClosedRangeStart(Date dateClosedRangeStart) {
        this.dateClosedRangeStart = dateClosedRangeStart;
    }
    /**
     * @return Returns the dateClosedRangeStop.
     */
    public Date getDateClosedRangeStop() {
        return dateClosedRangeStop;
    }
    /**
     * @param dateClosedRangeStop The dateClosedRangeStop to set.
     */
    public void setDateClosedRangeStop(Date dateClosedRangeStop) {
        this.dateClosedRangeStop = dateClosedRangeStop;
    }
}
