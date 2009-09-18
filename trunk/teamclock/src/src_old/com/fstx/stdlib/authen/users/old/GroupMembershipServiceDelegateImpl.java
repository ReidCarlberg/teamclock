/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fstx.stdlib.authen.users.old;

import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 */
public class GroupMembershipServiceDelegateImpl implements
        GroupMembershipServiceDelegate {

    private GroupMembershipDAO groupMembershipDAO;

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.GroupMembershipServiceDelegate#isMember(com.fstx.stdlib.authen.users.User,
     *      java.lang.String)
     */
    public boolean isMember(User user, String groupName)
            throws GroupMembershipServiceDelegateException {

        List groups;

        boolean ret = false;

        try {
            groups = this.getGroupMembershipDAO().find(user);
        } catch (DAOException e) {
            throw new GroupMembershipServiceDelegateException(e);
        }
        for (Iterator i = groups.iterator(); i.hasNext();) {
            GroupMembership groupMembership = (GroupMembership) i.next();

            UserGroup userGroup;
            try {
                userGroup = UserGroupBD.factory.build().get(
                        groupMembership.getGroupId());
            } catch (UserGroupBDException e1) {
                throw new GroupMembershipServiceDelegateException(e1);
            }

            if (userGroup.getName().equals(groupName)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.GroupMembershipServiceDelegate#isMember(com.fstx.stdlib.authen.users.User,
     *      com.fivesticks.time.useradmin.UserTypeEnum)
     */
    public boolean isMember(User user, UserTypeEnum userType)
            throws GroupMembershipServiceDelegateException {
        return this.isMember(user, userType.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.GroupMembershipDAOAware#setGroupMembershipDAO(com.fstx.stdlib.authen.users.GroupMembershipDAO)
     */
    public void setGroupMembershipDAO(GroupMembershipDAO groupMembershipDAO) {
        this.groupMembershipDAO = groupMembershipDAO;
    }

    public GroupMembershipDAO getGroupMembershipDAO() {
        return this.groupMembershipDAO;
    }

}