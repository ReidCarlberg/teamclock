/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;
import com.opensymphony.xwork.Action;

/**
 * @author Reid
 */
public class TimeclockAdminAction extends SessionContextAwareSupport {

    public String execute() throws Exception {
        this.getSessionContext().setMenuSection(MenuSection.ADMIN);
        return Action.SUCCESS;
    }
}