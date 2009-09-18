/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;

public class WebFormServiceDelegateFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME="webFormServiceDelegate";
    
    public static final WebFormServiceDelegateFactory factory = new WebFormServiceDelegateFactory();

    public WebFormServiceDelegate build(SessionContext sessionContext) {
        WebFormServiceDelegateImpl ret = (WebFormServiceDelegateImpl) this
                .getBean(SPRING_BEAN_NAME);
        ret.setSessionContext(sessionContext);
        return ret;

    }
}
