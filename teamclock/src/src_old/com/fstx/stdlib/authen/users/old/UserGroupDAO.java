/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users.old;

import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 *  
 */
public interface UserGroupDAO {

    public static final String SPRING_BEAN_NAME = "userGroupDAO";

    public static final UserGroupDAOFactory factory = new UserGroupDAOFactory();

    public abstract UserGroup save(UserGroup u) throws DAOException;

    public abstract UserGroup get(Long id) throws DAOException;

    public abstract void delete(UserGroup u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * Returns a list of <code>Group</code> s using the query specified by the
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

    public abstract UserGroup findByName(String groupName) throws DAOException;

    // Selects all of the groups based on the user name.
    // example: List l =
    // dao.searchGroups(EMSGroupDAO.SELECT_BY_USER,user.getUsername());
    public static final String SELECT_BY_USER = "select emsGroup from com.fstx.stdlib.authen.users.User as user, com.fstx.stdlib.authen.users.GroupMembership as grpmem, com.fstx.stdlib.authen.users.UserGroup as emsGroup where emsGroup.id = grpmem.groupId and grpmem.userId=user.id and user.username=?";

    public static final String SELECT_ALL = "from usergroup in class com.fstx.stdlib.authen.users.UserGroup where usergroup.id > 0 order by usergroup.name";
}