/*
 * Created on Jan 19, 2005 by Reid
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegate;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.externaluser.events.ExternalUserEventPublisher;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 */
public class CustomerUserAssociationAddUserAction extends
        SessionContextAwareSupport implements CustomerModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private String username;

    private String email;

    private String cancelUser;

    // private boolean notify;

    public String execute() throws Exception {

        if (this.getCancelUser() != null)
            return SUCCESS;

        if (this.getCustomerModifyContext().getTargetCustomer() == null)
            return ERROR;

        if (this.getEmail() == null)
            return INPUT;

        validate();

        if (this.hasErrors())
            return INPUT;

        String tempPassword = new RandomStringBuilder().buildRandomString(10);
        
        User user = handleAdd(tempPassword);

        /*
         * reid 2005-01-19 this would be exceptional;
         */
        if (this.hasErrors())
            return INPUT;

        new ExternalUserEventPublisher().publishUserAssociatedEvent(this
                .getSessionContext(), user,  this
                .getCustomerModifyContext().getTargetCustomer());

        return SUCCESS;
    }

    public void validate() {

        ValidationHelper helper = new ValidationHelper();

        if (!helper.isEmailAddress(this.getEmail())) {
            this.addFieldError("email",
                    "Does not appear to be a valid email address.");
        }

        if (!helper.isValidUsername(this.getUsername())) {
            this.addFieldError("username", "This is not a valid username");
        }

        if (!this.hasErrors()) {
            try {
                if (!this.isValidUsernameAndEmail()) {
                    // do something?
                }
            } catch (Exception e) {
                this.addActionError("Encountered an unknown error. ");
            }

        }

    }

    /**
     * have to check on the status of these usernames and passwords.
     * 
     * @return
     */
    private boolean isValidUsernameAndEmail() {

        boolean ret = false;

        UserBD bd = UserBDFactory.factory.build();

        User userByEmail = null;
        try {
            userByEmail = bd.getUserByEmail(this.getEmail());
        } catch (DAOException e) {
            // do nothing
        }
        User userByUsername = null;
        try {
            userByUsername = bd.getByUsername(this.getUsername());
        } catch (UserBDException e1) {
            // do nothing
        }

        if (this.isUniqueForBothOrTheSameForBoth(userByEmail, userByUsername)) {
            if (userByEmail != null && userByUsername != null) {
                /*
                 * this means they are the same.
                 */
                SystemUser systemUser = null;
                try {
                    systemUser = SystemUserServiceDelegateFactory.factory.build()
                            .getBySystemAndUser(
                                    this.getSessionContext().getSystemOwner(),
                                    this.getUsername());
                } catch (SystemUserServiceDelegateException e2) {
                    // do nothing
                }
                if (!systemUser.getUserType().equals(
                        UserTypeEnum.EXTERNAL.getName())) {
                    this
                            .addActionError("That user is already an internal user and connot be associated with this customers.");
                }
            }
        }

        return ret;
    }

    private boolean isUniqueForBothOrTheSameForBoth(User userByEmail,
            User userByUsername) {

        boolean ret = false;

        if (userByEmail == null && userByUsername == null) {
            ret = true;
        } else if (userByEmail != null && userByUsername == null) {
            this.setUsername(userByEmail.getUsername());
            this
                    .addFieldError(
                            "username",
                            "This email belongs to this user, not the username you entered. Submit to continue or enter new information.");
        } else if (userByUsername != null && userByEmail == null) {
            this.addFieldError("username", "This username is already taken.");
        } else if (userByEmail.getId().equals(userByUsername.getId())) {
            ret = true;
        }

        return ret;
    }

    /**
     * 
     */
    private User handleAdd(String tempPassword) throws Exception {
        /*
         * does the user exist?
         */

        User user = handleGetUser(tempPassword);

        /*
         * associate with this system owner;
         */
        SystemUserServiceDelegateFactory.factory.build().associate(
                this.getSessionContext().getSystemOwner(), user,
                UserTypeEnum.EXTERNAL);

        /*
         * is the user already associated with the customer?
         */
        ExternalUserServiceDelegate eusd = ExternalUserServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());

        if (!eusd.isAssociated(this.getCustomerModifyContext()
                .getTargetCustomer(), user)) {
            eusd.associate(user.getUsername(), this.getCustomerModifyContext()
                    .getTargetCustomer().getId());
        }

        return user;

    }

    /**
     * @return
     */
    private User handleGetUser(String password) {
        User ret = null;

        UserBD bd = UserBDFactory.factory.build();
        try {
            ret = bd.getUserByEmail(this.getEmail());
        } catch (DAOException e) {
            // do nothing
        }

        if (ret == null) {
            try {
                ret = bd.addUser(this.getUsername(), password, this.getEmail());
            } catch (UserBDException e1) {
                // do nothing
            }
        }

        return ret;
    }

    /**
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    /**
     * @return Returns the userEmail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param userEmail
     *            The userEmail to set.
     */
    public void setEmail(String userEmail) {
        this.email = userEmail;
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
}