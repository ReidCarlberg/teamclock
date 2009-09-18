/*
 * Created on Oct 1, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.system.messages.PasswordHelp;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class PasswordResetSendLinkAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private String targetEmail;

    private String submitPasswordHelp;

    public String execute() throws Exception {

        if (this.getSubmitPasswordHelp() == null)
            return INPUT;

        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getTargetEmail())) {
            this.addFieldError("targetEmail",
                    "Email address is required to receive password help.");
            return INPUT;
        }

        UserBD bd = UserBDFactory.factory.build();

        User foundUser = null;

        try {
            foundUser = bd.getUserByEmail(this.getTargetEmail());
        } catch (Exception e) {
            this.addFieldError("targetEmail", "That email is not valid.");
            return INPUT;
        }

        /*
         * 2005-11-27 Reid the original reset the PW to a random string, which
         * is irritating if someone does that to you when you'd prefer they
         * didn't
         */
        // PasswordHelp ph = new PasswordHelp();
        // ph.sendPasswordHelp(this.getTargetEmail());
        bd.addResetKey(foundUser);

        PasswordHelp ph = new PasswordHelp();
        ph.sendPasswordResetLink(foundUser);

        return SUCCESS;
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
     * @return Returns the targetEmail.
     */
    public String getTargetEmail() {
        return targetEmail;
    }

    /**
     * @param targetEmail
     *            The targetEmail to set.
     */
    public void setTargetEmail(String targetEmail) {
        this.targetEmail = targetEmail;
    }

    /**
     * @return Returns the submitPasswordHelp.
     */
    public String getSubmitPasswordHelp() {
        return submitPasswordHelp;
    }

    /**
     * @param submitPasswordHelp
     *            The submitPasswordHelp to set.
     */
    public void setSubmitPasswordHelp(String submitPasswordHelp) {
        this.submitPasswordHelp = submitPasswordHelp;
    }
}