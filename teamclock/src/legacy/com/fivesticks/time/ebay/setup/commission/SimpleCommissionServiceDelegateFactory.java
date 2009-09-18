/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *  
 */
public class SimpleCommissionServiceDelegateFactory {

    public SimpleCommissionServiceDelegate build(SystemOwner systemOwner) {
        SimpleCommissionServiceDelegateImpl ret = (SimpleCommissionServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        SimpleCommissionServiceDelegate.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);
        
        return ret;
        
    }
}
