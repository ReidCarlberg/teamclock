/*
 * Created on Dec 4, 2003
 *
 */
package com.fivesticks.time.timeclock.util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.timeclock.Timeclock;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;
import com.fivesticks.time.timeclock.TimeclockFilterParametersBuilder;
import com.fivesticks.time.timeclock.TimeclockSearchBD;
import com.fivesticks.time.timeclock.TimeclockSearchBDException;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 * 
 */
public class TimeclockDetailDisplayWrapperSetBuilder {

    private SystemOwner systemOwner;

    public TimeclockDetailDisplayWrapperSetBuilder(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;

    }

    public TimeclockDetailDisplayWrapperSet build(Collection raw,
            TimeclockDetailComparator comparator) {

        TimeclockDetailDisplayWrapperSet ret = new TimeclockDetailDisplayWrapperSet();

        Iterator iRaw = raw.iterator();

        while (iRaw.hasNext()) {
            Timeclock current = (Timeclock) iRaw.next();
            ret.add(new TimeclockDetailDisplayWrapper(current, comparator));
        }

        return ret;
    }

    public TimeclockDetailDisplayWrapperSet buildStandard(User user, Date resolvedDate) {
        TimeclockFilterParameters filter = new TimeclockFilterParametersBuilder()
                .buildUserForDate(user, resolvedDate);

        Collection raw;
        try {
            raw = new TimeclockSearchBD(this.systemOwner).getRawRecords(filter);
        } catch (TimeclockSearchBDException e) {
            throw new RuntimeException(e);
        }

        // do the comparator
        TimeclockDetailComparator comparator = TimeclockDetailComparatorFactory.factory
                .buildDefault();

        // send to displayable builder

        return build(raw, comparator);
    }
}