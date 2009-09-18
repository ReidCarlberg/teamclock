/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fivesticks.time.settings.broker;

import java.io.Serializable;

/**
 * @author Reid
 */
public class SettingsBroker implements Serializable {

//    private SystemOwner owner;
//
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    private MasterSettingsBroker getMaster() {
//        return MasterSettingsBroker.singleton;
//    }
//
//    public SettingFeatureSetTypeEnum getFeatureSet() {
//        return this.getMaster().getSettings(this.getOwner()).getFeatureSet();
//    }
//
//    public int getMaximumActiveUsers() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getMaxActiveUsers();
//    }
//
//    /**
//     * @return
//     */
//    public String getLogoURL() {
//        return this.getMaster().getSettings(this.getOwner()).getLogoURL();
//
//    }
//
//    /**
//     * @return
//     */
//    public boolean isOkToLoginFromTimeclock() {
//        return this.getMaster().getSettings(this.getOwner())
//                .isOkToLoginFromTimeclock();
//
//    }
//
//    /**
//     * @return
//     */
//    public boolean isOkToRememberPassword() {
//        return this.getMaster().getSettings(this.getOwner())
//                .isOkToRememberPassword();
//    }
//
//    /**
//     * @return
//     */
//    public String getTimeClockRounderType() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTimeClockRounderType();
//    }
//
//    /**
//     * @return
//     */
//    public String getTimeClockUserShiftType() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTimeClockUserShiftType();
//    }
//
//    /*
//     * Nick 2005-10-20 we need to override the SystemTimeZone with a user
//     * specifies one.
//     */
//
//    /*
//     * By reloading we guarantee that when a setting changes we have a fresh
//     * set. If we see a performance hit we can build something to let the
//     * Service Delegate cache the results until it changes.
//     */
//    public UserSettingVO getUserSettingVO() {
//        try {
//            return UserSettingServiceDelegate.factory.build(this.getOwner())
//                    .find(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public String getSystemTimeZone() {
//        /*
//         * All User setting should be available here.
//         */
//        UserSettingVO userSettingVO = this.getUserSettingVO();
//
//        /*
//         * Should Check if valie TimeZone.
//         */
//        if (userSettingVO != null && userSettingVO.isTimeZoneSet()) {
//            return userSettingVO.getUserTimeZone();
//        }
//
//        return this.getMaster().getSettings(this.getOwner())
//                .getSystemTimeZone();
//    }
//
//    public String getTimeClockPayPeriodType() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTimeClockPayPeriodType();
//    }
//
//    public SimpleDate getTimeClockPayPeriodRefDate() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTimeClockPayPeriodRefDate();
//    }
//
//    /**
//     * @return
//     */
//    public String getSystemName() {
//        return this.getMaster().getSettings(this.getOwner()).getSystemName();
//    }
//
//    /**
//     * @return
//     */
//    public long getCalendarNormalDayEnds() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getCalendarNormalDayEnds();
//    }
//
//    /**
//     * @return
//     */
//    public long getCalendarNormalDayStart() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getCalendarNormalDayStart();
//    }
//
//    /**
//     * @return
//     */
//    public String getActivityRounderType() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getActivityRounderType();
//    }
//
//    /**
//     * @return
//     */
//    public long getActivityDefaultProject() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getActivityDefaultProject();
//    }
//
//    /**
//     * @return
//     */
//    public long getActivityDefaultTask() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getActivityDefaultTask();
//    }
//
//    public SimpleDate getActivityPostedThrough() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getActivityPostedThrough();
//    }
//
//    public long getToDoDefaultProject() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTodoDefaultProject();
//    }
//
//    public String getToDoDefaultEnteredBy() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTodoDefaultEnteredBy();
//    }
//
//    public String getToDoDefaultAssignedTo() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getTodoDefaultAssignedTo();
//    }
//
//    /*
//     * user password etc.
//     */
//    public long getUserPasswordLife() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getUserPasswordLife();
//    }
//
//    public UserPasswordComplexityEnum getUserPasswordComplexity() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getUserPasswordComplexity();
//    }
//
//    /*
//     * to do queue
//     */
//    public String getQueueServer() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getQueueSmtpServer();
//    }
//
//    public String getQueueFolder() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getQueueSmtpServerFolder();
//    }
//
//    public String getQueueUsername() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getQueueSmtpServerUsername();
//    }
//
//    public String getQueuePasswrd() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getQueueSmtpServerPassword();
//    }
//
//    /*
//     * ebay settings
//     */
//
//    public String getEbayImagesFtpServer() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesFtpServer();
//    }
//
//    public String getEbayImagesFtpUsername() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesFtpUsername();
//    }
//
//    public String getEbayImagesFtpPassword() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesFtpPassword();
//    }
//
//    public String getEbayImagesFtpPath() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesFtpPath();
//    }
//
//    public int getEbayImagesWidthFull() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesWidthFull();
//    }
//
//    public int getEbayImagesWidthThumb() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayImagesWidthThumb();
//    }
//
//    public String getEbayImagesSrc() {
//        return this.getMaster().getSettings(this.getOwner()).getEbayImagesSrc();
//    }
//
//    public long getEbayDefaultCommission() {
//        return this.getMaster().getSettings(this.getOwner())
//                .getEbayDefaultCommission();
//    }

    /*
     * 8/31/2004 - Reid - These are all the default only settings.
     */

    // /**
    // * @return
    // */
    // public SimpleDate getConfigDatabaseVersion() {
    // return this.defaultSettings.getConfigDatabaseVersion();
    // }
    // /**
    // * @return
    // */
    // public String getSmtpHost() {
    // return this.defaultSettings.getSmtpHost();
    // }
    //
    // /**
    // * @return
    // */
    // public String getSmtpPassword() {
    // return this.defaultSettings.getSmtpPassword();
    // }
    //
    // /**
    // * @return
    // */
    // public String getSmtpUsername() {
    // return this.defaultSettings.getSmtpUsername();
    // }
    //
    // /**
    // * @return Returns the systemOwnerSpecific.
    // */
    // public FstxTimeSettings getSystemOwnerSpecific() {
    // return systemOwnerSpecific;
    // }
    //
    // /**
    // * @param systemOwnerSpecific
    // * The systemOwnerSpecific to set.
    // */
    // public void setSystemOwnerSpecific(FstxTimeSettings systemOwnerSpecific)
    // {
    // this.systemOwnerSpecific = systemOwnerSpecific;
    // }
    /**
     * @return Returns the owner.
     */
//    public SystemOwner getOwner() {
//        return owner;
//    }
//
//    /**
//     * @param owner
//     *            The owner to set.
//     */
//    public void setOwner(SystemOwner owner) {
//        this.owner = owner;
//    }

//    /*
//     * Delegated from the the User Settings.
//     */
//    public String getCalendarDefaultDashboardView() {
//        return this.getUserSettingVO().getCalendarDefaultDashboardView();
//    }
//
//    public String getCalendarDefaultTabView() {
//        return this.getUserSettingVO().getCalendarDefaultTabView();
//    }
//
//    public String getCalendarNotificationEmailAddress() {
//        return this.getUserSettingVO().getCalendarNotificationEmailAddress();
//    }
//
//    public String getCalendarNotificationOnOrOff() {
//        return this.getUserSettingVO().getCalendarNotificationOnOrOff();
//    }
//
//    public String getShowTimeClockStatus() {
//        return this.getUserSettingVO().getShowTimeClockStatus();
//    }
//
//    public boolean isDefaultDashboardViewDaily() {
//        return this.getUserSettingVO().isDefaultDashboardViewDaily();
//    }
//
//    public boolean isDefaultDashboardViewWeekly() {
//        return this.getUserSettingVO().isDefaultDashboardViewWeekly();
//    }
//
//    public boolean isDefaultTabViewDaily() {
//        return this.getUserSettingVO().isDefaultTabViewDaily();
//    }
//
//    public boolean isDefaultTabViewMonthly() {
//        return this.getUserSettingVO().isDefaultTabViewMonthly();
//    }
//
//    public boolean isDefaultTabViewWeekly() {
//        return this.getUserSettingVO().isDefaultTabViewWeekly();
//    }
//
//    public boolean isDefaultToShowingTimeClockStatus() {
//        return this.getUserSettingVO().isDefaultToShowingTimeClockStatus();
//    }
}