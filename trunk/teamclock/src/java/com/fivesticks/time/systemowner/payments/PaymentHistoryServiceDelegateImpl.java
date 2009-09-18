/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;

public class PaymentHistoryServiceDelegateImpl extends AbstractServiceDelegate
        implements PaymentHistoryServiceDelegate {

    public void save(PaymentHistory paymentHistory) {
        this.handleDecorateRuntime(paymentHistory);
        this.getDao().save(paymentHistory);
    }

    public Collection search(PaymentHistoryCriteriaParameters parameters) {
        this.handleDecorateRuntime(parameters);
        return this.getDao().find(parameters);
    }

    public PaymentHistory get(Long id) {
        
        PaymentHistory ret =  (PaymentHistory) this.getDao().get(id);
        this.handleValidateRuntime(ret);
        return ret;
    }

    public void delete(PaymentHistory paymentHistory) {
        this.handleValidateRuntime(paymentHistory);
        this.getDao().delete(paymentHistory);
    }

}
