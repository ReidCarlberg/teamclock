/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class AccountTransactionCriteriaParameters extends AccountTransaction implements
        CriteriaParameters {

    /**
     * 
     */
    private static final long serialVersionUID = 3093660325736273645L;

    private Boolean archived;

    private SimpleDate dateRangeStart;

    private SimpleDate dateRangeStop;

    private boolean sortDateAscending;
    
    private Long vestigalCustomerId;
    

    /**
     * @return Returns the archived.
     */
    public Boolean getArchived() {
        return archived;
    }

    /**
     * @param archived
     *            The archived to set.
     */
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public void setArchived(boolean archived) {
        throw new RuntimeException("use the object boolean");
    }

    public void setAmount(Double amount) {
        throw new RuntimeException("can't search by amount");
    }

    public void setDate(Date date) {
        throw new RuntimeException("can't search by a single date");
    }

    /**
     * @return Returns the dateRangeStart.
     */
    public SimpleDate getDateRangeStart() {
        return dateRangeStart;
    }

    /**
     * @param dateRangeStart
     *            The dateRangeStart to set.
     */
    public void setDateRangeStart(SimpleDate dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    /**
     * @return Returns the dateRangeStop.
     */
    public SimpleDate getDateRangeStop() {
        return dateRangeStop;
    }

    /**
     * @param dateRangeStop
     *            The dateRangeStop to set.
     */
    public void setDateRangeStop(SimpleDate dateRangeStop) {
        this.dateRangeStop = dateRangeStop;
    }

    /**
     * @return Returns the sortDateAscending.
     */
    public boolean isSortDateAscending() {
        return sortDateAscending;
    }

    /**
     * @param sortDateAscending
     *            The sortDateAscending to set.
     */
    public void setSortDateAscending(boolean sortDateAscending) {
        this.sortDateAscending = sortDateAscending;
    }
    /**
     * @return Returns the vestigalCustomerId.
     */
    public Long getVestigalCustomerId() {
        return vestigalCustomerId;
    }
    /**
     * @param vestigalCustomerId The vestigalCustomerId to set.
     */
    public void setVestigalCustomerId(Long vestigalCustomerId) {
        this.vestigalCustomerId = vestigalCustomerId;
    }
}