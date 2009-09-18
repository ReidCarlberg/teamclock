/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.system;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * @author REID
 */
public class Update2005_January_SystemSettingsUpdateCommand {

    public void execute() throws Exception {

        //get all the system owners
        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();

        for (Iterator iter = owners.iterator(); iter.hasNext();) {
            SystemOwner current = (SystemOwner) iter.next();

            SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                    .build(current);

            Collection users = SystemUserServiceDelegateFactory.factory.build().list(
                    current);

            FstxTimeSettings settings = new SettingsInitializer(current)
                    .initialize();

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_DEFAULT_PROJECT,
                    settings.getActivityDefaultProject());

            SystemUser systemUser = null;

            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                SystemUser element = (SystemUser) iterator.next();
                if (element.getUserType().equals(
                        UserTypeEnum.OWNERADMIN.getName())) {
                    systemUser = element;
                    break;
                }
            }

            if (systemUser == null && users.size() > 0) {
                systemUser = (SystemUser) users.toArray()[0];
            }

            if (systemUser != null) {
                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_DEFAULT_ASSIGNEDTO,
                        systemUser.getUsername());
                sd.updateSetting(
                        SettingTypeEnum.SETTING_TODO_DEFAULT_ENTEREDBY,
                        systemUser.getUsername());
            }

            sd.updateSetting(SettingTypeEnum.SYSTEM_IS_FREE_SYSTEM, true);

            sd.updateSetting(
                    SettingTypeEnum.SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS, false);

            sd.updateSetting(SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS,
                    false);

            sd.updateSetting(SettingTypeEnum.SYSTEM_CAN_USE_BETA_FEATURES,
                    false);

        }

    }
}