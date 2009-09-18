/*
 * Created on Jul 6, 2004
 *
 */
package com.fivesticks.time.xwork.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * @author Reid
 * 
 */
public class SessionValidatorInterceptor implements Interceptor {

    private Log log = LogFactory.getLog(SessionValidatorInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#intercept(com.opensymphony.xwork.ActionInvocation)
     */
    public String intercept(ActionInvocation arg0) throws Exception {

        //log.info("trying to get the component manager");

        //System.out.println("1");
        
//        System.out.println(arg0.getInvocationContext().getName());
        
        ComponentManager cm = (ComponentManager) arg0.getInvocationContext()
                .getSession().get("DefaultComponentManager");

        SessionContext sc = (SessionContext) cm
                .getComponent(SessionContextAware.class);
        
//        log.info("HTTP session id " + arg0.getInvocationContext().getSession());
//        
//        log.info("component manager id " + cm.toString());
//        
//
//        log.info("is the sc valid? " + sc.isValid());
//        log.info("Session Context is: " + sc.toString());

        if (!sc.isValid()) {
//            log.info("current session context is invalid.");
//            log.info("action invocation class: "
//                    + arg0.getAction().getClass().getName());
//            log.info("action invocation toString: "
//                    + arg0.getAction().toString());
            log.info("Session context is: " + sc.toString());
            return "global-login"; // global login page.
        }

        /*
         * Make sure the user and the system owner jive.
         */
        SystemOwner asystemowner = null;
        AuthenticatedUser au = sc.getUser();
        try {
            asystemowner = SystemOwnerServiceDelegateFactory.factory.build().get(
                    au.getUser());
        } catch (Exception e) {
            return "global-login";
        }
        if (!sc.getSystemOwner().getKey().equals(asystemowner.getKey())) {
            return "global-login";
        }
        // log.info("trying to return the next invoke.");
        
        String ret = null;
        
        try {
            ret = arg0.invoke();
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("Runtime Exception thown in action: "
                    + arg0.getAction().toString());
            sc.setMessage("error " + e.getMessage());
            return "global-login";
        }
        
        //System.out.println("Result is " + ret);
        return ret;
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