/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.dashboard.util;

import org.apache.commons.lang.enums.Enum;

public class DashboardRecentActionType extends Enum {

    public static final DashboardRecentActionType TODO_ASSIGNED = new DashboardRecentActionType("Assigned");
    
    public static final DashboardRecentActionType TODO_COMPLETED = new DashboardRecentActionType("Completed");
    
    public static final DashboardRecentActionType TODO_MODIFIED = new DashboardRecentActionType("Modified");
    
    public static final DashboardRecentActionType TODO_PRIORITY = new DashboardRecentActionType("Priority Changed");
    
    public static final DashboardRecentActionType ACTIVITY = new DashboardRecentActionType("ACTIVITY");
    
    public static final DashboardRecentActionType TIME_CLOCK = new DashboardRecentActionType("Time clock");
    
    protected DashboardRecentActionType(String arg0) {
        super(arg0);
        
    }

    
}
