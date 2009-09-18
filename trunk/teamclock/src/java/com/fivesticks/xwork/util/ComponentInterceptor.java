/*
 * Created on Aug 19, 2004 by Reid
 */
package com.fivesticks.xwork.util;

import java.util.Map;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * 
 * 2006-07-06 shouldn't need this anymore.
 * 
 * @author Reid
 */
public class ComponentInterceptor extends AroundInterceptor {

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
    protected void before(ActionInvocation dispatcher) throws Exception {
        ComponentManager containerOriginal = (ComponentManager) ActionContext
                .getContext()
                .get(
                        "com.opensymphony.xwork.interceptor.component.ComponentManager");

        ComponentManager container = (ComponentManager) ActionContext
                .getContext().getSession().get("DefaultComponentManager");

        ActionContext c = ActionContext.getContext();

        Map m = c.getSession();

        if (container != null) {
            container.initializeObject(dispatcher.getAction());
        }
    }

}