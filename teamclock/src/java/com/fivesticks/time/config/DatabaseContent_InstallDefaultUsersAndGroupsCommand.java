/*
 * Created on Aug 14, 2004 by Reid
 */
package com.fivesticks.time.config;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * <p>
 * Need to have 2 versions. 1 that is called from the default database installer
 * and 1 that is called from the udpate script.
 * 
 * <p>
 * Default Installer: installs users for all roles.
 * <p>
 * Update Script: installs only the new admin user.
 * 
 * @author Reid
 */
public class DatabaseContent_InstallDefaultUsersAndGroupsCommand {

    private SystemOwner systemOwner;

    private int iterationNumber;

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public void execute(boolean installAllUsers) throws Exception {
        //        handleTimeClockOnly();
        //
        //        handleStandardUserGroupSetup();
        //
        //        handlePriviledgedUserGroupSetup();
        //
        //        handleOwnerAdminUserGroupSetup();

        if (installAllUsers) {
            handleAllUsers();
        }
    }

    /**
     *  
     */
    private void handleAllUsers() throws Exception {

        if (this.getSystemOwner() == null)
            throw new RuntimeException("System Owner not set.");

        //        UserGroupBD groupBD = UserGroupBD.factory.build();

        SystemUserServiceDelegate systemUserSD = SystemUserServiceDelegateFactory.factory
                .build();

        //default timeclock only user
        User timeclock = UserBDFactory.factory.build().addUser(
                "timeclock" + this.getIterationSuffix(),
                "timeclock" + this.getIterationSuffix(),
                "timeclock" + this.getIterationSuffix() + "@fivesticks.com");
        //        groupBD.join(timeclock, groupBD
        //                .getByName(UserTypeEnum.USERTYPE_TIMECLOCK.getName()));
        systemUserSD.associate(this.getSystemOwner(), timeclock,
                UserTypeEnum.TIMECLOCK);

        //default standard user "standard"
        User standard = UserBDFactory.factory.build().addUser(
                "standard" + this.getIterationSuffix(),
                "standard" + this.getIterationSuffix(),
                "standard" + this.getIterationSuffix() + "@fivesticks.com");
        //        groupBD.join(standard,
        // groupBD.getByName(UserTypeEnum.USERTYPE_STANDARD
        //                .getName()));
        systemUserSD.associate(this.getSystemOwner(), standard,
                UserTypeEnum.STANDARD);

        //default priviledged user "privuser"
        User privuser = UserBDFactory.factory.build().addUser(
                "privuser" + this.getIterationSuffix(),
                "privuser" + this.getIterationSuffix(),
                "privuser" + this.getIterationSuffix() + "@fivesticks.com");
        //        groupBD.join(privuser, groupBD
        //                .getByName(UserTypeEnum.USERTYPE_PRIVILEGED.getName()));
        systemUserSD.associate(this.getSystemOwner(), privuser,
                UserTypeEnum.PRIVILEGED);

        //default admin user "admin"
        User admin = UserBDFactory.factory.build().addUser(
                "admin" + this.getIterationSuffix(),
                "admin" + this.getIterationSuffix(),
                "admin" + this.getIterationSuffix() + "@fivesticks.com");
        //        groupBD.join(admin,
        // groupBD.getByName(UserTypeEnum.USERTYPE_OWNERADMIN
        //                .getName()));
        systemUserSD.associate(this.getSystemOwner(), admin,
                UserTypeEnum.OWNERADMIN);

        //default external user
        User external = UserBDFactory.factory.build().addUser(
                "external" + this.getIterationSuffix(),
                "external" + this.getIterationSuffix(),
                "external" + this.getIterationSuffix() + "@fivesticks.com");
        systemUserSD.associate(this.getSystemOwner(), external,
                UserTypeEnum.EXTERNAL);
    }

