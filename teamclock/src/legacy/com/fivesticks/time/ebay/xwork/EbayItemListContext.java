/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.ebay.EbayItemFilter;


/**
 * updated to include the filter.
 * 
 * @author REID
 */
public class EbayItemListContext implements Serializable {

    private EbayItemFilter filter;

    private OpenClosedType openClosedType;
    
    private ViewType viewType = ViewType.STANDARD;

    private Collection results;
    
    private int maxRecords;
    
    private String dateRangeType;
    
    private Date dateRangeStart;
    
    private Date dateRangeStop;
    
    /*
     * 2005-08-10 refactored out since they're in the filter...
     */
//    private String status;
//    
//    private Long customerId;
//    
//    private int returnMaximum;
    
    
//    public Long getCustomerId() {
//        return customerId;
//    }
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }

    /**
     * @return Returns the openClosedType.
     */
    public OpenClosedType getOpenClosedType() {
        return openClosedType;
    }
    /**
     * @param openClosedType The openClosedType to set.
     */
    public void setOpenClosedType(OpenClosedType openClosedType) {
        this.openClosedType = openClosedType;
    }
    /**
     * @return Returns the viewType.
     */
    public ViewType getViewType() {
        return viewType;
    }
    /**
     * @param viewType The viewType to set.
     */
    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }
    /**
     * @return Returns the results.
     */
    public Collection getResults() {
        return results;
    }
    /**
     * @param results The results to set.
     */
    public void setResults(Collection results) {
        this.results = results;
    }
    

    
//    /**
//     * @return Returns the returnMaximum.
//     */
//    public int getReturnMaximum() {
//        return returnMaximum;
//    }
//    /**
//     * @param returnMaximum The returnMaximum to set.
//     */
//    public void setReturnMaximum(int returnMaximum) {
//        this.returnMaximum = returnMaximum;
//    }
    /**
     * @return Returns the filter.
     */
    public EbayItemFilter getFilter() {
        return filter;
    }
    /**
     * @param filter The filter to set.
     */
    public void setFilter(EbayItemFilter filter) {
        this.filter = filter;
    }
    /**
     * @return Returns the maxRecords.
     */
    public int getMaxRecords() {
        return maxRecords;
    }
    /**
     * @param maxRecords The maxRecords to set.
     */
    public void setMaxRecords(int maxRecords) {
        this.maxRecords = maxRecords;
    }
    /**
     * @return Returns the dateRangeStart.
     */
    public Date getDateRangeStart() {
        return dateRangeStart;
    }
    /**
     * @param dateRangeStart The dateRangeStart to set.
     */
    public void setDateRangeStart(Date dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }
    /**
     * @return Returns the dateRangeStop.
     */
    public Date getDateRangeStop() {
        return dateRangeStop;
    }
    /**
     * @param dateRangeStop The dateRangeStop to set.
     */
    public void setDateRangeStop(Date dateRangeStop) {
        this.dateRangeStop = dateRangeStop;
    }
    /**
     * @return Returns the dateRangeType.
     */
    public String getDateRangeType() {
        return dateRangeType;
    }
    /**
     * @param dateRangeType The dateRangeType to set.
     */
    public void setDateRangeType(String dateRangeType) {
        this.dateRangeType = dateRangeType;
    }
}
