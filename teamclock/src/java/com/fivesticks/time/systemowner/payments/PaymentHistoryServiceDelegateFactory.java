/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.systemowner.SystemOwner;

public class PaymentHistoryServiceDelegateFactory extends
        AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "paymentHistoryServiceDelegate";
    public static final PaymentHistoryServiceDelegateFactory factory = new PaymentHistoryServiceDelegateFactory();
    
    public PaymentHistoryServiceDelegate build(SystemOwner systemOwner) {
        PaymentHistoryServiceDelegateImpl ret = (PaymentHistoryServiceDelegateImpl) this.getBean(SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
}
