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
public interface GroupMembershipDAO {

    public static final String SPRING_BEAN_NAME = "groupMembershipDAO";

    public static final GroupMembershipDAOFactory factory = new GroupMembershipDAOFactory();

    public abstract GroupMembership save(GroupMembership u) throws DAOException;

    public abstract GroupMembership get(Long id) throws DAOException;

    public abstract void delete(GroupMembership u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * Returns a list of <code>GroupMembership</code> s using the query
     * specified by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public abstract List find(String query, String value) throws DAOException;

    public abstract List find(String query, String[] values)
            throws DAOException;

    public abstract List find(User user) throws DAOException;

    public abstract List find(UserGroup target) throws DAOException;

    public static final String SELECT_GROUPMEMBERSHIP_BY_GROUP = "from groupmembership in class com.fstx.stdlib.authen.users.GroupMembership where groupmembership.groupId = ?";

    public static final String SELECT_GROUPMEMBERSHIP_BY_USER = "from groupmembership in class com.fstx.stdlib.authen.users.GroupMembership where groupmembership.userId = ?";

    public static final String SELECT_BY_GROUP_USER_MEMBERS = "select groupuser from com.fstx.stdlib.authen.users.GroupMembership as membership, com.fstx.stdlib.authen.users.User as groupuser where membership.userId = groupuser.id and membership.groupId = ?";

    public static final String SELECT_BY_GROUP_AND_USER = "from groupmembership in class com.fstx.stdlib.authen.users.GroupMembership where groupmembership.groupId = ? and groupmembership.userId = ?";

}