/*
 * Created on Sep 27, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import java.util.Date;

/**
 * @author Reid
 */
public class ShowMyPayPeriodSummaryAction extends AbstractTimeclockSupportAction {

    

    

    private String periodType;

    private Date targetDate;

    public String execute() throws Exception {

        this.initializeUserPayPeriodSummary(this.getPeriodType(), this.getTargetDate());

        return SUCCESS;
    }


    /**
     * @return Returns the periodType.
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * @param periodType
     *            The periodType to set.
     */
    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    /**
     * @return Returns the targetDate.
     */
    public Date getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate
     *            The targetDate to set.
     */
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }


}