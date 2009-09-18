/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ItemShippingServiceDelegateFactory {

    public ItemShippingServiceDelegate build(SystemOwner owner) {
        ItemShippingServiceDelegateImpl ret = (ItemShippingServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        ItemShippingServiceDelegate.SPRING_BEAN_NAME);

        ret.setSystemOwner(owner);

        return ret;
    }
}