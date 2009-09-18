package com.fivesticks.time.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 *  
 */
public class SetupBasicData {

//    private int compCount = 0;

//    private SystemOwner defaultOwner;

    public SetupBasicData() throws Exception {

    }
// 2005-11-12 RSC
//    /**
//     *  
//     */
//    private void handleSettings() {
//
//        //		SystemSettingsServiceDelegate sd =
//        // SystemSettingsServiceDelegate.factory.build();
//        //		
//        //		sd.updateSetting(SettingTypeEnum.SETTING_LOGO_URL.getName(),
//        // "http://www.fivesticks.com/fstxweb/img/logo-125.png");
//        //		sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK.getName(),
//        // "false");
//        //		sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD.getName(),
//        // "false");
//
//    }

    //    /**
    //     *
    //     */
    //    private void handleUsers() {
    //
    //        handleAdminUser();
    //
    //        handleSuperUser();
    //
    //        handleUser();
    //
    //    }
// 2005-11-12 RSC
//    /**
//     *  
//     */
//    private void handleUser() {
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword("user");
//        user.setEmail("user@fivesticks.com");
//
//        try {
//            user = UserDAO.factory.build().save(user);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            log.info("handleUsers exception");
//            e.printStackTrace();
//        }
//
//        //        UserGroup group = new UserGroup();
//        //        group.setName("Users");
//        //        try {
//        //            group = UserGroupDAO.factory.build().save(group);
//        //        } catch (DAOException e1) {
//        //
//        //            e1.printStackTrace();
//        //        }
//        //
//        //        try {
//        //            GroupRightBD.factory.build().add(FstxTimeSystemRights.HOME, group);
//        //        } catch (GroupRightBDException e2) {
//        //            e2.printStackTrace();
//        //        }
//        //
//        //        try {
//        //            UserGroupBD.factory.build().join(user, group);
//        //        } catch (UserGroupBDException e3) {
//        //            e3.printStackTrace();
//        //        }
//
//    }

    /**
     *  
     */
    //    private void handleSuperUser() {
    //        User user = new User();
    //        user.setUsername("super");
    //        user.setPassword("super");
    //        user.setEmail("super@fivesticks.com");
    //
    //        try {
    //            user = UserDAO.factory.build().save(user);
    //        } catch (DAOException e) {
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            log.info("handleUsers exception");
    //            e.printStackTrace();
    //        }
    //
    //        UserGroup group = new UserGroup();
    //        group.setName("Super Users");
    //        try {
    //            group = UserGroupDAO.factory.build().save(group);
    //        } catch (DAOException e1) {
    //            e1.printStackTrace();
    //        }
    //
    //        try {
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.TIME_MODIFY_ALL, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.TIME_PROJECT_MODIFY, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.TIME_TASK_MODIFY, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.CALENDAR_MODIFY_ALL, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.CALENDAR_VIEW_ALL, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.TIMECLOCK_MODIFY_SELF, group);
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.TIMECLOCK_MODIFY_ALL, group);
    //            GroupRightBD.factory.build().add(FstxTimeSystemRights.TODO_ALL,
    //                    group);
    //        } catch (GroupRightBDException e2) {
    //            e2.printStackTrace();
    //        }
    //
    //        try {
    //            UserGroupBD.factory.build().join(user, group);
    //        } catch (UserGroupBDException e3) {
    //            e3.printStackTrace();
    //        }
    //
    //    }
    //    /**
    //     *
    //     */
    //    private void handleAdminUser() {
    //
    //        User user = new User();
    //        user.setUsername("admin");
    //        user.setPassword("admin");
    //        user.setEmail("admin@fivesticks.com");
    //
    //        try {
    //            user = UserDAO.factory.build().save(user);
    //        } catch (DAOException e) {
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            log.info("handleUsers exception");
    //            e.printStackTrace();
    //        }
    //
    //        UserGroup group = new UserGroup();
    //        group.setName("Administrators");
    //        try {
    //            group = UserGroupDAO.factory.build().save(group);
    //        } catch (DAOException e1) {
    //            e1.printStackTrace();
    //        }
    //
    //        try {
    //            GroupRightBD.factory.build().add(
    //                    FstxTimeSystemRights.GENERAL_ADMINISTRATION, group);
    //            GroupRightBD.factory.build().add(FstxTimeSystemRights.TODO_ALL,
    //                    group);
    //
    //        } catch (GroupRightBDException e2) {
    //            e2.printStackTrace();
    //        }
    //
    //        try {
    //            UserGroupBD.factory.build().join(user, group);
    //        } catch (UserGroupBDException e3) {
    //            e3.printStackTrace();
    //        }
    //
    //    }
    static Log log = LogFactory.getLog(SetupBasicData.class.getName());

}