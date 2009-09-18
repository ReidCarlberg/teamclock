/*
 * Created on Oct 29, 2004 by Reid
 */
package com.fivesticks.time.help.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.menu.MenuSection;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ShowHelpAction extends ActionSupport implements
        SessionContextAware {

    private String back;

    private String file;

    private SessionContext sessionContext;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.HELP);

        return SUCCESS;
    }

    /**
     * @return Returns the back.
     */
    public String getBack() {
        return back;
    }

    /**
     * @param back
     *            The back to set.
     */
    public void setBack(String back) {
        this.back = back;
    }

    /**
     * @return Returns the file.
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file
     *            The file to set.
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}