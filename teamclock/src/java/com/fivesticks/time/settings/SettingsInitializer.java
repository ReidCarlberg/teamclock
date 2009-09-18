/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import com.fivesticks.time.settings.broker.SystemRights;
import com.fivesticks.time.settings.broker.SystemRightsImpl;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.useradmin.UserPasswordComplexityEnum;

/**
 * These are just the settings used throughout the system.
 * 
 * @author REID
 * 
 */
public class SettingsInitializer implements SystemOwnerAware {

    private SystemOwner systemOwner;

    public SettingsInitializer(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public FstxTimeSettings initialize(SystemOwner systemOwner) {
        this.setSystemOwner(systemOwner);
        return this.initialize();
    }

    public FstxTimeSettings initialize() {

        if (this.getSystemOwner() == null) {
            throw new RuntimeException("System owner is not set.");
        }

        FstxTimeSettings settings = new FstxTimeSettings();

        decorateSettings(settings);
        return settings;
    }

    public void decorateSettings(FstxTimeSettings settings) {

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        settings.setSystemName(sd
                .getSettingAsString(SettingTypeEnum.SETTING_SYSTEM_NAME
                        .getName()));

        settings.setSystemTokenAllowed(sd
                .getSettingAsBoolean(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW
                        .getName()));

        settings
                .setLogoURL(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_LOGO_URL
                                .getName()));

        settings
                .setSystemTimeZone(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TIMEZONE
                                .getName()));
        // settings
        // .setSystemCounty(sd
        // .getSettingAsString(SettingTypeEnum.SETTING_COUNTRY
        // .getName()));
        //        
        settings
                .setOkToRememberPassword(sd
                        .getSettingAsBoolean(SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD
                                .getName()));

        settings
                .setActivityRounderType(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_ACTIVITY_ROUNDER_TYPE
                                .getName()));

        settings
                .setActivityDefaultProject(sd
                        .getSettingAsLong(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_PROJECT));

        settings
                .setActivityDefaultTask(sd
                        .getSettingAsLong(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK));

        settings
                .setTodoDefaultProject(sd
                        .getSettingAsLong(SettingTypeEnum.SETTING_TODO_DEFAULT_PROJECT));

        settings
                .setTodoDefaultEnteredBy(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY));

        settings
                .setTodoDefaultAssignedTo(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO));

        settings
                .setQueueImportFromEmail(sd
                        .getSettingAsBoolean(SettingTypeEnum.SETTING_TODO_QUEUE_IMPORT));

        settings
                .setQueueSmtpServer(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_SERVER));

        // settings
        // .setQueueSmtpServer(sd
        // .getSettingAsString(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_SERVER));

        settings
                .setQueueSmtpServerFolder(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_FOLDER));

        settings
                .setQueueSmtpServerUsername(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_USERNAME));

        settings
                .setQueueSmtpServerPassword(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_PASSWORD));

        settings
                .setOkToLoginFromTimeclock(sd
                        .getSettingAsBoolean(SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK
                                .getName()));

        settings
                .setTimeClockRounderType(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE
                                .getName()));

        settings
                .setTimeClockPayPeriodType(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE
                                .getName()));

        settings
                .setTimeClockPayPeriodRefDate(sd
                        .getSettingAsSimpleDate(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE
                                .getName()));
        settings
                .setTimeClockUserShiftType(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TIME_CLOCK_USER_SHIFT_TYPE
                                .getName()));

        settings
                .setTimeClockPayPeriodRefDate(sd
                        .getSettingAsSimpleDate(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE
                                .getName()));

        settings
                .setTimeClockPayPeriodType(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE
                                .getName()));

        settings
                .setCalendarNormalDayStart(sd
                        .getSettingAsLong(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START
                                .getName()));

        settings
                .setCalendarNormalDayEnds(sd
                        .getSettingAsLong(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS
                                .getName()));


        settings
        .setMailFromName(sd
                .getSettingAsString(SettingTypeEnum.SETTING_MAIL_FROM_NAME
                        .getName()));

        
        settings
        .setMailFromAddress(sd
                .getSettingAsString(SettingTypeEnum.SETTING_MAIL_FROM_ADDRESS
                        .getName()));

        
        settings
                .setSmtpHost(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_SMTP_HOST
                                .getName()));

        settings
                .setSmtpPort(sd
                        .getSettingAsString(SettingTypeEnum.SETTING_SMTP_PORT
                                .getName()));

        settings.setSmtpUsername(sd
                .getSettingAsString(SettingTypeEnum.SETTING_SMTP_USERNAME
                        .getName()));

        settings.setSmtpPassword(sd
                .getSettingAsString(SettingTypeEnum.SETTING_SMTP_PASSWORD
                        .getName()));

        settings.setConfigDatabaseVersion(sd
                .getSettingAsSimpleDate(SettingTypeEnum.CONFIG_DATABASE_VERSION
                        .getName()));

        settings
                .setActivityPostedThrough(sd
                        .getSettingAsSimpleDate(SettingTypeEnum.SETTING_ACCOUNT_ACTIVITY_POSTED_THROUGH
                                .getName()));

        settings.setUserPasswordLife(sd
                .getSettingAsLong(SettingTypeEnum.SETTING_USER_PASSWORD_LIFE));

        settings
                .setUserPasswordComplexity(UserPasswordComplexityEnum
                        .getSingle(sd
                                .getSettingAsString(SettingTypeEnum.SETTING_USER_PASSWORD_COMPLEXITY)));

        settings.setFeatureSet(SettingFeatureSetTypeEnum.getByName(sd
                .getSettingAsString(SettingTypeEnum.SETTING_FEATURE_SET
                        .getName())));

        settings.setMaxActiveUsers(sd
                .getSettingAsInt(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS));

        /*
         * ebay information
         */

