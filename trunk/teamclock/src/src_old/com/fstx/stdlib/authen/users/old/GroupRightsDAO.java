/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users.old;

import java.util.List;

import com.fstx.stdlib.common.dao.todelete.DAOException;

/**
 * @author Reid
 *  
 */
public interface GroupRightsDAO {

    public static final String SPRING_BEAN_NAME = "groupRightsDAO";

    public static final GroupRightsDAOFactory factory = new GroupRightsDAOFactory();

    public abstract GroupRights save(GroupRights u) throws DAOException;

    public abstract GroupRights get(Long id) throws DAOException;

    public abstract void delete(GroupRights u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * Returns a list of <code>GroupRights</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public abstract List find(String query, String value) throws DAOException;

    public static final String SELECT_BY_GROUP = "select rights from com.fstx.stdlib.authen.users.UserGroup as grp,com.fstx.stdlib.authen.users.GroupRights as rights where rights.groupId = grp.id and grp.name=?";

    public static final String SELECT_ALL_BY_GROUP_ID = "from rights in class com.fstx.stdlib.authen.users.GroupRights where rights.groupId = ?";
}