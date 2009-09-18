/*
 * Created on Jun 24, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractActivityListContextAwareSupport extends
        SessionContextAwareSupport implements ActivityListContextAware {
    
    private ActivityListContext activityListContext;

    /**
     * @return Returns the activityListContext.
     */
    public ActivityListContext getActivityListContext() {
        return activityListContext;
    }

    /**
     * @param activityListContext The activityListContext to set.
     */
    public void setActivityListContext(ActivityListContext activityListContext) {
        this.activityListContext = activityListContext;
    }
    
    

}
