/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fivesticks.time.admin.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class SystemSettingsModifyAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private String submitSettings;

    private String cancelSettings;

    private FstxTimeSettings settings = new FstxTimeSettings();

    public String execute() throws Exception {
        if (this.getCancelSettings() != null)
            return SUCCESS;

        if (this.getSubmitSettings() == null) {
            settings  = this.getSessionContext().getSettings();

            settings.setSystemName(settings.getSystemName());
            settings.setLogoURL(settings.getLogoURL());
            settings.setActivityRounderType(settings.getActivityRounderType());
            settings.setActivityDefaultTask(settings.getActivityDefaultTask());
            settings.setTimeClockRounderType(settings.getTimeClockRounderType());

            settings.setTimeClockPayPeriodRefDate(settings
                    .getTimeClockPayPeriodRefDate());
            settings.setTimeClockPayPeriodType(settings
                    .getTimeClockPayPeriodType());

            settings.setOkToLoginFromTimeclock(settings
                    .isOkToLoginFromTimeclock());
            settings.setOkToRememberPassword(settings.isOkToRememberPassword());
            settings.setCalendarNormalDayStart(settings
                    .getCalendarNormalDayStart());
            settings
                    .setCalendarNormalDayEnds(settings.getCalendarNormalDayEnds());
            return INPUT;
        }

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());

        sd.updateSetting(SettingTypeEnum.SETTING_SYSTEM_NAME, settings
                .getSystemName());

        sd.updateSetting(SettingTypeEnum.SETTING_LOGO_URL, settings
                .getLogoURL());

        sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_ROUNDER_TYPE,
                settings.getActivityRounderType());

        sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK,
                settings.getActivityDefaultTask());

        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE,
                settings.getTimeClockRounderType());

        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE,
                settings.getTimeClockPayPeriodType());

        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE,
                settings.getTimeClockPayPeriodRefDate());

        sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK, ""
                + settings.isOkToLoginFromTimeclock());

        sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD, ""
                + settings.isOkToRememberPassword());

        sd.updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START, ""
                + settings.getCalendarNormalDayStart());

        sd.updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS, ""
                + settings.getCalendarNormalDayEnds());

        return SUCCESS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.authen.webwork.SessionContextAware#setSessionContext(com.fivesticks.time.common.SessionContext)
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @return Returns the cancelSettings.
     */
    public String getCancelSettings() {
        return cancelSettings;
    }

    /**
     * @param cancelSettings
     *            The cancelSettings to set.
     */
    public void setCancelSettings(String cancelSettings) {
        this.cancelSettings = cancelSettings;
    }

    /**
     * @return Returns the submitSettings.
     */
    public String getSubmitSettings() {
        return submitSettings;
    }

    /**
     * @param submitSettings
     *            The submitSettings to set.
     */
    public void setSubmitSettings(String submitSettings) {
        this.submitSettings = submitSettings;
    }
}