/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.superuser.SuperUserEmailAllSystemOwnersCommand;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SuperUserEmailAllSystemOwnersAction extends ActionSupport
        implements SessionContextAware {

    private SessionContext sessionContext;

    private String message;

    private String subject;

    private String submit;

    private String cancel;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        if (StringUtils.hasText(this.getMessage())
                && this.getMessage().length() < 50) {
            this
                    .addActionError("Message length must be at least 50 characters long!");
            return ERROR;
        }
        if (StringUtils.hasText(this.getCancel())) {
            return SUCCESS;
        }
        if (StringUtils.hasText(this.getSubmit())) {

            SuperUserEmailAllSystemOwnersCommand command = this
                    .getSuperUserEmailAllSystemOwnersCommand();
            command.setSuperUser(this.getSessionContext().getSystemOwner());
            command.setMessage(this.getMessage());
            command.setSubject(this.getSubject());
            command.execute();

            return SUCCESS;
        }
        return INPUT;
    }

    public SuperUserEmailAllSystemOwnersCommand getSuperUserEmailAllSystemOwnersCommand() {
        return new SuperUserEmailAllSystemOwnersCommand();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

}