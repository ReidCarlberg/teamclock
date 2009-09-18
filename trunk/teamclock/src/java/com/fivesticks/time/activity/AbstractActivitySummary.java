/*
 * Created on Jun 24, 2006
 *
 */
package com.fivesticks.time.activity;

import java.text.DecimalFormat;

public abstract class AbstractActivitySummary {

    private static DecimalFormat df = new DecimalFormat("#,##0.00");
    protected Long count;
    protected Double elapsed;
    protected Double elapsedChargeable;
    
    protected double elapsedPercentOfShiftTime;
    protected double elapsedChargeablePercentOfShiftTime;

    /**
     * @return Returns the percentOfShift.
     */
    public double getElapsedPercentOfShiftTime() {
        return elapsedPercentOfShiftTime;
    }
    
    public String getElapsedPercentOfShiftTimeFormatted() {
        return DecimalFormat.getPercentInstance().format(elapsedPercentOfShiftTime);
    }

    /**
     * @param percentOfShift The percentOfShift to set.
     */
    public void setElapsedPercentOfShiftTime(double percentOfShift) {
        this.elapsedPercentOfShiftTime = percentOfShift;
    }

    public String getChargeablePercentFormatted() {
        if (this.getElapsed().doubleValue() == 0.0) {
            return df.format(0.0);
        }
        return df.format((this.getElapsedChargeable().doubleValue()/this.getElapsed().doubleValue()) * 100);
    }

    /**
     * @return Returns the count.
     */
    public Long getCount() {
        return count;
    }

    /**
     * @return Returns the elapsed.
     */
    public Double getElapsed() {
        return elapsed;
    }

    /**
     * @return Returns the elapsedChargeable.
     */
    public Double getElapsedChargeable() {
        return elapsedChargeable;
    }

    public String getElapsedChargeableFormatted() {
        return df.format(this.getElapsedChargeable().doubleValue());
    }

    public String getElapsedFormatted() {
        return df.format(this.getElapsed().doubleValue());
    }

    /**
     * @param count
     *            The count to set.
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * @param elapsed
     *            The elapsed to set.
     */
    public void setElapsed(Double elapsed) {
        this.elapsed = elapsed;
    }

    /**
     * @param elapsedChargeable
     *            The elapsedChargeable to set.
     */
    public void setElapsedChargeable(Double elapsedChargeable) {
        this.elapsedChargeable = elapsedChargeable;
    }

    /**
     * @return Returns the elapsedChargeablePercentOfShiftTime.
     */
    public double getElapsedChargeablePercentOfShiftTime() {
        return elapsedChargeablePercentOfShiftTime;
    }

    /**
     * @param elapsedChargeablePercentOfShiftTime The elapsedChargeablePercentOfShiftTime to set.
     */
    public void setElapsedChargeablePercentOfShiftTime(
            double elapsedChargeablePercentOfShiftTime) {
        this.elapsedChargeablePercentOfShiftTime = elapsedChargeablePercentOfShiftTime;
    }
    
    public String getElapsedChargeablePercentOfShiftTimeFormatted() {
        return DecimalFormat.getPercentInstance().format(elapsedPercentOfShiftTime);
    }
    
    public abstract String getLabel();
    
    public abstract String getShortLabel();

}
