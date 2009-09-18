/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import com.fivesticks.time.common.SpringBeanBroker;

public class TokenServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "tokenServiceDelegate";
    public static final TokenServiceDelegateFactory factory = new TokenServiceDelegateFactory();

    public TokenServiceDelegate build() {
        
        TokenServiceDelegateImpl ret = (TokenServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(TokenServiceDelegateFactory.SPRING_BEAN_NAME);
        
//        ret.setSystemOwner(systemOwner);
        
        return ret;
        
    }
}