//        settings.setEbayImagesFtpServer(sd
//                .getSettingAsString(SettingTypeEnum.EBAY_IMAGE_FTP_SERVER));
//
//        settings.setEbayImagesFtpUsername(sd
//                .getSettingAsString(SettingTypeEnum.EBAY_IMAGE_FTP_USERNAME));
//
//        settings.setEbayImagesFtpPassword(sd
//                .getSettingAsString(SettingTypeEnum.EBAY_IMAGE_FTP_PASSWORD));
//
//        settings.setEbayImagesFtpPath(sd
//                .getSettingAsString(SettingTypeEnum.EBAY_IMAGE_FTP_PATH));
//
//        settings.setEbayImagesWidthFull(sd
//                .getSettingAsInt(SettingTypeEnum.EBAY_IMAGE_WIDTH_FULL));
//
//        settings.setEbayImagesWidthThumb(sd
//                .getSettingAsInt(SettingTypeEnum.EBAY_IMAGE_WIDTH_THUMB));
//
//        settings.setEbayImagesSrc(sd
//                .getSettingAsString(SettingTypeEnum.EBAY_IMAGE_SRC));
//
//        settings.setEbayDefaultCommission(sd
//                .getSettingAsLong(SettingTypeEnum.EBAY_DEFAULT_COMMISSION));

    }

    public SystemRights getRights(SystemOwner systemOwner) {
        this.setSystemOwner(systemOwner);
        return this.getRights();
    }

    public SystemRights getRights() {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("System owner is not set.");
        }
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        SystemRightsImpl rights = new SystemRightsImpl();

        rights
                .setCanHaveExternalUsers(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS));

        rights
                .setCanUseAccountTransactions(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS));

        rights
                .setCanUseBetaFeatures(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_USE_BETA_FEATURES));

        rights.setFreeSystem(sd
                .getSettingAsBoolean(SettingTypeEnum.SYSTEM_IS_FREE_SYSTEM));

        return rights;
    }

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
}