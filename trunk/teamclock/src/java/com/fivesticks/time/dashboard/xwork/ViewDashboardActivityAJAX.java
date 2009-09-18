/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.util.Activity2JSONConverter;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;

/**
 * @author Reid
 * 
 */
public class ViewDashboardActivityAJAX extends AbstractDashboardJSONAction {



    public String execute() throws Exception {

        // activity
        if (this.getDashboardContext().getActivityUsername() == null) {
            this.getDashboardContext().setActivityUsername(
                    this.getSessionContext().getUser().getUsername());
        }

        Collection activity = ActivityBDFactory.factory.build(
                this.getSessionContext()).getDaysActivityForUser(
                this.getDashboardContext().getActivityUsername(),
                this.getDashboardContext().getActivityTargetDate());

        /*
         * 2006-07-07 this converts to an object with a collection and totals
         */
        this.setJsonResult(new Activity2JSONConverter(this
                .getSessionContext()).convert(activity));

        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_DASHBOARD);

        return SUCCESS;
    }


}