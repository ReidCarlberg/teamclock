/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclock.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.timeclock.DisplayableUserShiftBuilderFactory;
import com.fivesticks.time.timeclock.UserShiftCollectionBuilderException;
import com.fivesticks.time.timeclock.UserShiftCollectionBuilderFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class UserActivityPayPeriodSummaryBuilder {

    public Collection buildResolvedForAllUsers(SystemOwner systemOwner,
            DatePair targetPayPeriod) throws 
            UserShiftCollectionBuilderException,
            SystemUserServiceDelegateException, UserBDException {

        Collection ret = new ArrayList();

        Collection users = SystemUserServiceDelegateFactory.factory.build().list(
                systemOwner);

        for (Iterator i = users.iterator(); i.hasNext();) {
            SystemUser current = (SystemUser) i.next();
            User currentUser = UserBDFactory.factory.build().getByUsername(
                    current.getUsername());

            if (!currentUser.isBooleanInactive()) {
                UserActivityPayPeriodSummary currentSummary = buildResolvedForUser(systemOwner,
                        currentUser, targetPayPeriod);
                ret.add(currentSummary);
            }
        }
        return ret;

    }

    public UserActivityPayPeriodSummary buildResolvedForUser(SystemOwner systemOwner,
            User user,
            DatePair targetPayPeriod)
            throws UserShiftCollectionBuilderException {
        UserActivityPayPeriodSummary ret = new UserActivityPayPeriodSummary();

        Collection shifts = UserShiftCollectionBuilderFactory.factory.build(systemOwner).buildAlreadyResolved(
                user, targetPayPeriod);

        Collection displayableUserShifts = DisplayableUserShiftBuilderFactory.factory
                .build().getDisplayable(systemOwner, shifts);

        UserShiftSummaryVO summary = new UserShiftSummaryVO(
                displayableUserShifts);

        ret.setDisplayableShifts(displayableUserShifts);

        ret.setSummary(summary);

        ret.setUser(user);

        return ret;
    }
}