/*
 * Created on Jul 6, 2004
 *
 */
package com.fivesticks.time.xwork.util;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.systemowner.AccountType;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.opensymphony.xwork.interceptor.component.ComponentManager;

/**
 * @author Reid
 *  
 */
public class SystemOwnerNotTimeClockOnlyInterceptor implements Interceptor {

//    private Log log = LogFactory.getLog(UserRoleAdminInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#intercept(com.opensymphony.xwork.ActionInvocation)
     */
    public String intercept(ActionInvocation arg0) throws Exception {

        ComponentManager cm = (ComponentManager) arg0.getInvocationContext()
                .getSession().get("DefaultComponentManager");

        SessionContext sc = (SessionContext) cm
                .getComponent(SessionContextAware.class);

        if (sc.getSystemOwner().getAccountType() == AccountType.TIMECLOCK) {
            return "global-timeclock-only"; //global login page.
        }

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