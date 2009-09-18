/*
 * Created on Jul 6, 2004
 *
 */
package com.fivesticks.time.xwork.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * @author Reid
 *  
 */
public class SuperUserFeatureSetValidatorInterceptor implements Interceptor {

    private Log log = LogFactory
            .getLog(SuperUserFeatureSetValidatorInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#intercept(com.opensymphony.xwork.ActionInvocation)
     */
    public String intercept(ActionInvocation arg0) throws Exception {

        //log.info("trying to get the component manager");

        ComponentManager cm = (ComponentManager) arg0.getInvocationContext()
                .getSession().get("DefaultComponentManager");

        SessionContext sc = (SessionContext) cm
                .getComponent(SessionContextAware.class);

        //log.info("is the sc valid? " + sc.isValid());

        if (!sc.isValid()) {
            log.info("current session context is invalid.");
            return "global-login"; //global login page.
        }

        if (sc.getSettings().getFeatureSet() != SettingFeatureSetTypeEnum.SUPERUSER) {
            log.info("current session context is invalid.");
            return "global-login"; //global login page.
        }
        //log.info("trying to return the next invoke.");

        return arg0.invoke();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#destroy()
     */
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#init()
     */
    public void init() {
    }

}