/*
 * Created on Mar 4, 2005
 *
 * 
 */
package com.fivesticks.time.todo.queue;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Nick
 * 
 *  
 */
public class QueueServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "queueServiceDelegate";
    public static final QueueServiceDelegateFactory factory = new QueueServiceDelegateFactory();

    public QueueServiceDelegateFactory() {
        super();

    }

    public QueueServiceDelegate build(SystemOwner systemOwner) {

        QueueServiceDelegateImpl ret = (QueueServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        QueueServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);

        return ret;
    }

}