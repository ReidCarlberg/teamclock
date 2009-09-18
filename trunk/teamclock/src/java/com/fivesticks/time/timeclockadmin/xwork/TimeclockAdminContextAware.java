/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

/**
 * @author Reid
 */
public interface TimeclockAdminContextAware {

    public void setTimeclockAdminContext(
            TimeclockAdminContext timeclockAdminContext);
}