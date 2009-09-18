/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.system;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserPasswordComplexityEnum;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.authen.users.UserDAO;
import com.fstx.stdlib.authen.users.UserDAOFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 */
public class Update2005_April_SystemSettingsUpdateCommand {

    public void execute() throws Exception {

        //used below
        UserBD userbd = UserBDFactory.factory.build();
        
        UserDAO userdao = UserDAOFactory.factory.build();
        
        SimpleDate expires = SimpleDate.factory.buildMidnight();
        expires.advanceDay(30);
        Date expDate = expires.getDate();
        
        //get all the system owners
        Collection owners = SystemOwnerServiceDelegateFactory.factory.build()
                .findAll();

        for (Iterator iter = owners.iterator(); iter.hasNext();) {
            SystemOwner current = (SystemOwner) iter.next();

            SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                    .build(current);

            sd.updateSetting(SettingTypeEnum.SETTING_USER_PASSWORD_LIFE,
                    30);

            sd.updateSetting(SettingTypeEnum.SETTING_USER_PASSWORD_COMPLEXITY,
                    UserPasswordComplexityEnum.STANDARD.getName());

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_QUEUE_IMPORT, "FALSE");
            
            sd.updateSetting(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_SERVER, "NONE");

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_USERNAME, "NONE");

            sd.updateSetting(SettingTypeEnum.SETTING_TODO_QUEUE_SMTP_PASSWORD, "NONE");

            Collection users = UserServiceDelegateFactory.factory.build(current).listUserAndType();
            
            
            
            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                UserAndTypeVO element = (UserAndTypeVO) iterator.next();                
                element.getUser().setPasswordExpires(expDate);
                userdao.save(element.getUser());
                
            }
            
            
        }
        
        

    }
}