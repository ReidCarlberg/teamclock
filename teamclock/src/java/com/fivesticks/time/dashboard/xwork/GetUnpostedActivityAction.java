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
public class GetUnpostedActivityAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {


        /*
         * 2007-01-10 RSC this is the same as the other except it gets the unposted.
         */
        Collection activity = ActivityBDFactory.factory.build(
                this.getSessionContext())
                .getUnpostedActivityForPostableProjects(
                        this.getSessionContext().getUser().getUsername());

        /*
         * 2006-07-07 this converts to an object with a collection and totals
         */
        this.setJsonResult(new Activity2JSONConverter(this.getSessionContext())
                .convert(activity));

        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_DASHBOARD);

        return SUCCESS;
    }

}