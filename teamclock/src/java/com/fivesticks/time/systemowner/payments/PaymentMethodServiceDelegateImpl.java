/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;

public class PaymentMethodServiceDelegateImpl extends AbstractServiceDelegate
        implements PaymentMethodServiceDelegate {

    public void save(PaymentMethod paymentMethod) {
        this.handleDecorateRuntime(paymentMethod);
        this.getDao().save(paymentMethod);
    }

    public PaymentMethod get(Long id) {
        
        PaymentMethod ret = (PaymentMethod) this.getDao().get(id);
        this.handleValidateRuntime(ret);
        
        return ret;
    }

    public void delete(PaymentMethod paymentMethod) {
        this.handleValidateRuntime(paymentMethod);
        this.getDao().delete(paymentMethod);
    }

    public Collection search(PaymentMethodCriteriaParameters parameters) {
        this.handleDecorateRuntime(parameters);
        return this.getDao().find(parameters);
    }
    
    public Collection getAll() {
        return this.getDao().getAll();
    }

    public PaymentMethod getCurrent() {
        
        PaymentMethod ret = null;
        
        PaymentMethodCriteriaParameters p = new PaymentMethodCriteriaParameters();
        p.setOwnerKey(this.getSystemOwner().getKey());
        p.setCurrentAsObject(Boolean.TRUE);
        
        Collection c = this.search(p);
        
        if (c.size() == 1) {
            ret = (PaymentMethod) c.toArray()[0];
        }
        return ret;
    }

}
