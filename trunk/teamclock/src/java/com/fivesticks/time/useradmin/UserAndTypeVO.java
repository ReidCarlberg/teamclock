/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class UserAndTypeVO {

    private User user;

    private UserTypeEnum userTypeEnum;

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Returns the userTypeEnum.
     */
    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    /**
     * @param userTypeEnum
     *            The userTypeEnum to set.
     */
    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }
}