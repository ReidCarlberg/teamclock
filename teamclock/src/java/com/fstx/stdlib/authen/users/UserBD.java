/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users;

import java.util.Collection;

import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public interface UserBD extends UserDAOAware {

    /*
     * udpated 2005-03-12 no longer use.
     */
    //    public abstract void changePassword(String username,
    //            String currentPassword, String newPassword)
    //    	throws ChangePasswordException;
    public abstract void changePassword(User user, String newPassword)
            throws ChangePasswordException;

    public abstract void changePasswordFromReset(User user, String newPassword)
    throws ChangePasswordException;
    
    public abstract void changePassword(User user, String newPassword,
            SimpleDate expirationDate) throws ChangePasswordException;

    public abstract User getByUsername(String username) throws UserBDException;

    public abstract User getUserByEmail(String email) throws DAOException;

    public abstract User getUserByResetKey(String resetKey) throws DAOException;

    /**
     * @param string
     * @param string2
     * @param string3
     */
    public abstract User addUser(String username, String password, String email)
            throws UserBDException;

    /**
     * @return
     */
    public abstract Collection getAll() throws UserBDException;

    public abstract User updateUserEmail(String oldEmail, String newEmail)
            throws UserBDException;

    public abstract User makeInactive(User user) throws UserBDException;

    public abstract User makeActive(User user) throws UserBDException;

    public abstract User addResetKey(User user) throws UserBDException;
    
    public void delete(User user) throws UserBDException;

    /*
     * reid 2005-07-15 moved from dao.
     */
    public abstract User authenticateUser(String username, String password)
            throws UserBDException;
}