/*
 * Created on Dec 21, 2005
 *
 */
package com.fivesticks.time.settings;

import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public interface SystemSettingsServiceDelegate extends SystemOwnerAware{

    public abstract void updateSetting(SettingTypeEnum setting, String value);

    public abstract void updateSetting(String settingKey, String setting);

    public abstract void updateSetting(SettingTypeEnum settingKey,
            SimpleDate setting);
    
    public abstract void updateSetting(SettingTypeEnum settingKey,
            Date setting);    

    public abstract void updateSetting(String settingKey, SimpleDate setting);

    public abstract void updateSetting(String settingKey, boolean setting);

    public abstract void updateSetting(SettingTypeEnum settingKey,
            boolean setting);

    public abstract void updateSetting(SettingTypeEnum settingKey, long setting);

    public abstract void updateSetting(String settingKey, long setting);

    public abstract String getSettingAsString(SettingTypeEnum settingKey);

    public abstract SystemSettings getSetting(String settingKey);

    public abstract String getSettingAsString(String settingKey);

    public abstract int getSettingAsInt(SettingTypeEnum settingKey);

    public abstract long getSettingAsLong(SettingTypeEnum settingKey);

    public abstract long getSettingAsLong(String settingKey);

    public abstract String getSettingAsStringAllowNull(String settingKey);

    public abstract boolean getSettingAsBoolean(SettingTypeEnum settingTypeEnum);

    public abstract boolean getSettingAsBoolean(String settingKey);

    public abstract SimpleDate getSettingAsSimpleDate(String settingKey);

    public abstract void validateSettingNotNull(SettingTypeEnum settingKey,
            String setting);

    public abstract void validateSettingNotNull(String settingKey,
            String setting);

    /**
     * @return
     */
    public abstract SystemSettingsDao getSystemSettingsDao();

    /**
     * @param dao
     */
    public abstract void setSystemSettingsDao(SystemSettingsDao dao);

}