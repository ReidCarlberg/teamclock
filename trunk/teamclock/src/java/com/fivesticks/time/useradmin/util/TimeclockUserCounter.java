/*
 * Created on Dec 29, 2004 by REID
 */
package com.fivesticks.time.useradmin.util;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserCollectionBuilder;

/**
 * @author REID
 */
public class TimeclockUserCounter {

    public int count(Collection userAndTypeVOs) {
        int ret = 0;

        for (Iterator iter = userAndTypeVOs.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();
            if (element.getUserTypeEnum() == UserTypeEnum.TIMECLOCK) {
                ret++;
            }
        }
        return ret;

    }

    public int countTotalByOwner(SystemOwner systemOwner) {
        int ret = 0;

        Collection allUsers;
        try {
            allUsers = new UserCollectionBuilder()
                    .buildAsUserTypeVO(systemOwner);
        } catch (UserServiceDelegateException e) {
            throw new RuntimeException(e);
        }
        ret = count(allUsers);

        return ret;
    }

    public int countActiveByOwner(SystemOwner systemOwner) {
        int ret = 0;

        Collection allUsers;
        try {
            allUsers = new UserCollectionBuilder()
                    .buildInternalActiveOnlyAsUserAndTypeVO(systemOwner);
        } catch (UserServiceDelegateException e) {
            throw new RuntimeException(e);
        }
        ret = count(allUsers);

        return ret;
    }

}