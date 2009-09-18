/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.dashboard.util;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.todo.ToDo;

public class DashboardRecentToDoAction implements DashboardRecentAction {

    private final DashboardRecentActionType actionType;
    private final ToDo target;
    private final SystemOwner systemOwner;
    
    private String description;
    
    public DashboardRecentToDoAction(DashboardRecentActionType actionType, ToDo target, SystemOwner systemOwner) {
        this.actionType = actionType;
        this.target = target;
        this.systemOwner = systemOwner;
    }
    public String getTypeName() {
        
        return this.actionType.getName();
    }

    public Long getId() {
        
        return target.getId();
    }

    public String getDescription() throws Exception  {
        
        if (this.description == null) {
            Project proj = ProjectServiceDelegateFactory.factory.build(this.systemOwner).getFstxProject(this.target.getProjectId());
            this.description = "" + proj.getShortName() + ", " + target.getAssignedToUser() + ", " ;
            if (target.getDetail().length() > 90) {
                description += target.getDetail().substring(0,90) + "...";
            } else {
                description+=target.getDetail();
            }
        }
        return description;
    }

}
