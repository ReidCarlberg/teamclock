/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class BoxServiceDelegateFactory {

    public BoxServiceDelegate build(SystemOwner owner) {
        BoxServiceDelegateImpl ret = (BoxServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(
                BoxServiceDelegate.SPRING_BEAN_NAME);
        ret.setSystemOwner(owner);
        
        return ret;
        
    }
}