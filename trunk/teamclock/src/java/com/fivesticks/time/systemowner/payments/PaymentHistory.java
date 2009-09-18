/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.util.Date;

import com.fivesticks.time.common.AbstractTimeObject;

public class PaymentHistory extends AbstractTimeObject {

    private Date date;
    private String methodName;
    private String transactionId;
    private String cvv2;
    private String avs;
    private Double amount;
    /**
     * @return Returns the amount.
     */
    public Double getAmount() {
        return amount;
    }
    /**
     * @param amount The amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    /**
     * @return Returns the avs.
     */
    public String getAvs() {
        return avs;
    }
    /**
     * @param avs The avs to set.
     */
    public void setAvs(String avs) {
        this.avs = avs;
    }
    /**
     * @return Returns the cvv2.
     */
    public String getCvv2() {
        return cvv2;
    }
    /**
     * @param cvv2 The cvv2 to set.
     */
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return Returns the methodName.
     */
    public String getMethodName() {
        return methodName;
    }
    /**
     * @param methodName The methodName to set.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    /**
     * @return Returns the transactionId.
     */
    public String getTransactionId() {
        return transactionId;
    }
    /**
     * @param transactionId The transactionId to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    
}
