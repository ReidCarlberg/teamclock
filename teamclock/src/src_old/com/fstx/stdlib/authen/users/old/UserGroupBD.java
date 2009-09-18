/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users.old;

/**
 * @author Reid
 *  
 */
public interface UserGroupBD extends GroupMembershipDAOAware {

    public static final String SPRING_BEAN_NAME = "userGroupBD";

    public static final UserGroupBDFactory factory = new UserGroupBDFactory();

    public UserGroup save(UserGroup userGroup) throws UserGroupBDException;

    public abstract void join(User user, UserGroup group)
            throws UserGroupBDException;

    public abstract void leave(User user, UserGroup group)
            throws UserGroupBDException;

    public abstract UserGroup getByName(String name)
            throws UserGroupBDException;

    public abstract UserGroup get(Long id) throws UserGroupBDException;
}