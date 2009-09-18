/*
 * Created on Jun 22, 2004
 *
 */
package com.fivesticks.time.activity.xwork;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

/**
 * @author Reid
 *  
 */
public class StopFromDashboardAction extends AbstractJSONAction {

    private Long target;



    public String execute() throws Exception {

        if (this.getTarget() == null)
            return INPUT;

        ActivityBD bd = ActivityBDFactory.factory.build(this
                .getSessionContext());

        Activity targetActivity = bd.get(this.getTarget());

        if (targetActivity == null)
            return INPUT;

        if (targetActivity.getStop() != null) {
            //what are we doing here???
        } else {
            bd.stop(targetActivity);
        }
        
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