/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

public class MessageServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "messageServiceDelegate";
    public static final MessageServiceDelegateFactory factory = new MessageServiceDelegateFactory();

    public MessageServiceDelegate build(SystemOwner systemOwner) {
        
        MessageServiceDelegateImpl ret = (MessageServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(MessageServiceDelegateFactory.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
        
    }
}
