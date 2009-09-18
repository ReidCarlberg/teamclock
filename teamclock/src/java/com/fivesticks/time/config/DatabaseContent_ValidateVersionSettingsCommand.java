/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;

/**
 * 8/31/04 -- Reid -- what can we do with this--we basically need to now do this
 * per system owner.
 * 
 * @author REID
 *  
 */
public class DatabaseContent_ValidateVersionSettingsCommand {

    private Log log = LogFactory.getLog(this.getClass());

    private int iterationNumber;

    /**
     *  
     */
    public void execute(SystemOwner systemOwner) {

        log.info("Starting to validate settings");

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(systemOwner);

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_SYSTEM_NAME.getName(), "TeamClock.com"
                        + this.getIterationSuffix());
        sd.validateSettingNotNull(SettingTypeEnum.SETTING_LOGO_URL.getName(),
                "https://my.teamclock.com/images/teamclock-logo.gif");

        sd.validateSettingNotNull(SettingTypeEnum.SETTING_TIMEZONE.getName(),
                "US Central");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK.getName(),
                "true");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD.getName(),
                "true");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_PROJECT, "0");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK, "Task");

        sd.validateSettingNotNull(SettingTypeEnum.SETTING_ACTIVITY_ROUNDER_TYPE
                .getName(), "sixths");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE.getName(),
                "quarter");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_TIME_CLOCK_USER_SHIFT_TYPE.getName(),
                "twoWeek");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE.getName(),
                PayPeriodTypeEnum.PAYPERIOD_WEEKLY.getName());

        sd
                .validateSettingNotNull(
                        SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE
                                .getName(), "9/13/2004");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_ACCOUNT_ACTIVITY_POSTED_THROUGH
                        .getName(), "10/1/2004");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START.getName(),
                "7");

        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS.getName(),
                "19");

        /*
         * user types
         */
        sd.validateSettingNotNull(
                SettingTypeEnum.SETTING_FEATURE_SET.getName(),
                SettingFeatureSetTypeEnum.GENERAL.getName());

        sd.validateSettingNotNull(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS
                .getName(), "3");

        if (systemOwner.getKey().equals("_default")) {
            sd.validateSettingNotNull(SettingTypeEnum.SETTING_SMTP_HOST
                    .getName(), "");

            sd.validateSettingNotNull(SettingTypeEnum.SETTING_SMTP_USERNAME
                    .getName(), "");

            sd.validateSettingNotNull(SettingTypeEnum.SETTING_SMTP_PASSWORD
                    .getName(), "");

            sd.validateSettingNotNull(SettingTypeEnum.CONFIG_DATABASE_VERSION
                    .getName(), "08/01/2004");
        }

        log.info("Settings have been validated.");

    }

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