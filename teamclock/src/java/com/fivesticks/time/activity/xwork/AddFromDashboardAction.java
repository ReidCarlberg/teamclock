/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.activity.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.parser.ProjectUserDetailParser;
import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class AddFromDashboardAction extends AbstractDashboardJSONAction {

    private String searchTerm;

    private String submit;

    // private DashboardContext dashboardContext;

    public String execute() throws Exception {

        if (!StringUtils.hasText(this.getSearchTerm())) {
            return INPUT; // dashboard
        }

        Activity activity = new Activity();

        ProjectUserDetailParser parser = new ProjectUserDetailParser(this
                .getSessionContext());

        parser.parse(this.getSearchTerm());

        ProjectServiceDelegate projBd = ProjectServiceDelegateFactory.factory
                .build(this.getSessionContext());
        Project proj = parser.getTargetProject();

        String assignee = this.getSessionContext().getUser().getUsername();

        if (parser.getTargetUser() != null) {
            assignee = parser.getTargetUser().getUsername();
        }
        
        activity.setComments(parser.getTargetDetail());

        if (proj != null) {
            activity.setProjectId(proj.getId());

        } else {

            activity.setProjectId(new Long(this.getSessionContext()
                    .getSettings().getActivityDefaultProject()));
        }

        activity.setTaskId(new Long(this.getSessionContext().getSettings()
                .getActivityDefaultTask()));

        /*
         * 2006-05-22 RSC These are just legacy fields.
         */
        activity.setProject("");
        activity.setTask("");

        activity.setUsername(assignee);

        activity.setStart(DateTimeRounderFactory.factory
                .build(
                        this.getSessionContext().getSettings()
                                .getActivityRounderType()).round(
                        this.getSessionContext().getResolvedNow()));

        activity.setDate(SimpleDate.factory.buildMidnight(
                this.getSessionContext().getResolvedNow()).getDate());

        activity.setId(null);

        ActivityBDFactory.factory.build(this.getSessionContext()).save(
                activity);

        return SUCCESS;
    }

    /**
     * @return
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @return
     */
    public String getSubmit() {
        return submit;
    }

    /**
     * @param string
     */
    public void setSearchTerm(String string) {
        searchTerm = string;
    }

    /**
     * @param string
     */
    public void setSubmit(String string) {
        submit = string;
    }

    // /**
    // * @return Returns the dashboardContext.
    // */
    // public DashboardContext getDashboardContext() {
    // return dashboardContext;
    // }
    //
    // /**
    // * @param dashboardContext
    // * The dashboardContext to set.
    // */
    // public void setDashboardContext(DashboardContext dashboardContext) {
    // this.dashboardContext = dashboardContext;
    // }
}