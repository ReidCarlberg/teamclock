/*
 * Created on Nov 28, 2004 by REID
 */
package com.fivesticks.time.useradmin.util;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserCollectionBuilder;

/**
 * @author REID
 */
public class MaxActiveUserValidator {

    private static final int MAX_TIMECLOCK_USER_MULTIPLIER = 10;

    public boolean isValid(SystemOwner systemOwner, int maxUsers,
            UserTypeEnum newUserType) throws MaxActiveUserValidatorException {

        boolean ret = true;

        Collection active = null;
        try {
            active = new UserCollectionBuilder()
                    .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);
        } catch (UserServiceDelegateException e) {
            throw new MaxActiveUserValidatorException(e);
        }

        /*
         * 2004-12-29 Reid Take a look at timeclock users.
         */
        int activeTimeClockUsers = new TimeclockUserCounter().count(active);

        if (newUserType != UserTypeEnum.TIMECLOCK) {
            if (active != null
                    && active.size() - activeTimeClockUsers >= maxUsers) {
                ret = false;
            }
        } else {
            /*
             * 2004-12-30 Reid the other rule is that there can only be
             * 10*maxUsers active timeclock users.
             */
            if (activeTimeClockUsers >= maxUsers
                    * MAX_TIMECLOCK_USER_MULTIPLIER) {
                ret = false;
            }
        }

        return ret;
    }

}