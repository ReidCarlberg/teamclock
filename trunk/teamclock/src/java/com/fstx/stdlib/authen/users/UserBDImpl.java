/*
 * Created on Oct 29, 2003
 *
 */
package com.fstx.stdlib.authen.users;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;

import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.useradmin.util.PasswordDigester;
import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class UserBDImpl implements UserBD {


    private Random random = new Random(new Date().getTime());

    private static int MINIMUM_LENGTH = 50;

    private static int MAXIMUM_LENGTH = 99;
    
    private UserDAO userDAO;

    private GenericDAO dao;

    private PasswordDigester passwordDigester;



    public User getByUsername(String username) throws UserBDException {
        User ret = null;


        UserCriteriaParameters p = new UserCriteriaParameters();
        p.setUsername(username);
        
        Collection c = this.getDao().find(p);
        
        if (c.size() == 1)
            ret = (User) c.toArray()[0];
        else
            throw new UserBDException("not found." + username);
            
        return ret;
    }

    public User getUserByEmail(String email) throws DAOException {
        
        UserCriteriaParameters p = new UserCriteriaParameters();
        p.setEmail(email);
        
//        List u = this.getUserDAO().find(UserDAO.SELECT_BY_EMAIL, email);
//
        Collection c = this.getDao().find(p);
        
        if (c.size() == 0)
            throw new DAOException("user not found");

        if (c.size() > 1)
            throw new DAOException(
                    "Somehow we have more than one user with this email");
//
//        User ret = (User) u.get(0);

        
        return (User) c.toArray()[0];
    }

    public User getUserByResetKey(String resetKey) throws DAOException {
        
        UserCriteriaParameters p = new UserCriteriaParameters();
        p.setResetKey(resetKey);
        
//        List u = this.getUserDAO().find(UserDAO.SELECT_BY_EMAIL, email);
//
        Collection c = this.getDao().find(p);
        
        if (c.size() == 0)
            throw new DAOException("user not found");

        if (c.size() > 1)
            throw new DAOException(
                    "Somehow we have more than one user with this email");
//
//        User ret = (User) u.get(0);

        
        return (User) c.toArray()[0];
    }

    /**
     * @param string
     * @param string2
     * @param string3
     */
    public User addUser(String username, String password, String email)
            throws UserBDException {
        User t = new User();

        t.setUsername(username);
        t.setPassword(password);
        t.setEmail(email);

        try {
            t = this.getUserDAO().save(t);
        } catch (DAOException e) {
            throw new UserBDException(e);
        }

        return t;
    }

    /**
     * @return
     */
    public Collection getAll() throws UserBDException {
        UserFilterParameters params = new UserFilterParameters();
        try {
            return this.getUserDAO().find(params);
        } catch (HibernateException e) {
            throw new UserBDException(e);
        } catch (DAOException e) {
            throw new UserBDException(e);
        }
    }

    /**
     * @return
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * @param userDAO
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserBD#updateUserEmail(java.lang.String)
     */
    public User updateUserEmail(String oldEmail, String newEmail)
            throws UserBDException {

        User user;
        try {
            user = this.getUserByEmail(oldEmail);
        } catch (DAOException e1) {

            throw new UserBDException(e1);
        }
        if (!oldEmail.equals(newEmail)) {
            user.setEmail(newEmail);

            try {
                this.getUserDAO().save(user);
            } catch (DAOException e) {
                throw new UserBDException(e);
            }

        }
        return user;
    }

    public void changePassword(User user, String newPassword)
            throws ChangePasswordException {
        this.changePassword(user, newPassword, SimpleDate.factory
                .buildMidnight());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserBD#changePassword(com.fstx.stdlib.authen.users.User,
     *      java.lang.String)
     */
    public void changePassword(User user, String newPassword,
            SimpleDate simpleDate) throws ChangePasswordException {

        user.setPassword(newPassword);
        user.setPasswordExpires(simpleDate.getDate());

        try {
            this.getUserDAO().save(user);
        } catch (DAOException e) {

            throw new ChangePasswordException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserBD#makeInactive(com.fstx.stdlib.authen.users.User)
     */
    public User makeInactive(User user) throws UserBDException {

        user.setBooleanInactive(true);

        try {
            return this.getUserDAO().save(user);
        } catch (DAOException e) {

            throw new UserBDException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserBD#makeActive(com.fstx.stdlib.authen.users.User)
     */
    public User makeActive(User user) throws UserBDException {
        user.setBooleanInactive(false);

        try {
            return this.getUserDAO().save(user);
        } catch (DAOException e) {
            throw new UserBDException(e);
        }
    }

    public void delete(User user) throws UserBDException {
        try {
            this.getUserDAO().delete(user);
        } catch (DAOException e) {
            throw new UserBDException(e);
        }
    }

    /**
     * @return Returns the dao.
     */
    public GenericDAO getDao() {
        return dao;
    }

    /**
     * @param dao
     *            The dao to set.
     */
    public void setDao(GenericDAO dao) {
        this.dao = dao;
    }

    public User authenticateUser(String username, String password)
            throws UserBDException {

        String passwordHash = this.getPasswordDigester().getHash(password);

        UserCriteriaParameters p = new UserCriteriaParameters();
        p.setUsername(username);
        p.setPasswordHash(passwordHash);

        Collection list = this.getDao().find(p);

        if (list.size() != 1) {
            //            log.info("list size is not 1 " + list.size());
            
            
            p.setUsername(null);
            p.setEmail(username);
            
            list = this.getDao().find(p);
            
            if (list.size() != 1) {
                throw new UserBDException("not found or too many found "
                        + list.size());
            }
        }

        return (User) list.toArray()[0];

    }

    /**
     * @return Returns the passwordDigester.
     */
    public PasswordDigester getPasswordDigester() {
        return passwordDigester;
    }

    /**
     * @param passwordDigester
     *            The passwordDigester to set.
     */
    public void setPasswordDigester(PasswordDigester passwordDigester) {
        this.passwordDigester = passwordDigester;
    }
    
    
    private void decorateWithResetKey(User user) {
        
        boolean ready = false;
        
        String keyCandidate = null;
        while (!ready) {
            keyCandidate = this.generateKey();
            
            try {
                this.getUserByResetKey(keyCandidate);
            } catch (DAOException e) {
                ready = true;
            }
            
        }
        
        user.setResetKey(keyCandidate);
        
    }
    
    private String generateKey() {

        int keyLength = MINIMUM_LENGTH
                + Math
                        .abs(random.nextInt()
                                % (MAXIMUM_LENGTH - MINIMUM_LENGTH));

        return new RandomStringBuilder().buildRandomString(keyLength);

    }

    public User addResetKey(User user) throws UserBDException {
        this.decorateWithResetKey(user);
        
        try {
            this.getUserDAO().save(user);
        } catch (DAOException e) {
            throw new UserBDException(e);
        }
        
        return user;
    }

    public void changePasswordFromReset(User user, String newPassword) throws ChangePasswordException {
        this.changePassword(user, newPassword);
        user.setResetKey(null);
        try {
            this.getUserDAO().save(user);
        } catch (DAOException e) {
            throw new ChangePasswordException(e);
        }
    }    

}