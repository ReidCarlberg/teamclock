/*
 * Created on Nov 29, 2005
 *
 */
package com.fivesticks.time.payperiod;

import com.fivesticks.time.common.AbstractTimeObject;

public class PayPeriodEmployee extends AbstractTimeObject {

    private Long payPeriodId;
    private Long employeeId;
    private String description;
    private double amountHour;
    private double amountCurrency;
    
    /**
     * @return Returns the amountCurrency.
     */
    public double getAmountCurrency() {
        return amountCurrency;
    }
    /**
     * @param amountCurrency The amountCurrency to set.
     */
    public void setAmountCurrency(double amountCurrency) {
        this.amountCurrency = amountCurrency;
    }
    /**
     * @return Returns the amountHour.
     */
    public double getAmountHour() {
        return amountHour;
    }
    /**
     * @param amountHour The amountHour to set.
     */
    public void setAmountHour(double amountHour) {
        this.amountHour = amountHour;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the employeeId.
     */
    public Long getEmployeeId() {
        return employeeId;
    }
    /**
     * @param employeeId The employeeId to set.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    /**
     * @return Returns the payPeriodId.
     */
    public Long getPayPeriodId() {
        return payPeriodId;
    }
    /**
     * @param payPeriodId The payPeriodId to set.
     */
    public void setPayPeriodId(Long payPeriodId) {
        this.payPeriodId = payPeriodId;
    }
    
}
