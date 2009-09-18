/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class LookupReplacementDelegateFactory {

    public static final LookupReplacementDelegateFactory factory = new LookupReplacementDelegateFactory();

    public LookupReplacementDelegate build(LookupType type, SystemOwner systemOwner) throws LookupReplacementDelegateException {
        LookupReplacementDelegate ret = null;
        
        if (type == LookupType.CUSTOMER_STATUS) {
            ret = (LookupReplacementDelegateCustomerStatusImpl) SpringBeanBroker.getBeanFactory().getBean(LookupReplacementDelegateCustomerStatusImpl.SPRING_BEAN_NAME);
        }
        
        if (ret == null) {
            throw new LookupReplacementDelegateException("delegate not available");
        }
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
             
}
