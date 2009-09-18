/*
 * Created on Sep 10, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fstx.stdlib.authen.users.User;

/**
 * <p>
 * This actually interacts with the regular stdlib user information. Abstracts
 * role information from the user.
 * 
 * @author Reid
 */
public interface UserServiceDelegate extends SystemOwnerAware {

    public Collection listUserAndType() throws UserServiceDelegateException;

    public void updateUserType(User user, UserTypeEnum oldUserTypeEnum,
            UserTypeEnum newUserTypeEnum) throws UserServiceDelegateException;

    public User createNewUser(String username, String password, String email,
            UserTypeEnum userTypeEnum) throws UserServiceDelegateException;

    public User makeInactive(String username)
            throws UserServiceDelegateException;

    public User makeActive(String username) throws UserServiceDelegateException;

}