/*
 * Created on May 12, 2004
 *
 */
package com.fivesticks.time.common.xwork;

import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

/**
 * @author Reid
 *  
 */
public class AuthenticationInterceptor extends AroundInterceptor {

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.AroundInterceptor#after(com.opensymphony.xwork.ActionInvocation,
     *      java.lang.String)
     */
    protected void after(ActionInvocation arg0, String arg1) throws Exception {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.AroundInterceptor#before(com.opensymphony.xwork.ActionInvocation)
     */
    protected void before(ActionInvocation arg0) throws Exception {

    }

}