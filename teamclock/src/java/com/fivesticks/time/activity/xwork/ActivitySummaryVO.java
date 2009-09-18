/*
 * Created on Jan 6, 2007
 *
 */
package com.fivesticks.time.activity.xwork;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;

public class ActivitySummaryVO {

    private Date start;

    private Date stop;

    private Collection summary;

    private double totalShiftMinutes;

    private double totalShiftMinutesBreak;
    
    public static final DecimalFormat hundredthsFormatter = new DecimalFormat("#,##0.00");

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the stop
     */
    public Date getStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(Date stop) {
        this.stop = stop;
    }

    /**
     * @return the summary
     */
    public Collection getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(Collection summary) {
        this.summary = summary;
    }

    /**
     * @return the totalShiftMinutes
     */
    public double getTotalShiftMinutes() {
        return totalShiftMinutes;
    }

    /**
     * @param totalShiftMinutes the totalShiftMinutes to set
     */
    public void setTotalShiftMinutes(double totalShiftMinutes) {
        this.totalShiftMinutes = totalShiftMinutes;
    }

    /**
     * @return the totalShiftMinutesBreak
     */
    public double getTotalShiftMinutesBreak() {
        return totalShiftMinutesBreak;
    }

    /**
     * @param totalShiftMinutesBreak the totalShiftMinutesBreak to set
     */
    public void setTotalShiftMinutesBreak(double totalShiftMinutesBreak) {
        this.totalShiftMinutesBreak = totalShiftMinutesBreak;
    }
    
    public double getTotalShiftHours() {
        return this.getTotalShiftMinutes() / 60.0;
    }

    public double getTotalShiftHoursBreak() {
        return this.getTotalShiftMinutesBreak() / 60.0;
    }

    public String getTotalShiftHoursFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHours());
    }

    public String getTotalShiftHoursBreakFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHoursBreak());
    }

    public String getTotalShiftHoursMinusBreakFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHours()
                - this.getTotalShiftHoursBreak());
    }
}
