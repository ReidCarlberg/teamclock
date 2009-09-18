/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

public interface PaymentMethodServiceDelegate extends SystemOwnerAware {

    public void save(PaymentMethod paymentMethod);
    
    public PaymentMethod get(Long id);
    
    public PaymentMethod getCurrent();
    
    public void delete(PaymentMethod paymentMethod);
    
    public Collection search(PaymentMethodCriteriaParameters parameters);
    
    
}
