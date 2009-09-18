/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.metrics;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ObjectMetricServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "objectMetricServiceDelegate";
    public static final ObjectMetricServiceDelegateFactory factory = new ObjectMetricServiceDelegateFactory();

    public ObjectMetricServiceDelegate build(SystemOwner systemOwner) {
        ObjectMetricServiceDelegateImpl ret = (ObjectMetricServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        ObjectMetricServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}