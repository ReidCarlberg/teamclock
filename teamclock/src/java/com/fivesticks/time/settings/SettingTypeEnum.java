/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 * 
 */
public class SettingTypeEnum extends Enum {

    /*
     * System Wide -- only one per system
     */
    public static final SettingTypeEnum CONFIG_DATABASE_VERSION = new SettingTypeEnum(
            "CONFIG_DATABASE_VERSION", true);

    /*
     * Registration Plan
     */
    public static final SettingTypeEnum REGISTRATION_PLAN = new SettingTypeEnum(
            "REGISTRATION_PLAN", false);

    /*
     * Initial Settings
     */
    public static final SettingTypeEnum INITIAL_TIMECLOCK_COMPLETE = new SettingTypeEnum(
            "INITIAL_TIMECLOCK_COMPLETE", false);

    /*
     * SMTP Settings different per system
     */
    public static final SettingTypeEnum SETTING_MAIL_FROM_NAME = new SettingTypeEnum(
            "SETTING_MAIL_FROM_NAME", false);

    public static final SettingTypeEnum SETTING_MAIL_FROM_ADDRESS = new SettingTypeEnum(
            "SETTING_MAIL_FROM_ADDRESS", false);

    public static final SettingTypeEnum SETTING_SMTP_HOST = new SettingTypeEnum(
            "SETTING_SMTP_HOST", false);

    public static final SettingTypeEnum SETTING_SMTP_PORT = new SettingTypeEnum(
            "SETTING_SMTP_PORT", false);

    public static final SettingTypeEnum SETTING_SMTP_USERNAME = new SettingTypeEnum(
            "SETTING_SMTP_USERNAME", false);

    public static final SettingTypeEnum SETTING_SMTP_PASSWORD = new SettingTypeEnum(
            "SETTING_SMTP_PASSWORD", true);

    /*
     * Operation -- These are not for general display.
     */
    // determines what features they can use.
    public static final SettingTypeEnum SETTING_FEATURE_SET = new SettingTypeEnum(
            "SETTING_FEATURE_SET", false);

    // determines their maximum number of active users
    public static final SettingTypeEnum SETTING_MAX_ACTIVE_USERS = new SettingTypeEnum(
            "SETTING_MAX_ACTIVE_USERS", false);

    /*
     * Geneneral Display
     */
    public static final SettingTypeEnum SETTING_SYSTEM_NAME = new SettingTypeEnum(
            "SETTING_SYSTEM_NAME", false);

    public static final SettingTypeEnum SETTING_SYSTEM_TOKEN_ALLOW = new SettingTypeEnum(
            "SETTING_SYSTEM_TOKEN_ALLOW", false);

    public static final SettingTypeEnum SETTING_LOGO_URL = new SettingTypeEnum(
            "SETTING_LOGO_URL", false);

    public static final SettingTypeEnum SETTING_TIMEZONE = new SettingTypeEnum(
            "SETTING_TIMEZONE", false);

    public static final SettingTypeEnum SETTING_COUNTRY = new SettingTypeEnum(
            "SETTING_COUNTRY", false);

    public static final SettingTypeEnum SETTING_OK_TO_REMEMBER_PASSWORD = new SettingTypeEnum(
            "SETTING_OK_TO_REMEMBER_PASSWORD", false);

    /*
     * Activity
     */
    public static final SettingTypeEnum SETTING_ACTIVITY_DEFAULT_PROJECT = new SettingTypeEnum(
            "SETTING_ACTIVITY_DEFAULT_PROJECT", false);

    public static final SettingTypeEnum SETTING_ACTIVITY_DEFAULT_TASK = new SettingTypeEnum(
            "SETTING_ACTIVITY_DEFAULT_TASK", false);

    public static final SettingTypeEnum SETTING_ACTIVITY_ROUNDER_TYPE = new SettingTypeEnum(
            "SETTING_ACTIVITY_ROUNDER_TYPE", false);

