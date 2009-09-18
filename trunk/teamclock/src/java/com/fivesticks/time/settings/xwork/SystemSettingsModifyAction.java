/*
 * Created on Oct 21, 2004 by Reid
 */
package com.fivesticks.time.settings.xwork;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.RounderTypeEnum;
import com.fivesticks.time.common.util.TimeZoneListBuilder;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.settings.event.SettingsEventPublisher;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateException;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.common.SelectItem;
import com.fstx.stdlib.common.simpledate.SimpleDate;
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
    
    private String token;

    public String execute() throws Exception {

        if (this.getCancelSettings() != null)
            return SUCCESS;

        if (this.getSubmitSettings() == null) {
            initializeSettings();
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        updateSettings();

        new SettingsEventPublisher().publishSettingsModifiedEvent(this
                .getSessionContext().getSystemOwner());

        return SUCCESS;
    }

    /**
     * 
     */
    private void updateSettings() {
        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());

        sd.updateSetting(SettingTypeEnum.SETTING_LOGO_URL, settings
                .getLogoURL());
        sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_LOGIN_FROM_TIMECLOCK
                .getName(), settings.isOkToLoginFromTimeclock());
        sd.updateSetting(SettingTypeEnum.SETTING_OK_TO_REMEMBER_PASSWORD
                .getName(), settings.isOkToRememberPassword());
        sd.updateSetting(SettingTypeEnum.SETTING_SYSTEM_NAME, settings
                .getSystemName());
        
        /*
         * should be automatic and not manual.
         */
        //sd.updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN, settings.getSystemToken());
        
        sd.updateSetting(SettingTypeEnum.SETTING_SYSTEM_TOKEN_ALLOW, settings.isSystemTokenAllowed());
        
