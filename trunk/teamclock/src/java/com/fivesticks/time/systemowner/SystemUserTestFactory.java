/*
 * Created on Jan 17, 2005 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class SystemUserTestFactory {

    private static int counter = 0;

    public static final SystemUserTestFactory singleton = new SystemUserTestFactory();

    public User build(SystemOwner systemOwner, UserTypeEnum userTypeEnum)
            throws Exception {
        counter++;

        User user = UserBDFactory.factory.build().addUser("systemuserun" + counter,
                "systemuserpw" + counter,
                "systemuserem" + counter + "@fivesticks.com");

        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, user,
                userTypeEnum);

        return user;
    }
    
    public User build(SystemOwner systemOwner, UserTypeEnum userTypeEnum, String password) throws Exception {
        User user = UserBDFactory.factory.build().addUser("systemuserun" + counter,
                password,
                "systemuserem" + counter + "@fivesticks.com");

        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, user,
                userTypeEnum);

        return user;        
    }

    public User buildExternal(SystemOwner systemOwner) throws Exception {
        return this.build(systemOwner, UserTypeEnum.EXTERNAL);
    }

    public User buildOwner(SystemOwner systemOwner) throws Exception {
        return this.build(systemOwner, UserTypeEnum.OWNERADMIN);
    }

}