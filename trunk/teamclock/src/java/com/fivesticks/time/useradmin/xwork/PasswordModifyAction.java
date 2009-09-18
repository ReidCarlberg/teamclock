/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.menu.MenuSection;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class PasswordModifyAction extends ActionSupport implements
        SessionContextAware, PasswordModifyContextAware, UserModifyContextAware {

    private SessionContext sessionContext;

    private PasswordModifyContext passwordModifyContext;

    private UserModifyContext userModifyContext;

    private String submitPassword;

    private String cancelPassword;

    private String userPassword1;

    private String userPassword2;

    private String targetUser;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.PASSWORD);

        if (this.getCancelPassword() != null) {
            if (this.getUserModifyContext().getTarget() == null)
                return SUCCESS;
            else
                return SUCCESS + "-admin";
        }

        if (this.getSubmitPassword() == null
                && this.getCancelPassword() == null) {
            if (this.getTargetUser() != null) {
                User user = UserBDFactory.factory.build().getByUsername(
                        this.getTargetUser());
                if (user == null) {
                    return ERROR;
                }
                this.getPasswordModifyContext().setTargetUser(user);
            } else {
                this.getPasswordModifyContext().setTargetUser(
                        this.getSessionContext().getUser().getUser());
            }

            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        UserBD bd = UserBDFactory.factory.build();

        SimpleDate expires = SimpleDate.factory.buildMidnight();
        expires.advanceDay(new Long(this.getSessionContext().getSettings().getUserPasswordLife()).intValue());
        
        bd.changePassword(this.getPasswordModifyContext().getTargetUser(), this
                .getUserPassword1(),expires);

        this.getPasswordModifyContext().setTargetUser(null);

        if (this.getUserModifyContext().getTarget() == null)
            return SUCCESS;
        else
            return SUCCESS + "-admin";
    }

    public void validate() {

        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getUserPassword1())) {
            this.addFieldError("userPassword1", "Password is required.");
        }

        if (helper.isStringEmpty(this.getUserPassword2())) {
            this.addFieldError("userPassword2",
                    "Password (confirm) is required.");
        }

        if (!this.getUserPassword1().equals(this.getUserPassword2())) {
            this.addFieldError("userPassword1", "Passwords must match.");
        }

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
     * @return Returns the passwordModifyContext.
     */
    public PasswordModifyContext getPasswordModifyContext() {
        return passwordModifyContext;
    }

    /**
     * @param passwordModifyContext
     *            The passwordModifyContext to set.
     */
    public void setPasswordModifyContext(
            PasswordModifyContext passwordModifyContext) {
        this.passwordModifyContext = passwordModifyContext;
    }

    /**
     * @return Returns the passwordCancel.
     */
    public String getCancelPassword() {
        return cancelPassword;
    }

    /**
     * @param passwordCancel
     *            The passwordCancel to set.
     */
    public void setCancelPassword(String passwordCancel) {
        this.cancelPassword = passwordCancel;
    }

    /**
     * @return Returns the passwordSubmit.
     */
    public String getSubmitPassword() {
        return submitPassword;
    }

    /**
     * @param passwordSubmit
     *            The passwordSubmit to set.
     */
    public void setSubmitPassword(String passwordSubmit) {
        this.submitPassword = passwordSubmit;
    }

    /**
     * @return Returns the userPassword1.
     */
    public String getUserPassword1() {
        return userPassword1;
    }

    /**
     * @param userPassword1
     *            The userPassword1 to set.
     */
    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

    /**
     * @return Returns the userPassword2.
     */
    public String getUserPassword2() {
        return userPassword2;
    }

    /**
     * @param userPassword2
     *            The userPassword2 to set.
     */
    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }

    /**
     * @return Returns the targetUser.
     */
    public String getTargetUser() {
        return targetUser;
    }

    /**
     * @param targetUser
     *            The targetUser to set.
     */
    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    /**
     * @return Returns the userModifyContext.
     */
    public UserModifyContext getUserModifyContext() {
        return userModifyContext;
    }

    /**
     * @param userModifyContext
     *            The userModifyContext to set.
     */
    public void setUserModifyContext(UserModifyContext userModifyContext) {
        this.userModifyContext = userModifyContext;
    }
}