/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserAndTypeVOBuilder;
import com.fivesticks.time.useradmin.UserServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.UseradminDeleteCommand;
import com.fivesticks.time.useradmin.UseradminDeleteCommandFactory;
import com.fivesticks.time.useradmin.util.MaxActiveUserValidator;
import com.fivesticks.time.useradmin.util.MaxActiveUserValidatorException;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * 2004-09-13
 * 
 * @author Reid
 */
public class UserModifyAction extends ActionSupport implements
        SessionContextAware, UserModifyContextAware {

    private SessionContext sessionContext;

    private UserModifyContext userModifyContext;

    private String target;

    private String submitUser;

    private String cancelUser;

    private String deleteUser;

    private String userEmailString;

    private String userTypeString;

    private String userPassword1;

    private String userPassword2;

    private Collection userTypes = UserTypeEnum.getTypes();

    public String execute() throws Exception {

        if (this.getCancelUser() != null) {
            return SUCCESS;
        }

        if (this.getDeleteUser() != null) {
            UseradminDeleteCommand udc = UseradminDeleteCommandFactory.factory.build();
            udc.setTarget(this.getUserModifyContext().getTarget().getUser());
            DeleteContext dc = DeleteContextFactory.factory.build(udc);
            this.getSessionContext().setDeleteContext(dc);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;

        }

        UserBD userBD = UserBDFactory.factory.build();

        if (this.getSubmitUser() == null) {
            if (this.getTarget() != null) {
                User user = userBD.getByUsername(this.getTarget());

                /*
                 * validate to be sure the target owner actually belongs to the
                 * current user;
                 */

                SystemOwner systemOwner = SystemOwnerServiceDelegateFactory.factory
                        .build().get(user);

                if (!systemOwner.getKey().equals(
                        this.getSessionContext().getSystemOwner().getKey())) {
                    return INPUT;
                }

                UserAndTypeVO vo = new UserAndTypeVOBuilder().build(this
                        .getSessionContext().getSystemOwner(), user);

                this.setUserEmailString(user.getEmail());
                this.setUserTypeString(vo.getUserTypeEnum().getPrivateName());

                //UserModifyContext modifyContext = new UserModifyContext();
                //modifyContext.setTarget(vo);
                this.getUserModifyContext().setTarget(vo);

                //                ActionContext.getContext().getSession().put(
                //                        UserModifyContext.KEY, modifyContext);
            } else {
                this.getUserModifyContext().setTarget(null);
            }
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        handleUserUpdate();

        return SUCCESS;
    }

    /*
     * 2004-12-29 Reid Added in
     */
    public void validate() {

        ValidationHelper helper = new ValidationHelper();
        
        UserBD userBD = UserBDFactory.factory.build();

        if (!StringUtils.hasText(this.getUserEmailString()) || !helper.isEmailAddress(this.getUserEmailString())) {
            this.addFieldError("userEmailString", "Email is required and must be an Internet style email address.");
        }

        //        UserModifyContext userModifyContext = (UserModifyContext)
        // ActionContext
        //                .getContext().getSession().get(UserModifyContext.KEY);

        if (!this.hasErrors()
                && this.getUserModifyContext().getTarget() != null
                && !this.getUserEmailString().equals(
                        this.getUserModifyContext().getTarget().getUser()
                                .getEmail())) {
            User user = null;
            try {
                user = userBD.getUserByEmail(this.getUserEmailString());
            } catch (Exception e) {

            }

            if (user != null) {
                this.addFieldError("userAndType.user.email",
                        "That email address is already in use.");
            }
        }

        if (this.getUserModifyContext().getTarget() != null) {
            if (this.getUserModifyContext().getTarget().getUserTypeEnum() == UserTypeEnum.TIMECLOCK
                    && !this.getUserTypeString().equals(
                            UserTypeEnum.TIMECLOCK.getName())) {
                try {
                    //so the old type is timeclock only, the new type is not.
                    // let's make sure
                    //they have enough valid users to go forward
                    if (!new MaxActiveUserValidator().isValid(this
                            .getSessionContext().getSystemOwner(), this
                            .getSessionContext().getSettings()
                            .getMaxActiveUsers(), UserTypeEnum
                            .getByName(this.getUserTypeString()))) {
                        this
                                .addFieldError("userTypeString",
                                        "Cannot modify user type--you have exceed your active user limit.");
                    }
                } catch (MaxActiveUserValidatorException e) {
                    this.addActionError("coulnd't validate max active users.");
                }
            }

        }
    }

    private void handleUserUpdate() throws UserBDException,
            UserServiceDelegateException {

        //email
        User user = UserBDFactory.factory.build().updateUserEmail(
                this.getUserModifyContext().getTarget().getUser().getEmail(),
                this.getUserEmailString());

        //user type
        if (!this.getUserModifyContext().getTarget().getUserTypeEnum()
                .getName().equals(this.getUserTypeString())) {
            UserServiceDelegate userServiceDelegate = UserServiceDelegateFactory.factory
                    .build(this.getSessionContext().getSystemOwner());

            UserTypeEnum newUserType = UserTypeEnum.getByName(this
                    .getUserTypeString());

            /*
             * 2004-12-29 Reid If they are switching from a timeclock only to
             * something else, that should be
             */
            userServiceDelegate.updateUserType(user, userModifyContext
                    .getTarget().getUserTypeEnum(), newUserType);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.authen.webwork.SessionContextAware#setSessionContext(com.fivesticks.time.common.SessionContext)
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public SessionContext getSessionContext() {
        return this.sessionContext;
    }

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
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

    /**
     * @return Returns the deleteUser.
     */
    public String getDeleteUser() {
        return deleteUser;
    }

    /**
     * @param deleteUser
     *            The deleteUser to set.
     */
    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
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
}