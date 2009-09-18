/*
 * Created on Aug 23, 2006
 *
 */
package com.fivesticks.time.systemowner.payments.xwork;

import com.fivesticks.time.systemowner.payments.PaymentMethod;

public class AccountManagementContext {

    private PaymentMethod paymentMethod;

    /**
     * @return Returns the paymentMethod.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod The paymentMethod to set.
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    
}
