/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import java.util.Collection;

import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.useradmin.UserTypeEnum;

public class ActivitySummarizeByProjectAction extends
        AbstractSummarizeActivityAction {



    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.ACTIVITY);

        ActivityBD activityBD = ActivityBDFactory.factory.build(this
                .getSessionContext());

        if (this.getSearchActivities() != null) {
            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
                params.setUsername(this.getSessionContext().getUser().getUsername());
            }
            
            Collection summary = activityBD.getTimeTotalsByProject(params);

            this.setSummary(summary);
            
            this.getActivityListContext().setParams(this.params);
            
            this.initializeShiftTotals();
        } else {
            if (this.getActivityListContext().getParams() != null) {
                this.params = this.getActivityListContext().getParams();
            }
        }

        return SUCCESS;
    }

 
}