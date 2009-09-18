/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;

/**
 * 2004-09-13 Just needs to match the name of some group.
 * 
 * @author Reid
 */
public class UserTypeDeterminator {

    public UserTypeEnum getUserType(SystemOwner systemOwner, User user)
            throws UserTypeDeterminatorException {

        SystemUser current;
        try {
            current = SystemUserServiceDelegateFactory.factory.build()
                    .getBySystemAndUser(systemOwner, user);
        } catch (SystemUserServiceDelegateException e) {
            throw new UserTypeDeterminatorException(e);
        }
        UserTypeEnum ret = UserTypeEnum.getByName(current.getUserType());

        /*
         * Reid 2004-12-29 The old system used this style of lookup. It implies
         * that a user can only have a single type. The new system allows users
         * to be associated with different system owners and have different
         * roles.
         */
        //        if (ret == null) {
        //            ret = getUserTypeByUser(user);
        //        }
        if (ret == null) {
            throw new UserTypeDeterminatorException("couldn't determine it.");
        }
        return ret;
    }

    //    public UserTypeEnum getUserType(User user)
    //            throws UserTypeDeterminatorException {
    //
    //        throw new RuntimeException("shouldn't be using this.");
    //
    //    }

//    public UserTypeEnum getUserTypeByUser(User user)
//            throws UserTypeDeterminatorException {
//
//        GroupMembershipServiceDelegate sd = GroupMembershipServiceDelegate.factory
//                .build();
//
//        UserTypeEnum ret = null;
//
//        try {
//            //UserTypeEnum.USERTYPE_OWNERADMIN;
//            if (sd.isMember(user, UserTypeEnum.OWNERADMIN)) {
//                return UserTypeEnum.OWNERADMIN;
//            }
//
//            //UserTypeEnum.USERTYPE_PRIVILEGED;
//            if (sd.isMember(user, UserTypeEnum.PRIVILEGED)) {
//                return UserTypeEnum.PRIVILEGED;
//            }
//
//            //UserTypeEnum.USERTYPE_STANDARD;
//            if (sd.isMember(user, UserTypeEnum.STANDARD)) {
//                return UserTypeEnum.STANDARD;
//            }
//        } catch (GroupMembershipServiceDelegateException e) {
//            throw new UserTypeDeterminatorException(e);
//        }
//
//        return UserTypeEnum.TIMECLOCK;
//
//    }
}