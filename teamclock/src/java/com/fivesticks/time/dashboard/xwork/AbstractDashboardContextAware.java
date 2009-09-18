/*
 * Created on May 22, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractDashboardContextAware extends
        SessionContextAwareSupport implements DashboardContextAware {

    private DashboardContext dashboardContext;

    /**
     * @return Returns the dashboardContext.
     */
    public DashboardContext getDashboardContext() {
        return dashboardContext;
    }

    /**
     * @param dashboardContext The dashboardContext to set.
     */
    public void setDashboardContext(DashboardContext dashboardContext) {
        this.dashboardContext = dashboardContext;
    }
    
    
}
