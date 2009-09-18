/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class EbayItemImageServiceDelegateFactory {

    public EbayItemImageServiceDelegate build(SystemOwner systemOwner) {
        EbayItemImageServiceDelegateImpl ret = (EbayItemImageServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        EbayItemImageServiceDelegate.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
}