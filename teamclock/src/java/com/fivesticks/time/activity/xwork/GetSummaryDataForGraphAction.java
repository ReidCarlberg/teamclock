/*
 * Created on Jul 19, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.opensymphony.xwork.Action;

public class GetSummaryDataForGraphAction extends SessionContextAwareSupport
        implements ActivitySummaryContextAware {

    private ActivitySummaryContext activitySummaryContext;

    private String type;
    
    
    
    public String execute() throws Exception {
        
        if (StringUtils.hasText(this.getType()))
            return Action.SUCCESS + "." + this.getType();
        
        return Action.SUCCESS;
    }
    /**
     * @return Returns the activitySummaryContext.
     */
    public ActivitySummaryContext getActivitySummaryContext() {
        return activitySummaryContext;
    }

    /**
     * @param activitySummaryContext The activitySummaryContext to set.
     */
    public void setActivitySummaryContext(
            ActivitySummaryContext activitySummaryContext) {
        this.activitySummaryContext = activitySummaryContext;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    
    
}