    /*
     * To Do
     */
    public static final SettingTypeEnum SETTING_TODO_DEFAULT_PROJECT = new SettingTypeEnum(
            "SETTING_TODO_DEFAULT_PROJECT", false);

    public static final SettingTypeEnum SETTING_TODO_DEFAULT_ENTEREDBY = new SettingTypeEnum(
            "SETTING_TODO_DEFAULT_ENTEREDBY", false);

    public static final SettingTypeEnum SETTING_TODO_DEFAULT_ASSIGNEDTO = new SettingTypeEnum(
            "SETTING_TODO_DEFAULT_ASSIGNEDTO", false);

    public static final SettingTypeEnum SETTING_TODO_QUEUE_IMPORT = new SettingTypeEnum(
            "SETTING_TODO_QUEUE_IMPORT", false);

    public static final SettingTypeEnum SETTING_TODO_QUEUE_SMTP_SERVER = new SettingTypeEnum(
            "SETTING_TODO_QUEUE_SMTP_SERVER", false);

    public static final SettingTypeEnum SETTING_TODO_QUEUE_SMTP_FOLDER = new SettingTypeEnum(
            "SETTING_TODO_QUEUE_SMTP_FOLDER", false);

    public static final SettingTypeEnum SETTING_TODO_QUEUE_SMTP_USERNAME = new SettingTypeEnum(
            "SETTING_TODO_QUEUE_SMTP_USERNAME", false);

    public static final SettingTypeEnum SETTING_TODO_QUEUE_SMTP_PASSWORD = new SettingTypeEnum(
            "SETTING_TODO_QUEUE_SMTP_PASSWORD", false);

    /*
     * Timeclock
     */
    public static final SettingTypeEnum SETTING_OK_TO_LOGIN_FROM_TIMECLOCK = new SettingTypeEnum(
            "SETTING_OK_TO_LOGIN_FROM_TIMECLOCK", false);

    public static final SettingTypeEnum SETTING_TIME_CLOCK_ROUNDER_TYPE = new SettingTypeEnum(
            "SETTING_TIME_CLOCK_ROUNDER_TYPE", false);

    public static final SettingTypeEnum SETTING_TIME_CLOCK_USER_SHIFT_TYPE = new SettingTypeEnum(
            "SETTING_TIME_CLOCK_USER_SHIFT_TYPE", false);

    public static final SettingTypeEnum SETTING_TIME_CLOCK_PAY_PERIOD_TYPE = new SettingTypeEnum(
            "SETTING_TIME_CLOCK_PAY_PERIOD_TYPE", false);

    public static final SettingTypeEnum SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE = new SettingTypeEnum(
            "SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE", false);

    /*
     * Calendar
     */
    public static final SettingTypeEnum SETTING_CALENDAR_NORMAL_DAY_START = new SettingTypeEnum(
            "SETTING_CALENDAR_NORMAL_DAY_START", false);

    public static final SettingTypeEnum SETTING_CALENDAR_NORMAL_DAY_ENDS = new SettingTypeEnum(
            "SETTING_CALENDAR_NORMAL_DAY_ENDS", false);

    /*
     * Account Activity
     */
    public static final SettingTypeEnum SETTING_ACCOUNT_ACTIVITY_POSTED_THROUGH = new SettingTypeEnum(
            "SETTING_ACCOUNT_ACTIVITY_POSTED_THROUGH", false);

    /*
     * Users
     */
    public static final SettingTypeEnum SETTING_USER_PASSWORD_LIFE = new SettingTypeEnum(
            "SETTING_USER_PASSWORD_LIFE", false);

    public static final SettingTypeEnum SETTING_USER_PASSWORD_COMPLEXITY = new SettingTypeEnum(
            "SETTING_USER_PASSWORD_COMPLEXITY", false);

    /*
     * system settings
     */
    public static final SettingTypeEnum SYSTEM_IS_FREE_SYSTEM = new SettingTypeEnum(
            "SYSTEM_IS_FREE_SYSTEM", false);

