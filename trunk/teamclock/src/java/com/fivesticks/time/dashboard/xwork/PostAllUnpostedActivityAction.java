/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.accountactivity.xwork.AccountActivityPoster;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.util.Activity2JSONConverter;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;

/**
 * @author Reid
 * 
 */
public class PostAllUnpostedActivityAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {

        Collection activity = ActivityBDFactory.factory.build(
                this.getSessionContext())
                .getUnpostedActivityForPostableProjects(
                        this.getSessionContext().getUser().getUsername());

        AccountActivityPoster poster = new AccountActivityPoster(this.getSessionContext());
        
        ActivityBD activityBD = ActivityBDFactory.factory.build(this.getSessionContext());
        
        for (Iterator iter = activity.iterator(); iter.hasNext();) {
            Activity element = (Activity) iter.next();
            poster.handlePost(element);
            activityBD.save(element);
        }
        
        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_DASHBOARD);

        return SUCCESS;
    }

}