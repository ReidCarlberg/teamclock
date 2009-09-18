/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.dashboard.util;

public interface DashboardRecentAction {

    public String getTypeName();
    
    public Long getId();
    
    public String getDescription() throws Exception ;
}
