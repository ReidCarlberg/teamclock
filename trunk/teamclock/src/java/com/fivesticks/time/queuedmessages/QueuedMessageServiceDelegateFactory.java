/*
 * Created on Dec 15, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author REID
 */
public class QueuedMessageServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "queuedMessageServiceDelegate";
    public static final QueuedMessageServiceDelegateFactory factory = new QueuedMessageServiceDelegateFactory();

    public QueuedMessageServiceDelegate build(SystemOwner systemOwner) {
        QueuedMessageServiceDelegate ret = (QueuedMessageServiceDelegate) SpringBeanBroker
                .getBeanFactory().getBean(
                        QueuedMessageServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }

    public QueuedMessageServiceDelegate build(SessionContext sessionContext) {
        return build(sessionContext.getSystemOwner());
    }

    public QueuedMessageServiceDelegate build(String ownerKey) {
        SystemOwner systemOwner;
        try {
            systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                    ownerKey);
        } catch (SystemOwnerServiceDelegateException e) {
            throw new RuntimeException("failed to lookup system owner");
        }
        return build(systemOwner);
    }

}