/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.useradmin.UserServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.util.MaxActiveUserValidator;
import com.fivesticks.time.useradmin.util.MaxActiveUserValidatorException;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;
import com.opensymphony.xwork.ActionSupport;

/**
 * 2004-09-13
 * 
 * @author Reid
 */
public class UserAddAction extends ActionSupport implements SessionContextAware {

    private SessionContext sessionContext;

    private String submitUser;

    private String cancelUser;

    private String username;

    private String userEmailString;

    private String userTypeString;

    private String userPassword1;

    private String userPassword2;

    private Collection userTypes = UserTypeEnum.getTypes();

    public String execute() throws Exception {

        if (this.getCancelUser() != null) {
            return SUCCESS;
        }

        if (this.getSubmitUser() == null) {
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        UserServiceDelegate sd = UserServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());

        sd.createNewUser(this.getUsername(), this.getUserPassword1(), this
                .getUserEmailString(), UserTypeEnum.getByName(this
                .getUserTypeString()));

        return SUCCESS;
    } /*
       * (non-Javadoc)
       * 
       * @see com.opensymphony.xwork.Validateable#validate()
       */

    public void validate() {

        if (this.getCancelUser() != null || this.getSubmitUser() == null) {
            return;
        }

        UserBD bd = UserBDFactory.factory.build();

        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getUsername())) {
            this.addFieldError("username", "Username is required.");
        } else if (!helper.isValidUsername(this.getUsername())) {
            this.addFieldError("username", "Not a valid username.");
        } else {
            User testUser = null;
            try {
                testUser = bd.getByUsername(this.getUsername());
            } catch (UserBDException e) {

            }
            if (testUser != null) {
                this.addFieldError("username",
                        "Username is taken.  Please try another.");
            }
        }

        if (helper.isStringEmpty(this.getUserEmailString())) {
            this.addFieldError("userEmailString", "Email address is required.");
        } else if (!helper.isEmailAddress(this.getUserEmailString())) {
            this.addFieldError("userEmailString",
                    "Does not appear to be a valid username.");
        } else {
            User testUser = null;
            try {
                testUser = bd.getUserByEmail(this.getUserEmailString());
            } catch (DAOException e) {

            }
            if (testUser != null) {
                this
                        .addFieldError("userEmailString",
                                "Email address is already registered on this system.  Please try another.");
            }
        }

        if (helper.isStringEmpty(this.getUserTypeString())) {
            this.addFieldError("userTypeString", "User type is required.");
        }

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

        /*
         * Are we OK on active users?
         */
        if (!this.hasErrors()) {
            try {
                if (!new MaxActiveUserValidator().isValid(this
                        .getSessionContext().getSystemOwner(), this
                        .getSessionContext().getSettings()
                        .getMaxActiveUsers(), UserTypeEnum.getByName(this
                        .getUserTypeString()))) {
                    this.addActionError("failed");
                    this.getSessionContext().setMessage(
                            "Too many active users. (Max: "
                                    + this.getSessionContext().getSettings()
                                            .getMaxActiveUsers() + ")");
                }
            } catch (MaxActiveUserValidatorException e) {
                this.addActionError("Couldn't validate max active users.");
            }
        }
    }

    /**
     * @return Returns the cancelUser.
     */
    public String getCancelUser() {
        return cancelUser;
    }

    /**
     * @param cancelUser
     *            The cancelUser to set.
     */
    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
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
     * @return Returns the submitUser.
     */
    public String getSubmitUser() {
        return submitUser;
    }

    /**
     * @param submitUser
     *            The submitUser to set.
     */
    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }

    /**
     * @return Returns the userEmailString.
     */
    public String getUserEmailString() {
        return userEmailString;
    }

    /**
     * @param userEmailString
     *            The userEmailString to set.
     */
    public void setUserEmailString(String userEmailString) {
        this.userEmailString = userEmailString;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return Returns the userTypes.
     */
    public Collection getUserTypes() {
        return userTypes;
    }

    /**
     * @param userTypes
     *            The userTypes to set.
     */
    public void setUserTypes(Collection userTypes) {
        this.userTypes = userTypes;
    }

    /**
     * @return Returns the userTypeString.
     */
    public String getUserTypeString() {
        return userTypeString;
    }

    /**
     * @param userTypeString
     *            The userTypeString to set.
     */
    public void setUserTypeString(String userTypeString) {
        this.userTypeString = userTypeString;
    }
}