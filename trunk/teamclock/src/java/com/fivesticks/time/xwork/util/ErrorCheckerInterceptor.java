/*
 * Created on Jul 6, 2004
 *
 */
package com.fivesticks.time.xwork.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

/**
 * @author Reid
 *  
 */
public class ErrorCheckerInterceptor extends AroundInterceptor {

    private Log log = LogFactory.getLog(ErrorCheckerInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.AroundInterceptor#before(com.opensymphony.xwork.ActionInvocation)
     */
    protected void before(ActionInvocation arg0) throws Exception {
        //do nothing.
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.AroundInterceptor#after(com.opensymphony.xwork.ActionInvocation,
     *      java.lang.String)
     */
    protected void after(ActionInvocation arg0, String arg1) throws Exception {

        log.info("Here we are in after interceptor.");

    }

}