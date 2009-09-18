/*
 * Created on Dec 16, 2003
 *
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.ScheduleBuilder;
import com.fivesticks.time.calendar.ScheduleBuilderFactory;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.common.SessionContext;

/**
 * @author REID
 *  
 */
public class ScheduleBuilderBroker {

    /**
     * @param scheduleContext
     */
    public ScheduleBuilder findScheduleBuilder(SessionContext sessionContext, ScheduleContext scheduleContext) {

        ScheduleBuilder ret = null;

        ret = handleFind(sessionContext, scheduleContext);

        return ret;

    }

    /**
     * @param scheduleContext
     * @return
     */
    private ScheduleBuilder handleFind(SessionContext sessionContext, ScheduleContext scheduleContext) {

        ScheduleBuilder ret = null;

        if (scheduleContext.getViewType() == ScheduleTypeEnum.DAILY_30_MINUTES) {
            ret = ScheduleBuilderFactory.factory.buildDaily(sessionContext.getSystemOwner(),scheduleContext
                    .getFilter(), 30, sessionContext.getSettings().getCalendarNormalDayStart(), sessionContext.getSettings().getCalendarNormalDayEnds());
        }
        if (scheduleContext.getViewType() == ScheduleTypeEnum.DAILY_60_MINUTES) {
            ret = ScheduleBuilderFactory.factory.buildDaily(sessionContext.getSystemOwner(),scheduleContext
                    .getFilter(), 60, sessionContext.getSettings().getCalendarNormalDayStart(), sessionContext.getSettings().getCalendarNormalDayEnds());
        }

        /*
         * 2006-06-28 need to know resolved now
         */
        if (scheduleContext.getViewType() == ScheduleTypeEnum.WEEKLY) {
            ret = ScheduleBuilderFactory.factory.buildWeekly(sessionContext.getSystemOwner(),scheduleContext
                    .getFilter(), 60, sessionContext.getSettings().getCalendarNormalDayStart(), sessionContext.getSettings().getCalendarNormalDayEnds(), sessionContext.getResolvedNow());
        }
        
        /*
         * 2006-06-28 need to know resolved now
         */
        if (scheduleContext.getViewType() == ScheduleTypeEnum.MONTHLY) {
            ret = ScheduleBuilderFactory.factory.buildMonthly(sessionContext.getSystemOwner(),scheduleContext
                    .getFilter(), sessionContext.getResolvedNow());
        }

        return ret;
    }

}