/*
 * Created on Dec 30, 2004 by REID
 */
package com.fivesticks.time.system.old;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.useradmin.UserTypeDeterminator;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;

/**
 * @author REID
 */
public class Update2004_December_UserTypeUpdateCommand {

    public void execute() throws Exception {

        //get all the system owners
        Collection owners = SystemOwnerServiceDelegate.factory.build()
                .findAll();

        UserBD userBD = UserBD.factory.build();

        SystemUserServiceDelegate systemUserServiceDelegate = SystemUserServiceDelegate.factory
                .build();

        UserTypeDeterminator determinator = new UserTypeDeterminator();

        for (Iterator iter = owners.iterator(); iter.hasNext();) {
            SystemOwner currentOwner = (SystemOwner) iter.next();

            Collection users = SystemUserServiceDelegate.factory.build().list(
                    currentOwner);

            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                SystemUser currentUser = (SystemUser) iterator.next();

                User user = userBD.getByUsername(currentUser.getUsername());

                UserTypeEnum userTypeEnum = determinator
                        .getUserTypeByUser(user);

                currentUser.setUserType(userTypeEnum.getName());

                systemUserServiceDelegate.save(currentUser);
            }

        }

    }
}