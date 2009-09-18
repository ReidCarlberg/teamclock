///*
// * Created on Aug 14, 2004 by Reid
// */
//package com.fivesticks.time.system.update.august;
//
//import com.fivesticks.time.config.DatabaseContent_InstallDefaultUsersAndGroupsCommand;
//import com.fivesticks.time.useradmin.FstxTimeSystemRights;
//import com.fivesticks.time.useradmin.UserTypeEnum;
//import com.fstx.stdlib.authen.users.GroupRightBD;
//import com.fstx.stdlib.authen.users.GroupRightBDException;
//import com.fstx.stdlib.authen.users.UserGroup;
//import com.fstx.stdlib.authen.users.UserGroupBD;
//import com.fstx.stdlib.authen.users.UserGroupBDException;
//
///**
// * @author Reid
// */
//public class August_AddDefaultUserGroupsAndRights {
//
//    public void execute() throws Exception {
//
//        /*
//         * when false, this doesn't install any base users.
//         */
//        new DatabaseContent_InstallDefaultUsersAndGroupsCommand()
//                .execute(false);
//        //        
//        //        handleTimeClockOnly();
//        //
//        //        handleStandardUser();
//        //
//        //        handlePriviledgedUser();
//        //
//        //        handleOwnerAdminUser();
//
//    }
//
//    private UserGroup addGroup(UserTypeEnum userTypeEnum) throws Exception {
//        //create a group
//        UserGroup userGroup = new UserGroup();
//        userGroup.setName(userTypeEnum.getName());
//
//        userGroup = UserGroupBD.factory.build().save(userGroup);
//
//        return userGroup;
//
//    }
//
//    /**
//     * @throws UserGroupBDException
//     * @throws GroupRightBDException
//     *  
//     */
//    private void handleTimeClockOnly() throws Exception, GroupRightBDException {
//
//        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_TIMECLOCK);
//
//        //add the timeclock only rights
//        GroupRightBD.factory.build().add(FstxTimeSystemRights.TIMECLOCK_ONLY,
//                userGroup);
//
//    }
//
//    /**
//     *  
//     */
//    private void handleStandardUser() throws Exception {
//
//        UserGroup userGroup = addGroup(UserTypeEnum.USERTYPE_STANDARD);
//
//        GroupRightBD bd = GroupRightBD.factory.build();
//
//        bd.add(FstxTimeSystemRights.HOME, userGroup);
//        bd.add(FstxTimeSystemRights.TIME, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIME_MODIFY_ALL, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIME_PROJECT_MODIFY, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIME_TASK_MODIFY, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_SELF, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIMECLOCK_MODIFY_ALL, userGroup);
//        //        bd.add(FstxTimeSystemRights.TIMECLOCK_ADD, userGroup);
//        bd.add(FstxTimeSystemRights.TIMECLOCK_LOGIN, userGroup);
//        //        bd.add(FstxTimeSystemRights.CALENDAR_VIEW_ALL, userGroup);
//        //        bd.add(FstxTimeSystemRights.CALENDAR_MODIFY_ALL, userGroup);
//        //        bd.add(FstxTimeSystemRights.TODO_ALL, userGroup);
//        //bd.add(FstxTimeSystemRights.GENERAL_ADMINISTRATION, userGroup);
//        //bd.add(FstxTimeSystemRights.SYSTEMOWNER_ADMINISTRATION, userGroup);
//
//    }
//
//    /**
//     *  
//     */
//    private void handlePriviledgedUser() throws Exception {
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
//
//    /**
//     *  
//     */
//    private void handleOwnerAdminUser() throws Exception {
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
//}