/*
 * Created on Oct 6, 2005
 *
 * 
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

public class CustomerSettingServiceDelegateFactory  {

    public static final String SPRING_BEAN_NAME = "customerSettingServiceDelegate";
    public static final CustomerSettingServiceDelegateFactory factory = new CustomerSettingServiceDelegateFactory();

    public CustomerSettingServiceDelegateFactory() {
        super();

    }

    public CustomerSettingServiceDelegate build(SystemOwner owner) {
        
        CustomerSettingServiceDelegateImpl ret = (CustomerSettingServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                         CustomerSettingServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(owner);
        return ret;
    }

}