//        sd.updateSetting(SettingTypeEnum.SETTING_COUNTRY, settings
//                .getSystemCounty());
        sd.updateSetting(SettingTypeEnum.SETTING_TIMEZONE, settings
                .getSystemTimeZone());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE,
                settings.getTimeClockPayPeriodRefDate());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE,
                settings.getTimeClockPayPeriodType());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE,
                settings.getTimeClockRounderType());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_USER_SHIFT_TYPE,
                settings.getTimeClockUserShiftType());

        if (this.getSessionContext().isFeatureGeneral()) {

            sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_PROJECT,
                    settings.getActivityDefaultProject());
            sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK,
                    settings.getActivityDefaultTask());
            sd.updateSetting(SettingTypeEnum.SETTING_ACTIVITY_ROUNDER_TYPE,
                    settings.getActivityRounderType());
            sd.updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS,
                    settings.getCalendarNormalDayEnds());
            sd.updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START,
                    settings.getCalendarNormalDayStart());

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_PROJECT,
                    settings.getTodoDefaultProject());

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO,
                    settings.getTodoDefaultAssignedTo());

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY,
                    settings.getTodoDefaultEnteredBy());
        }
        
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_HOST, settings.getSmtpHost());
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_PORT, settings.getSmtpPort());
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_USERNAME, settings.getSmtpUsername());
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_PASSWORD, settings.getSmtpPassword());
        

        if (this.getSessionContext().isFeatureComplete()) {
            sd.updateSetting(
                    SettingTypeEnum.SETTING_ACCOUNT_ACTIVITY_POSTED_THROUGH,
                    settings.getActivityPostedThrough());

            if (this.getSessionContext().getRights().isCanUseBetaFeatures()) {

                sd.updateSetting(SettingTypeEnum.SETTING_TODO_QUEUE_IMPORT,
                        settings.isQueueImportFromEmail());

                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_SERVER,
                        settings.getQueueSmtpServer());

                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_FOLDER,
                        settings.getQueueSmtpServerFolder());

                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_USERNAME,
                        settings.getQueueSmtpServerUsername());

                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_PASSWORD,
                        settings.getQueueSmtpServerPassword());

                /*
                 * 2005-12-21 RSC Removed.
                 */
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_FTP_SERVER,
//                        settings.getEbayImagesFtpServer());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_FTP_USERNAME,
//                        settings.getEbayImagesFtpUsername());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_FTP_PASSWORD,
//                        settings.getEbayImagesFtpPassword());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_FTP_PATH, settings
//                        .getEbayImagesFtpPath());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_WIDTH_FULL,
//                        settings.getEbayImagesWidthFull());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_WIDTH_THUMB,
//                        settings.getEbayImagesWidthThumb());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_IMAGE_SRC, settings
//                        .getEbayImagesSrc());
//
//                sd.updateSetting(SettingTypeEnum.EBAY_DEFAULT_COMMISSION,
//                        settings.getEbayDefaultCommission());
            }
        }

        // FstxTimeSettingsInitializer init = new FstxTimeSettingsInitializer();
        // init.setSystemOwner(this.getSessionContext().getSystemOwner());
        // FstxTimeSettings newSettings = init.initialize();
        // this.getSessionContext().getSettings().setSystemOwnerSpecific(
        // newSettings);

    }

    /**
     * 
     */
    private void initializeSettings() {
        // SettingsBroker broker = this.getSessionContext().getSettings();
        // settings = new FstxTimeSettings();
        // settings.setActivityDefaultProject(broker.getActivityDefaultProject());
        // settings.setActivityDefaultTask(broker.getActivityDefaultTask());
        // settings.setActivityRounderType(broker.getActivityRounderType());
        // settings.setCalendarNormalDayEnds(broker.getCalendarNormalDayEnds());
        // settings.setCalendarNormalDayStart(broker.getCalendarNormalDayStart());
        // settings.setLogoURL(broker.getLogoURL());
        // settings.setOkToLoginFromTimeclock(broker.isOkToLoginFromTimeclock());
        // settings.setOkToRememberPassword(broker.isOkToRememberPassword());
        // settings.setSystemName(broker.getSystemName());
        // settings.setSystemTimeZone(broker.getSystemTimeZone());
        // settings.setTimeClockPayPeriodRefDate(broker
        // .getTimeClockPayPeriodRefDate());
        // settings.setTimeClockPayPeriodType(broker.getTimeClockPayPeriodType());
        // settings.setTimeClockRounderType(broker.getTimeClockRounderType());
        // settings.setTimeClockUserShiftType(broker.getTimeClockUserShiftType());
        //
        // settings.setActivityPostedThrough(broker.getActivityPostedThrough());

        settings = new SettingsInitializer(this
                .getSessionContext().getSystemOwner()).initialize();
        
        /*
         * here's the token
         */
        
        TokenServiceDelegate sd = TokenServiceDelegateFactory.factory.build();
        
        try {
            this.setToken(sd.findToken(this.getSessionContext().getSystemOwner()));
        } catch (TokenServiceDelegateException e) {
            //means there's nothing there...
        }
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
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

    /**
     * @return Returns the settings.
     */
    public FstxTimeSettings getSettings() {
        return settings;
    }

    /**
     * @param settings
     *            The settings to set.
     */
    public void setSettings(FstxTimeSettings settings) {
        this.settings = settings;
    }

    public Collection getProjects() throws Exception {
        return ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                .getAllActive();
    }

    public Collection getTasks() throws Exception {
        return TaskServiceDelegateFactory.factory.build(this.getSessionContext())
                .getAllTaskTypes();
    }

    public Collection getRounderTypes() {
        return RounderTypeEnum.getRounders();
    }

    public String getPayPeriodRefDate() {
        return settings.getTimeClockPayPeriodRefDate().getMmddyyyy();
    }

    public void setPayPeriodRefDate(String newDate) {
        settings.setTimeClockPayPeriodRefDate(SimpleDate.factory
                .buildMidnight(newDate));
    }

    public String getStringStart() {
        return "" + settings.getCalendarNormalDayStart();
    }

    public void setStringStart(String start) {
        if (start.trim().length() == 0)
            start = "5";
        settings.setCalendarNormalDayStart(Long.parseLong(start));
    }


    
    

    public String getStringEnd() {
        return "" + settings.getCalendarNormalDayEnds();
    }

    public void setStringEnd(String end) {
        if (end.trim().length() == 0)
            end = "19";
        settings.setCalendarNormalDayEnds(Long.parseLong(end));
    }

    public String getStringRemember() {
        return "" + settings.isOkToRememberPassword();
    }

    public void setStringRemember(String remember) {
        settings.setOkToRememberPassword(new Boolean(remember).booleanValue());
    }

    public String getStringImportFromEmail() {
        return "" + settings.isQueueImportFromEmail();
    }

    public void setStringImportFromEmail(String imp) {
        settings.setQueueImportFromEmail(new Boolean(imp).booleanValue());
    }

    public String getStringTokenAccess() {
        return "" + settings.isSystemTokenAllowed();
    }

    public void setStringTokenAccess(String imp) {
        settings.setSystemTokenAllowed(new Boolean(imp).booleanValue());
    }    
    
    public String getTimeclockLogin() {
        return Boolean.toString(settings.isOkToLoginFromTimeclock());
    }

    public void setTimeclockLogin(String login) {
        settings.setOkToLoginFromTimeclock(new Boolean(login).booleanValue());
    }

    public Collection getStartTimes() {
        Collection ret = new ArrayList();
        ret.add(new SelectItem("4", "4"));
        ret.add(new SelectItem("5", "5"));
        ret.add(new SelectItem("6", "6"));
        ret.add(new SelectItem("7", "7"));
        ret.add(new SelectItem("8", "8"));
        ret.add(new SelectItem("9", "9"));
        ret.add(new SelectItem("10", "10"));
        return ret;
    }

    public Collection getEndTimes() {
        Collection ret = new ArrayList();
        ret.add(new SelectItem("15", "15"));
        ret.add(new SelectItem("16", "16"));
        ret.add(new SelectItem("17", "17"));
        ret.add(new SelectItem("18", "18"));
        ret.add(new SelectItem("19", "19"));
        ret.add(new SelectItem("20", "20"));
        ret.add(new SelectItem("21", "21"));
        ret.add(new SelectItem("22", "22"));
        ret.add(new SelectItem("23", "23"));
        return ret;
    }

    public Collection getTrueFalse() {
        Collection ret = new ArrayList();
        ret.add(new SelectItem("true", "Yes"));
        ret.add(new SelectItem("false", "No"));
        return ret;
    }

    public Collection getTimeZones() {
        return TimeZoneListBuilder.singleton.build();

    }

//    public Collection getCountries() {
//        return new GeographicCollection().getCountries();
//
//    }

    public Collection getPayPeriodTypes() {
        return PayPeriodTypeEnum.getTypes();
    }

    public String getActivityPostedThrough() {
        if (settings.getActivityPostedThrough() == null)
            return "";
        else
            return settings.getActivityPostedThrough().getMmddyyyy();
    }

    public void setActivityPostedThrough(String newDate) {
        settings.setActivityPostedThrough(SimpleDate.factory
                .buildMidnight(newDate));
    }

    public Collection getUsers() throws Exception {
        return UserListBroker.singleton.getInternalUserList(this
                .getSessionContext().getSystemOwner());
    }

//    public Collection getCommissions() throws Exception {
//        return SimpleCommissionServiceDelegate.factory.build(
//                this.getSessionContext().getSystemOwner()).getAll();
//    }

    /**
     * @return Returns the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

}