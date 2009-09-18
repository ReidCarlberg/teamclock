/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class LookupServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "lookupsServiceDelegate";
    public static final LookupServiceDelegateFactory factory = new LookupServiceDelegateFactory();

    public LookupServiceDelegate build(SystemOwner systemOwner) {
        LookupServiceDelegateImpl ret = (LookupServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(LookupServiceDelegateFactory.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
}
