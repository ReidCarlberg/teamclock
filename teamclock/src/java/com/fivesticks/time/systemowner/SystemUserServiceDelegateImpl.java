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
public class SystemUserServiceDelegateImpl implements
        SystemUserServiceDelegate, SystemUserDAOAware {

    private SystemUserDAO systemUserDAO;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemUserServiceDelegate#get(com.fstx.stdlib.authen.users.User)
     */
    public SystemUser get(User user) throws SystemUserServiceDelegateException {

        return this.get(user.getUsername());
    }

    /**
     * @return Returns the systemUserDAO.
     */
    public SystemUserDAO getSystemUserDAO() {
        return systemUserDAO;
    }

    /**
     * @param systemUserDAO
     *            The systemUserDAO to set.
     */
    public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemUserServiceDelegate#associate(com.fivesticks.time.systemowner.SystemOwner,
     *      com.fstx.stdlib.authen.users.User)
     */
    public void associate(SystemOwner systemOwner, User user,
            UserTypeEnum userTypeEnum)
            throws SystemUserServiceDelegateException {

        if (isAssociated(systemOwner, user)) {
            return;
        }

        SystemUser systemUser = new SystemUser();
        systemUser.setOwnerKey(systemOwner.getKey());
        systemUser.setUsername(user.getUsername());
        if (userTypeEnum != null)
            systemUser.setUserType(userTypeEnum.getName());
        this.getSystemUserDAO().save(systemUser);
    }

    public boolean isAssociated(SystemOwner systemOwner, User user) {

        SystemUser system = null;

        try {
            system = this.getBySystemAndUser(systemOwner, user);
        } catch (SystemUserServiceDelegateException e) {
            //do nothing.
        }

        return system != null;

    }

    public SystemUser getBySystemAndUser(SystemOwner systemOwner, User user)
            throws SystemUserServiceDelegateException {

        return this.getBySystemAndUser(systemOwner, user.getUsername());
    }

    public SystemUser getBySystemAndUser(SystemOwner systemOwner,
            String username) throws SystemUserServiceDelegateException {

        SystemUserSearchParameters params = new SystemUserSearchParameters();

        params.setOwnerKey(systemOwner.getKey());
        params.setUsername(username);

        Collection matches = this.getSystemUserDAO().search(params);

        if (matches.size() == 1)
            return (SystemUser) matches.toArray()[0];
        else
            throw new SystemUserServiceDelegateException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemUserServiceDelegate#disassociate(com.fivesticks.time.systemowner.SystemOwner,
     *      com.fstx.stdlib.authen.users.User)
     */
    public void disassociate(SystemOwner systemOwner, User user)
            throws SystemUserServiceDelegateException {
        SystemUser target = this.getBySystemAndUser(systemOwner, user);

        if (target != null)
            this.getSystemUserDAO().delete(target);
    }

    public Collection list(SystemOwner systemOwner)
            throws SystemUserServiceDelegateException {

        SystemUserSearchParameters params = new SystemUserSearchParameters();

        params.setOwnerKey(systemOwner.getKey());

        return this.getSystemUserDAO().search(params);

    }

    public Collection list(String username)
            throws SystemUserServiceDelegateException {

        SystemUserSearchParameters params = new SystemUserSearchParameters();

        params.setUsername(username);

        return this.getSystemUserDAO().search(params);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemUserServiceDelegate#get(java.lang.String)
     */
    public SystemUser get(String username)
            throws SystemUserServiceDelegateException {

        SystemUserSearchParameters params = new SystemUserSearchParameters();

        params.setUsername(username);

        Collection collection = this.getSystemUserDAO().search(params);

        SystemUser ret = null;

        if (collection.size() == 1)
            ret = (SystemUser) collection.toArray()[0];
        else
            throw new SystemUserServiceDelegateException("couldn't get user");

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemUserServiceDelegate#save(com.fivesticks.time.systemowner.SystemUser)
     */
    public SystemUser save(SystemUser systemUser)
            throws SystemUserServiceDelegateException {

        this.getSystemUserDAO().save(systemUser);

        return systemUser;
    }

}