    //    private UserGroup addGroup(UserTypeEnum userTypeEnum) throws Exception {
    //        //create a group
    //
    //        UserGroupBD bd = UserGroupBD.factory.build();
    //
    //        UserGroup testUserGroup = bd.getByName(userTypeEnum.getName());
    //
    //        if (testUserGroup != null)
    //            return testUserGroup;
    //
    //        UserGroup userGroup = new UserGroup();
    //        userGroup.setName(userTypeEnum.getName());
    //
    //        userGroup = bd.save(userGroup);
    //
    //        return userGroup;
    //
    //    }

    //    /**
    //     * @throws UserGroupBDException
    //     * @throws GroupRightBDException
    //     *
    //     */
    //    private void handleTimeClockOnly() throws Exception,
    // GroupRightBDException {
    //
    //        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_TIMECLOCK);
    //
    //        //add the timeclock only rights
    //        GroupRightBD.factory.build().add(FstxTimeSystemRights.TIMECLOCK_ONLY,
    //                userGroup);
    //
    //    }

    //    /**
    //     *
    //     */
    //    private void handleStandardUserGroupSetup() throws Exception {
    //
    //        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_STANDARD);
    //
    //        GroupRightBD bd = GroupRightBD.factory.build();
    //
    //        bd.add(FstxTimeSystemRights.HOME, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIME_MODIFY_ALL, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIME_PROJECT_MODIFY, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIME_TASK_MODIFY, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_SELF, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_ALL, userGroup);
    //        // bd.add(FstxTimeSystemRights.TIMECLOCK_ADD, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_LOGIN, userGroup);
    //        // bd.add(FstxTimeSystemRights.CALENDAR_VIEW_ALL, userGroup);
    //        // bd.add(FstxTimeSystemRights.CALENDAR_MODIFY_ALL, userGroup);
    //        // bd.add(FstxTimeSystemRights.TODO_ALL, userGroup);
    //        //bd.add(FstxTimeSystemRights.GENERAL_ADMINISTRATION, userGroup);
    //        //bd.add(FstxTimeSystemRights.SYSTEMOWNER_ADMINISTRATION, userGroup);
    //
    //    }

    //    /**
    //     *
    //     */
    //    private void handlePriviledgedUserGroupSetup() throws Exception {
    //
    //        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_PRIVILEGED);
    //
    //        GroupRightBD bd = GroupRightBD.factory.build();
    //
    //        bd.add(FstxTimeSystemRights.HOME, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_PROJECT_MODIFY, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_TASK_MODIFY, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_SELF, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_ADD, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_LOGIN, userGroup);
    //        bd.add(FstxTimeSystemRights.CALENDAR_VIEW_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.CALENDAR_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TODO_ALL, userGroup);
    //        //bd.add(FstxTimeSystemRights.GENERAL_ADMINISTRATION, userGroup);
    //        //bd.add(FstxTimeSystemRights.SYSTEMOWNER_ADMINISTRATION, userGroup);
    //
    //    }

    //    /**
    //     *
    //     */
    //    private void handleOwnerAdminUserGroupSetup() throws Exception {
    //
    //        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_OWNERADMIN);
    //
    //        GroupRightBD bd = GroupRightBD.factory.build();
    //
    //        bd.add(FstxTimeSystemRights.HOME, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_PROJECT_MODIFY, userGroup);
    //        bd.add(FstxTimeSystemRights.TIME_TASK_MODIFY, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_SELF, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_ADD, userGroup);
    //        bd.add(FstxTimeSystemRights.TIMECLOCK_LOGIN, userGroup);
    //        bd.add(FstxTimeSystemRights.CALENDAR_VIEW_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.CALENDAR_MODIFY_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.TODO_ALL, userGroup);
    //        bd.add(FstxTimeSystemRights.GENERAL_ADMINISTRATION, userGroup);
    //        bd.add(FstxTimeSystemRights.SYSTEMOWNER_ADMINISTRATION, userGroup);
    //
    //    }

    private String getIterationSuffix() {
        if (this.getIterationNumber() == 0)
            return "";
        else
            return "" + (this.getIterationNumber() + 1);
    }

    /**
     * @return Returns the iterationNumber.
     */
    public int getIterationNumber() {
        return iterationNumber;
    }

    /**
     * @param iterationNumber
     *            The iterationNumber to set.
     */
    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }
}