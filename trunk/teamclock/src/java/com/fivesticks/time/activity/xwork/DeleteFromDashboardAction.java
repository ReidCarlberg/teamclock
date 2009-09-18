/*
 * Created on Jul 6, 2006 by Reid
 */
package com.fivesticks.time.activity.xwork;

import java.text.SimpleDateFormat;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.dashboard.util.DashboardMiscellaneousRecentAction;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.opensymphony.xwork.Action;

public class DeleteFromDashboardAction extends AbstractDashboardJSONAction {

    private Long target;
    
    public String execute() throws Exception {
        
        if (this.getTarget() == null || this.getTarget().longValue() == 0) {
            return Action.SUCCESS;
        }
        
        ActivityBD bd = ActivityBDFactory.factory.build(this.getSessionContext());
        
        Activity a = bd.get(this.getTarget());
        
        
        if (!a.getUsername().equals(this.getSessionContext().getUser().getUsername())) {
            /*
             * standard cannot delete anything for another users
             */
            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD ) {
                return Action.SUCCESS;
            }
            
            
        }
        
        ProjectServiceDelegate psd = ProjectServiceDelegateFactory.factory.build(this.getSessionContext());
        
        Project p = psd.getFstxProject(a.getProjectId());
        
        bd.delete(a);
        
        DashboardMiscellaneousRecentAction ra = new DashboardMiscellaneousRecentAction();
        ra.setTypeName("Activity deleted: ");
        
        String c = a.getComments();
        
        if (c.length() > 35) {
            c = c.substring(0,34) + "...";
        }
        ra.setDescription(p.getShortName() + ", " + SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(a.getDate()) + ", " + a.getElapsed() + ", " + c);
        this.getSessionContext().addToRecentActions(ra);
        
        
        return Action.SUCCESS;
        
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
    
}
