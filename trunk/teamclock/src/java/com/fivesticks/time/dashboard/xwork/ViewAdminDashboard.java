/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.dashboard.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;

/**
 * @author Reid
 */
public class ViewAdminDashboard extends SessionContextAwareSupport 
         {

    public String execute() throws Exception {
        this.getSessionContext().setMenuSection(MenuSection.ADMIN);
        return SUCCESS;
    }

}