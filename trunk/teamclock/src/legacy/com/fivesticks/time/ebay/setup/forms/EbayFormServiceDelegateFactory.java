/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *
 */
public class EbayFormServiceDelegateFactory {

    public EbayFormServiceDelegate build(SystemOwner systemOwner) {
        EbayFormServiceDelegateImpl ret = (EbayFormServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(EbayFormServiceDelegate.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
        
    }
}
