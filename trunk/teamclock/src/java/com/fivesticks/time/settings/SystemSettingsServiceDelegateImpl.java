/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import java.util.Date;
import java.util.List;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
public class SystemSettingsServiceDelegateImpl extends AbstractServiceDelegate implements SystemSettingsServiceDelegate {

    private SystemSettingsDao systemSettingsDao;



    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(com.fivesticks.time.settings.SettingTypeEnum, java.lang.String)
     */
    public void updateSetting(SettingTypeEnum setting, String value) {
        updateSetting(setting.getName(), value);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(java.lang.String, java.lang.String)
     */
    public void updateSetting(String settingKey, String setting) {

        SystemSettings settings = null;

        settings = this.getSetting(settingKey);

        if (settings == null)
            settings = new SystemSettings();

        //settings = new SystemSettings();
        settings.setSettingKey(settingKey);
        settings.setOwnerKey(this.getSystemOwner().getKey());

        settings.setSetting(setting);

        this.getSystemSettingsDao().save(settings);

    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(com.fivesticks.time.settings.SettingTypeEnum, com.fstx.stdlib.common.simpledate.SimpleDate)
     */
    public void updateSetting(SettingTypeEnum settingKey, SimpleDate setting) {
        updateSetting(settingKey.getName(), setting);
    }

    public void updateSetting(SettingTypeEnum settingKey, Date setting) {
        updateSetting(settingKey.getName(), SimpleDate.factory.build(setting));
    }
    
    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(java.lang.String, com.fstx.stdlib.common.simpledate.SimpleDate)
     */
    public void updateSetting(String settingKey, SimpleDate setting) {
        updateSetting(settingKey, setting.getMmddyyyy());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(java.lang.String, boolean)
     */
    public void updateSetting(String settingKey, boolean setting) {
        updateSetting(settingKey, new Boolean(setting).toString());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(com.fivesticks.time.settings.SettingTypeEnum, boolean)
     */
    public void updateSetting(SettingTypeEnum settingKey, boolean setting) {
        updateSetting(settingKey.getName(), setting);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(com.fivesticks.time.settings.SettingTypeEnum, long)
     */
    public void updateSetting(SettingTypeEnum settingKey, long setting) {
        updateSetting(settingKey.getName(), setting);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#updateSetting(java.lang.String, long)
     */
    public void updateSetting(String settingKey, long setting) {
        updateSetting(settingKey, new Long(setting).toString());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsString(com.fivesticks.time.settings.SettingTypeEnum)
     */
    public String getSettingAsString(SettingTypeEnum settingKey) {
        return getSettingAsString(settingKey.getName());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSetting(java.lang.String)
     */
    public SystemSettings getSetting(String settingKey) {
        SystemSettings ret = null;

        SystemSettingsParameters params = new SystemSettingsParameters();

        params.setSettingKey(settingKey);
        if (this.getSystemOwner() == null) {
            throw new RuntimeException(
                    "No System Owner set in System Setting Service Delegate");
        }
        params.setOwnerKey(this.getSystemOwner().getKey());

        List list = this.getSystemSettingsDao().find(params);

        if (list.size() == 1) {
            ret = (SystemSettings) list.toArray()[0];
        }

        return ret;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsString(java.lang.String)
     */
    public String getSettingAsString(String settingKey) {

        String ret = null;

        SystemSettings systemSettings = getSetting(settingKey);

        if (systemSettings != null) {
            ret = systemSettings.getSetting();
        }
        return ret;

    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsInt(com.fivesticks.time.settings.SettingTypeEnum)
     */
    public int getSettingAsInt(SettingTypeEnum settingKey) {
        return (int) getSettingAsLong(settingKey.getName());
    }
    
    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsLong(com.fivesticks.time.settings.SettingTypeEnum)
     */
    public long getSettingAsLong(SettingTypeEnum settingKey) {
        return getSettingAsLong(settingKey.getName());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsLong(java.lang.String)
     */
    public long getSettingAsLong(String settingKey) {
        Long ret = new Long(0);

        SystemSettings systemSettings = this.getSetting(settingKey);

        if (systemSettings != null && systemSettings.getSetting() != null) {
            try {
                ret = new Long(systemSettings.getSetting());
            } catch (NumberFormatException e) {
                //2003-11-03 reid added in for the settings update.
                //do nothing;
            }
        }
        return ret.longValue();
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsStringAllowNull(java.lang.String)
     */
    public String getSettingAsStringAllowNull(String settingKey) {

        String ret = null;

        try {
            SystemSettings settings = this.getSetting(settingKey);
            ret = settings.getSetting();
        } catch (RuntimeException e) {

        }

        return ret;

    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsBoolean(com.fivesticks.time.settings.SettingTypeEnum)
     */
    public boolean getSettingAsBoolean(SettingTypeEnum settingTypeEnum) {
        return this.getSettingAsBoolean(settingTypeEnum.getName());
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsBoolean(java.lang.String)
     */
    public boolean getSettingAsBoolean(String settingKey) {

        String setting = this.getSettingAsString(settingKey);

        Boolean ret = new Boolean(setting);

        return ret.booleanValue();

    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSettingAsSimpleDate(java.lang.String)
     */
    public SimpleDate getSettingAsSimpleDate(String settingKey) {

        String setting = this.getSettingAsString(settingKey);

        SimpleDate ret = SimpleDate.factory.build(setting);

        return ret;

    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#validateSettingNotNull(com.fivesticks.time.settings.SettingTypeEnum, java.lang.String)
     */
    public void validateSettingNotNull(SettingTypeEnum settingKey,
            String setting) {
        validateSettingNotNull(settingKey.getName(), setting);
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#validateSettingNotNull(java.lang.String, java.lang.String)
     */
    public void validateSettingNotNull(String settingKey, String setting) {

        String test = null;

        try {
            test = this.getSettingAsString(settingKey);
        } catch (RuntimeException e) {
            //do nothing
        }

        if (test == null) {
            this.updateSetting(settingKey, setting);
        }
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#getSystemSettingsDao()
     */
    public SystemSettingsDao getSystemSettingsDao() {
        return systemSettingsDao;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.settings.SystemSettingServiceDelegate#setSystemSettingsDao(com.fivesticks.time.settings.SystemSettingsDao)
     */
    public void setSystemSettingsDao(SystemSettingsDao dao) {
        systemSettingsDao = dao;
    }

}