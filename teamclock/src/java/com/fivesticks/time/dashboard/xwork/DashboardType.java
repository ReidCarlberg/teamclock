/*
 * Created on Jul 1, 2005
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 *
 */
public class DashboardType extends Enum {

    public static final DashboardType DEFAULT = new  DashboardType("Default");
    
    public static final DashboardType STANDARD = new DashboardType("Standard");
    
    public static final DashboardType CALENDAR = new DashboardType("Calendar");
    
    public static final DashboardType TIME_CLOCK = new DashboardType("TimeClock");
    
    
    
    /**
     * @param arg0
     */
    protected DashboardType(String arg0) {
        super(arg0);
        
    }
    
    public static DashboardType getType(String name) {
        return (DashboardType) Enum.getEnum(DashboardType.class, name);
    }
    public static Collection getAll() {
        return Enum.getEnumList(DashboardType.class);
    }

}
