/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.activity.rest;

import org.apache.commons.lang.enums.Enum;

public class ActivityRestResultType extends Enum {

    public static final ActivityRestResultType SUCCESS_POST = new ActivityRestResultType("SUCCESS_POST");
    
    protected ActivityRestResultType(String arg0) {
        super(arg0);
    }
    
    public static ActivityRestResultType getByName(String name) {
        return (ActivityRestResultType) Enum.getEnum(ActivityRestResultType.class,name);
    }

}