    public static final SettingTypeEnum SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS = new SettingTypeEnum(
            "SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS", false);

    public static final SettingTypeEnum SYSTEM_CAN_HAVE_EXTERNAL_USERS = new SettingTypeEnum(
            "SYSTEM_CAN_HAVE_EXTERNAL_USERS", false);

    public static final SettingTypeEnum SYSTEM_CAN_USE_BETA_FEATURES = new SettingTypeEnum(
            "SYSTEM_CAN_USE_BETA_FEATURES", false);

    // public static final SettingTypeEnum EBAY_IMAGE_FTP_SERVER = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_FTP_SERVER", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_FTP_USERNAME = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_FTP_USERNAME", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_FTP_PASSWORD = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_FTP_PASSWORD", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_FTP_PATH = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_FTP_PATH", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_WIDTH_FULL = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_WIDTH_FULL", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_WIDTH_THUMB = new
    // SettingTypeEnum(
    // "EBAY_IMAGE_WIDTH_THUMB", false);
    //
    // public static final SettingTypeEnum EBAY_IMAGE_SRC = new SettingTypeEnum(
    // "EBAY_IMAGE_SRC", false);
    //
    // public static final SettingTypeEnum EBAY_DEFAULT_COMMISSION = new
    // SettingTypeEnum(
    // "EBAY_DEFAULT_COMMISSION", false);

    private static Collection allSettings;

    private final boolean defaultOnly;

    /**
     * @param arg0
     */
    protected SettingTypeEnum(String arg0, boolean defaultOnly) {
        super(arg0);
        this.defaultOnly = defaultOnly;
    }

    /**
     * @return Returns the defaultOnly.
     */
    public boolean isDefaultOnly() {
        return defaultOnly;
    }

    public static Collection getSettings() {
        if (allSettings == null) {
            allSettings = new ArrayList();
            allSettings.add(SETTING_SYSTEM_NAME);

            allSettings.add(SETTING_LOGO_URL);

            allSettings.add(SETTING_TIMEZONE);

            allSettings.add(SETTING_COUNTRY);

            allSettings.add(SETTING_OK_TO_REMEMBER_PASSWORD);

            /*
             * Activity
             */
            allSettings.add(SETTING_ACTIVITY_DEFAULT_PROJECT);

            allSettings.add(SETTING_ACTIVITY_DEFAULT_TASK);

            allSettings.add(SETTING_ACTIVITY_ROUNDER_TYPE);

            /*
             * Timeclock
             */
            allSettings.add(SETTING_OK_TO_LOGIN_FROM_TIMECLOCK);

            allSettings.add(SETTING_TIME_CLOCK_ROUNDER_TYPE);

            allSettings.add(SETTING_TIME_CLOCK_USER_SHIFT_TYPE);

            allSettings.add(SETTING_TIME_CLOCK_PAY_PERIOD_TYPE);

            allSettings.add(SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE);

            /*
             * Calendar
             */
            allSettings.add(SETTING_CALENDAR_NORMAL_DAY_START);

            allSettings.add(SETTING_CALENDAR_NORMAL_DAY_ENDS);

            /*
             * SMTP
             */
            allSettings.add(SETTING_SMTP_HOST);

            allSettings.add(SETTING_SMTP_USERNAME);

            allSettings.add(SETTING_SMTP_PASSWORD);

            allSettings.add(CONFIG_DATABASE_VERSION);
        }

        return allSettings;
    }

    public static SettingTypeEnum getByName(String name) {

        SettingTypeEnum ret = null;
        for (Iterator iter = SettingTypeEnum.getSettings().iterator(); iter
                .hasNext();) {
            SettingTypeEnum element = (SettingTypeEnum) iter.next();
            if (element.getName().equals(name)) {
                ret = element;
                break;
            }
        }
        return ret;
    }
}