/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Collection;

import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface SystemUserServiceDelegate {

    public SystemUser get(User user) throws SystemUserServiceDelegateException;

    public SystemUser get(String username)
            throws SystemUserServiceDelegateException;

    public SystemUser save(SystemUser systemUser)
            throws SystemUserServiceDelegateException;

    public SystemUser getBySystemAndUser(SystemOwner systemOwner, User user)
            throws SystemUserServiceDelegateException;

    public SystemUser getBySystemAndUser(SystemOwner systemOwner,
            String username) throws SystemUserServiceDelegateException;

    public void associate(SystemOwner systemOwner, User user,
            UserTypeEnum userTypeEnum)
            throws SystemUserServiceDelegateException;

    public void disassociate(SystemOwner systemOwner, User user)
            throws SystemUserServiceDelegateException;

    public Collection list(SystemOwner systemOwner)
            throws SystemUserServiceDelegateException;

    public Collection list(String username)
            throws SystemUserServiceDelegateException;

}