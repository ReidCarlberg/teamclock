/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import com.fivesticks.time.common.dao.CriteriaParameters;

public class PaymentMethodCriteriaParameters extends PaymentMethod implements CriteriaParameters {

    private Boolean currentAsObject;

    /**
     * @return Returns the currentPaymentMethod.
     */
    public Boolean getCurrentAsObject() {
        return currentAsObject;
    }

    /**
     * @param currentPaymentMethod The currentPaymentMethod to set.
     */
    public void setCurrentAsObject(Boolean currentPaymentMethod) {
        this.currentAsObject = currentPaymentMethod;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.systemowner.payments.PaymentMethod#isCurrent()
     */
    @Override
    public boolean isCurrent() {
        
        throw new RuntimeException("use currentPaymentMethod");
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.systemowner.payments.PaymentMethod#setCurrent(boolean)
     */
    @Override
    public void setCurrent(boolean current) {
        
        throw new RuntimeException("use currentPaymentMethod");
    }
    
    
}
