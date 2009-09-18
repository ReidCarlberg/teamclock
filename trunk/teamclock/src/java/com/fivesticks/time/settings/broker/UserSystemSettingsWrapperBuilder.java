/*
 * Created on Nov 8, 2005
 *
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;

public class UserSystemSettingsWrapperBuilder {

    public UserSystemSettingsWrapper build(User user, SystemOwner systemOwner) {
        UserSystemSettingsWrapper ret = new UserSystemSettingsWrapper(user,
                systemOwner);
        SettingsInitializer initer = new SettingsInitializer(
                systemOwner);

        initer.decorateSettings(ret);

        if (user != null) {
            try {
                ret.setUserSettingVO(UserSettingServiceDelegateFactory.factory.build(
                        systemOwner).find(user));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return ret;
    }
}
