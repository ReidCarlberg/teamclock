/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class EbayItemServiceDelegateFactory {

    public EbayItemServiceDelegate build(SystemOwner owner) {
        EbayItemServiceDelegateImpl ret = (EbayItemServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        EbayItemServiceDelegate.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(owner);
        
        return ret;
    }
    
    public EbayItemServiceDelegate build(SessionContext sessionContext) {
        return build(sessionContext.getSystemOwner());
    }    

}