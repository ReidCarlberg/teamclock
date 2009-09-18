/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 *  
 */
public interface UserDAO {

    public abstract User authenticateUser(String username, String password)
            throws DAOException;

    public abstract User save(User u) throws DAOException;

    public abstract User get(Long id) throws DAOException;

    public abstract void delete(User u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    public abstract Collection find(UserFilterParameters params)
            throws DAOException, HibernateException;

    /**
     * Returns a list of <code>User</code> s using the query specified by the
     * <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public abstract List find(String query, String value) throws DAOException;

    public abstract List find(String query) throws DAOException;

    public abstract User get(String email) throws DAOException;

    //public static final String SELECT_ALL = "select from users in class com.fstx.stdlib.authen.users.User where users.id > 0 order by users.username";

    //public static final String SELECT_ALL_KEY = "users.select.all";

    //public static final String SELECT_BY_AUTHENTICATE = "select from users in class com.fstx.stdlib.authen.users.User where (users.username = ? or users.email = ? ) and users.password = ?";

    //public static final String SELECT_BY_AUTHENTICATE_HASH = "select from users in class com.fstx.stdlib.authen.users.User where (users.username = ? or users.email = ? ) and users.passwordHash = ?";
    
    //public static final String SELECT_BY_EMAIL = "select from users in class com.fstx.stdlib.authen.users.User where users.email = ?";

}