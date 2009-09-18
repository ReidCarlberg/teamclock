/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.activity.rest;

import org.apache.commons.lang.enums.Enum;

public class ActivityRestActionType extends Enum {

    public static final ActivityRestActionType POST_COMPLETE = new ActivityRestActionType("POST_COMPLETE");
    
    protected ActivityRestActionType(String arg0) {
        super(arg0);
    }
    
    public static ActivityRestActionType getByName(String name) {
        return (ActivityRestActionType) Enum.getEnum(ActivityRestActionType.class,name);
    }

}
