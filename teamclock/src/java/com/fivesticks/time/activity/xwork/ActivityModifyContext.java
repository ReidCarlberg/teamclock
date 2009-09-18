/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import com.fivesticks.time.activity.Activity;

public class ActivityModifyContext {

    public static final String KEY = "context.modify.activity";

    private Activity targetActivity;

    public ActivityModifyContext() {
        super();
    }

    public Activity getTargetActivity() {
        return targetActivity;
    }

    /**
     * @param customer
     */
    public void setTargetActivity(Activity activity) {
        targetActivity = activity;
    }

}