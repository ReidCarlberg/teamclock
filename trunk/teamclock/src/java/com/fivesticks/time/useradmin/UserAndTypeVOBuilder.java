/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class UserAndTypeVOBuilder {

    public UserAndTypeVO build(SystemOwner systemOwner, User user) {

        UserAndTypeVO ret = new UserAndTypeVO();
        ret.setUser(user);
        try {
            ret.setUserTypeEnum(new UserTypeDeterminator().getUserType(
                    systemOwner, user));
        } catch (UserTypeDeterminatorException e) {
            throw new RuntimeException("failed to match user type enums");
        }

        return ret;
    }
}