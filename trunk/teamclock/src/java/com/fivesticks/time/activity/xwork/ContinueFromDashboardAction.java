/*
 * Created on Jun 22, 2004
 *
 */
package com.fivesticks.time.activity.xwork;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class ContinueFromDashboardAction extends AbstractJSONAction
         {

    private Long target;



    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ActivityBD bd = ActivityBDFactory.factory.build(this
                .getSessionContext());

        Activity targetActivity = bd.get(this.getTarget());

        if (targetActivity == null)
            return INPUT;

        targetActivity.setStart(DateTimeRounderFactory.factory
                .build(
                        this.getSessionContext().getSettings()
                                .getActivityRounderType()).round(this.getSessionContext().getResolvedNow()));

        /*
         * 2006-08-21 blech.  reid@fivesticks.com  without the "buildMidnight" we get something where
         * the data doesn't report to the proper date.  Yech.
         */
        targetActivity.setDate(SimpleDate.factory.buildMidnight(this.getSessionContext().getResolvedNow()).getDate());
        targetActivity.setStop(null);
        targetActivity.setElapsed(null);
        targetActivity.setChargeableElapsed(null);

        targetActivity.setId(null);

        targetActivity.setUsername(this.getSessionContext().getUser().getUsername());
        
        bd.save(targetActivity);

        this.setSuccessful();
        
        return SUCCESS;
    }

    /**
     * @return
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param long1
     */
    public void setTarget(Long long1) {
        target = long1;
    }


}