/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.util.Collection;

public interface PaymentHistoryServiceDelegate  {

    public void save(PaymentHistory paymentHistory);
    
    public Collection search(PaymentHistoryCriteriaParameters parameters);
    
    public PaymentHistory get(Long id);
    
    public void delete(PaymentHistory paymentHistory);
    
}
