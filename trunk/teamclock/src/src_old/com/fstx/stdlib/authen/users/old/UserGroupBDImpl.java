package com.fstx.stdlib.authen.users.old;

import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author REID
 *  
 */
public class UserGroupBDImpl implements UserGroupBD {

    private GroupMembershipDAO groupMembershipDAO;

    private UserGroupDAO userGroupDAO;

    public void join(User user, UserGroup group) throws UserGroupBDException {
        //be sure its not already a member
        String[] data = { group.getId().toString(), user.getId().toString() };
        List test = null;
        try {
            test = this.getGroupMembershipDAO().find(
                    GroupMembershipDAO.SELECT_BY_GROUP_AND_USER, data);
        } catch (DAOException e1) {
            throw new UserGroupBDException("already a member", e1);
        }

        if (test != null && test.size() > 0) {

        } else {
            //add it to the group
            GroupMembership gm = new GroupMembership(group.getId(), user
                    .getId());

            try {
                this.getGroupMembershipDAO().save(gm);
            } catch (DAOException e2) {
                throw new UserGroupBDException(e2);
            }
        }

    }

    public void leave(User user, UserGroup group) throws UserGroupBDException {
        //be sure its not already a member
        String[] data = { group.getId().toString(), user.getId().toString() };
        List test = null;
        try {
            test = this.getGroupMembershipDAO().find(
                    GroupMembershipDAO.SELECT_BY_GROUP_AND_USER, data);
        } catch (DAOException e1) {
            throw new UserGroupBDException(e1);
        }

        if (test == null || test.size() == 0) {

        } else {
            //add it to the group
            GroupMembership gm = (GroupMembership) test.get(0);

            try {
                this.getGroupMembershipDAO().delete(gm);
            } catch (DAOException e2) {
                throw new UserGroupBDException(e2);
            }
        }

    }

    /**
     * @return
     */
    public GroupMembershipDAO getGroupMembershipDAO() {
        return groupMembershipDAO;
    }

    /**
     * @param membershipDAO
     */
    public void setGroupMembershipDAO(GroupMembershipDAO membershipDAO) {
        groupMembershipDAO = membershipDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserGroupBD#getByName(java.lang.String)
     */
    public UserGroup getByName(String name) throws UserGroupBDException {

        UserGroup ret = null;

        try {
            ret = this.getUserGroupDAO().findByName(name);
        } catch (DAOException e) {

            throw new UserGroupBDException(e);
        }

        return ret;
    }

    public UserGroup get(Long id) throws UserGroupBDException {

        UserGroup ret = null;

        try {
            ret = this.getUserGroupDAO().get(id);
        } catch (DAOException e) {

            throw new UserGroupBDException(e);
        }

        return ret;
    }

    /**
     * @return Returns the userGroupDAO.
     */
    public UserGroupDAO getUserGroupDAO() {
        return userGroupDAO;
    }

    /**
     * @param userGroupDAO
     *            The userGroupDAO to set.
     */
    public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
        this.userGroupDAO = userGroupDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserGroupBD#save(com.fstx.stdlib.authen.users.UserGroup)
     */
    public UserGroup save(UserGroup userGroup) throws UserGroupBDException {

        try {
            return this.getUserGroupDAO().save(userGroup);
        } catch (DAOException e) {
            throw new UserGroupBDException(e);
        }

    }
}