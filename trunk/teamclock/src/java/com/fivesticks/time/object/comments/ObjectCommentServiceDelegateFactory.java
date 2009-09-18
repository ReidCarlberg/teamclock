/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.comments;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ObjectCommentServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "objectCommentServiceDelegate";
    public static final ObjectCommentServiceDelegateFactory factory = new ObjectCommentServiceDelegateFactory();

    public ObjectCommentServiceDelegate build(SystemOwner systemOwner) {
        ObjectCommentServiceDelegateImpl ret = (ObjectCommentServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        ObjectCommentServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}