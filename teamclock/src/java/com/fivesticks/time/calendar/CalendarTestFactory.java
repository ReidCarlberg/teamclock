/*
 * Created on Nov 17, 2005
 *
 */
package com.fivesticks.time.calendar;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.DatePair;
import com.fstx.stdlib.common.simpledate.DatePairBuilder;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class CalendarTestFactory {

    private static int count = 0;

    public static TcCalendar build(SessionContext sessionContext)
            throws Exception {
        count++;

        SimpleDate start = SimpleDate.factory.build(sessionContext
                .getResolvedNow());
        SimpleDate end = SimpleDate.factory.build(start.getDate());
        end.advanceMinute(10);

        if (end.getDayOfMonth() > start.getDayOfMonth())
            throw new RuntimeException(
                    "calendar test failed -- too close to midnight.");

        TcCalendar ret = new TcCalendar();
        ret.setComplete(new Boolean(false));
        ret.setDescription("test" + count);
        ret.setEndDate(end.getDate());
        ret.setStartDate(start.getDate());
        ret.setUsername(sessionContext.getUser().getUsername());

        CalendarBDFactory.factory.build(sessionContext).save(ret);

        return ret;
    }

    /*
     * 2006-04-24 RSC
     */
    public static TcCalendar build(SystemOwner systemOwner, User user)
            throws Exception {
        return build(systemOwner, user, null);
    }

    public static TcCalendar build(SystemOwner systemOwner, User user,
            Project proj) throws Exception {
        TcCalendar ret = new TcCalendar();
        count++;
        ret.setUsername(user.getUsername());
        ret.setDescription("description for calendar " + count);
        ret.setOwnerKey(systemOwner.getKey());

        SimpleDate sd = SimpleDate.factory.build();
        // need for UTC compatibility.
        sd.setHours(11);
        sd.advanceMinute(count * 30);

        DatePair s = new DatePairBuilder().buildStartPlusMinutes(sd.getDate(),
                60);

        ret.setStartDate(s.getStart());
        ret.setEndDate(s.getEnd());

        if (proj != null)
            ret.setProjectId(proj.getId());

        // ret = FstxCalendarDAOFactory.factory.build().save(ret);

        ret = CalendarBDFactory.factory.build(systemOwner).save(ret);
        return ret;
    }
}
