/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class UserBuilderImpl implements UserBuilder {

    //    private UserGroupBD userGroupBD;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserAdminServiceDelegate#createTimeclockOnly(com.fivesticks.time.systemowner.SystemOwner,
     *      java.lang.String, java.lang.String)
     */
    public User createTimeclockOnly(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException {

        return this.handleCreateByType(UserTypeEnum.TIMECLOCK, systemOwner,
                username, password, email);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserAdminServiceDelegate#createStandardUser(com.fivesticks.time.systemowner.SystemOwner,
     *      java.lang.String, java.lang.String)
     */
    public User createStandardUser(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException {

        return this.handleCreateByType(UserTypeEnum.STANDARD, systemOwner,
                username, password, email);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserAdminServiceDelegate#createPriviledgedUser(com.fivesticks.time.systemowner.SystemOwner,
     *      java.lang.String, java.lang.String)
     */
    public User createPriviledgedUser(SystemOwner systemOwner, String username,
            String password, String email) throws UserBuilderException {

        return this.handleCreateByType(UserTypeEnum.PRIVILEGED, systemOwner,
                username, password, email);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UserAdminServiceDelegate#createOwnerAdministratorUser(com.fivesticks.time.systemowner.SystemOwner,
     *      java.lang.String, java.lang.String)
     */
    public User createOwnerAdministratorUser(SystemOwner systemOwner,
            String username, String password, String email)
            throws UserBuilderException {

        return this.handleCreateByType(UserTypeEnum.OWNERADMIN, systemOwner,
                username, password, email);

    }

    private User handleCreateByType(UserTypeEnum type, SystemOwner systemOwner,
            String username, String password, String email)
            throws UserBuilderException {

        User ret = null;
        try {
            ret = UserBDFactory.factory.build().addUser(username, password, email);
        } catch (UserBDException e) {
            throw new UserBuilderException(e);
        }

        try {
            SystemUserServiceDelegateFactory.factory.build().associate(systemOwner,
                    ret, type);
            //        try {
            //            handleJoinGroup(type, ret);
            //        } catch (UserGroupBDException e1) {
            //            throw new UserBuilderException(e1);
            //        }
        } catch (SystemUserServiceDelegateException e1) {
            throw new UserBuilderException(e1);
        }

        return ret;
        //        return null;
    }

    //    /**
    //     * @param type
    //     * @param ret
    //     * @throws UserGroupBDException
    //     */
    //    private void handleJoinGroup(UserTypeEnum type, User ret)
    //            throws UserGroupBDException {
    //
    //        UserGroup groupForType = this.getUserGroupBD()
    //                .getByName(type.getName());
    //
    //        this.getUserGroupBD().join(ret, groupForType);
    //
    //    }

    //    /**
    //     * @return Returns the userGroupBD.
    //     */
    //    public UserGroupBD getUserGroupBD() {
    //        return userGroupBD;
    //    }
    //
    //    /**
    //     * @param userGroupBD
    //     * The userGroupBD to set.
    //     */
    //    public void setUserGroupBD(UserGroupBD userGroupBD) {
    //        this.userGroupBD = userGroupBD;
    //    }
}