/*
 * Created on Dec 15, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 */
public class QueuedMessageDAOFactory {

    public static final String SPRING_BEAN_NAME = "queuedMessageDAO";
    public static final QueuedMessageDAOFactory factory = new QueuedMessageDAOFactory();

    public QueuedMessageDAO build() {
        return (QueuedMessageDAO) SpringBeanBroker.getBeanFactory().getBean(
                QueuedMessageDAOFactory.SPRING_BEAN_NAME);
    }
}