/*
 * Created on Aug 27, 2004 by Reid
 */
package com.fivesticks.time.system.update.august;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class August_InstallOwnerAdminUserCommand {

    public void execute(SystemOwner systemOwner) throws Exception {

        User user = UserBDFactory.factory.build().addUser("newadmin", "newadmin",
                "newadmin@notareal.domain");

        //        UserGroupBD bd = UserGroupBD.factory.build();
        //
        //        UserGroup ownerAdminGroup = bd
        //                .getByName(UserTypeEnum.USERTYPE_OWNERADMIN.getName());
        //
        //        bd.join(user, ownerAdminGroup);
        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, user,
                UserTypeEnum.OWNERADMIN);

    }
}