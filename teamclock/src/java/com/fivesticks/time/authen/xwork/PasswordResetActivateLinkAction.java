/*
 * Created on Oct 1, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 */
public class PasswordResetActivateLinkAction extends SessionContextAwareSupport {

    // private SessionContext sessionContext;

    private String key;

    private String submitUpdate;

    private String password1;

    private String password2;

    public String execute() throws Exception {

        UserBD bd = UserBDFactory.factory.build();

        if (this.submitUpdate == null) {
            return INPUT;
        }

        if (this.getPassword1() == null || this.getPassword2() == null) {
            return INPUT;
        }

        if (!this.getPassword1().equals(this.getPassword2())) {
            this.addActionMessage("Passwords must be the same.");
            return INPUT;
        }

        User targetUser;

        try {
            targetUser = bd.getUserByResetKey(this.getKey());
        } catch (DAOException e) {
            this
                    .addActionMessage("Not a valid reset key.  Please cut and paste the entire key into the \"key\" field or click on the link you received to continue.");
            return INPUT;
        }

        bd.changePasswordFromReset(targetUser, this.getPassword1());

        this.addActionMessage("Password change successful.");

        return SUCCESS;
    }

    /**
     * @return Returns the targetEmail.
     */
    public String getKey() {
        return key;
    }

    /**
     * @param targetEmail
     *            The targetEmail to set.
     */
    public void setKey(String targetEmail) {
        this.key = targetEmail;
    }

    /**
     * @return Returns the password1.
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param password1
     *            The password1 to set.
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * @return Returns the password2.
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2
     *            The password2 to set.
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * @return Returns the submitUpdate.
     */
    public String getSubmitUpdate() {
        return submitUpdate;
    }

    /**
     * @param submitUpdate
     *            The submitUpdate to set.
     */
    public void setSubmitUpdate(String submitUpdate) {
        this.submitUpdate = submitUpdate;
    }

}