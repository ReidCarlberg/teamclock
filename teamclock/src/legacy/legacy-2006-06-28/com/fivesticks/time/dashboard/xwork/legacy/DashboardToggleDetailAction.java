/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.dashboard.xwork.DashboardContextAware;

/**
 * @author REID
 */
public class DashboardToggleDetailAction extends SessionContextAwareSupport implements
        DashboardContextAware {

    private DashboardContext dashboardContext;

    public String execute() throws Exception {

        this.getSessionContext().getSettings();
        
        this.getDashboardContext().setShowingToDoDetail(
                !this.getDashboardContext().isShowingToDoDetail());

        return SUCCESS;
    }

    /**
     * @return Returns the dashboardContext.
     */
    public DashboardContext getDashboardContext() {
        return dashboardContext;
    }

    /**
     * @param dashboardContext
     *            The dashboardContext to set.
     */
    public void setDashboardContext(DashboardContext dashboardContext) {
        this.dashboardContext = dashboardContext;
    }

}