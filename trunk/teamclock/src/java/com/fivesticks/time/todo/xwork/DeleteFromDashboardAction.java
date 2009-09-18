/*
 * Created on Jul 6, 2006 by Reid
 */
package com.fivesticks.time.todo.xwork;

import java.text.SimpleDateFormat;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.dashboard.util.DashboardMiscellaneousRecentAction;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.opensymphony.xwork.Action;

public class DeleteFromDashboardAction extends AbstractDashboardJSONAction {

    private Long target;
    
    public String execute() throws Exception {
        
        if (this.getTarget() == null || this.getTarget().longValue() == 0) {
            return Action.SUCCESS;
        }
        
        ToDoServiceDelegate tsd = ToDoServiceDelegateFactory.factory.build(this.getSessionContext());
        
        ToDo todo = tsd.get(this.getTarget());
        
        
        
        ProjectServiceDelegate psd = ProjectServiceDelegateFactory.factory.build(this.getSessionContext());
        
        Project p = psd.getFstxProject(todo.getProjectId());
        
        tsd.delete(todo);
        
        DashboardMiscellaneousRecentAction ra = new DashboardMiscellaneousRecentAction();
        ra.setTypeName("To-do deleted: ");
        
        String c = todo.getDetail();
        
        if (c.length() > 35) {
            c = c.substring(0,34) + "...";
        }
        ra.setDescription(p.getShortName() + ", " + SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(todo.getCreateTimestamp()) + ", " + c);

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
