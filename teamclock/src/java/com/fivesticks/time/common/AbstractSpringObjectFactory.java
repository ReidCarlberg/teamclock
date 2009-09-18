/*
 * Created on Nov 30, 2005 by Reid
 */
package com.fivesticks.time.common;

public abstract class AbstractSpringObjectFactory {

    protected Object getBean(final String springBeanName) {
        return SpringBeanBroker.getBeanFactory().getBean(springBeanName);
    }
}
