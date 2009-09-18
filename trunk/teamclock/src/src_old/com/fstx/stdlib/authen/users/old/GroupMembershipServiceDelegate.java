/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fstx.stdlib.authen.users.old;

import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * @author Reid
 */
public interface GroupMembershipServiceDelegate extends GroupMembershipDAOAware {

    public static final String SPRING_BEAN_NAME = "groupMembershipServiceDelegate";

    public static final GroupMembershipServiceDelegateFactory factory = new GroupMembershipServiceDelegateFactory();

    public boolean isMember(User user, String groupName)
            throws GroupMembershipServiceDelegateException;

    public boolean isMember(User user, UserTypeEnum userType)
            throws GroupMembershipServiceDelegateException;
}