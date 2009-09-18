/*
 * Created on Oct 20, 2004 by Reid
 */
package com.fivesticks.time.activity.xwork;

import java.io.Serializable;

import com.fivesticks.time.activity.ActivityFilterVO;

/**
 * @author Reid
 */
public class ActivityListContext implements Serializable {

    private ActivityFilterVO params;

    private boolean groupByUser;

    /**
     * @return Returns the params.
     */
    public ActivityFilterVO getParams() {
        return params;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(ActivityFilterVO params) {
        this.params = params;
    }

    /**
     * @return Returns the groupByUser.
     */
    public boolean isGroupByUser() {
        return groupByUser;
    }

    /**
     * @param groupByUser
     *            The groupByUser to set.
     */
    public void setGroupByUser(boolean groupByUser) {
        this.groupByUser = groupByUser;
    }
}