/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.common.delete.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class CommonDeleteAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private String deleteConfirm;

    private String deleteCancel;

    public String execute() throws Exception {

        if (this.getSessionContext().getDeleteContext() == null
                || !this.getSessionContext().getDeleteContext().isValid())
            return ERROR;

        if (this.getDeleteConfirm() == null && this.getDeleteCancel() == null)
            return INPUT;

        if (this.getDeleteCancel() != null) {
            //do nothing...
        } else {
            try {
                this.getSessionContext().getDeleteContext().getCommand()
                        .delete(this.getSessionContext());
            } catch (Exception e) {
                return ERROR;
            }
        }

        String ret = this.getSessionContext().getDeleteContext().getSuccess();

        this.getSessionContext().setDeleteContext(null);

        return ret;
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

    /**
     * @return Returns the submitCancel.
     */
    public String getDeleteCancel() {
        return deleteCancel;
    }

    /**
     * @param submitCancel
     *            The submitCancel to set.
     */
    public void setDeleteCancel(String submitCancel) {
        this.deleteCancel = submitCancel;
    }

    /**
     * @return Returns the submitConfirm.
     */
    public String getDeleteConfirm() {
        return deleteConfirm;
    }

    /**
     * @param submitConfirm
     *            The submitConfirm to set.
     */
    public void setDeleteConfirm(String submitConfirm) {
        this.deleteConfirm = submitConfirm;
    }
}