/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class PasswordModifyContext {

    private User targetUser;

    /**
     * @return Returns the targetUser.
     */
    public User getTargetUser() {
        return targetUser;
    }

    /**
     * @param targetUser
     *            The targetUser to set.
     */
    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }
}