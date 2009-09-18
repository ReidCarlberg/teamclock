/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.systemowner.SystemOwner;

public class PaymentMethodServiceDelegateFactory extends
        AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME="paymentMethodServiceDelegate";
    public static final PaymentMethodServiceDelegateFactory factory = new PaymentMethodServiceDelegateFactory();
    
    public PaymentMethodServiceDelegate build(SystemOwner systemOwner) {
        
        PaymentMethodServiceDelegateImpl ret = (PaymentMethodServiceDelegateImpl) this.getBean(SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
    
    public PaymentMethodServiceDelegate buildEmpty() {
        
        PaymentMethodServiceDelegateImpl ret = (PaymentMethodServiceDelegateImpl) this.getBean(SPRING_BEAN_NAME);
               
        return ret;
    }
}
